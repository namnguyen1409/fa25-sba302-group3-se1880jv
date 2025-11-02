package sba.group3.backendmvc.controller.organization;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.organization.RoomRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.organization.RoomResponse;
import sba.group3.backendmvc.service.organization.RoomService;

@Slf4j
@RestController
@RequestMapping("/api/organization/rooms")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Rooms Management", description = "APIs for managing rooms")
public class RoomController {
    RoomService roomService;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<RoomResponse>>> filter(
            @RequestBody SearchFilter filter) {
        return ResponseEntity.ok(
                CustomApiResponse.<Page<RoomResponse>>builder()
                        .data(roomService.filter(filter))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<RoomResponse>> getRoomById(
            @PathVariable String id
    ) {
        log.info("Fetching room with ID: {}", id);
        return ResponseEntity.ok(
                CustomApiResponse.<RoomResponse>builder()
                        .data(roomService.getRoomById(id))
                        .message("Room fetched successfully")
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<RoomResponse>> create(@RequestBody @Validated RoomRequest request) {
        log.info("Creating room {}", request);
        return ResponseEntity.ok(
                CustomApiResponse.<RoomResponse>builder()
                        .data(roomService.createRoom(request))
                        .message("Room create successful")
                        .build()
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<RoomResponse>> update(@PathVariable String id,
                                                                  @RequestBody @Validated RoomRequest request) {
        log.info("Updating room with ID {}: {}", id, request);
        return ResponseEntity.ok(
                CustomApiResponse.<RoomResponse>builder()
                        .data(roomService.updateRoom(id, request))
                        .message("Room update successful")
                        .build()
        );
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(@PathVariable String id) {
        log.info("Deleting room with ID: {}", id);
        roomService.deleteRoom(id);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Room delete successful")
                        .build()
        );
    }
}
