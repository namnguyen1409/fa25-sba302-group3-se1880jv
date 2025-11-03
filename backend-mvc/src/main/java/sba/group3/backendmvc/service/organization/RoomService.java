package sba.group3.backendmvc.service.organization;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.organization.RoomRequest;
import sba.group3.backendmvc.dto.response.organization.RoomResponse;

public interface RoomService {
    Page<RoomResponse> filter(SearchFilter filter);
    Page<RoomResponse> filterRoomsByDepartment(SearchFilter filter);


    RoomResponse getRoomById(String id);

    RoomResponse createRoom(RoomRequest request);

    RoomResponse updateRoom(String id, RoomRequest request);

    void deleteRoom(String id);

}
