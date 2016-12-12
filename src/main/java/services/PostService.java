/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import models.Post;
import java.util.List;
import projection.PostDetail;

/**
 *
 * @author sergio
 */
public interface PostService {
    List<Post> findAll();
    List<Post> findLatest5();
    List<PostDetail> findPostsByAuthor(Long id);
    Post findById(Long id);
    Post create(Post post);
    Post edit(Post post);
    void deleteById(Long id);
}
