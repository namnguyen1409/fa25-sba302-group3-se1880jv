package sba.group3.backendmvc.service.appointment.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.appointment.QueueTicketResponse;
import sba.group3.backendmvc.entity.appointment.QueueStatus;
import sba.group3.backendmvc.entity.appointment.QueueTicket;
import sba.group3.backendmvc.entity.billing.Invoice;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.ExaminationStatus;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.mapper.appointment.QueueTicketMapper;
import sba.group3.backendmvc.publisher.QueueEventPublisher;
import sba.group3.backendmvc.repository.appointment.QueueTicketRepository;
import sba.group3.backendmvc.repository.patient.ExaminationRepository;
import sba.group3.backendmvc.service.appointment.QueueTicketService;
import sba.group3.backendmvc.service.billing.InvoiceService;
import sba.group3.backendmvc.service.examination.ExaminationService;
import sba.group3.backendmvc.service.pharmacy.DispenseRecordService;
import sba.group3.backendmvc.validate.QueueStateValidator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class QueueTicketServiceImpl implements QueueTicketService {

    QueueTicketRepository queueTicketRepository;
    QueueTicketMapper queueTicketMapper;
    QueueStateValidator queueStateValidator;
    QueueEventPublisher queueEventPublisher;
    ExaminationService examinationService;
    private final ExaminationRepository examinationRepository;
    private final InvoiceService invoiceService;
    private final DispenseRecordService dispenseRecordService;

    public Page<QueueTicketResponse> filter(SearchFilter filter) {
        return queueTicketRepository.search(filter).map(queueTicketMapper::toDto);
    }

    @Override
    public List<QueueTicketResponse> getDoctorQueueToday(UUID doctorId) {
        ZoneId zone = ZoneId.of("Asia/Ho_Chi_Minh");

        // 00:00 hôm nay theo timezone VN
        Instant startOfToday = LocalDate.now(zone)
                .atStartOfDay(zone)
                .toInstant();

        // 00:00 ngày mai VN
        Instant startOfTomorrow = LocalDate.now(zone)
                .plusDays(1)
                .atStartOfDay(zone)
                .toInstant();

        var queue = queueTicketRepository.findAllByAssignedDoctor_IdAndCreatedDateBetween(
                doctorId,
                startOfToday,
                startOfTomorrow
        );

        return queue.stream()
                .map(queueTicketMapper::toDto)
                .toList();
    }


    @Override
    public QueueTicketResponse call(UUID queueTicketId) {
        var ticket = get(queueTicketId);
        queueStateValidator.ensureCanCall(ticket);
        ticket.setStatus(QueueStatus.CALLED);
        queueTicketRepository.save(ticket);
        var response = queueTicketMapper.toDto(ticket);
        queueEventPublisher.publish(response);
        return response;
    }

    @Override
    public QueueTicketResponse start(UUID queueTicketId) {
        var ticket = get(queueTicketId);
        queueStateValidator.ensureCanStart(ticket);
        ticket.setStatus(QueueStatus.IN_SERVICE);
        if (ticket.getExamination() == null) {
            var examination = examinationService.createFromQueueTicket(ticket);
            ticket.setExamination(examination);
        }
        queueTicketRepository.save(ticket);
        var response = queueTicketMapper.toDto(ticket);
        queueEventPublisher.publish(response);
        return response;
    }

    @Override
    public QueueTicketResponse skip(UUID queueTicketId) {
        var ticket = get(queueTicketId);
        queueStateValidator.ensureCanSkip(ticket);
        ticket.setStatus(QueueStatus.SKIPPED);
        queueTicketRepository.save(ticket);
        var response = queueTicketMapper.toDto(ticket);
        queueEventPublisher.publish(response);
        return response;
    }

    @Override
    public QueueTicketResponse requeue(UUID queueTicketId) {
        var ticket = get(queueTicketId);
        queueStateValidator.ensureCanRequeue(ticket);
        ticket.setStatus(QueueStatus.WAITING);
        queueTicketRepository.save(ticket);
        var response = queueTicketMapper.toDto(ticket);
        queueEventPublisher.publish(response);
        return response;
    }

    @Override
    public QueueTicketResponse waitResult(UUID queueTicketId) {
        var ticket = get(queueTicketId);
        queueStateValidator.ensureCanWaitResult(ticket);
        ticket.setStatus(QueueStatus.IN_SERVICE_WAITING_RESULT);
        queueTicketRepository.save(ticket);
        var response = queueTicketMapper.toDto(ticket);
        queueEventPublisher.publish(response);
        return response;
    }

    @Override
    public QueueTicketResponse resume(UUID queueTicketId) {
        var ticket = get(queueTicketId);
        queueStateValidator.ensureCanResume(ticket);
        ticket.setStatus(QueueStatus.IN_SERVICE);
        queueTicketRepository.save(ticket);
        var response = queueTicketMapper.toDto(ticket);
        queueEventPublisher.publish(response);
        return response;
    }

    @Override
    @Transactional
    public QueueTicketResponse done(UUID queueTicketId) {
        QueueTicket ticket = get(queueTicketId);
        queueStateValidator.ensureCanDone(ticket);

        Examination exam = ticket.getExamination();

        exam.setStatus(ExaminationStatus.COMPLETED);
        examinationRepository.save(exam);

        invoiceService.createInvoiceForExamination(exam);

        if (exam.getPrescription() != null) {
            dispenseRecordService.createDispenseRecord(exam.getPrescription());
        }
        ticket.setStatus(QueueStatus.DONE);
        queueTicketRepository.save(ticket);
        QueueTicketResponse response = queueTicketMapper.toDto(ticket);
        queueEventPublisher.publish(response);

        return response;
    }

    @Override
    public List<QueueTicketResponse> filterList(SearchFilter filter) {
        return queueTicketRepository.searchAll(filter).stream().map(queueTicketMapper::toDto).collect(Collectors.toList());
    }


    private QueueTicket get(UUID id) {
        return queueTicketRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.QUEUE_TICKET_NOT_FOUND));
    }
}
