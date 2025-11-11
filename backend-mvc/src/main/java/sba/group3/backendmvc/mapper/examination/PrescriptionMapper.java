package sba.group3.backendmvc.mapper.examination;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.examination.PrescriptionRequest;
import sba.group3.backendmvc.dto.response.examination.PrescriptionResponse;
import sba.group3.backendmvc.entity.examination.Prescription;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {PrescriptionItemMapper.class})
public interface PrescriptionMapper {
    @Mapping(source = "examinationId", target = "examination.id")
    Prescription toEntity(PrescriptionRequest prescriptionRequest);

    @Mapping(source = "examination.id", target = "examinationId")
    PrescriptionRequest toDto(Prescription prescription);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "examinationId", target = "examination.id")
    Prescription partialUpdate(PrescriptionRequest prescriptionRequest, @MappingTarget Prescription prescription);

    @Mapping(source = "dispenseRecordId", target = "dispenseRecord.id")
    Prescription toEntity(PrescriptionResponse prescriptionResponse);

    @AfterMapping
    default void linkItems(@MappingTarget Prescription prescription) {
        prescription.getItems().forEach(item -> item.setPrescription(prescription));
    }

    @Mapping(source = "dispenseRecord.id", target = "dispenseRecordId")
    PrescriptionResponse toDto1(Prescription prescription);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Prescription partialUpdate(PrescriptionResponse prescriptionResponse, @MappingTarget Prescription prescription);
}