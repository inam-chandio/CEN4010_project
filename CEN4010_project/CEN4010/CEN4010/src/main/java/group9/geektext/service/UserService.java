package group9.geektext.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import group9.geektext.entity.User;
import group9.geektext.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Find user by username
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Save a new user
    public User createUser(User user) {
        return userRepository.save(user);
    }

    // Update user details
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    // Delete user by ID
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
