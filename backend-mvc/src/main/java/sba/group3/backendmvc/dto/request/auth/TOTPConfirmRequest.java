package sba.group3.backendmvc.dto.request.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TOTPConfirmRequest {
    String code;
    String secret;
}
