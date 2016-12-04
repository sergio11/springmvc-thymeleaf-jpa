package repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import models.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
	
}
