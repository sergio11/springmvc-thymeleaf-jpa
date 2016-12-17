/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import exceptions.UserAlredyExistsException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.validation.Valid;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.UserService;
import services.security.CurrentUser;

/**
 *
 * @author sergio
 */
@Controller
@RequestMapping("/admin/users")
public class UsersController {
    
    @Autowired
    private UserService userService;
    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;
    
    @GetMapping("/self")
    public String self(@CurrentUser User activeUser, Model model){
        model.addAttribute("user", activeUser);
        return "admin/user/self";
    }
    
    @PostMapping("/self")
    public String self(@ModelAttribute @Valid User user, Errors errors, RedirectAttributes model){
        String viewName = "admin/user/self";
        if(!errors.hasErrors()){
            try{
                userService.update(user);
                List<String> successMessages = new ArrayList();
                successMessages.add(messageSource.getMessage("message.profile.save.success", new Object[]{ }, Locale.getDefault()));
                model.addFlashAttribute("successFlashMessages", successMessages);
                viewName = "redirect:/admin/user/self";
            }catch(UserAlredyExistsException e){
                errors.rejectValue("email", "user.exists");
            }
        }
        return viewName;
    }
    
    @GetMapping("/all")
    public String all(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/all";
    }
}
