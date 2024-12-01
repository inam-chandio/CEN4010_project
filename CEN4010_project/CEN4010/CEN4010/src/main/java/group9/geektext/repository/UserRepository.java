package group9.geektext.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import group9.geektext.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query method to find a user by their username
    Optional<User> findByUsername(String username);
}
