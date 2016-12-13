/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

import controllers.*;
import exceptions.PostNotFoundException;
import javax.validation.Valid;
import models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import projection.PostDetail;
import services.PostService;

/**
 *
 * @author sergio
 */
@Controller("FrontendPostController")
@RequestMapping("/posts")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @GetMapping("/{postId}")
    public String show(@PathVariable Long postId, Model model){
        if(!model.containsAttribute("post")){
            PostDetail post = postService.findById(postId);
            if(post == null){
                throw new PostNotFoundException();
            }
            model.addAttribute("post", post);
        }
        return "frontend/post/show";
    }
}
