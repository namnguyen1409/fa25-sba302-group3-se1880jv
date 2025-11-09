package sba.group3.backendmvc.entity.appointment;

public enum QueueStatus {
    WAITING,    // Chờ
    CALLED,     // Đang gọi
    IN_SERVICE, // Đang được khám
    IN_SERVICE_WAITING_RESULT, // Đang chờ kết quả xét nghiệm/ chẩn đoán hình ảnh ...
    WAITING_AFTER_RESULT,
    SKIPPED,    // Bỏ qua
    DONE,       // Hoàn tất
    CANCELLED   // Hủy
}
