package sba.group3.backendmvc.service.auth;

import sba.group3.backendmvc.controller.auth.AuthController;
import sba.group3.backendmvc.dto.request.auth.LoginRequest;
import sba.group3.backendmvc.dto.request.auth.OAuthLoginRequest;
import sba.group3.backendmvc.dto.response.auth.AuthResponse;
import sba.group3.backendmvc.dto.response.user.MeResponse;

import java.util.UUID;

public interface AuthService {
    AuthResponse login(LoginRequest loginRequest);

    AuthResponse loginWithOAuth(OAuthLoginRequest oAuthLoginRequest);

    AuthResponse refreshToken();

    MeResponse getCurrentUser(UUID userId);

    void logout(UUID userId);

    void requestPasswordReset(AuthController.PasswordResetRequest request);


    void confirmPasswordReset(AuthController.PasswordResetConfirmRequest request);

    AuthResponse verifyMfa(AuthController.MfaVerifyRequest request);
}
