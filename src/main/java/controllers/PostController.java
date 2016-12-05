/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import exceptions.PostNotFoundException;
import models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import services.PostService;

/**
 *
 * @author sergio
 */
@Controller
@RequestMapping("/posts")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @RequestMapping(value="/{postId}", method=GET)
    public String showPost(@PathVariable("postId") Long postId, Model model){
        Post post = postService.findById(postId);
        if(post == null){
            throw new PostNotFoundException();
        }
        model.addAttribute("post", post);
        return "post/show";
    }
    
    @RequestMapping(value="/create", method=GET)
    public String showCreatePostForm(){
        return "post/create";
    }
    
    @RequestMapping(value="/create", method=POST)
    public String processPost(Post post, Model model){
        postService.create(post);
        model.addAttribute("postId", post.getId());
        return "redirect:/posts/{postId}";
    }
}
