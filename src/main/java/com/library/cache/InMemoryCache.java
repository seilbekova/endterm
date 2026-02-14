package com.library.cache;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCache implements Cache {
    private static InMemoryCache instance;
    private final Map<String, Object> cacheMap = new ConcurrentHashMap<>();

    private InMemoryCache() {}

    public static synchronized InMemoryCache getInstance() {
        if (instance == null) {
            instance = new InMemoryCache();
        }
        return instance;
    }

    @Override
    public void put(String key, Object value) {
        cacheMap.put(key, value);
    }

    @Override
    public Optional<Object> get(String key) {
        return Optional.ofNullable(cacheMap.get(key));
    }

    @Override
    public void remove(String key) {
        cacheMap.remove(key);
    }

    @Override
    public void clear() {
        cacheMap.clear();
    }
}



