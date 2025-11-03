package sba.group3.backendmvc.service.patient.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.patient.AllergyRequest;
import sba.group3.backendmvc.dto.response.patient.AllergyResponse;
import sba.group3.backendmvc.mapper.patient.AllergyMapper;
import sba.group3.backendmvc.repository.patient.AllergyRepository;
import sba.group3.backendmvc.repository.patient.PatientRepository;
import sba.group3.backendmvc.service.patient.AllergiesService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class AllergiesServiceImpl implements AllergiesService {

    AllergyRepository allergyRepository;
    AllergyMapper allergyMapper;
    PatientRepository patientRepository;

    @Override
    public Page<AllergyResponse> filter(SearchFilter filter) {
        // Implement the filtering logic here
        return allergyRepository.search(filter).map(allergyMapper::toDto);
    }


    @Override
    public AllergyResponse create(UUID patientId, AllergyRequest request) {
        var patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + patientId));
        var allergy = allergyMapper.toEntity(request);
        allergy.setPatient(patient);
        var savedAllergy = allergyRepository.save(allergy);
        return allergyMapper.toDto(savedAllergy);
    }

    @Override
    public AllergyResponse update(UUID allergyId, AllergyRequest request) {
        var existingAllergy = allergyRepository.findById(allergyId)
                .orElseThrow(() -> new IllegalArgumentException("Allergy not found with id: " + allergyId));
        allergyMapper.partialUpdate(request, existingAllergy);
        return allergyMapper.toDto(allergyRepository.save(existingAllergy));
    }

    @Override
    public void delete(UUID allergyId) {
        var allergy = allergyRepository.findById(allergyId)
                .orElseThrow(() -> new IllegalArgumentException("Allergy not found with id: " + allergyId));
        allergy.setDeleted(true);
        allergyRepository.save(allergy);
    }
}
