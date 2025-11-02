package sba.group3.backendmvc.service.organization;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.organization.ClinicRequest;
import sba.group3.backendmvc.dto.response.organization.ClinicResponse;


public interface ClinicService {
    Page<ClinicResponse> filter(SearchFilter filter);
    ClinicResponse getClinicById(String id);
    ClinicResponse createClinic(ClinicRequest clinic);
    ClinicResponse updateClinic(String id, ClinicRequest clinic);
    void deleteClinic(String id);
}
