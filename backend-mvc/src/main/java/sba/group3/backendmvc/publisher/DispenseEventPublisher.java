package sba.group3.backendmvc.publisher;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import sba.group3.backendmvc.dto.response.pharmacy.DispenseRecordResponse;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DispenseEventPublisher {

    SimpMessagingTemplate template;

    public void publishNewOrder(DispenseRecordResponse response) {
        var staffId = response.dispensedBy().id();
        template.convertAndSend("/topic/dispense/" + staffId + "/orders", response);
    }
}
