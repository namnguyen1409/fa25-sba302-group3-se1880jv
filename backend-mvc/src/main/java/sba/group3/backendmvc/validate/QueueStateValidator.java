package sba.group3.backendmvc.validate;

import org.springframework.stereotype.Component;
import sba.group3.backendmvc.entity.appointment.QueueStatus;
import sba.group3.backendmvc.entity.appointment.QueueTicket;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;

@Component
public class QueueStateValidator {

    public void ensureCanCall(QueueTicket t) {
        if (t.getStatus() != QueueStatus.WAITING)
            throw new AppException(ErrorCode.BAD_REQUEST, "Chỉ có thể gọi bệnh nhân đang ở trạng thái WAITING.");
    }

    public void ensureCanStart(QueueTicket t) {
        if (!(t.getStatus() == QueueStatus.CALLED || t.getStatus() == QueueStatus.WAITING_AFTER_RESULT))
            throw new AppException(ErrorCode.BAD_REQUEST, "Chỉ bắt đầu khám khi bệnh nhân đang ở trạng thái CALLED.");
    }

    public void ensureCanSkip(QueueTicket t) {
        if (t.getStatus() != QueueStatus.WAITING &&
                t.getStatus() != QueueStatus.CALLED)
            throw new AppException(ErrorCode.BAD_REQUEST, "Chỉ có thể bỏ qua bệnh nhân khi đang WAITING hoặc CALLED.");
    }

    public void ensureCanRequeue(QueueTicket t) {
        if (t.getStatus() != QueueStatus.SKIPPED)
            throw new AppException(ErrorCode.BAD_REQUEST, "Chỉ đưa lại queue nếu đang ở trạng thái SKIPPED.");
    }

    public void ensureCanWaitResult(QueueTicket t) {
        if (t.getStatus() != QueueStatus.IN_SERVICE)
            throw new AppException(ErrorCode.BAD_REQUEST, "Chỉ chuyển sang chờ kết quả khi đang khám (IN_SERVICE).");
    }

    public void ensureCanResume(QueueTicket t) {
        if (t.getStatus() != QueueStatus.IN_SERVICE_WAITING_RESULT)
            throw new AppException(ErrorCode.BAD_REQUEST, "Chỉ tiếp tục khám khi đang chờ kết quả.");
    }

    public void ensureCanDone(QueueTicket t) {
        if (t.getStatus() != QueueStatus.IN_SERVICE &&
                t.getStatus() != QueueStatus.IN_SERVICE_WAITING_RESULT)
            throw new AppException(ErrorCode.BAD_REQUEST, "Chỉ hoàn tất khám khi đang IN_SERVICE hoặc IN_SERVICE_WAITING_RESULT.");
    }
}
