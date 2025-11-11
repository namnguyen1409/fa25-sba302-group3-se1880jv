package sba.group3.backendmvc.service.organization.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.organization.RoomRequest;
import sba.group3.backendmvc.dto.response.organization.RoomResponse;
import sba.group3.backendmvc.entity.organization.Room;
import sba.group3.backendmvc.mapper.organization.RoomMapper;
import sba.group3.backendmvc.repository.organization.DepartmentRepository;
import sba.group3.backendmvc.repository.organization.RoomRepository;
import sba.group3.backendmvc.service.organization.RoomService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class RoomServiceImpl implements RoomService {
    RoomRepository roomRepository;
    RoomMapper roomMapper;
    private final DepartmentRepository departmentRepository;

    @Override
    public Page<RoomResponse> filter(SearchFilter filter) {
        return roomRepository.search(filter).map(roomMapper::toDto);
    }

    @Override
    public Page<RoomResponse> filterRoomsByDepartment(SearchFilter filter) {
        return roomRepository.search(filter).map(roomMapper::toDto);
    }

    @Override
    public RoomResponse getRoomById(String id) {
        return roomMapper.toDto(roomRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Room not found")));
    }

    @Override
    public RoomResponse createRoom(RoomRequest request) {
        var entity = roomMapper.toEntity(request);
        entity.setDepartment(departmentRepository.getReferenceById(request.departmentId()));
        return roomMapper.toDto(roomRepository.save(entity));
    }

    @Override
    public RoomResponse updateRoom(String id, RoomRequest request) {
        Room existingRoom = roomRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Room not found"));
        roomMapper.partialUpdate(request, existingRoom);
        if (request.departmentId() != null) {
            existingRoom.setDepartment(departmentRepository.getReferenceById(request.departmentId()));
        }
        return roomMapper.toDto(roomRepository.save(existingRoom));
    }

    @Override
    public void deleteRoom(String id) {
        Room existingRoom = roomRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Room not found"));
        existingRoom.setDeleted(true);
        roomRepository.save(existingRoom);
    }

}
