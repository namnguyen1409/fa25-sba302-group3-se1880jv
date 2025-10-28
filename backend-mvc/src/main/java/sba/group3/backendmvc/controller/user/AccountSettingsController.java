package sba.group3.backendmvc.controller.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.user.AccountSettingResponse;
import sba.group3.backendmvc.service.user.AccountSettingsService;

import java.util.UUID;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@RequestMapping("/api/account/settings")
@Tag(name = "Account setting", description = "Email, phone, OAuth, password ")
public class AccountSettingsController {


    AccountSettingsService accountSettingsService;

    @GetMapping
    public ResponseEntity<CustomApiResponse<AccountSettingResponse>> getAccountSettings(
            @AuthenticationPrincipal Jwt jwt
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<AccountSettingResponse>builder()
                        .data(accountSettingsService.getAccountSettings(UUID.fromString(jwt.getSubject())))
                        .build()
        );
    }


    // update user name
    @PatchMapping("/username")
    public ResponseEntity<CustomApiResponse<AccountSettingResponse>> updateUsername(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody @Validated UpdateUsernameRequest updateUsernameRequest
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<AccountSettingResponse>builder()
                        .data(accountSettingsService.updateUsername(UUID.fromString(jwt.getSubject()), updateUsernameRequest))
                        .message("Username updated successfully")
                        .build()
        );
    }

    @PatchMapping("/email/request-change")
    public ResponseEntity<CustomApiResponse<Void>> updateEmailRequest(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody @Validated UpdateEmailRequest updateEmailRequest
    ) {
        accountSettingsService.requestEmailChange(
                UUID.fromString(jwt.getSubject()),
                updateEmailRequest
        );
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Send email verification successfully")
                        .build()
        );
    }

    @GetMapping("/email/verify-change")
    public ResponseEntity<CustomApiResponse<Void>> verifyEmailChange(
            @RequestParam("token") String token
    ) {
        accountSettingsService.verifyEmailChange(token);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Email changed successfully")
                        .build()
        );
    }

    public record UpdateUsernameRequest(
            @NotBlank(message = "Username cannot be blank")
            @Pattern(regexp = "^[a-zA-Z0-9._-]{3,20}$",
                    message = "Username must be 3-20 characters long and can only contain letters, numbers, dots, underscores, and hyphens")
            String newUsername) {
    }

    public record UpdateEmailRequest(
            @NotBlank(message = "Email cannot be blank")
            @Email(message = "Invalid email format")
            String newEmail
    ) {
    }


}
