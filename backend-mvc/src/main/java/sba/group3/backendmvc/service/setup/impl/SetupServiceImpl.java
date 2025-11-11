package sba.group3.backendmvc.service.setup.impl;

import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.entity.common.Address;
import sba.group3.backendmvc.entity.organization.Clinic;
import sba.group3.backendmvc.entity.organization.Department;
import sba.group3.backendmvc.entity.staff.Specialty;
import sba.group3.backendmvc.entity.staff.Staff;
import sba.group3.backendmvc.entity.staff.StaffType;
import sba.group3.backendmvc.entity.user.Role;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.entity.user.UserProfile;
import sba.group3.backendmvc.repository.organization.ClinicRepository;
import sba.group3.backendmvc.repository.organization.DepartmentRepository;
import sba.group3.backendmvc.repository.organization.RoomRepository;
import sba.group3.backendmvc.repository.staff.SpecialtyRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.repository.user.RoleRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.setup.JsonSeedLoader;
import sba.group3.backendmvc.service.setup.SetupService;
import sba.group3.backendmvc.service.user.RoleService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SetupServiceImpl implements SetupService {


    RoleService roleService;
    RoleRepository roleRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    ClinicRepository clinicRepository;
    StaffRepository staffRepository;
    DepartmentRepository departmentRepository;
    RoomRepository roomRepository;
    SpecialtyRepository specialtyRepository;
    JsonSeedLoader jsonSeedLoader;

    public record DepartmentSeed(String name, String clinic, String description) {}
    public record SpecialtySeed(String name, String description, String department) {}
    public record StaffSeed(
            String username,
            String password,
            String fullName,
            String email,
            String phone,
            String staffType,
            String department,
            String specialty,
            String licenseNumber,
            Integer experienceYears
    ) {}


    List<Role> roles = List.of(
            Role.builder().name("ROLE_SYSTEM_ADMIN").build(),
            Role.builder().name("ROLE_PATIENT").isDefault(true).build(),
            Role.builder().name("ROLE_DOCTOR").build(),
            Role.builder().name("ROLE_NURSE").build(),
            Role.builder().name("ROLE_TECHNICIAN").build(),
            Role.builder().name("ROLE_LAB_TECHNICIAN").build(),
            Role.builder().name("ROLE_PHARMACIST").build(),
            Role.builder().name("ROLE_RECEPTIONIST").build(),
            Role.builder().name("ROLE_CASHIER").build(),
            Role.builder().name("ROLE_MANAGER").build()
    );

    @Transactional
    @PostConstruct
    public void init() {
        if (roleRepository.count() == 0) {
            roleRepository.saveAll(roles);
        }
        if (clinicRepository.count() == 0) {
            clinicRepository.save(
                    Clinic.builder()
                            .name("Group 3 Clinic")
                            .address(
                                    Address.builder()
                                            .street("123 Main St")
                                            .wardName("Ward 1")
                                            .city("Anytown")
                                            .build()
                            )
                            .phone("0123456789")
                            .email("group3clinic@gmail.com")
                            .build()
            );
        }
        seedDepartments();
        seedSpecialties();
        seedStaff();
    }

    private void seedDepartments() {
        if (departmentRepository.count() > 0) return;

        var seeds = jsonSeedLoader.load("seed/departments.json", DepartmentSeed.class);
        var entities = seeds.stream()
                .map(s -> Department.builder()
                        .clinic(clinicRepository.findByName(s.clinic))
                        .name(s.name)
                        .description(s.description)
                        .build())
                .toList();

        departmentRepository.saveAll(entities);
    }

    private void seedSpecialties() {
        if (specialtyRepository.count() > 0) return;

        var seeds = jsonSeedLoader.load("seed/specialties.json", SpecialtySeed.class);

        var entities = seeds.stream()
                .map(s -> Specialty.builder()
                        .name(s.name())
                        .description(s.description())
                        .department(departmentRepository.findByName(s.department()))
                        .build())
                .toList();

        specialtyRepository.saveAll(entities);
    }

    private void seedStaff() {
        if (staffRepository.count() > 0) return;

        var seeds = jsonSeedLoader.load("seed/staff.json", StaffSeed.class);

        for (var s : seeds) {
            if (userRepository.existsByUsername(s.username())) continue;

            var dept = departmentRepository.findByName(s.department());
            var sp   = specialtyRepository.findByName(s.specialty());

            var profile = UserProfile.builder()
                    .fullName(s.fullName())
                    .phone(s.phone())
                    .dateOfBirth(LocalDate.of(1990, 1, 1))
                    .build();

            var user = User.builder()
                    .username(s.username())
                    .password(passwordEncoder.encode(s.password()))
                    .email(s.email())
                    .active(true)
                    .locked(false)
                    .firstLogin(true)
                    .roles(Set.of(roleService.findByName("ROLE_" + s.staffType())))
                    .userProfile(profile)
                    .build();
            profile.setUser(user);
            userRepository.save(user);

            var staff = Staff.builder()
                    .fullName(s.fullName())
                    .email(s.email())
                    .phone(s.phone())
                    .staffType(StaffType.valueOf(s.staffType()))
                    .department(dept)
                    .specialty(sp)
                    .licenseNumber(s.licenseNumber())
                    .experienceYears(s.experienceYears())
                    .user(user)
                    .joinedDate(LocalDate.now().minusYears(1))
                    .build();

            staffRepository.save(staff);
        }
    }

}
