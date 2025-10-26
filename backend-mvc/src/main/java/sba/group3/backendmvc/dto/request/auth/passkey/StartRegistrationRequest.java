package sba.group3.backendmvc.dto.request.auth.passkey;

import java.util.UUID;

public record StartRegistrationRequest(UUID userId, String username, String displayName) {}

