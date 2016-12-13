/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import java.util.List;
import javax.validation.Valid;
import models.Post;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import projection.PostDetail;
import services.PostService;
import services.security.CurrentUser;
import services.security.CurrentUserAttached;
/**
 *
 * @author sergio
 */
@Controller("AdminPostController")
@RequestMapping("/admin/posts")
public class PostController {
    
    private static Logger logger = LoggerFactory.getLogger(PostController.class);
    
    @Autowired
    private PostService postService;
    
    @GetMapping("/all")
    public String all(@CurrentUser User activeUser, Model model){
        logger.info("Info del usuario: " + activeUser.toString());
        List<PostDetail> posts = postService.findPostsByAuthor(activeUser.getId());
        model.addAttribute("posts", posts);
        return "admin/post/all";
    }
    
    @GetMapping("/create")
    public String showCreatePostForm(Model model){
        model.addAttribute("post", new Post());
        return "admin/post/create";
    }
    
    @PostMapping("/create")
    public String processPost(@ModelAttribute @Valid Post post, Errors errors, @CurrentUserAttached User activeUser){
        if(errors.hasErrors()){
            return "admin/post/create";
        }
        post.setAuthor(activeUser);
        postService.create(post);
        return "redirect:/admin/posts/all";
    }
}
