package sba.group3.backendmvc.publisher;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import sba.group3.backendmvc.dto.response.laboratory.LabOrderResponse;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LabOrderEventPublisher {

    SimpMessagingTemplate template;

    public void publishNewOrder(LabOrderResponse response) {
        var staffId = response.assignedStaff().id();
        template.convertAndSend("/topic/lab/" + staffId + "/orders", response);
    }
}