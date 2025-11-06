// Tất cả enum map để dịch ra tiếng Việt
// Mở rộng thoải mái

export const ENUM_TRANSLATIONS = {
  roomType: {
    CONSULTATION: "Khám tổng quát",
    XRAY: "X-quang",
    ULTRASOUND: "Siêu âm",
    LABORATORY: "Xét nghiệm",
    PROCEDURE: "Thủ thuật",
    PHARMACY: "Nhà thuốc",
    CASHIER: "Thu ngân",
    RECEPTION: "Lễ tân",
    WAITING_AREA: "Khu vực chờ",
    ENDOSCOPY: "Nội soi",
    ECG: "Điện tim (ECG)",
    EEG: "Điện não (EEG)",
    DEXA: "Đo loãng xương (DEXA)",
    CT_SCAN: "Chụp CT",
    MRI: "Chụp MRI",
    OPTOMETRY: "Đo mắt / khúc xạ",
    VACCINATION: "Phòng tiêm chủng",
    PHYSIOTHERAPY: "Vật lý trị liệu",
    PFT: "Đo chức năng hô hấp",
    MINOR_SURGERY: "Phòng tiểu phẫu",
  },

  gender: {
    MALE: "Nam",
    FEMALE: "Nữ",
    OTHER: "Khác",
  },

  bloodType: {
    O_POSITIVE: "O+",
    O_NEGATIVE: "O−",
    A_POSITIVE: "A+",
    A_NEGATIVE: "A−",
    B_POSITIVE: "B+",
    B_NEGATIVE: "B−",
    AB_POSITIVE: "AB+",
    AB_NEGATIVE: "AB−",
  },

  queueStatus: {
    WAITING: "Đang chờ",
    CALLED: "Đã gọi",
    SERVING: "Đang phục vụ",
    FINISHED: "Hoàn tất",
    CANCELLED: "Đã hủy",
  },

  paymentStatus: {
    PAID: "Đã thanh toán",
    UNPAID: "Chưa thanh toán",
    DEBT: "Công nợ",
  },
};
