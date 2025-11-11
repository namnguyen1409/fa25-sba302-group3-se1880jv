package sba.group3.backendmvc.service.pharmacy;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.pharmacy.MedicineRequest;
import sba.group3.backendmvc.dto.response.pharmacy.MedicineResponse;

import java.util.UUID;

public interface MedicineService {
    Page<MedicineResponse> filter(SearchFilter filter);

    MedicineResponse create(MedicineRequest medicineRequest);

    MedicineResponse getById(UUID medicineId);

    MedicineResponse update(UUID medicineId, MedicineRequest medicineRequest);

    void delete(UUID medicineId);
}
