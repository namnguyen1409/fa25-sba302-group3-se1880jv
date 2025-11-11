package sba.group3.backendmvc.dto.request.examination;

import sba.group3.backendmvc.entity.examination.ServiceOrderStatus;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.ServiceOrder}
 */
public record ServiceOrderRequest(UUID examinationId, String orderCode, ServiceOrderStatus status) implements Serializable {
}