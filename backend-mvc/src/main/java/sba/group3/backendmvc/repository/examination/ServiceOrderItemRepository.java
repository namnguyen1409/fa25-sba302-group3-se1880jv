package sba.group3.backendmvc.repository.examination;

import sba.group3.backendmvc.entity.examination.ServiceOrderItem;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceOrderItemRepository extends BaseRepository<ServiceOrderItem, UUID> {
    List<ServiceOrderItem> findAllByServiceOrder_IdAndDeletedIsFalse(UUID serviceOrderId);
}