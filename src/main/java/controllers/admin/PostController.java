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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.PostService;

/**
 *
 * @author sergio
 */
@Controller("AdminPostController")
@RequestMapping("/admin/posts")
public class PostController {
    
    @Autowired
    private PostService postService;
    
    @GetMapping("/all")
    public String all(@AuthenticationPrincipal User activeUser, Model model){
        List<Post> posts = postService.findPostsByAuthor(activeUser.getId());
        model.addAttribute("posts", posts);
        return "admin/post/all";
    }
    
    @GetMapping("/create")
    public String showCreatePostForm(Model model){
        model.addAttribute("post", new Post());
        return "admin/post/create";
    }
    
    @PostMapping("/create")
    public String processPost(@ModelAttribute @Valid Post post, Errors errors, RedirectAttributes model){
        if(errors.hasErrors()){
            return "admin/post/create";
        }
        postService.create(post);
        model.addAttribute("postId", post.getId());
        model.addFlashAttribute("post", post);
        return "redirect:/admin/posts/{postId}";
    }
}
