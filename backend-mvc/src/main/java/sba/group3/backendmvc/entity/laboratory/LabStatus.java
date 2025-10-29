package sba.group3.backendmvc.entity.laboratory;

public enum LabStatus {
    PENDING,      // Chờ xử lý
    IN_PROGRESS,  // Đang thực hiện
    COMPLETED,    // Đã có kết quả
    VERIFIED,     // Đã kiểm duyệt
    CANCELLED     // Hủy
}
