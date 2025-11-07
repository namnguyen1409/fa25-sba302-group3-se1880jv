package sba.group3.backendmvc.service.appointment;

import sba.group3.backendmvc.dto.response.appointment.QueueTicketResponse;

import java.util.List;
import java.util.UUID;

public interface QueueTicketService {
    List<QueueTicketResponse> getDoctorQueueToday(UUID doctorId);

    QueueTicketResponse call(UUID queueTicketId);

    QueueTicketResponse start(UUID queueTicketId);

    QueueTicketResponse skip(UUID queueTicketId);

    QueueTicketResponse requeue(UUID queueTicketId);

    QueueTicketResponse waitResult(UUID queueTicketId);

    QueueTicketResponse resume(UUID queueTicketId);

    QueueTicketResponse done(UUID queueTicketId);
}
