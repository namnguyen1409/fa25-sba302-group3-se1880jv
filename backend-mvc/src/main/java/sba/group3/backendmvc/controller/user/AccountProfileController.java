package sba.group3.backendmvc.controller.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.user.UserProfileResponse;
import sba.group3.backendmvc.service.user.AccountProfileService;

import java.util.UUID;

@RestController
@RequestMapping("/api/account/profile")
public class AccountProfileController {

    private final AccountProfileService accountProfileService;

    public AccountProfileController(AccountProfileService accountProfileService) {
        this.accountProfileService = accountProfileService;
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


}
