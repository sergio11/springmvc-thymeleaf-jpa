/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author sergio
 */
@Component
public class AuthenticationEventListener implements ApplicationListener<AbstractAuthenticationEvent> {


    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationEventListener.class);

    public AuthenticationEventListener() {
    }

    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent authenticationEvent) {
        if (authenticationEvent instanceof InteractiveAuthenticationSuccessEvent || authenticationEvent instanceof AuthenticationSuccessEvent) {
            LOG.info("Authentication success.");

        } else if (authenticationEvent instanceof AbstractAuthenticationFailureEvent) {
            LOG.info("Authentication failure.");
        }
    }
}
