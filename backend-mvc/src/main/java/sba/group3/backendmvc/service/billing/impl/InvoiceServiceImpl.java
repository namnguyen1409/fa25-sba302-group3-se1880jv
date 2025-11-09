package sba.group3.backendmvc.service.billing.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.billing.InvoiceRequest;
import sba.group3.backendmvc.dto.response.billing.InvoiceResponse;
import sba.group3.backendmvc.entity.billing.Invoice;
import sba.group3.backendmvc.entity.billing.InvoiceItem;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.PrescriptionItem;
import sba.group3.backendmvc.entity.examination.ServiceOrder;
import sba.group3.backendmvc.entity.examination.ServiceOrderItem;
import sba.group3.backendmvc.entity.laboratory.LabOrder;
import sba.group3.backendmvc.entity.laboratory.LabTestResult;
import sba.group3.backendmvc.entity.organization.Room;
import sba.group3.backendmvc.entity.organization.RoomType;
import sba.group3.backendmvc.entity.staff.StaffSchedule;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.mapper.billing.InvoiceMapper;
import sba.group3.backendmvc.publisher.InvoiceEventPublisher;
import sba.group3.backendmvc.repository.billing.InvoiceRepository;
import sba.group3.backendmvc.repository.laboratory.LabOrderRepository;
import sba.group3.backendmvc.repository.staff.StaffScheduleRepository;
import sba.group3.backendmvc.service.billing.InvoiceService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;
    private final StaffScheduleRepository staffScheduleRepository;
    private final LabOrderRepository labOrderRepository;
    private final InvoiceEventPublisher invoiceEventPublisher;

    @Override
    public Page<InvoiceResponse> filter(SearchFilter filter) {
        return invoiceRepository.search(filter).map(invoiceMapper::toDto1);
    }

    @Override
    public InvoiceResponse getById(UUID invoiceId) {
        var entity = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + invoiceId));
        return invoiceMapper.toDto1(entity);
    }

    @Override
    public InvoiceResponse create(InvoiceRequest invoiceRequest) {
        var entity = invoiceMapper.toEntity(invoiceRequest);
        var savedEntity = invoiceRepository.save(entity);
        return invoiceMapper.toDto1(savedEntity);
    }

    @Override
    public InvoiceResponse update(UUID invoiceId, InvoiceRequest invoiceRequest) {
        var existingEntity = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + invoiceId));
        invoiceMapper.partialUpdate(invoiceRequest, existingEntity);
        var updatedEntity = invoiceRepository.save(existingEntity);
        return invoiceMapper.toDto1(updatedEntity);
    }

    @Override
    public void delete(UUID invoiceId) {
        var existingEntity = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + invoiceId));
        existingEntity.setDeleted(true);
        invoiceRepository.save(existingEntity);
    }

    @Transactional
    public Invoice createInvoiceForExamination(Examination exam) {
        StaffSchedule cashierSchedule = pickLeastBusySchedule(RoomType.CASHIER);
        Room cashierRoom = cashierSchedule.getRoom();

        Invoice invoice = Invoice.builder()
                .assignedStaff(cashierSchedule.getStaff())
                .examination(exam)
                .patient(exam.getPatient())
                .invoiceNumber(generateCode())
                .issueDate(LocalDateTime.now())
                .paid(false)
                .room(cashierRoom)
                .totalAmount(BigDecimal.ZERO)
                .build();

        BigDecimal total = BigDecimal.ZERO;


        for (ServiceOrder order : exam.getServiceOrders()) {
            for (ServiceOrderItem item : order.getItems()) {
                BigDecimal price = item.getPrice();
                total = total.add(price);

                invoice.getItems().add(
                        InvoiceItem.builder()
                                .invoice(invoice)
                                .description(item.getService().getName())
                                .unitPrice(price)
                                .quantity(1)
                                .totalPrice(price)
                                .build()
                );
            }
        }

        for (LabOrder order : exam.getLabOrders()) {
            for (LabTestResult r : order.getResults()) {
                BigDecimal price = r.getLabTest().getPrice();
                total = total.add(price);

                invoice.getItems().add(
                        InvoiceItem.builder()
                                .invoice(invoice)
                                .description(r.getLabTest().getName())
                                .unitPrice(price)
                                .quantity(1)
                                .totalPrice(price)
                                .build()
                );
            }
        }

        if (exam.getPrescription() != null) {
            for (PrescriptionItem item : exam.getPrescription().getItems()) {
                BigDecimal price = item.getMedicine().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity()));

                total = total.add(price);

                invoice.getItems().add(
                        InvoiceItem.builder()
                                .invoice(invoice)
                                .description(item.getMedicine().getName())
                                .unitPrice(item.getMedicine().getPrice())
                                .quantity(item.getQuantity())
                                .totalPrice(price)
                                .build()
                );
            }
        }

        invoice.setTotalAmount(total);

        invoiceEventPublisher.publishNewOrder(invoiceMapper.toDto1(invoice));

        return invoiceRepository.save(invoice);
    }

    @Override
    public List<InvoiceResponse> filterList(SearchFilter filter) {
        return invoiceRepository.searchAll(filter).stream().map(invoiceMapper::toDto1).collect(Collectors.toList());
    }

    public StaffSchedule pickLeastBusySchedule(RoomType roomType) {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        List<StaffSchedule> schedules =
                staffScheduleRepository.findAvailableStaffForRoomType(roomType, today, now);

        if (schedules.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND,
                    "Không có nhân viên trực cho loại phòng: " + roomType);
        }

        List<UUID> roomIds = schedules.stream()
                .map(ss -> ss.getRoom().getId())
                .distinct()
                .toList();

        var countList = invoiceRepository.countActiveOrdersByRoomIds(roomIds);

        Map<UUID, Long> roomOrderCount = new HashMap<>();
        countList.forEach(row -> {
            UUID roomId = (UUID) row.get("roomId");
            Long count = (Long) row.get("activeOrders");
            roomOrderCount.put(roomId, count);
        });

        for (UUID roomId : roomIds) {
            roomOrderCount.putIfAbsent(roomId, 0L);
        }

        UUID chosenRoomId = roomOrderCount.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow()
                .getKey();

        return schedules.stream()
                .filter(s -> s.getRoom().getId().equals(chosenRoomId))
                .findFirst()
                .orElseThrow();
    }

    public String generateCode() {
        long count = invoiceRepository.count();
        return "BIL-" + String.format("%06d", count + 1);
    }

}
