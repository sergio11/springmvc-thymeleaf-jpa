/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import exceptions.PostNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import models.FileImage;
import models.Post;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.PostService;
import services.security.CurrentUser;
import services.security.CurrentUserAttached;
import projection.PostByAuthor;
/**
 *
 * @author sergio
 */
@Controller("AdminPostController")
@RequestMapping("/admin/posts")
@SessionAttributes({PostController.ATTRIBUTE_NAME})
public class PostController {
    
    private static Logger logger = LoggerFactory.getLogger(PostController.class);
    
    public static final String ATTRIBUTE_NAME = "post";
    public static final String BINDING_RESULT_NAME = "org.springframework.validation.BindingResult." + ATTRIBUTE_NAME;
    
    @Autowired
    private PostService postService;
    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;
    
    @GetMapping("/all")
    public String all(@CurrentUser User activeUser, Model model){
        logger.info("Info del usuario: " + activeUser.toString());
        List<PostByAuthor> posts = postService.findPostsByAuthor(activeUser.getId());
        model.addAttribute("posts", posts);
        return "admin/post/all";
    }
    
    @GetMapping("/edit/{postId}")
    public String showUpdatePostForm(@PathVariable Long postId, Model model) {
        /* if "fresh" GET (ie, not redirect w validation errors): */
        if(!model.containsAttribute(BINDING_RESULT_NAME)) {
            Post post = postService.findById(postId);
            if (post == null) {
                throw new PostNotFoundException();
            }
            model.addAttribute(ATTRIBUTE_NAME, post);
        }
        return "admin/post/edit";
    }
    
    @GetMapping("/delete/{postId}")
    public String showDeletePostForm(@PathVariable Long postId, Model model) {
        Post post = postService.findById(postId);
        if (post == null) {
            throw new PostNotFoundException();
        }
        model.addAttribute("post", post);
        return "admin/post/delete";
    }
    
    @PostMapping("/delete")
    public String processDelete(@ModelAttribute Post post, RedirectAttributes model) {
        postService.delete(post);
        List<String> successMessages = new ArrayList();
        successMessages.add(messageSource.getMessage("message.post.remove.success", new Object[] {}, Locale.getDefault()));
        model.addFlashAttribute("successFlashMessages", successMessages);
        return "redirect:/admin/posts/all";
    }
    
    @GetMapping("/create")
    public String showCreatePostForm(Model model){
        model.addAttribute(ATTRIBUTE_NAME,  new Post());
        return "admin/post/create";
    }
    
    @PostMapping("/save")
    public String processPost(
            @RequestPart("postImage") MultipartFile postImage, 
            @ModelAttribute(ATTRIBUTE_NAME) @Valid Post post, 
            BindingResult bindingResult, 
            @CurrentUserAttached User activeUser, 
            RedirectAttributes model) throws IOException, SQLException{
        
        String url = "redirect:/admin/posts/all";

        if (bindingResult.hasErrors()) {
            model.addFlashAttribute(BINDING_RESULT_NAME, bindingResult);
            return url;
        }

        if (postImage != null && !postImage.isEmpty()) {
            logger.info("Añadiendo información de la imagen");
            FileImage image = new FileImage();
            image.setName(postImage.getName());
            image.setContentType(postImage.getContentType());
            image.setSize(postImage.getSize());
            image.setContent(postImage.getBytes());
            post.setImage(image);
        }
        
        post.setAuthor(activeUser);
        if (post.getId() == null) {
            postService.create(post);
        } else {
            postService.edit(post);
        }

        List<String> successMessages = new ArrayList();
        successMessages.add(messageSource.getMessage("message.post.save.success", new Object[]{post.getId()}, Locale.getDefault()));
        model.addFlashAttribute("successFlashMessages", successMessages);
        return url;
    }
}
