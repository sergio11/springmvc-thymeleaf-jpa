package repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import models.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import projection.PostDetail;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findFirst5ByOrderByDateDesc();
    @EntityGraph(attributePaths = { "author" })
    List<PostDetail> findByAuthorId(Long id);
}
