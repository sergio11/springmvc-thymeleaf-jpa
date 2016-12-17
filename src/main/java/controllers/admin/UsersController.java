/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import java.util.List;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import services.UserService;

/**
 *
 * @author sergio
 */
@Controller
@RequestMapping("/admin/users")
public class UsersController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/all")
    public String all(Model model){
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/all";
    }
}
