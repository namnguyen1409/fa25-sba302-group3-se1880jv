package sba.group3.backendmvc.service.pharmacy.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.pharmacy.DispenseRecordRequest;
import sba.group3.backendmvc.dto.response.pharmacy.DispenseRecordResponse;
import sba.group3.backendmvc.mapper.pharmacy.DispenseRecordMapper;
import sba.group3.backendmvc.repository.pharmacy.DispenseRecordRepository;
import sba.group3.backendmvc.service.pharmacy.DispenseRecordService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class DispenseRecordServiceImpl implements DispenseRecordService {
    private final DispenseRecordRepository dispenseRecordRepository;
    private final DispenseRecordMapper dispenseRecordMapper;

    @Override
    public Page<DispenseRecordResponse> filter(SearchFilter filter) {
        return dispenseRecordRepository.search(filter).map(dispenseRecordMapper::toDto);
    }

    @Override
    public DispenseRecordResponse getById(String recordId) {
        var dispenseRecord = dispenseRecordRepository.findById(UUID.fromString(recordId))
                .orElseThrow(() -> new RuntimeException("Dispense record not found"));
        return dispenseRecordMapper.toDto(dispenseRecord);
    }

    @Override
    public DispenseRecordResponse create(DispenseRecordRequest dispenseRecordRequest) {
        var dispenseRecord = dispenseRecordMapper.toEntity(dispenseRecordRequest);
        var savedRecord = dispenseRecordRepository.save(dispenseRecord);
        return dispenseRecordMapper.toDto(savedRecord);
    }

    @Override
    public DispenseRecordResponse update(UUID recordId, DispenseRecordRequest dispenseRecordRequest) {
        var existingRecord = dispenseRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Dispense record not found"));
        dispenseRecordMapper.partialUpdate(dispenseRecordRequest, existingRecord);
        var updatedRecord = dispenseRecordRepository.save(existingRecord);
        return dispenseRecordMapper.toDto(updatedRecord);
    }

    @Override
    public void delete(UUID recordId) {
        var entity = dispenseRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Dispense record not found"));
        entity.setDeleted(true);
        dispenseRecordRepository.save(entity);

    }
}
