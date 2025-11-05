package sba.group3.backendmvc.service.organization;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.organization.DepartmentRequest;
import sba.group3.backendmvc.dto.response.organization.ClinicResponse;
import sba.group3.backendmvc.dto.response.organization.DepartmentResponse;

public interface DepartmentService {
    Page<DepartmentResponse> filter(SearchFilter filter);
    Page<DepartmentResponse> filterDepartmentsByClinic(SearchFilter filter);
    DepartmentResponse getDepartmentById(String id);
    DepartmentResponse createDepartment(DepartmentRequest departmentRequest);

    DepartmentResponse updateDepartment(String id, DepartmentRequest departmentRequest);

    void deleteDepartment(String id);
}
