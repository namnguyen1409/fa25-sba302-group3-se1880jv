package sba.group3.backendmvc.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sba.group3.backendmvc.dto.response.auth.passkey.StartPasskeyLoginResponse;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class CustomApiResponse<T> {
    @Builder.Default
    int code = 200;
    String message;
    T data;
    @Builder.Default
    Instant timestamp = Instant.now();
    String path;

}