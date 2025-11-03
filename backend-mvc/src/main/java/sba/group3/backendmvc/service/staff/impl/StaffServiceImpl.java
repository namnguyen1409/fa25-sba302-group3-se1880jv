package sba.group3.backendmvc.service.staff.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.StaffRequest;
import sba.group3.backendmvc.dto.response.staff.StaffResponse;
import sba.group3.backendmvc.mapper.staff.StaffMapper;
import sba.group3.backendmvc.repository.organization.DepartmentRepository;
import sba.group3.backendmvc.repository.staff.PositionRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.service.staff.StaffService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffServiceImpl implements StaffService {
    private final StaffRepository staffRepository;
    private final StaffMapper staffMapper;
    private final PositionRepository positionRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Page<StaffResponse> filter(SearchFilter filter) {
        return staffRepository.search(filter).map(staffMapper::toDto1);
    }

    @Override
    public StaffResponse create(StaffRequest request) {
        var entity = staffMapper.toEntity(request);
        if (request.positionId() != null) {
            entity.setPosition(positionRepository.getReferenceById(request.positionId()));
        }
        if (request.departmentId() != null) {
            entity.setDepartment(departmentRepository.getReferenceById(request.departmentId()));
        }
        var savedEntity = staffRepository.save(entity);
        return staffMapper.toDto1(savedEntity);
    }

    @Override
    public StaffResponse update(UUID id, StaffRequest request) {
        var entity = staffRepository.findById(id).orElseThrow();
        staffMapper.partialUpdate(request, entity);
        if (request.positionId() != null) {
            entity.setPosition(positionRepository.getReferenceById(request.positionId()));
        }
        if (request.departmentId() != null) {
            entity.setDepartment(departmentRepository.getReferenceById(request.departmentId()));
        }
        var updatedEntity = staffRepository.save(entity);
        return staffMapper.toDto1(updatedEntity);
    }

    @Override
    public void delete(UUID id) {
        var entity = staffRepository.findById(id).orElseThrow();
        entity.setDeleted(true);
        staffRepository.save(entity);
    }

    @Override
    public StaffResponse getById(UUID id) {
        var entity = staffRepository.findById(id).orElseThrow();
        return staffMapper.toDto1(entity);
    }
}
