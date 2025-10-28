package sba.group3.backendmvc.controller.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.user.DeviceSessionResponse;
import sba.group3.backendmvc.service.user.AccountDeviceService;
import sba.group3.backendmvc.service.user.AccountSecurityService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/account/devices")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Account Device Controller", description = "Endpoints for managing user device sessions")
public class AccountDeviceController {


     AccountSecurityService accountSecurityService;
   AccountDeviceService accountDeviceService;



    @GetMapping
    public ResponseEntity<CustomApiResponse<List<DeviceSessionResponse>>> getAllDevices(
            @AuthenticationPrincipal Jwt jwt
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<List<DeviceSessionResponse>>builder()
                        .data(accountDeviceService.getAllDeviceSessions(UUID.fromString(jwt.getSubject())))
                        .message("User devices retrieved successfully")
                        .build()
        );
    }

    @PostMapping("/logout-all")
    public ResponseEntity<CustomApiResponse<Void>> logoutAllDevices(
            @AuthenticationPrincipal Jwt jwt
    ) {
        accountDeviceService.logoutAllDeviceSessions(UUID.fromString(jwt.getSubject()));
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("All devices logged out successfully")
                        .build()
        );
    }

    @PostMapping("/logout/{deviceSessionId}")
    public ResponseEntity<CustomApiResponse<Void>> logoutDevice(
            @AuthenticationPrincipal Jwt jwt,
            @PathVariable UUID deviceSessionId
    ) {
        accountDeviceService.logoutDeviceSession(UUID.fromString(jwt.getSubject()), deviceSessionId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Device logged out successfully")
                        .build()
        );
    }

}
