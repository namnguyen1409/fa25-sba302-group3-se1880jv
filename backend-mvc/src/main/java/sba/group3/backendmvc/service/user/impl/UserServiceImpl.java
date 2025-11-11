package sba.group3.backendmvc.service.user.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.user.UserResponse;
import sba.group3.backendmvc.mapper.user.UserMapper;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.user.RoleService;
import sba.group3.backendmvc.service.user.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    @Override
    public Page<UserResponse> filter(SearchFilter filter) {
        return userRepository.search(filter).map(userMapper::toDto2);
    }

    @Transactional
    @Override
    public void lockUserAccount(UUID userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        user.setLocked(true);
        userRepository.save(user);

    }

    @Transactional
    @Override
    public void unlockUserAccount(UUID userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        user.setLocked(false);
        userRepository.save(user);
    }

}
