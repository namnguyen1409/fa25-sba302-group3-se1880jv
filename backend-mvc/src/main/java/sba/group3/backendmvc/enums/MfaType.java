package sba.group3.backendmvc.enums;

/**
 * Các phương thức xác thực đa yếu tố (MFA) được hỗ trợ.
 */
public enum MfaType {
    TOTP,
    SMS,
    EMAIL,
    PUSH_NOTIFICATION,
    PASSKEY,
    EMAIL_VERIFICATION,

}
