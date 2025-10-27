package sba.group3.backendmvc.controller.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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




}
