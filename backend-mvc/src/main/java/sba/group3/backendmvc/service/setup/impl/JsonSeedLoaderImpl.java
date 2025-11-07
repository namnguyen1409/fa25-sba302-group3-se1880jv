package sba.group3.backendmvc.service.setup.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import sba.group3.backendmvc.service.setup.JsonSeedLoader;

import java.util.List;

@Component
public class JsonSeedLoaderImpl implements JsonSeedLoader {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> List<T> load(String path, Class<T> clazz) {
        try {
            var resource = new ClassPathResource(path);
            var collectionType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            return mapper.readValue(resource.getInputStream(), collectionType);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load seed: " + path, e);
        }
    }
}
