package com.example.authdemo.user;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryUserRepository implements UserRepository {
    private final Map<String, User> storage = new ConcurrentHashMap<>();

    @Override
    public boolean existsByUsername(String username) {
        return storage.containsKey(username);
    }

    @Override
    public void save(User user) {
        storage.put(user.getUsername(), user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(storage.get(username));
    }
}
