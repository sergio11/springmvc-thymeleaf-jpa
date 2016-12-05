package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
