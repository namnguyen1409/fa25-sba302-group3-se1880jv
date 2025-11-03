package sba.group3.backendmvc.service.patient.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.patient.PatientRequest;
import sba.group3.backendmvc.dto.response.patient.PatientResponse;
import sba.group3.backendmvc.mapper.patient.PatientMapper;
import sba.group3.backendmvc.repository.patient.PatientRepository;
import sba.group3.backendmvc.service.patient.PatientService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class PatientServiceImpl implements PatientService {

    PatientRepository patientRepository;
    PatientMapper patientMapper;

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

        var patient = patientMapper.toEntity(request);
        patient.setPatientCode(generatePatientCode());
        patient.setInitPassword(generateRandomPassword());
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

    private String generatePatientCode() {

        return "P-" + System.currentTimeMillis();
    }

    private String generateRandomPassword() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789@#$%";

        StringBuilder password = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = (int) (Math.random() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }

}
