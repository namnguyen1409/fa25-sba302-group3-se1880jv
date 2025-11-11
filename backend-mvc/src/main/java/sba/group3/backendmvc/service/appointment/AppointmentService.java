package sba.group3.backendmvc.service.appointment;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.appointment.AppointmentRequest;
import sba.group3.backendmvc.dto.response.appointment.AppointmentResponse;

import java.util.UUID;

public interface AppointmentService {
    Page<AppointmentResponse> filter(SearchFilter filter);

    AppointmentResponse create(AppointmentRequest appointmentRequest);

    AppointmentResponse update(UUID appointmentId, AppointmentRequest appointmentRequest);

    AppointmentResponse getById(UUID appointmentId);

    void delete(UUID appointmentId);
}
