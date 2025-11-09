package sba.group3.backendmvc.service.examination;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ExaminationRequest;
import sba.group3.backendmvc.dto.response.examination.ExaminationResponse;
import sba.group3.backendmvc.entity.appointment.QueueTicket;
import sba.group3.backendmvc.entity.examination.Examination;

import java.util.List;
import java.util.UUID;

public interface ExaminationService {
    Page<ExaminationResponse> filter(SearchFilter filter);

    ExaminationResponse create(ExaminationRequest request);
    ExaminationResponse getExaminationDetail(String id);

    ExaminationResponse update(String id, ExaminationRequest request);

    void delete(String id);

    Examination createFromQueueTicket(QueueTicket ticket);

    void handleOrderStatusChanged(UUID examId);
}
