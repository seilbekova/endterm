package com.library.cache;

import java.util.Optional;

public interface Cache {
    void put(String key, Object value);
    Optional<Object> get(String key);
    void remove(String key);
    void clear();
}