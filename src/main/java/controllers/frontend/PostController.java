/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

import exceptions.PostNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private static Logger logger = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private PostService postService;
    
    @GetMapping("/{postId}")
    public String show(@PathVariable Long postId, Model model){
        PostDetail post = postService.findByIdAndPublishedTrue(postId);
        if (post == null) {
            throw new PostNotFoundException();
        }
        model.addAttribute("post", post);
        return "frontend/post/show";
    }
}
