package sba.group3.backendmvc.entity.appointment;

public enum AppointmentStatus {
    PENDING,      // Đang chờ xác nhận
    CONFIRMED,    // Đã xác nhận
    CHECKED_IN,   // Bệnh nhân đã đến
    IN_PROGRESS,  // Đang khám
    COMPLETED,    // Đã khám xong
    CANCELLED,    // Hủy lịch
    NO_SHOW       // Không đến
}
