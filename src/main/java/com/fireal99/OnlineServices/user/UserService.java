package com.fireal99.OnlineServices.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(User user) {
        return userRepository.save(user).getId();
    }

    public List<User> findAll(Integer pageNumber, Integer pageSize) {
        var sortedPagination = PageRequest.of(pageNumber, pageSize, Sort.by("id").descending());
        return userRepository.findAll(sortedPagination).toList();
    }

    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
