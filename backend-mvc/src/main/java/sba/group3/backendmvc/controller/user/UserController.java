package sba.group3.backendmvc.controller.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.user.UserResponse;
import sba.group3.backendmvc.service.user.UserService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "User Management", description = "APIs for managing users by admin")
public class UserController {


    UserService userService;

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<UserResponse>>> filter(
            @RequestBody SearchFilter filter) {
        log.info("Filtering users by admin {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<UserResponse>>builder()
                        .data(userService.filter(filter))
                        .build()
        );
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @PostMapping("/{userId}/lock")
    public ResponseEntity<CustomApiResponse<Void>> lockUserAccount(
            @PathVariable UUID userId
    ) {
        userService.lockUserAccount(userId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("User account locked successfully")
                        .build()
        );
    }

    @PreAuthorize("hasRole('SYSTEM_ADMIN')")
    @PostMapping("/{userId}/unlock")
    public ResponseEntity<CustomApiResponse<Void>> unlockUserAccount(
            @PathVariable UUID userId
    ) {
        userService.unlockUserAccount(userId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("User account unlocked successfully")
                        .build()
        );
    }

}
