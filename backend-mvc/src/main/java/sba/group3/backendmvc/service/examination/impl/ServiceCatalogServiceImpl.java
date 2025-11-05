package sba.group3.backendmvc.service.examination.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ServiceCatalogRequest;
import sba.group3.backendmvc.dto.response.examination.ServiceCatalogResponse;
import sba.group3.backendmvc.entity.examination.ServiceCatalog;
import sba.group3.backendmvc.mapper.examination.ServiceCatalogMapper;
import sba.group3.backendmvc.repository.examination.ServiceCatalogRepository;
import sba.group3.backendmvc.service.examination.ServiceCatalogService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceCatalogServiceImpl implements ServiceCatalogService {
    private final ServiceCatalogRepository serviceCatalogRepository;
    private final ServiceCatalogMapper serviceCatalogMapper;

    private String generatePositionCode(){
        return "SCL-" + UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    @Override
    public Page<ServiceCatalogResponse> filter(SearchFilter filter) {
        return serviceCatalogRepository.search(filter).map(serviceCatalogMapper::toDto);
    }

    @Override
    public List<ServiceCatalogResponse> getListServiceCatalogs(SearchFilter filter) {
        return serviceCatalogRepository.searchAll(filter).stream().map(serviceCatalogMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public ServiceCatalogResponse create(ServiceCatalogRequest serviceCatalogRequest) {
        ServiceCatalog serviceCatalog = serviceCatalogMapper.toEntity(serviceCatalogRequest);
        serviceCatalog.setCode(generatePositionCode());
        return serviceCatalogMapper.toDto(serviceCatalogRepository.save(serviceCatalog));
    }

    @Override
    public ServiceCatalogResponse update(String id, ServiceCatalogRequest request) {
        ServiceCatalog serviceCatalog = serviceCatalogRepository.findById(UUID.fromString(id)).orElseThrow(()->
                new IllegalArgumentException("Service Catalog with id " + id + " not found."));
        serviceCatalog.setName(request.name());
        serviceCatalog.setCategory(request.category());
        serviceCatalog.setPrice(request.price());
        serviceCatalog.setDescription(request.description());

        return serviceCatalogMapper.toDto(serviceCatalogRepository.save(serviceCatalog));
    }

    @Override
    public void delete(String id) {
        ServiceCatalog serviceCatalog = serviceCatalogRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new IllegalArgumentException("Service Catalog with id " + id + " not found."));
        serviceCatalog.setDeleted(true);
        serviceCatalogRepository.save(serviceCatalog);
    }
}
