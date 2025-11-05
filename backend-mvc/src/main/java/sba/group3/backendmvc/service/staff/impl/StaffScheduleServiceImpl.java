package sba.group3.backendmvc.service.staff.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.staff.StaffScheduleResponse;
import sba.group3.backendmvc.mapper.staff.StaffScheduleMapper;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.repository.staff.StaffScheduleRepository;
import sba.group3.backendmvc.service.staff.StaffScheduleService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffScheduleServiceImpl implements StaffScheduleService {

    private final StaffScheduleRepository staffScheduleRepository;
    private final StaffScheduleMapper staffScheduleMapper;
    private final StaffRepository staffRepository;

    @Override
    public Page<StaffScheduleResponse> filter(SearchFilter filter) {
        return staffScheduleRepository.search(filter).map(staffScheduleMapper::toDto1);
    }

    @Override
    public StaffScheduleResponse create(UUID staffId, StaffScheduleResponse request) {
        var entity = staffScheduleMapper.toEntity(request);
        entity.setStaff(staffRepository.getReferenceById(staffId));
        var savedEntity = staffScheduleRepository.save(entity);
        return staffScheduleMapper.toDto1(savedEntity);
    }

    @Override
    public StaffScheduleResponse update(UUID scheduleId, StaffScheduleResponse request) {
        var entity = staffScheduleRepository.findById(scheduleId).orElseThrow();
        staffScheduleMapper.partialUpdate(request, entity);
        var updatedEntity = staffScheduleRepository.save(entity);
        return staffScheduleMapper.toDto1(updatedEntity);
    }

    @Override
    public void delete(UUID scheduleId) {
        var entity = staffScheduleRepository.findById(scheduleId).orElseThrow();
        entity.setDeleted(true);
        staffScheduleRepository.save(entity);
    }
}
