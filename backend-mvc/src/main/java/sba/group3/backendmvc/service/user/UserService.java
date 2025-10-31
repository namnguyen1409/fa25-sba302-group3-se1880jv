package sba.group3.backendmvc.service.user;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.user.UserResponse;

import java.util.UUID;

public interface UserService {
    Page<UserResponse> filter(SearchFilter filter);

    void lockUserAccount(UUID userId);

    void unlockUserAccount(UUID userId);
}
