package sba.group3.backendmvc.service.staff.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.StaffRequest;
import sba.group3.backendmvc.dto.response.staff.StaffResponse;
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
import sba.group3.backendmvc.repository.staff.SpecialtyRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.repository.user.RoleRepository;
import sba.group3.backendmvc.repository.user.UserProfileRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.infrastructure.EmailSender;
import sba.group3.backendmvc.service.infrastructure.EmailTemplateService;
import sba.group3.backendmvc.service.staff.StaffService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
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
    private final SpecialtyRepository specialtyRepository;
    private final EmailTemplateService emailTemplateService;

    @Override
    public Page<StaffResponse> filter(SearchFilter filter) {
        return staffRepository.search(filter).map(staffMapper::toDto1);
    }

    @Transactional
    @Override
    public StaffResponse create(StaffRequest request) {

        // 1. Find user by email
        var existingUserOpt = userRepository.findByEmail(request.email());

        User user;
        boolean isNewUser = false;
        String rawPassword = null;

        if (existingUserOpt.isPresent()) {
            user = existingUserOpt.get();

            // User đã tồn tại nhưng đã được gán Staff rồi
            if (staffRepository.existsByUser_Id(user.getId())) {
                throw new AppException(ErrorCode.STAFF_ALREADY_EXISTS_FOR_USER);
            }

        } else {
            // 2. Tạo user mới
            rawPassword = generateRandomPassword(10);

            user = User.builder()
                    .username(request.email())
                    .email(request.email())
                    .password(passwordEncoder.encode(rawPassword))
                    .active(true)
                    .firstLogin(true)
                    .build();

            user = userRepository.save(user);

            // 3. Tạo profile
            var profile = UserProfile.builder()
                    .user(user)
                    .fullName(request.fullName())
                    .phone(request.phone())
                    .build();
            userProfileRepository.save(profile);

            isNewUser = true;
        }

        // 4. Gán role
        String roleName = "ROLE_" + request.staffType().name();
        Role role = roleRepository.findByName(roleName);

        if (role == null) {
            throw new AppException(ErrorCode.ROLE_NOT_FOUND);
        }

        user.getRoles().add(role);
        userRepository.save(user);

        // 5. Tạo staff
        var staff = staffMapper.toEntity(request);
        staff.setUser(user);

        staff.setPosition(positionRepository.getReferenceById(request.positionId()));

        staff.setDepartment(departmentRepository.getReferenceById(request.departmentId()));

        staff.setSpecialty(specialtyRepository.getReferenceById(request.specialtyId()));

        staffRepository.save(staff);

        // 6. Gửi email sau khi transaction commit
        if (isNewUser) {
            String email = user.getEmail();
            String fullName = request.fullName();
            String pwd = rawPassword;

            TransactionSynchronizationManager.registerSynchronization(
                    new TransactionSynchronization() {
                        @Override
                        public void afterCommit() {
                            log.info("Sending account credentials email to {}", email);

                            var model = Map.of(
                                    "fullName", fullName,
                                    "username", email,
                                    "password", pwd
                            );

                            String body = emailTemplateService.render(
                                    "account-created.ftl",
                                    model
                            );

                            emailSender.send(
                                    email,
                                    "Your Account Credentials",
                                    body
                            );
                        }
                    }
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
        return doctors.stream().map(staffMapper::toDto1).toList();
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
