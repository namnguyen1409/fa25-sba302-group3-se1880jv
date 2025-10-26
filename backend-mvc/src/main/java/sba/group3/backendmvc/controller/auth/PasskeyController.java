package sba.group3.backendmvc.controller.auth;

import com.yubico.webauthn.data.PublicKeyCredentialCreationOptions;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sba.group3.backendmvc.dto.request.auth.passkey.FinishLoginRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.auth.AuthResponse;
import sba.group3.backendmvc.dto.response.auth.passkey.StartPasskeyLoginResponse;
import sba.group3.backendmvc.service.auth.PasskeyService;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth/passkeys")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Passkey Authentication", description = "API for passkey-based authentication")
public class PasskeyController {

    PasskeyService passkeyService;

    @PostMapping("/register/start")
    public ResponseEntity<CustomApiResponse<PublicKeyCredentialCreationOptions>> startRegistration(@AuthenticationPrincipal Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        String username = jwt.getClaimAsString("username");
        return ResponseEntity.ok(
                CustomApiResponse.<PublicKeyCredentialCreationOptions>builder()
                        .data(passkeyService.startRegistration(userId, username, username))
                        .message("Passkey registration started successfully")
                        .build()
        );
    }

    @PostMapping("/register/finish")
    public ResponseEntity<CustomApiResponse<Void>> finishRegistration(@AuthenticationPrincipal Jwt jwt, String credential) {
        UUID userId = UUID.fromString(jwt.getSubject());
        passkeyService.finishRegistration(userId, credential);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Passkey registration finished successfully")
                        .build()
        );
    }

    @PostMapping("/login/start")
    public ResponseEntity<CustomApiResponse<StartPasskeyLoginResponse>> startLogin() {
        return ResponseEntity.ok(
                CustomApiResponse.<StartPasskeyLoginResponse>builder()
                        .data(passkeyService.startLogin())
                        .message("Passkey login started successfully")
                        .build()
        );
    }

    @PostMapping("/login/finish")
    public ResponseEntity<CustomApiResponse<AuthResponse>> finishLogin(
            @RequestBody FinishLoginRequest request
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<AuthResponse>builder()
                        .data(passkeyService.finishLogin(request))
                        .message("Passkey login finished successfully")
                        .build()
        );
    }
}