package sba.group3.backendmvc.service.staff.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.PositionRequest;
import sba.group3.backendmvc.dto.response.staff.PositionResponse;
import sba.group3.backendmvc.entity.staff.Position;
import sba.group3.backendmvc.mapper.staff.PositionMapper;
import sba.group3.backendmvc.repository.staff.PositionRepository;
import sba.group3.backendmvc.service.staff.PositionService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PositionServiceImpl implements PositionService {


    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    private String generatePositionCode(){
        return "PO-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    @Override
    public Page<PositionResponse> filter(SearchFilter filter) {
        return positionRepository.search(filter).map(positionMapper::toDto);
    }

    @Override
    public PositionResponse create(PositionRequest request) {
        Position position = new Position(generatePositionCode(), request.title(), request.description());
        return positionMapper.toDto(positionRepository.save(position));
    }

    @Override
    public PositionResponse update(String id, PositionRequest request) {
        Position position = positionRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Position not found"));
        position.setTitle(request.title());
        position.setDescription(request.description());
        return positionMapper.toDto(positionRepository.save(position));
    }

    @Override
    public void delete(String id) {
        Position position = positionRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Position not found"));
        position.setDeleted(true);
        positionRepository.save(position);
    }
}
