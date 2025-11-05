package sba.group3.backendmvc.repository.examination;

import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.VitalSign;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.UUID;

public interface VitalSignRepository extends BaseRepository<VitalSign, UUID> {
    VitalSign findByExamination(Examination examination);
}