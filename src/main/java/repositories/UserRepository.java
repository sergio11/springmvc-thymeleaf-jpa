package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    @Query("SELECT COUNT(u.id) FROM User u WHERE u.email=:email OR u.username=:username")
    Long existsUserWithEmailOrUsername(@Param("email") String email, @Param("username") String username);
}
