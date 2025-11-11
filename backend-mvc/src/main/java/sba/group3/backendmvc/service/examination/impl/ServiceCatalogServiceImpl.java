package sba.group3.backendmvc.service.examination.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ServiceCatalogRequest;
import sba.group3.backendmvc.dto.response.examination.ServiceCatalogResponse;
import sba.group3.backendmvc.entity.examination.ServiceCatalog;
import sba.group3.backendmvc.mapper.examination.ServiceCatalogMapper;
import sba.group3.backendmvc.repository.examination.ServiceCatalogRepository;
import sba.group3.backendmvc.service.examination.ServiceCatalogService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ServiceCatalogServiceImpl implements ServiceCatalogService {
    private final ServiceCatalogRepository serviceCatalogRepository;
    private final ServiceCatalogMapper serviceCatalogMapper;

    private String generatePositionCode() {
        long count = serviceCatalogRepository.count();
        return String.format("SC-%04d", count + 1);
    }

    @Transactional
    @PostConstruct
    public void init() {
        if (serviceCatalogRepository.count() == 0) {
            try{
                AtomicLong initialCount = new AtomicLong();
                var resource = new ClassPathResource("service_catalog.json");
                String prefix = "SC";
                ObjectMapper mapper = new ObjectMapper();
                List<ServiceCatalog> serviceCatalogs = mapper.readValue(
                        resource.getInputStream(), mapper.getTypeFactory().constructCollectionType(List.class, ServiceCatalog.class)
                );
                Random random = new Random();
                for (ServiceCatalog serviceCatalog : serviceCatalogs) {
                    serviceCatalog.setCode(String.format("%s%05d", prefix, initialCount.incrementAndGet()));
                    if (serviceCatalog.getPrice() == null) {
                        int min = 40000;
                        int max = 400000;
                        int value = ((random.nextInt(max - min) + min) / 1000) * 1000;
                        serviceCatalog.setPrice(BigDecimal.valueOf(value));
                    }
                }
                serviceCatalogRepository.saveAll(serviceCatalogs);
                log.info("Imported {} service catalogs", serviceCatalogs.size());
            } catch (Exception e) {
                log.error("Failed to import service catalogs", e);
            }
        }
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
