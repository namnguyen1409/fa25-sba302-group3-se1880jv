package sba.group3.backendmvc.publisher;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import sba.group3.backendmvc.dto.response.examination.ServiceOrderResponse;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderEventPublisher {

    SimpMessagingTemplate template;

    public void publishNewOrder(ServiceOrderResponse response) {
        var staffId = response.assignedStaff().id();
        template.convertAndSend("/topic/technician/" + staffId + "/orders", response);
    }
}