package sba.group3.backendmvc.mapper.staff;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.staff.StaffScheduleTemplateRequest;
import sba.group3.backendmvc.dto.response.staff.StaffScheduleTemplateResponse;
import sba.group3.backendmvc.entity.staff.StaffScheduleTemplate;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StaffScheduleTemplateMapper {
    @Mapping(source = "roomId", target = "room.id")
    @Mapping(source = "staffJoinedDate", target = "staff.joinedDate")
    @Mapping(source = "staffBio", target = "staff.bio")
    @Mapping(source = "staffEducation", target = "staff.education")
    @Mapping(source = "staffExperienceYears", target = "staff.experienceYears")
    @Mapping(source = "staffLicenseNumber", target = "staff.licenseNumber")
    @Mapping(source = "staffStaffType", target = "staff.staffType")
    @Mapping(source = "staffId", target = "staff.id")
    StaffScheduleTemplate toEntity(StaffScheduleTemplateRequest staffScheduleTemplateRequest);

    @InheritInverseConfiguration(name = "toEntity")
    StaffScheduleTemplateRequest toDto(StaffScheduleTemplate staffScheduleTemplate);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StaffScheduleTemplate partialUpdate(StaffScheduleTemplateRequest staffScheduleTemplateRequest, @MappingTarget StaffScheduleTemplate staffScheduleTemplate);

    StaffScheduleTemplate toEntity(StaffScheduleTemplateResponse staffScheduleTemplateResponse);

    StaffScheduleTemplateResponse toDto1(StaffScheduleTemplate staffScheduleTemplate);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StaffScheduleTemplate partialUpdate(StaffScheduleTemplateResponse staffScheduleTemplateResponse, @MappingTarget StaffScheduleTemplate staffScheduleTemplate);
}