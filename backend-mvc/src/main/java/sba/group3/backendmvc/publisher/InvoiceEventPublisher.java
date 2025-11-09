package sba.group3.backendmvc.publisher;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import sba.group3.backendmvc.dto.response.billing.InvoiceResponse;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceEventPublisher {

    SimpMessagingTemplate template;

    public void publishNewOrder(InvoiceResponse response) {
        var staffId = response.assignedStaff().id();
        template.convertAndSend("/topic/invoice/" + staffId + "/orders", response);
    }
}
