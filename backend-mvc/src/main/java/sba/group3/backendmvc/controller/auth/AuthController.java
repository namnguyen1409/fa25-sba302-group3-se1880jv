package sba.group3.backendmvc.controller.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sba.group3.backendmvc.dto.request.auth.LoginRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.auth.AuthResponse;

@RestController("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<CustomApiResponse<AuthResponse>> login(
            @RequestBody @Validated LoginRequest loginRequest
    ) {
        return null;
    }


}
