package sba.group3.backendmvc.dto.response.examination;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.Prescription}
 */
public record PrescriptionResponse(UUID id, String note, Set<PrescriptionItemResponse> items) implements Serializable {
}