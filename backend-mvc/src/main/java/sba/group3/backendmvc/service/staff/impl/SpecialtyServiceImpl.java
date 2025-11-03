package sba.group3.backendmvc.service.staff.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.SpecialtyRequest;
import sba.group3.backendmvc.dto.response.staff.SpecialtyResponse;
import sba.group3.backendmvc.mapper.staff.SpecialtyMapper;
import sba.group3.backendmvc.repository.organization.DepartmentRepository;
import sba.group3.backendmvc.repository.staff.SpecialtyRepository;
import sba.group3.backendmvc.service.staff.SpecialtyService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpecialtyServiceImpl implements SpecialtyService {
    SpecialtyRepository specialtyRepository;
    SpecialtyMapper specialtyMapper;
    DepartmentRepository departmentRepository;

    @Override
    public Page<SpecialtyResponse> filter(SearchFilter filter) {
        return specialtyRepository.search(filter).map(specialtyMapper::toDto1);
    }

    @Override
    public SpecialtyResponse create(SpecialtyRequest request) {
        var entity = specialtyMapper.toEntity(request);
        var department = departmentRepository.getReferenceById(request.departmentId());
        entity.setDepartment(department);
        var savedEntity = specialtyRepository.save(entity);
        return specialtyMapper.toDto1(savedEntity);
    }

    @Override
    public SpecialtyResponse update(UUID id, SpecialtyRequest request) {
        var entity = specialtyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Specialty not found with id: " + id)
        );
        specialtyMapper.partialUpdate(request, entity);
        var department = departmentRepository.getReferenceById(request.departmentId());
        entity.setDepartment(department);
        var updatedEntity = specialtyRepository.save(entity);
        return specialtyMapper.toDto1(updatedEntity);
    }

    @Override
    public void delete(UUID id) {
        var entity = specialtyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Specialty not found with id: " + id)
        );
        entity.setDeleted(true);
        specialtyRepository.save(entity);
    }

    @Override
    public SpecialtyResponse getById(UUID id) {
        var entity = specialtyRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Specialty not found with id: " + id)
        );
        return specialtyMapper.toDto1(entity);
    }
}
