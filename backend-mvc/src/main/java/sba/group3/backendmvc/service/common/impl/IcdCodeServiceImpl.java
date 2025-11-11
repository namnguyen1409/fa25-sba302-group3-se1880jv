package sba.group3.backendmvc.service.common.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.common.IcdCodeRequest;
import sba.group3.backendmvc.dto.response.common.IcdCodeResponse;
import sba.group3.backendmvc.entity.common.IcdCode;
import sba.group3.backendmvc.mapper.common.IcdCodeMapper;
import sba.group3.backendmvc.repository.common.IcdCodeRepository;
import sba.group3.backendmvc.service.common.IcdCodeService;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IcdCodeServiceImpl implements IcdCodeService {
    private final IcdCodeMapper icdCodeMapper;

    IcdCodeRepository icdCodeRepository;

    @Transactional
    @PostConstruct
    public void init() {
        if (icdCodeRepository.count() == 0) {
            try{
                var resource = new ClassPathResource("icd.json");
                ObjectMapper mapper = new ObjectMapper();
                List<IcdCode> icdCodes = mapper.readValue(
                        resource.getInputStream(), mapper.getTypeFactory().constructCollectionType(List.class, IcdCode.class)
                );
                icdCodeRepository.saveAll(icdCodes);
                log.info("Imported {} ICD codes", icdCodes.size());
            } catch (Exception e) {
                log.error("Failed to import ICD codes", e);
            }
        }
    }


    @Transactional
    @Override
    public Page<IcdCodeResponse> filter(SearchFilter filter) {
        return icdCodeRepository.search(filter).map(icdCodeMapper::toDto1);
    }

    @Transactional
    @Override
    public IcdCodeResponse getById(UUID id) {
        return icdCodeMapper.toDto1(icdCodeRepository.findById(id).orElseThrow(() -> new RuntimeException("ICD code not found")));
    }

    @Transactional
    @Override
    public IcdCodeResponse save(IcdCodeRequest icdCode) {
        IcdCode newIcdCode = icdCodeMapper.toEntity(icdCode);
        newIcdCode = icdCodeRepository.save(newIcdCode);
        return icdCodeMapper.toDto1(newIcdCode);
    }

    @Transactional
    @Override
    public IcdCodeResponse update(UUID id, IcdCodeRequest icdCode) {
        IcdCode existingIcdCode = icdCodeRepository.findById(id).orElseThrow(() -> new RuntimeException("ICD code not found"));
        icdCodeMapper.partialUpdate(icdCode, existingIcdCode);
        existingIcdCode = icdCodeRepository.save(existingIcdCode);
        return icdCodeMapper.toDto1(existingIcdCode);
    }

    @Transactional
    @Override
    public void delete(UUID id) {
        IcdCode existingIcdCode = icdCodeRepository.findById(id).orElseThrow(() -> new RuntimeException("ICD code not found"));
        existingIcdCode.setDeleted(true);
        icdCodeRepository.save(existingIcdCode);
    }
}
