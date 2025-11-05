package sba.group3.backendmvc.repository.examination;

import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.ServiceOrder;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.UUID;

public interface ServiceOrderRepository extends BaseRepository<ServiceOrder, UUID> {
    ServiceOrder findByExamination(Examination examination);
}