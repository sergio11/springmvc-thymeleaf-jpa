/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;
import services.UserService;
import services.security.CustomUserDetails;

/**
 *
 * @author sergio
 */
@Component
public class AuthenticationSuccessEventHandler{
    
    private static Logger logger = LoggerFactory.getLogger(AuthenticationSuccessEventHandler.class);
    
    @Autowired
    private UserService userService;

    @EventListener({AuthenticationSuccessEvent.class, InteractiveAuthenticationSuccessEvent.class})
    public void processAuthenticationSuccessEvent(AbstractAuthenticationEvent  e) {
        logger.info("Autenticación realizada ....");
        // Actualizamos la útltima fecha de acceso
        String username = ((CustomUserDetails) e.getAuthentication().getPrincipal()).getUsername();
        logger.info("Actualizando último acceso para user: " + username);
        userService.updateLastLoginAccess(username, new Date());
    }   
}
