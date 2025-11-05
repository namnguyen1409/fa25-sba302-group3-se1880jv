package sba.group3.backendmvc.service.appointment.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.appointment.AppointmentRequest;
import sba.group3.backendmvc.dto.response.appointment.AppointmentResponse;
import sba.group3.backendmvc.entity.appointment.QueuePriority;
import sba.group3.backendmvc.entity.appointment.QueueStatus;
import sba.group3.backendmvc.entity.appointment.QueueTicket;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.mapper.appointment.AppointmentMapper;
import sba.group3.backendmvc.repository.appointment.AppointmentRepository;
import sba.group3.backendmvc.repository.appointment.QueueTicketRepository;
import sba.group3.backendmvc.repository.patient.PatientRepository;
import sba.group3.backendmvc.repository.staff.SpecialtyRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.repository.staff.StaffScheduleRepository;
import sba.group3.backendmvc.service.appointment.AppointmentService;
import sba.group3.backendmvc.service.staff.StaffService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final PatientRepository patientRepository;
    private final SpecialtyRepository specialtyRepository;
    private final StaffService staffService;
    private final StaffRepository staffRepository;
    private final StaffScheduleRepository staffScheduleRepository;
    private final QueueTicketRepository queueTicketRepository;

    @Transactional
    @Override
    public Page<AppointmentResponse> filter(SearchFilter filter) {
        return appointmentRepository.search(filter).map(appointmentMapper::toDto1);
    }

    @Transactional
    @Override
    public AppointmentResponse create(AppointmentRequest appointmentRequest) {
        var entity = appointmentMapper.toEntity(appointmentRequest);
        entity.setPatient(patientRepository.getReferenceById(appointmentRequest.patientId()));
        entity.setSpecialty(specialtyRepository.getReferenceById(appointmentRequest.specialtyId()));
        var savedEntity = appointmentRepository.save(entity);
        var doctorDto = staffService.autoPickDoctor(appointmentRequest.specialtyId());
        var doctor = staffRepository.getReferenceById(doctorDto.id());

        var day = LocalDate.now().getDayOfWeek();
        var now = LocalTime.now();
        var room = staffScheduleRepository.findActiveRoomForDoctor(doctor.getId(), day, now)
                .orElseThrow(() -> new AppException(ErrorCode.BAD_REQUEST, "Không tìm thấy phòng trực cho bác sĩ"));
        QueueTicket ticket = QueueTicket.builder()
                .appointment(entity)
                .assignedDoctor(doctor)
                .assignedRoom(room)
                .queueNumber(generateQueueNumber(doctor.getId()))
                .priority(QueuePriority.NORMAL)
                .status(QueueStatus.WAITING)
                .build();
        queueTicketRepository.save(ticket);
        entity.setQueueTicket(ticket);
        return appointmentMapper.toDto1(savedEntity);
    }

    @Transactional
    @Override
    public AppointmentResponse update(UUID appointmentId, AppointmentRequest appointmentRequest) {
        var existingAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.BAD_REQUEST, "Appointment not found"));
        appointmentMapper.partialUpdate(appointmentRequest, existingAppointment);
        var updatedAppointment = appointmentRepository.save(existingAppointment);
        return appointmentMapper.toDto1(updatedAppointment);
    }

    @Transactional
    @Override
    public AppointmentResponse getById(UUID appointmentId) {
        var existingAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.BAD_REQUEST, "Appointment not found"));
        return appointmentMapper.toDto1(existingAppointment);
    }

    @Transactional
    @Override
    public void delete(UUID appointmentId) {
        var existingAppointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppException(ErrorCode.BAD_REQUEST, "Appointment not found"));
        existingAppointment.setDeleted(true);
        appointmentRepository.save(existingAppointment);
    }

    private String generateQueueNumber(UUID doctorId) {
        var count = queueTicketRepository.countTotalTodayByDoctor(doctorId);
        return String.format("Q%03d", count + 1);
    }
}
