package sba.group3.backendmvc.entity.appointment;

public enum AppointmentSource {
    SYSTEM,         // Đặt qua hệ thống nội bộ
    STAFF_CREATED,  // Nhân viên tạo
    PATIENT_PORTAL, // Bệnh nhân tự đặt
    MOBILE_APP,     // App mobile
    WALK_IN         // Đến trực tiếp
}
