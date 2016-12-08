/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import controllers.ErrorController;
import es.sandbox.ui.messages.Flash;
import exceptions.EmailExistsException;
import exceptions.UsernameExistsException;
import javax.validation.Valid;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.UserService;

/**
 *
 * @author sergio
 */
@Controller
@RequestMapping("/admin")
public class SignupController {
    
    private static Logger logger = LoggerFactory.getLogger(ErrorController.class);
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/signup")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "admin/signup";
    }
    
    @PostMapping("/signup")
    public String processSignup(@ModelAttribute @Valid User user, Errors errors, RedirectAttributes model){
        String viewName = "admin/signup";
        if(!errors.hasErrors()){
            try {
                userService.registerNewUserAccount(user);
                model.addFlashAttribute("message", user);
                viewName = "redirect:/admin";
            }catch(EmailExistsException e){
                logger.error("Email alredy exists");
                errors.rejectValue("email", "user.email.exists");
            }catch(UsernameExistsException e){
                logger.error("Username alredy exists");
                errors.rejectValue("username", "user.username.exists");
            }
        }
        return viewName;
    }
}
