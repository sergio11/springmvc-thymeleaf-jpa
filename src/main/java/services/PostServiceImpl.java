/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
import models.FileImage;
import models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PostRepository;
import projection.PostByAuthor;
import projection.PostDetail;
import projection.PostSummary;
import repositories.FileImageRepository;

/**
 *
 * @author sergio
 */
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private FileImageRepository fileImageRepository;
    
    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public List<PostSummary> findLatest5() {
        return postRepository.findFirst5ByPublishedTrueOrderByDateDesc();
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
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.delete(id);
    }

    @Override
    public List<PostByAuthor> findPostsByAuthor(Long id) {
        return postRepository.findByAuthorId(id);
    }
    
    @Override
    public Post findById(Long id) {
        return postRepository.findById(id);
    }
    
    @Override
    public PostDetail findByIdAndPublishedTrue(Long id) {
        return postRepository.findByIdAndPublishedTrue(id);
    }

    @Override
    public FileImage getImageByPostId(Long id) {
        return fileImageRepository.findByPostId(id);
    }
}
