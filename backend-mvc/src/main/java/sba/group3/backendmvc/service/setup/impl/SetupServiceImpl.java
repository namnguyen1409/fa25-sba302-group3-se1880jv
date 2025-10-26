package sba.group3.backendmvc.service.setup.impl;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.entity.user.Role;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.repository.user.RoleRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.setup.SetupService;
import sba.group3.backendmvc.service.user.RoleService;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SetupServiceImpl implements SetupService {

    List<Role> roles = List.of(
            Role.builder()
                    .name("ROLE_ADMIN")
                    .build(),
            Role.builder()
                    .name("ROLE_PATIENT")
                    .isDefault(true)
                    .build(),
            Role.builder()
                    .name("ROLE_DOCTOR")
                    .build(),
            Role.builder()
                    .name("ROLE_NURSE")
                    .build(),
            Role.builder()
                    .name("ROLE_RECEPTIONIST")
                    .build()
    );

    RoleRepository roleRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    RoleService roleService;

    @Transactional
    @PostConstruct
    public void init() {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(roles);
        }
        if (userRepository.count() == 0) {
            var admin = User.builder()
                    .firstLogin(true)
                    .username("admin")
                    .password(passwordEncoder.encode("admin"))
                    .email("admin@gmail.com")
                    .active(true)
                    .locked(false)
                    .mfaEnabled(false)
                    .roles(
                            Set.of(roleService.findByName("ROLE_ADMIN"))
                    )
                    .phone("0123456789")
                    .build();
            userRepository.save(admin);
        }
    }


}
