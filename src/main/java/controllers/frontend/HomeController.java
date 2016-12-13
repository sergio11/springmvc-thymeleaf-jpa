/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.frontend;

import java.util.List;
import models.Post;
import net.rossillo.spring.web.mvc.CacheControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import services.PostService;
import projection.PostSummary;

/**
 *
 * @author sergio
 */
@Controller
public class HomeController {
    
    @Autowired
    private PostService postService;
    
    @CacheControl(maxAge = 300)
    @RequestMapping(value = {"/", "/home"})
    public String index(Model model) {
        List<PostSummary> latest5Posts = postService.findLatest5();
        model.addAttribute("latest5posts", latest5Posts);
        return "frontend/index";
    }
}
