package sba.group3.backendmvc.service.user.impl;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.user.RoleService;
import sba.group3.backendmvc.service.user.UserService;

import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


}
