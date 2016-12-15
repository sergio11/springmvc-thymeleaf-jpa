/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Post;
import java.util.List;
import projection.PostByAuthor;
import projection.PostDetail;
import projection.PostSummary;

/**
 *
 * @author sergio
 */
public interface PostService {
    List<Post> findAll();
    List<PostSummary> findLatest5();
    List<PostByAuthor> findPostsByAuthor(Long id);
    PostDetail findById(Long id);
    PostDetail findByIdAndPublishedTrue(Long id);
    Post create(Post post);
    Post edit(Post post);
    void delete(Post post);
    void delete(Long id);
}
