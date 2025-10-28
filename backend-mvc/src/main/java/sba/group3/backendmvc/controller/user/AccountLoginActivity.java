package sba.group3.backendmvc.controller.user;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sba.group3.backendmvc.dto.filter.Filter;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.auth.LoginAttemptResponse;
import sba.group3.backendmvc.service.auth.LoginAttemptService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/account/login-activity")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Account Login Activity", description = "API for retrieving user login activity")
public class AccountLoginActivity {

    private final LoginAttemptService loginAttemptService;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<LoginAttemptResponse>>> getLoginHistory(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody SearchFilter searchFilter
    ) {
        searchFilter.addMandatoryCondition(
                new Filter("user.id", null, "eq", UUID.fromString(jwt.getSubject()))
        );

        log.info("Filtering user login activity {} with filter: {}", jwt.getSubject(), searchFilter);
        return ResponseEntity
                .ok(
                        CustomApiResponse.<Page<LoginAttemptResponse>>builder()
                                .data(loginAttemptService.filter(searchFilter))
                                .message("Login activity retrieved successfully")
                                .build()
                );
    }


}
