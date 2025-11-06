package sba.group3.backendmvc.service.staff.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.StaffScheduleTemplateRequest;
import sba.group3.backendmvc.dto.response.staff.StaffScheduleTemplateResponse;
import sba.group3.backendmvc.mapper.staff.StaffScheduleTemplateMapper;
import sba.group3.backendmvc.repository.organization.RoomRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.repository.staff.StaffScheduleTemplateRepository;
import sba.group3.backendmvc.service.staff.StaffScheduleTemplateService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffScheduleTemplateServiceImpl implements StaffScheduleTemplateService {
    private final StaffScheduleTemplateRepository staffScheduleTemplateRepository;
    private final StaffScheduleTemplateMapper staffScheduleTemplateMapper;
    private final StaffRepository staffRepository;
    private final RoomRepository roomRepository;

    @Override
    public Page<StaffScheduleTemplateResponse> filter(SearchFilter filter) {
        return staffScheduleTemplateRepository.search(filter).map(staffScheduleTemplateMapper::toDto1);
    }

    @Override
    public StaffScheduleTemplateResponse create(UUID staffId, StaffScheduleTemplateRequest request) {
        var entity = staffScheduleTemplateMapper.toEntity(request);
        entity.setStaff(staffRepository.getReferenceById(staffId));
        entity.setRoom(roomRepository.getReferenceById(request.roomId()));
        var savedEntity = staffScheduleTemplateRepository.save(entity);
        return staffScheduleTemplateMapper.toDto1(savedEntity);
    }

    @Override
    public StaffScheduleTemplateResponse update(UUID templateId, StaffScheduleTemplateRequest request) {
        var entity = staffScheduleTemplateRepository.findById(templateId).orElseThrow();
        staffScheduleTemplateMapper.partialUpdate(request, entity);
        entity.setRoom(roomRepository.getReferenceById(request.roomId()));
        var updatedEntity = staffScheduleTemplateRepository.save(entity);
        return staffScheduleTemplateMapper.toDto1(updatedEntity);
    }

    @Override
    public void delete(UUID templateId) {
        var entity = staffScheduleTemplateRepository.findById(templateId).orElseThrow();
        entity.setDeleted(true);
        staffScheduleTemplateRepository.save(entity);
    }
}
