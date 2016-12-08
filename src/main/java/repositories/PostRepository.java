package repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findFirst5ByOrderByDateDesc();
    List<Post> findByAuthorId(Long id);
}
