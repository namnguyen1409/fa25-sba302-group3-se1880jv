package sba.group3.backendmvc.service.auth;

import sba.group3.backendmvc.dto.request.auth.LoginRequest;
import sba.group3.backendmvc.dto.request.auth.OAuthLoginRequest;
import sba.group3.backendmvc.dto.response.auth.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);

    AuthResponse loginWithOAuth(OAuthLoginRequest oAuthLoginRequest);

    AuthResponse refreshToken();
}
