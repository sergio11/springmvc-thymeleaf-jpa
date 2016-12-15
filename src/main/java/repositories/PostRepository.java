package repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import models.Post;
import projection.PostByAuthor;
import projection.PostDetail;
import projection.PostSummary;

public interface PostRepository extends JpaRepository<Post, Long> {
    PostDetail findByIdAndPublishedTrue(Long id);
    PostDetail findById(Long id);
    List<PostSummary> findFirst5ByPublishedTrueOrderByDateDesc();
    List<PostByAuthor> findByAuthorId(Long id);
}
