/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.admin;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author sergio
 */
@Controller
@RequestMapping("/admin")
public class SigninController {
    
    @GetMapping("/login")
    public String login(HttpServletRequest request){
        String referrer = request.getHeader("Referer");
        if(referrer!=null){
            request.getSession().setAttribute("url_prior_login", referrer);
        }
        return "admin/login";
    }
}
