package sba.group3.backendmvc.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;
import sba.group3.backendmvc.dto.response.organization.RoomResponse;
import sba.group3.backendmvc.dto.response.patient.PatientResponse;
import sba.group3.backendmvc.dto.response.staff.StaffResponse;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.user.User}
 */
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class MeResponse implements Serializable {
    UUID id;
    String username;
    String email;
    String phone;
    boolean active;
    boolean locked;
    boolean mfaEnabled;
    SimpleProfileResponse userProfile;
    boolean firstLogin;
    Set<RoleNameResponse> roles;
    DeviceInfo device;
    private StaffResponse staff;
    private PatientResponse patient;
    private RoomResponse room;
}