/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config.root;


import config.persistence.PersistenceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author sergio
 */
@Configuration
@PropertySource(value = { "classpath:application.properties" })
@Import(value = { PersistenceConfig.class })
public class RootConfig {
    
}
