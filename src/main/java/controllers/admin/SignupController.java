/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import exceptions.UserAlredyExistsException;
import java.util.Locale;
import javax.validation.Valid;
import models.Role;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import repositories.RolesRepository;
import services.UserService;
import java.util.List;
import java.util.ArrayList;
import org.springframework.validation.annotation.Validated;
/**
 *
 * @author sergio
 */
@Controller
@RequestMapping("/admin")
public class SignupController {
    
    private static Logger logger = LoggerFactory.getLogger(SignupController.class);
    
    @Autowired
    private UserService userService;

    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;
    
    @GetMapping("/signup")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "admin/signup";
    }
    
    @PostMapping("/signup")
    public String processSignup(@Validated(User.UserCreation.class) @ModelAttribute User user, Errors errors, RedirectAttributes model){
        String viewName = "admin/signup";
        if(!errors.hasErrors()){
            try {
                userService.create(user);
                List<String> successMessages = new ArrayList();
                successMessages.add(messageSource.getMessage("message.signup.success", new Object[]{ user.getUsername() }, Locale.getDefault()));
                model.addFlashAttribute("successFlashMessages", successMessages);
                viewName = "redirect:/admin/login";
            }catch(UserAlredyExistsException e){
                logger.error("Email alredy exists");
                errors.rejectValue("email", "user.exists");
            }
        }
        return viewName;
    }
}
