package sba.group3.backendmvc.repository.laboratory;

import sba.group3.backendmvc.entity.laboratory.LabTestResult;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface LabTestResultRepository extends BaseRepository<LabTestResult, UUID> {
    Optional<LabTestResult> findByIdAndLabOrderId(UUID id, UUID labOrderId);
}