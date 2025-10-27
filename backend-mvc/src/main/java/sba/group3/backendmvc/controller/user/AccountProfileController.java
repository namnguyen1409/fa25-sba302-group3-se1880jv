package sba.group3.backendmvc.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sba.group3.backendmvc.dto.request.user.UserProfileRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.user.UserProfileResponse;
import sba.group3.backendmvc.service.infrastructure.FileUploadService;
import sba.group3.backendmvc.service.user.AccountProfileService;

import java.util.UUID;

@RestController
@RequestMapping("/api/account/profile")
public class AccountProfileController {

    private final AccountProfileService accountProfileService;
    private final FileUploadService fileUploadService;

    public AccountProfileController(AccountProfileService accountProfileService, FileUploadService fileUploadService) {
        this.accountProfileService = accountProfileService;
        this.fileUploadService = fileUploadService;
    }

    @GetMapping
    public ResponseEntity<CustomApiResponse<UserProfileResponse>> getProfile(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(
                CustomApiResponse.<UserProfileResponse>builder()
                        .data(accountProfileService.getProfile(UUID.fromString(jwt.getSubject())))
                        .message("User profile retrieved successfully")
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<CustomApiResponse<UserProfileResponse>> updateProfile(@AuthenticationPrincipal Jwt jwt,
                                                                                 @RequestBody UserProfileRequest profileRequest
    ) {
        return ResponseEntity.ok(
                        CustomApiResponse.<UserProfileResponse>builder()
                                .data(accountProfileService.updateProfile(UUID.fromString(jwt.getSubject()), profileRequest))
                                .message("User profile updated successfully")
                                .build()

        );
    }

    @PostMapping(value = "/avatar", consumes = "multipart/form-data")
    public ResponseEntity<CustomApiResponse<UserProfileResponse>> updateAvatar(
            @AuthenticationPrincipal Jwt jwt,
            @RequestParam MultipartFile file) throws Exception {
        return ResponseEntity.ok(
                CustomApiResponse.<UserProfileResponse>builder()
                        .data(accountProfileService.updateAvatar(
                                UUID.fromString(jwt.getSubject()),
                                file
                        ))
                        .message("User avatar updated successfully")
                        .build()
        );

    }

}
