package sba.group3.backendmvc.entity.appointment;

public enum QueueStatus {
    WAITING,    // Chờ
    CALLED,     // Đang gọi
    IN_SERVICE, // Đang được khám
    SKIPPED,    // Bỏ qua
    DONE,       // Hoàn tất
    CANCELLED   // Hủy
}
