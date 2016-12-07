/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import javax.validation.Valid;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repositories.UserRepository;

/**
 *
 * @author sergio
 */
@Controller
@RequestMapping("/admin")
public class SignupController {
    
    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/signup")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "admin/signup";
    }
    
    @PostMapping("/signup")
    public String processSignup(@ModelAttribute @Valid User user, Errors errors, RedirectAttributes model){
        if(errors.hasErrors()){
            return "admin/signup";
        }
        userRepository.save(user);
        model.addFlashAttribute("user", user);
        return "redirect:/admin";
    }
}
