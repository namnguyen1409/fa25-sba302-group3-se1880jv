package sba.group3.backendmvc.service.organization.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.organization.DepartmentRequest;
import sba.group3.backendmvc.dto.response.organization.DepartmentResponse;
import sba.group3.backendmvc.entity.organization.Department;
import sba.group3.backendmvc.mapper.organization.DepartmentMapper;
import sba.group3.backendmvc.repository.organization.ClinicRepository;
import sba.group3.backendmvc.repository.organization.DepartmentRepository;
import sba.group3.backendmvc.service.organization.DepartmentService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class DepartmentServiceImpl implements DepartmentService {
    DepartmentRepository departmentRepository;
    DepartmentMapper departmentMapper;
    private final ClinicRepository clinicRepository;

    @Override
    public Page<DepartmentResponse> filter(SearchFilter filter) {
        return departmentRepository.search(filter).map(departmentMapper::toDto);
    }

    @Override
    public Page<DepartmentResponse> filterDepartmentsByClinic(SearchFilter filter) {
        return departmentRepository.search(filter).map(departmentMapper::toDto);
    }

    @Override
    public DepartmentResponse getDepartmentById(String id) {
        return departmentMapper.toDto(departmentRepository.findById(UUID.fromString(id))
                        .orElseThrow(() -> new RuntimeException("Department not found")));
    }

    @Override
    public DepartmentResponse createDepartment(DepartmentRequest departmentRequest) {
        var entity = departmentMapper.toEntity(departmentRequest);
        entity.setClinic(clinicRepository.getReferenceById(departmentRequest.clinicId()));
        return departmentMapper.toDto(departmentRepository.save(entity));
    }

    @Override
    public DepartmentResponse updateDepartment(String id, DepartmentRequest departmentRequest) {
        Department existingDepartment = departmentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Department not found"));
        departmentMapper.partialUpdate(departmentRequest, existingDepartment);
        return departmentMapper.toDto(departmentRepository.save(existingDepartment));
    }

    @Override
    public void deleteDepartment(String id) {
        Department existingDepartment = departmentRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Department not found"));
        existingDepartment.setDeleted(true);
        departmentRepository.save(existingDepartment);
    }
}
