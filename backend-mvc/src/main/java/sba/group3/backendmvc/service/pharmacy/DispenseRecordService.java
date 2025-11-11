package sba.group3.backendmvc.service.pharmacy;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.pharmacy.DispenseRecordRequest;
import sba.group3.backendmvc.dto.response.pharmacy.DispenseRecordResponse;
import sba.group3.backendmvc.entity.examination.Prescription;
import sba.group3.backendmvc.entity.pharmacy.DispenseRecord;

import java.util.List;
import java.util.UUID;

public interface DispenseRecordService {
    Page<DispenseRecordResponse> filter(SearchFilter filter);

    DispenseRecordResponse getById(String recordId);

    DispenseRecordResponse create(DispenseRecordRequest dispenseRecordRequest);

    DispenseRecordResponse update(UUID recordId, DispenseRecordRequest dispenseRecordRequest);

    void delete(UUID recordId);

    DispenseRecord createDispenseRecord(Prescription prescription);

    List<DispenseRecordResponse> filterList(SearchFilter filter);
}
