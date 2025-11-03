package sba.group3.backendmvc.service.patient.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.patient.EmergencyContactRequest;
import sba.group3.backendmvc.dto.response.patient.EmergencyContactResponse;
import sba.group3.backendmvc.mapper.patient.EmergencyContactMapper;
import sba.group3.backendmvc.repository.patient.EmergencyContactRepository;
import sba.group3.backendmvc.repository.patient.PatientRepository;
import sba.group3.backendmvc.service.patient.EmergencyService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class EmergencyServiceImpl implements EmergencyService {
    EmergencyContactRepository repository;
    private final EmergencyContactMapper emergencyContactMapper;
    private final PatientRepository patientRepository;

    @Override
    public Page<EmergencyContactResponse> filter(SearchFilter filter) {
        return repository.search(filter).map(emergencyContactMapper::toDto);
    }

    @Override
    public EmergencyContactResponse create(UUID patientId, EmergencyContactRequest request) {
        var patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found with id: " + patientId));
        var emergencyContact = emergencyContactMapper.toEntity(request);
        emergencyContact.setPatient(patient);
        var savedContact = repository.save(emergencyContact);
        return emergencyContactMapper.toDto(savedContact);
    }

    @Override
    public EmergencyContactResponse update(UUID contactId, EmergencyContactRequest request) {
        var existingContact = repository.findById(contactId)
                .orElseThrow(() -> new IllegalArgumentException("Emergency contact not found with id: " + contactId));
        emergencyContactMapper.partialUpdate(request, existingContact);
        return emergencyContactMapper.toDto(repository.save(existingContact));
    }

    @Override
    public void delete(UUID contactId) {
        var contact = repository.findById(contactId)
                .orElseThrow(() -> new IllegalArgumentException("Emergency contact not found with id: " + contactId));
        contact.setDeleted(true);
        repository.save(contact);
    }
}
