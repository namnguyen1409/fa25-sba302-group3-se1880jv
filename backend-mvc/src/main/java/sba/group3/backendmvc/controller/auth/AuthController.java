package sba.group3.backendmvc.controller.auth;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.request.auth.LoginRequest;
import sba.group3.backendmvc.dto.request.auth.OAuthLoginRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.auth.AuthResponse;
import sba.group3.backendmvc.dto.response.user.MeResponse;
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

}
