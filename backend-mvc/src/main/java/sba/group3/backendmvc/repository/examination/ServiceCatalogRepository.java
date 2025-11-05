package sba.group3.backendmvc.repository.examination;

import sba.group3.backendmvc.entity.examination.ServiceCatalog;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.UUID;

public interface ServiceCatalogRepository extends BaseRepository<ServiceCatalog, UUID> {
  ServiceCatalog findByCode(String code);
}