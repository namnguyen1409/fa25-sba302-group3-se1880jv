package sba.group3.backendmvc.service.pharmacy;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.pharmacy.DispenseRecordRequest;
import sba.group3.backendmvc.dto.response.pharmacy.DispenseRecordResponse;

import java.util.UUID;

public interface DispenseRecordService {
    Page<DispenseRecordResponse> filter(SearchFilter filter);

    DispenseRecordResponse getById(String recordId);

    DispenseRecordResponse create(DispenseRecordRequest dispenseRecordRequest);

    DispenseRecordResponse update(UUID recordId, DispenseRecordRequest dispenseRecordRequest);

    void delete(UUID recordId);
}
