package sba.group3.backendmvc.dto.response.examination;

import sba.group3.backendmvc.dto.response.pharmacy.MedicineResponse;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.PrescriptionItem}
 */
public record PrescriptionItemResponse(UUID id, MedicineResponse medicine, String dosage, String frequency,
                                       String duration, String instruction) implements Serializable {
}