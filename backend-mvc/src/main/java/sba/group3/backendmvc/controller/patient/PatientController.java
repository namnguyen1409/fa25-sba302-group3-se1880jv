package sba.group3.backendmvc.controller.patient;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.Filter;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.patient.AllergyRequest;
import sba.group3.backendmvc.dto.request.patient.EmergencyContactRequest;
import sba.group3.backendmvc.dto.request.patient.PatientRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.patient.AllergyResponse;
import sba.group3.backendmvc.dto.response.patient.EmergencyContactResponse;
import sba.group3.backendmvc.dto.response.patient.PatientResponse;
import sba.group3.backendmvc.service.patient.AllergiesService;
import sba.group3.backendmvc.service.patient.EmergencyService;
import sba.group3.backendmvc.service.patient.PatientService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Patient Management", description = "APIs for managing patient")
public class PatientController {
    PatientService patientService;
    AllergiesService allergiesService;
    EmergencyService emergencyService;


    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<PatientResponse>>> filter(
            @RequestBody SearchFilter filter) {
        log.info("Filtering users by admin {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<PatientResponse>>builder()
                        .data(patientService.filter(filter))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<PatientResponse>> createPatient(
            @RequestBody @Validated PatientRequest request
    ) {
        log.info("Creating new patient with data: {}", request);
        return ResponseEntity.ok(
                CustomApiResponse.<PatientResponse>builder()
                        .data(patientService.create(request))
                        .message("Patient created successfully")
                        .build()
        );
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<CustomApiResponse<PatientResponse>> updatePatient(
            @PathVariable UUID patientId,
            @RequestBody @Validated PatientRequest request
    ) {
        log.info("Updating patient with id: {} and data: {}", patientId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<PatientResponse>builder()
                        .data(patientService.update(patientId, request))
                        .message("Patient updated successfully")
                        .build()
        );
    }

    @DeleteMapping("/{patientId}")
    public ResponseEntity<CustomApiResponse<Void>> deletePatient(
            @PathVariable UUID patientId
    ) {
        log.info("Deleting patient with id: {}", patientId);

        patientService.delete(patientId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Patient deletion is not implemented yet")
                        .build()
        );
    }

    @GetMapping("/{patientId}")
    public ResponseEntity<CustomApiResponse<PatientResponse>> getPatientById(
            @PathVariable UUID patientId
    ) {
        log.info("Retrieving patient with id: {}", patientId);
        return ResponseEntity.ok(
                CustomApiResponse.<PatientResponse>builder()
                        .data(patientService.getById(patientId))
                        .build()
        );
    }

    @GetMapping("/check")
    public ResponseEntity<CustomApiResponse<Boolean>> checkPatientExistence(
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email
    ) {
        log.info("Checking existence of patient with phone: {} or email: {}", phone, email);
        return ResponseEntity.ok(
                CustomApiResponse.<Boolean>builder()
                        .data(patientService.existsByPhoneOrEmail(phone, email))
                        .build()
        );
    }

    @PostMapping("/{patientId}/allergies/filter")
    public ResponseEntity<CustomApiResponse<Page<AllergyResponse>>> filterAllergies(
            @RequestBody SearchFilter filter,
            @PathVariable UUID patientId
            ) {
        log.info("Filtering allergies for patient with filter: {}", filter);
        filter.addMandatoryCondition(new Filter("patient.id",
                null, "eq", patientId));

        return ResponseEntity.ok(
                CustomApiResponse.<Page<AllergyResponse>>builder()
                        .data(allergiesService.filter(filter))
                        .build()
        );
    }

    @PostMapping("/{patientId}/allergies")
    public ResponseEntity<CustomApiResponse<AllergyResponse>> createAllergy(
            @PathVariable UUID patientId,
            @RequestBody @Valid AllergyRequest request
    ) {
        log.info("Creating allergy for patient with id: {} and data: {}", patientId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<AllergyResponse>builder()
                        .data(allergiesService.create(patientId, request))
                        .message("Allergy created successfully")
                        .build()
        );
    }

    @PutMapping("allergies/{allergyId}")
    public ResponseEntity<CustomApiResponse<AllergyResponse>> updateAllergy(
            @PathVariable UUID allergyId,
            @RequestBody @Valid AllergyRequest request
    ) {
        log.info("Updating allergy with id: {} and data: {}", allergyId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<AllergyResponse>builder()
                        .data(allergiesService.update(allergyId, request))
                        .message("Allergy updated successfully")
                        .build()
        );
    }

    @DeleteMapping("/allergies/{allergyId}")
    public ResponseEntity<CustomApiResponse<Void>> deleteAllergy(
            @PathVariable UUID allergyId
    ) {
        log.info("Deleting allergy with id: {}", allergyId);
        allergiesService.delete(allergyId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Allergy deleted successfully")
                        .build()
        );
    }

    @PostMapping("/{patientId}/emergency-contacts/filter")
    public ResponseEntity<CustomApiResponse<Page<EmergencyContactResponse>>> filterEmergencyContacts(
            @RequestBody SearchFilter filter,
            @PathVariable UUID patientId
    ) {
        log.info("Filtering emergency contacts for patient with filter: {}", filter);
        filter.addMandatoryCondition(new Filter("patient.id",
                null, "eq", patientId));

        return ResponseEntity.ok(
                CustomApiResponse.<Page<EmergencyContactResponse>>builder()
                        .data(emergencyService.filter(filter))
                        .build()
        );
    }

    @PostMapping("{patientId}/emergency-contacts")
    public ResponseEntity<CustomApiResponse<EmergencyContactResponse>> createEmergencyContact(
            @PathVariable UUID patientId,
            @RequestBody @Valid EmergencyContactRequest request
    ) {
        log.info("Creating emergency contact for patient with id: {} and data: {}", patientId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<EmergencyContactResponse>builder()
                        .data(emergencyService.create(patientId, request))
                        .message("Emergency contact created successfully")
                        .build()
        );
    }

    @PutMapping("/emergency-contacts/{contactId}")
    public ResponseEntity<CustomApiResponse<EmergencyContactResponse>> updateEmergencyContact(
            @PathVariable UUID contactId,
            @RequestBody @Valid EmergencyContactRequest request
    ) {
        log.info("Updating emergency contact with id: {} and data: {}", contactId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<EmergencyContactResponse>builder()
                        .data(emergencyService.update(contactId, request))
                        .message("Emergency contact updated successfully")
                        .build()
        );
    }

    @DeleteMapping("/emergency-contacts/{contactId}")
    public ResponseEntity<CustomApiResponse<Void>> deleteEmergencyContact(
            @PathVariable UUID contactId
    ) {
        log.info("Deleting emergency contact with id: {}", contactId);
        emergencyService.delete(contactId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Emergency contact deleted successfully")
                        .build()
        );
    }



}
