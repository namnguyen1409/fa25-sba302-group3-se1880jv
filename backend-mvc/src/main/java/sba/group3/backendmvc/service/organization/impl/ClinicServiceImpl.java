package sba.group3.backendmvc.service.organization.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.organization.ClinicRequest;
import sba.group3.backendmvc.dto.response.organization.ClinicResponse;
import sba.group3.backendmvc.entity.organization.Clinic;
import sba.group3.backendmvc.mapper.organization.ClinicMapper;
import sba.group3.backendmvc.repository.organization.ClinicRepository;
import sba.group3.backendmvc.service.organization.ClinicService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClinicServiceImpl implements ClinicService {
    ClinicRepository clinicRepository;
    ClinicMapper clinicMapper;

    @Override
    public Page<ClinicResponse> filter(SearchFilter filter) {
        return clinicRepository.search(filter).map(clinicMapper::toDto);
    }

    @Override
    public ClinicResponse getClinicById(String id) {
        return clinicMapper.toDto(clinicRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("Clinic not found")));
    }

    @Override
    public ClinicResponse createClinic(ClinicRequest request) {
        return clinicMapper.toDto(clinicRepository.save(clinicMapper.toEntity(request)));
    }

    @Override
    public ClinicResponse updateClinic(String id, ClinicRequest request) {
        Clinic existingClinic = clinicRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("Clinic not found"));
        clinicMapper.partialUpdate(request, existingClinic);
        return clinicMapper.toDto(clinicRepository.save(existingClinic));
    }

    @Override
    public void deleteClinic(String id) {
        Clinic existingClinic = clinicRepository.findById(UUID.fromString(id)).orElseThrow(() -> new RuntimeException("Clinic not found"));
        existingClinic.setDeleted(true);
        clinicRepository.save(existingClinic);
    }

    @Override
    public ClinicResponse getDefaultClinic() {
        var entity = clinicRepository.findAll().stream().findFirst().orElseThrow(() -> new RuntimeException("Default clinic not found"));
        return clinicMapper.toDto(entity);
    }
}
