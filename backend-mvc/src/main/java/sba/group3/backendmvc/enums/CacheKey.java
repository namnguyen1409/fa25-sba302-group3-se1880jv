package sba.group3.backendmvc.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum CacheKey {
    BLACKLISTED_TOKENS("blacklist:%s"),
    USER_PROFILE("user:profile:%s"),
    SYSTEM_CONFIG("system:config"),
    PASSKEY_REGISTRATION_OPTIONS("user:passkey:registration-options:%s"),
    PASSKEY_AUTHENTICATION_OPTIONS("user:passkey:authentication-options:%s"),
    RESET_PASSWORD_TOKEN("reset-password:token:%s");


    String template;

    @NotNull
    @Contract(pure = true)
    public String of(Object... args) {
        return String.format(template, args);
    }
}
