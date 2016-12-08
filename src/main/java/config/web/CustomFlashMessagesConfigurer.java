/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config.web;

import es.sandbox.ui.messages.CssClassesByLevel;
import es.sandbox.ui.messages.spring.config.annotation.EnableFlashMessages;
import es.sandbox.ui.messages.spring.config.annotation.FlashMessagesConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import es.sandbox.ui.messages.Level;

/**
 *
 * @author sergio
 */
@Configuration
@EnableFlashMessages
public class CustomFlashMessagesConfigurer extends FlashMessagesConfigurerAdapter {
    /**
     * Sets the styles of flash-messages to be compatible
     * with twitter bootstrap alerts
     */
    @Override
    public void configureCssClassesByLevel(CssClassesByLevel cssClasses) {
        cssClasses.put(Level.ERROR, "alert alert-danger");
        cssClasses.put(Level.WARNING, "alert alert-warning");
        cssClasses.put(Level.SUCCESS, "alert alert-success");
    }
}
