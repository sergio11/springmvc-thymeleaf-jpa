/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PostRepository;
import projection.PostByAuthor;
import projection.PostDetail;
import projection.PostSummary;

/**
 *
 * @author sergio
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    
    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<PostSummary> findLatest5() {
        return postRepository.findFirst5ByOrderByDateDesc();
    }

    @Override
    public PostDetail findById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post edit(Post post) {
       return postRepository.save(post);
    }

    @Override
    public void deleteById(Long id) {
        postRepository.delete(id);
    }

    @Override
    public List<PostByAuthor> findPostsByAuthor(Long id) {
        return postRepository.findByAuthorId(id);
    }
    
}
