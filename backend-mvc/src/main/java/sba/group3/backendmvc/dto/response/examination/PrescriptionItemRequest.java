package sba.group3.backendmvc.dto.response.examination;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.PrescriptionItem}
 */
public record PrescriptionItemRequest(UUID prescriptionId, UUID medicineId, String dosage, String frequency,
                                      String duration, String instruction) implements Serializable {
}