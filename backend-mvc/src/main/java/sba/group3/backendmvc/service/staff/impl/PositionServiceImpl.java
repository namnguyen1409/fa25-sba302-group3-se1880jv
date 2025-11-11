package sba.group3.backendmvc.service.staff.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.PositionRequest;
import sba.group3.backendmvc.dto.response.staff.PositionResponse;
import sba.group3.backendmvc.entity.staff.Position;
import sba.group3.backendmvc.mapper.staff.PositionMapper;
import sba.group3.backendmvc.repository.staff.PositionRepository;
import sba.group3.backendmvc.service.staff.PositionService;

import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PositionServiceImpl implements PositionService {


    private final PositionRepository positionRepository;
    private final PositionMapper positionMapper;

    private String generatePositionCode() {
        long count = positionRepository.count();
        return String.format("POS-%04d", count + 1);
    }


    @Transactional
    @PostConstruct
    public void init() {
        if (positionRepository.count() == 0) {
            try {
                AtomicLong initialCount = new AtomicLong();
                var resource = new ClassPathResource("position.json");
                String prefix = "POS";
                ObjectMapper mapper = new ObjectMapper();
                List<Position> positions = mapper.readValue(
                        resource.getInputStream(), mapper.getTypeFactory().constructCollectionType(List.class, Position.class)
                );
                Random random = new Random();
                for (Position position : positions) {
                    position.setPositionCode(String.format("%s%05d", prefix, initialCount.incrementAndGet()));
                }
                positionRepository.saveAll(positions);
                log.info("Imported {} positions", positions.size());
            } catch (Exception e) {
                log.error("Failed to import positions", e);
            }
        }
    }

    @Override
    public Page<PositionResponse> filter(SearchFilter filter) {
        return positionRepository.search(filter).map(positionMapper::toDto);
    }

    @Override
    public PositionResponse create(PositionRequest request) {
        var position = new Position(generatePositionCode(), request.title(), request.description());
        return positionMapper.toDto(positionRepository.save(position));
    }

    @Override
    public PositionResponse update(UUID id, PositionRequest request) {
        var position = positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found"));
        position.setTitle(request.title());
        position.setDescription(request.description());
        return positionMapper.toDto(positionRepository.save(position));
    }

    @Override
    public void delete(UUID id) {
        var position = positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found"));
        position.setDeleted(true);
        positionRepository.save(position);
    }

    @Override
    public PositionResponse getById(UUID id) {
        var position = positionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Position not found"));
        return positionMapper.toDto(position);
    }
}
