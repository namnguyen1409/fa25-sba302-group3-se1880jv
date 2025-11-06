package sba.group3.backendmvc.dto.response.examination;

import sba.group3.backendmvc.dto.response.organization.RoomResponse;
import sba.group3.backendmvc.dto.response.staff.StaffResponse;
import sba.group3.backendmvc.entity.examination.ServiceOrderStatus;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.ServiceOrder}
 */
public record ServiceOrderResponse(UUID id, String orderCode,
                                   Set<ServiceOrderItemResponse> items, RoomResponse room, StaffResponse assignedStaff,
                                   ServiceOrderStatus status) implements Serializable {
}