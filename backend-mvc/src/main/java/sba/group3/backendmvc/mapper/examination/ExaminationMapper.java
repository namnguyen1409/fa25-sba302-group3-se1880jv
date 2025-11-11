package sba.group3.backendmvc.mapper.examination;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.examination.ExaminationRequest;
import sba.group3.backendmvc.dto.response.examination.ExaminationResponse;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.Prescription;
import sba.group3.backendmvc.mapper.laboratory.LabOrderMapper;
import sba.group3.backendmvc.mapper.patient.PatientMapper;
import sba.group3.backendmvc.mapper.staff.StaffMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {PatientMapper.class, StaffMapper.class, PrescriptionMapper.class, ServiceOrderMapper.class, VitalSignMapper.class, DiagnosisMapper.class, LabOrderMapper.class})
public interface ExaminationMapper {
    @Mapping(source = "staffId", target = "staff.id")
    @Mapping(source = "patientId", target = "patient.id")
    Examination toEntity(ExaminationRequest examinationRequest);

    @InheritInverseConfiguration(name = "toEntity")
    ExaminationRequest toDto(Examination examination);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Examination partialUpdate(ExaminationRequest examinationRequest, @MappingTarget Examination examination);

    @Mapping(source = "invoiceId", target = "invoice.id")
    Examination toEntity(ExaminationResponse examinationResponse);

    @AfterMapping
    default void linkPrescription(@MappingTarget Examination examination) {
        Prescription prescription = examination.getPrescription();
        if (prescription != null) {
            prescription.setExamination(examination);
        }
    }

    @AfterMapping
    default void linkServiceOrders(@MappingTarget Examination examination) {
        examination.getServiceOrders().forEach(serviceOrder -> serviceOrder.setExamination(examination));
    }

    @AfterMapping
    default void linkVitalSigns(@MappingTarget Examination examination) {
        examination.getVitalSigns().forEach(vitalSign -> vitalSign.setExamination(examination));
    }

    @AfterMapping
    default void linkDiagnoses(@MappingTarget Examination examination) {
        examination.getDiagnoses().forEach(diagnosis -> diagnosis.setExamination(examination));
    }

    @AfterMapping
    default void linkLabOrders(@MappingTarget Examination examination) {
        examination.getLabOrders().forEach(labOrder -> labOrder.setExamination(examination));
    }

    @Mapping(source = "invoice.id", target = "invoiceId")
    ExaminationResponse toDto1(Examination examination);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Examination partialUpdate(ExaminationResponse examinationResponse, @MappingTarget Examination examination);
}