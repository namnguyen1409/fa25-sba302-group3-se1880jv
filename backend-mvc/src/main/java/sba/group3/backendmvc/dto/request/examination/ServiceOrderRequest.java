package sba.group3.backendmvc.dto.request.examination;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.ServiceOrder}
 */
public record ServiceOrderRequest(UUID examinationId, String orderCode) implements Serializable {
}