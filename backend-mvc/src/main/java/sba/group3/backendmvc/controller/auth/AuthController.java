package sba.group3.backendmvc.controller.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.request.auth.LoginRequest;
import sba.group3.backendmvc.dto.request.auth.OAuthLoginRequest;
import sba.group3.backendmvc.dto.request.auth.RegisterRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.auth.AuthResponse;
import sba.group3.backendmvc.dto.response.user.MeResponse;
import sba.group3.backendmvc.enums.MfaType;
import sba.group3.backendmvc.service.auth.AuthService;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Authentication", description = "API for user authentication")
public class AuthController {

    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<AuthResponse>> login(
            @RequestBody @Validated LoginRequest loginRequest
    ) {
        return ResponseEntity
                .ok(
                        CustomApiResponse.<AuthResponse>builder()
                                .data(authService.login(loginRequest))
                                .message("Login successful")
                                .build()
                );
    }

    @PostMapping("/register")
    public ResponseEntity<CustomApiResponse<AuthResponse>> register(
            @RequestBody @Validated RegisterRequest registerRequest
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<AuthResponse>builder()
                        .data(authService.register(registerRequest))
                        .build()
        );
    }

    @PostMapping("/activate")
    public ResponseEntity<CustomApiResponse<String>> activateAccount(
            @RequestBody String token
    ) {
        authService.activateAccount(token);

        return ResponseEntity.ok(
                CustomApiResponse.<String>builder()
                        .data("Tài khoản đã được kích hoạt thành công.")
                        .build()
        );
    }


    @PostMapping("/oauth")
    public ResponseEntity<CustomApiResponse<AuthResponse>> loginWithOAuth(
            @RequestBody @Validated OAuthLoginRequest oAuthLoginRequest
    ) {
        return ResponseEntity
                .ok(
                        CustomApiResponse.<AuthResponse>builder()
                                .data(authService.loginWithOAuth(oAuthLoginRequest))
                                .message("OAuth login successful")
                                .build()
                );
    }

    @GetMapping("/me")
    public ResponseEntity<CustomApiResponse<MeResponse>> getCurrentUser(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity
                .ok(
                        CustomApiResponse.<MeResponse>builder()
                                .data(authService.getCurrentUser(UUID.fromString(jwt.getSubject())))
                                .message("Fetched current user successfully")
                                .build()
                );
    }

    @PostMapping("/refresh")
    public ResponseEntity<CustomApiResponse<AuthResponse>> refreshToken() {
        return ResponseEntity
                .ok(
                        CustomApiResponse.<AuthResponse>builder()
                                .data(authService.refreshToken())
                                .message("Token refreshed successfully")
                                .build()
                );
    }


    @PostMapping("/logout")
    public ResponseEntity<CustomApiResponse<Void>> logout(@AuthenticationPrincipal Jwt jwt) {
        authService.logout(UUID.fromString(jwt.getSubject()));
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Logout successful")
                        .build()
        );
    }

    @PostMapping("/password/reset/request")
    public ResponseEntity<CustomApiResponse<Void>> requestPasswordReset(
            @RequestBody @Validated PasswordResetRequest request
    ) {
        authService.requestPasswordReset(request);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Password reset request sent successfully")
                        .build()
        );
    }

    @PostMapping("/password/reset/confirm")
    public ResponseEntity<CustomApiResponse<Void>> confirmPasswordReset(
            @RequestBody @Validated PasswordResetConfirmRequest request
    ) {
        authService.confirmPasswordReset(request);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Password reset confirmed successfully")
                        .build()
        );
    }

    @PostMapping("/mfa/verify")
    public ResponseEntity<CustomApiResponse<AuthResponse>> verifyMfa(
            @RequestBody @Validated MfaVerifyRequest request
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<AuthResponse>builder()
                        .data(authService.verifyMfa(request))
                        .message("MFA verification successful")
                        .build()
        );
    }

    @PostMapping("/mfa/switch")
    public ResponseEntity<CustomApiResponse<AuthResponse>> switchMfa(
            @RequestBody @Validated SwitchMfaRequest request
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<AuthResponse>builder()
                        .message("MFA switched successfully")
                        .data(authService.switchMfa(
                                request
                        ))
                        .build()
        );
    }

    public record PasswordResetRequest(
            String email
    ) {
    }

    public record PasswordResetConfirmRequest(
            String token,
            String newPassword
    ) {
    }

    public record MfaVerifyRequest(
            @NotNull UUID challengeId,
            @NotBlank String code,
            @NotBlank String deviceId,
            Boolean rememberMe
    ) {
    }

    public record SwitchMfaRequest(
            @NotNull MfaType mfaType,
            @NotNull UUID challengeId
    ) {
    }


}
