package edu.school21.todoapp.service;

import edu.school21.todoapp.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    void save(User user);
    Optional<User> findByUsername(String login);
}
