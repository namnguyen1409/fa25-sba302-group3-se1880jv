package sba.group3.backendmvc.service.patient.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.patient.PatientRequest;
import sba.group3.backendmvc.dto.response.patient.PatientResponse;
import sba.group3.backendmvc.entity.organization.Department;
import sba.group3.backendmvc.entity.user.Role;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.entity.user.UserProfile;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.mapper.patient.PatientMapper;
import sba.group3.backendmvc.repository.patient.PatientRepository;
import sba.group3.backendmvc.repository.user.RoleRepository;
import sba.group3.backendmvc.repository.user.UserProfileRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.infrastructure.EmailSender;
import sba.group3.backendmvc.service.patient.PatientService;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PatientServiceImpl implements PatientService {

    PatientRepository patientRepository;
    PatientMapper patientMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserProfileRepository userProfileRepository;
    private final RoleRepository roleRepository;
    private final EmailSender emailSender;

    @Override
    public Page<PatientResponse> filter(SearchFilter filter) {
        return patientRepository.search(filter).map(patientMapper::toDto);
    }

    @Override
    public PatientResponse getById(UUID patientId) {
        var patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + patientId));
        return patientMapper.toDto(patient);
    }

    @Override
    public Boolean existsByPhoneOrEmail(String phone, String email) {
        return patientRepository.existsByPhoneOrEmail(phone, email);
    }

    @Override
    public PatientResponse create(PatientRequest request) {
        var existingUserOpt = userRepository.findByEmail(request.email());

        User user;
        boolean isNewUser = false;
        String rawPassword = generateRandomPassword(10);
        if (existingUserOpt.isPresent()) {
            user = existingUserOpt.get();

            if (patientRepository.existsByUser_Id(user.getId())) {
                throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
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

        var roleName = "ROLE_PATIENT";
        Role role = roleRepository.findByName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);

        var patient = patientMapper.toEntity(request);
        patient.setUser(user);
        patient.setPatientCode(generateCode());
        patient.setInitPassword(rawPassword);
        if (isNewUser) {
            log.info("New user created: {}", user.getEmail());
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
        var savedPatient = patientRepository.save(patient);
        return patientMapper.toDto(savedPatient);
    }

    @Override
    public PatientResponse update(UUID patientId, PatientRequest request) {
        var existingPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + patientId));
        patientMapper.partialUpdate(request, existingPatient);

        return patientMapper.toDto(patientRepository.save(existingPatient));
    }

    @Override
    public void delete(UUID patientId) {
        var existingPatient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + patientId));
        existingPatient.setDeleted(true);
        patientRepository.save(existingPatient);
    }

    public String generateCode() {
        long count = patientRepository.count();
        return "PT-" + String.format("%06d", count + 1);
    }

    private String generateRandomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }

}
