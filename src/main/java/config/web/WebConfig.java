/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author sergio
 */
@Configuration
@EnableWebMvc
@ComponentScan("blog.controllers")
@Import(value = { ThymeleafConfig.class })
public class WebConfig extends WebMvcConfigurerAdapter{
   
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // habilitar procesamiento de contenido estático
       configurer.enable();
    } 
}
