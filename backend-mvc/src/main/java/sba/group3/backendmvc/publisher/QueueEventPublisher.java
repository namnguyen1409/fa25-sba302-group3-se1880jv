package sba.group3.backendmvc.publisher;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import sba.group3.backendmvc.dto.response.appointment.QueueTicketResponse;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class QueueEventPublisher {

    SimpMessagingTemplate template;

    public void publish(QueueTicketResponse response) {
        template.convertAndSend("/topic/doctor/" + response.assignedDoctor().id() + "/queue", response);
    }
}
