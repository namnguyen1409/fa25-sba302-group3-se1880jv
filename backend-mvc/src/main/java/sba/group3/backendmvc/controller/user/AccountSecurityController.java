package sba.group3.backendmvc.controller.user;

import com.yubico.webauthn.data.PublicKeyCredentialCreationOptions;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.request.auth.TOTPConfirmRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.auth.MfaConfigResponse;
import sba.group3.backendmvc.dto.response.auth.MfaSetupResponse;
import sba.group3.backendmvc.service.auth.MfaConfigService;
import sba.group3.backendmvc.service.auth.PasskeyService;
import sba.group3.backendmvc.service.user.AccountSecurityService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/account/security")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Tag(name = "Account Security Controller", description = "Endpoints for managing account security settings")
public class AccountSecurityController {

    AccountSecurityService accountSecurityService;
    MfaConfigService mfaConfigService;
    PasskeyService passkeyService;


    @PostMapping("/first-login")
    public ResponseEntity<CustomApiResponse<Void>> firstLogin(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody @Validated FirstLoginRequest request
    ) {
        accountSecurityService.firstLogin(UUID.fromString(jwt.getSubject()), request);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("First login completed successfully")
                        .build()
        );
    }

    public record FirstLoginRequest(
            @NotBlank String username,
            @NotBlank String newPassword
    ) {
    }

    @PostMapping("/password/change")
    public ResponseEntity<CustomApiResponse<Void>> changePassword(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody @Validated ChangePasswordRequest request
    ) {
        accountSecurityService.changePassword(UUID.fromString(jwt.getSubject()), request);
        return ResponseEntity.ok(CustomApiResponse.<Void>builder()
                .message("Password changed successfully.")
                .build());
    }

    @PostMapping("/mfa/enable")
    public ResponseEntity<CustomApiResponse<Void>> enableMfa(
            @AuthenticationPrincipal Jwt jwt
    ) {
        accountSecurityService.enableMfa(UUID.fromString(jwt.getSubject()));
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("MFA enabled successfully")
                        .build()
        );
    }

    @PostMapping("/mfa/disable")
    public ResponseEntity<CustomApiResponse<Void>> disableMfa(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody MfaDisableRequest request
    ) {
        accountSecurityService.disableMfa(UUID.fromString(jwt.getSubject()), request);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("MFA disabled successfully")
                        .build()
        );
    }

    @GetMapping("/mfa")
    public ResponseEntity<CustomApiResponse<List<MfaConfigResponse>>> getMfaConfig(
            @AuthenticationPrincipal Jwt jwt
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<List<MfaConfigResponse>>builder()
                        .data(accountSecurityService.getMfaConfigsByUserId(UUID.fromString(jwt.getSubject())))
                        .message("MFA configurations retrieved successfully")
                        .build()
        );
    }

    @PostMapping("/mfa/delete")
    public ResponseEntity<CustomApiResponse<Void>> deleteMfaConfig(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody MfaDeleteRequest request
    ) {
        accountSecurityService.deleteMfaConfig(UUID.fromString(jwt.getSubject()), request);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("MFA configuration deleted successfully")
                        .build()
        );
    }

    @GetMapping("/mfa/backup-codes")
    public ResponseEntity<CustomApiResponse<List<String>>> generateMfaBackupCodes(
            @AuthenticationPrincipal Jwt jwt
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<List<String>>builder()
                        .data(accountSecurityService.generateBackupCodes(UUID.fromString(jwt.getSubject())))
                        .message("MFA backup codes generated successfully")
                        .build()
        );
    }

    @GetMapping("/mfa/totp")
    public ResponseEntity<CustomApiResponse<MfaSetupResponse>> requestTOTP(
            @AuthenticationPrincipal Jwt jwt
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<MfaSetupResponse>builder()
                        .data(mfaConfigService.setupTotpMfa(UUID.fromString(jwt.getSubject())))
                        .message("TOTP MFA setup initiated successfully")
                        .build()
        );
    }

    @PostMapping("/mfa/totp")
    public ResponseEntity<CustomApiResponse<Void>> confirmTOTPSetup(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody TOTPConfirmRequest request
    ) {
        mfaConfigService.confirmTotpMfa(
                UUID.fromString(jwt.getSubject()),
                request
        );
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("TOTP MFA setup confirmed successfully")
                        .build()
        );
    }

    @PostMapping("/mfa/passkey/registration/start")
    public ResponseEntity<CustomApiResponse<PublicKeyCredentialCreationOptions>> startRegistration(
            @AuthenticationPrincipal Jwt jwt
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<PublicKeyCredentialCreationOptions>builder()
                        .data(
                                passkeyService.startRegistration(
                                        UUID.fromString(jwt.getSubject()),
                                        jwt.getSubject(),
                                        jwt.getSubject()
                                )
                        )
                        .message("Passkey registration started successfully")
                        .build()
        );
    }

    @PostMapping("/mfa/passkey/registration/finish")
    public ResponseEntity<CustomApiResponse<Void>> finishRegistration(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody FinishPasskeyRegistrationRequest request
    ) {
        passkeyService.finishRegistration(
                UUID.fromString(jwt.getSubject()),
                request.credential()
        );
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Passkey registration finished successfully")
                        .build()
        );
    }

    @PostMapping("/mfa/email/init")
    public ResponseEntity<CustomApiResponse<MfaInitResponse>> initEmailMfa(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody @Validated MfaEmailInitRequest request
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<MfaInitResponse>builder()
                        .data(accountSecurityService.initEmailMfa(
                                UUID.fromString(jwt.getSubject()),
                                request.email()
                        ))
                        .message("Email MFA initialization successful")
                        .build()
        );
    }

    @PostMapping("/mfa/email/confirm")
    public ResponseEntity<CustomApiResponse<MfaConfigResponse>> confirmEmailMfa(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody @Validated MfaConfirmRequest request
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<MfaConfigResponse>builder()
                        .message("Email MFA confirmed successfully")
                        .data(accountSecurityService.confirmEmailMfa(
                                UUID.fromString(jwt.getSubject()),
                                request
                        ))
                        .build()
        );
    }

    public record ChangePasswordRequest(
            @NotBlank String currentPassword,
            @NotBlank String newPassword
    ) {
    }

    public record MfaDisableRequest(
            @NotBlank String verificationMethod,
            @NotBlank String code
    ) {
    }

    public record MfaDeleteRequest(
            @NotNull UUID configId,
            @NotBlank String verificationMethod, // PASSWORD | TOTP | BACKUP_CODE
            @NotBlank String code
    ) {
    }

    public record FinishPasskeyRegistrationRequest(
            String credential
    ) {
    }

    public record MfaConfirmRequest(
            @NotNull(message = "Challenge ID cannot be null")
            UUID challengeId,
            @NotBlank(message = "Code cannot be blank")
            String code
    ) {
    }

    public record MfaEmailInitRequest(
            @NotBlank(message = "Contact cannot be blank")
            @Email(message = "Invalid email format")
            String email
    ) {
    }


    public record MfaInitResponse(
            UUID challengeId
    ) {
    }

}
