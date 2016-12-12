package repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import models.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import projection.PostDetail;
import projection.PostInfo;

public interface PostRepository extends JpaRepository<Post, Long> {
    @EntityGraph(attributePaths = { "author" })
    List<PostInfo> findFirst5ByOrderByDateDesc();
    @EntityGraph(attributePaths = { "author" })
    List<PostDetail> findByAuthorId(Long id);
}
