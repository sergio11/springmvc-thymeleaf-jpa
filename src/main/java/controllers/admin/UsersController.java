/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import exceptions.UserNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import models.Role;
import models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import repositories.RolesRepository;
import services.UserService;

/**
 *
 * @author sergio
 */
@Controller
@RequestMapping("/admin/users")
@SessionAttributes({UsersController.ATTRIBUTE_USER_NAME})
public class UsersController {
    
    private static Logger logger = LoggerFactory.getLogger(UsersController.class);
    
    public static final String ATTRIBUTE_USER_NAME = "user";
    public static final String ATTRIBUTE_ROLES_NAME = "roles";
    public static final String BINDING_RESULT_NAME = "org.springframework.validation.BindingResult." + ATTRIBUTE_USER_NAME;
    
    @Autowired
    private UserService userService;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private ReloadableResourceBundleMessageSource messageSource;
    
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.setAllowedFields("enabled", "roles");
    }
    
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable Long userId, Model model){
        if(!model.containsAttribute(BINDING_RESULT_NAME)) {
            List<Role> allRoles = rolesRepository.findAll();
            User user = userService.findById(userId);
            if(user == null)
                throw new UserNotFoundException();
            model.addAttribute(ATTRIBUTE_ROLES_NAME, allRoles);
            model.addAttribute(ATTRIBUTE_USER_NAME, user);
        }
        return "admin/user/edit";
    }
    
    @PostMapping("/edit/{userId}")
    public String edit(
        @Validated(User.UserUpdate.class) @ModelAttribute(ATTRIBUTE_USER_NAME) User user,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes,
        SessionStatus sessionStatus
    ){
        String url = "redirect:/admin/users/edit/{userId}";
        
        logger.info("Usuario a actualizar: " + user.toString());
       
        redirectAttributes.addAttribute("userId", user.getId());

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute(BINDING_RESULT_NAME, bindingResult);
            return url;
        }
        
        userService.update(user);
        sessionStatus.setComplete(); //remove user from session
        List<String> successMessages = new ArrayList();
        successMessages.add(messageSource.getMessage("message.profile.save.success", new Object[]{ }, Locale.getDefault()));
        redirectAttributes.addFlashAttribute("successFlashMessages", successMessages);
        return url;
    }
    
    @GetMapping("/all")
    public String all(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/all";
    }
}
