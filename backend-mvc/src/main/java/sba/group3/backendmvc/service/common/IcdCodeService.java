package sba.group3.backendmvc.service.common;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.common.IcdCodeRequest;
import sba.group3.backendmvc.dto.response.common.IcdCodeResponse;

import java.util.UUID;

public interface IcdCodeService {
    Page<IcdCodeResponse> filter(SearchFilter filter);

    IcdCodeResponse getById(UUID id);

    IcdCodeResponse save(IcdCodeRequest icdCode);

    IcdCodeResponse update(UUID id, IcdCodeRequest icdCode);

    void delete(UUID id);
}
