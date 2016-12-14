package repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import projection.PostByAuthor;
import projection.PostDetail;
import projection.PostSummary;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p WHERE p.id = :id AND ( p.published = true OR p.author.username = :currentUser )")
    PostDetail findByIdAndPublishedTrueOrUserIsAuthor(@Param("id") Long id, @Param("currentUser") String currentUser);
    PostDetail findByIdAndPublishedTrue(Long id);
    PostDetail findById(Long id);
    List<PostSummary> findFirst5ByPublishedTrueOrderByDateDesc();
    List<PostByAuthor> findByAuthorId(Long id);
}
