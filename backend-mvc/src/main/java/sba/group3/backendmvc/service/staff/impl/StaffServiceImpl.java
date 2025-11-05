package sba.group3.backendmvc.service.staff.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.StaffRequest;
import sba.group3.backendmvc.dto.response.staff.StaffResponse;
import sba.group3.backendmvc.entity.organization.Department;
import sba.group3.backendmvc.entity.staff.Staff;
import sba.group3.backendmvc.entity.user.Role;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.entity.user.UserProfile;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.mapper.staff.StaffMapper;
import sba.group3.backendmvc.repository.appointment.QueueTicketRepository;
import sba.group3.backendmvc.repository.organization.DepartmentRepository;
import sba.group3.backendmvc.repository.staff.PositionRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.repository.user.RoleRepository;
import sba.group3.backendmvc.repository.user.UserProfileRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.infrastructure.EmailSender;
import sba.group3.backendmvc.service.staff.StaffService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffServiceImpl implements StaffService {
    StaffRepository staffRepository;
    StaffMapper staffMapper;
    PositionRepository positionRepository;
    DepartmentRepository departmentRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    UserProfileRepository userProfileRepository;
    RoleRepository roleRepository;
    EmailSender emailSender;
    private final QueueTicketRepository queueTicketRepository;

    @Override
    public Page<StaffResponse> filter(SearchFilter filter) {
        return staffRepository.search(filter).map(staffMapper::toDto1);
    }

    @Override
    public StaffResponse create(StaffRequest request) {
        var existingUserOpt = userRepository.findByEmail(request.email());

        User user;
        boolean isNewUser = false;
        String rawPassword = generateRandomPassword(10);
        if (existingUserOpt.isPresent()) {
            user = existingUserOpt.get();

            if (staffRepository.existsByUser_Id(user.getId())) {
                throw new AppException(ErrorCode.UNCATEGORIZED);
            }

        } else {
            user = User.builder()
                    .username(request.email())
                    .email(request.email())
                    .password(passwordEncoder.encode(rawPassword))
                    .active(true)
                    .firstLogin(true)
                    .build();

            user = userRepository.save(user);

            UserProfile profile = UserProfile.builder()
                    .user(user)
                    .fullName(request.fullName())
                    .phone(request.phone())
                    .build();
            userProfileRepository.save(profile);
            isNewUser = true;
        }

        var roleName = "ROLE_" + request.staffType().name();
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);

        var staff = staffMapper.toEntity(request);
        staff.setUser(user);
        var position = positionRepository.getReferenceById(request.positionId());
        staff.setPosition(position);
        Department department = departmentRepository.getReferenceById(request.departmentId());
        staff.setDepartment(department);
        staffRepository.save(staff);
        if (isNewUser) {
            emailSender.send(
                    user.getEmail(),
                    "Your Account Credentials",
                    "Dear " + request.fullName() + ",\n\n" +
                            "An account has been created for you.\n" +
                            "Username: " + user.getUsername() + "\n" +
                            "Password: " + rawPassword + "\n\n" +
                            "Please change your password upon first login.\n\n" +
                            "Best regards,\n" +
                            "Medical Staff Management Team"
            );
        }
        return staffMapper.toDto1(staff);
    }

    @Override
    public StaffResponse update(UUID id, StaffRequest request) {
        var entity = staffRepository.findById(id).orElseThrow();
        staffMapper.partialUpdate(request, entity);
        if (request.positionId() != null) {
            entity.setPosition(positionRepository.getReferenceById(request.positionId()));
        }
        if (request.departmentId() != null) {
            entity.setDepartment(departmentRepository.getReferenceById(request.departmentId()));
        }
        var updatedEntity = staffRepository.save(entity);
        return staffMapper.toDto1(updatedEntity);
    }

    @Override
    public void delete(UUID id) {
        var entity = staffRepository.findById(id).orElseThrow();
        entity.setDeleted(true);
        staffRepository.save(entity);
    }

    @Override
    public StaffResponse getById(UUID id) {
        var entity = staffRepository.findById(id).orElseThrow();
        return staffMapper.toDto1(entity);
    }

    @Override
    public List<StaffResponse> findDoctorsBySpecialty(UUID specialtyId) {
        var doctors = staffRepository.findBySpecialty_IdAndStaffType(specialtyId, sba.group3.backendmvc.entity.staff.StaffType.DOCTOR);
        return doctors.stream().map(staffMapper::toDto1).collect(Collectors.toList());
    }

    @Override
    public List<StaffResponse> findAvailableDoctors(UUID specialtyId, DayOfWeek day, LocalTime time) {
        // Nếu trong giờ → lấy bác sĩ đang trực
        var activeDoctors = staffRepository.findAvailableDoctors(specialtyId, day, time);

        if (!activeDoctors.isEmpty()) {
            return activeDoctors.stream()
                    .map(staffMapper::toDto1)
                    .toList();
        }

        // Nếu đến sớm → lấy bác sĩ có lịch hôm nay, không check giờ
        var scheduledDoctors = staffRepository.findDoctorsScheduledToday(specialtyId, day);
        return scheduledDoctors.stream()
                .map(staffMapper::toDto1)
                .toList();
    }

    @Override
    public StaffResponse autoPickDoctor(UUID specialtyId) {
        var day = LocalDate.now().getDayOfWeek();
        var now = LocalTime.now();

        // 1) Thử lấy bác sĩ đang trực ngay lúc này
        var activeDoctors = staffRepository.findAvailableDoctors(specialtyId, day, now);

        List<Staff> candidates;

        if (!activeDoctors.isEmpty()) {
            candidates = activeDoctors;
        } else {
            // 2) Không có bác sĩ đang trực → có thể bệnh nhân đến sớm
            var scheduledDoctors = staffRepository.findDoctorsScheduledToday(specialtyId, day);

            if (scheduledDoctors.isEmpty()) {
                throw new AppException(ErrorCode.RESOURCE_NOT_FOUND,
                        "Không có bác sĩ trực cho chuyên khoa này hôm nay");
            }

            // Lọc bác sĩ còn ca (không lấy người đã hết giờ)
            candidates = scheduledDoctors.stream()
                    .filter(doc -> staffRepository.isDoctorStillWorkingToday(doc.getId(), day, now))
                    .toList();

            if (candidates.isEmpty()) {
                throw new AppException(ErrorCode.BAD_REQUEST,
                        "Tất cả bác sĩ đã kết thúc ca trực. Vui lòng chọn ca khác.");
            }

        }

        // 3) Pick bác sĩ có ít bệnh nhân nhất hôm nay
        var selected = candidates.stream()
                .min(Comparator.comparing(doc -> queueTicketRepository.countWaitingTodayByDoctor(doc.getId())))
                .orElseThrow(() -> new AppException(ErrorCode.RESOURCE_NOT_FOUND));

        return staffMapper.toDto1(selected);
    }


    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%&*!";
        StringBuilder password = new StringBuilder();
        java.util.Random random = new java.util.Random();
        for (int i = 0; i < length; i++) {
            password.append(chars.charAt(random.nextInt(chars.length())));
        }
        return password.toString();
    }
}
