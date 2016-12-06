/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import exceptions.PostNotFoundException;
import javax.validation.Valid;
import models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    
    @GetMapping("/{postId}")
    public String showPost(@PathVariable Long postId, Model model){
        if(!model.containsAttribute("post")){
            Post post = postService.findById(postId);
            if(post == null){
                throw new PostNotFoundException();
            }
            model.addAttribute("post", post);
        }
        return "post/show";
    }
    
    @GetMapping("/create")
    public String showCreatePostForm(Model model){
        model.addAttribute("post", new Post());
        return "post/create";
    }
    
    @PostMapping("/create")
    public String processPost(@ModelAttribute @Valid Post post, Errors errors, RedirectAttributes model){
        if(errors.hasErrors()){
            return "post/create";
        }
        postService.create(post);
        model.addAttribute("postId", post.getId());
        model.addFlashAttribute("post", post);
        return "redirect:/posts/{postId}";
    }
}
