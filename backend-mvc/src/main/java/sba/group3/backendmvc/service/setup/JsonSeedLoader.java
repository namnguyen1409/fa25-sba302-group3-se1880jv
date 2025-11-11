package sba.group3.backendmvc.service.setup;

import java.util.List;

public interface JsonSeedLoader {
    <T> List<T> load(String path, Class<T> clazz);
}
