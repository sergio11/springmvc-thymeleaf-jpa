/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config.web;

import net.rossillo.spring.web.mvc.CacheControlHandlerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

/**
 *
 * @author sergio
 */
@Configuration
@EnableWebMvc
@ComponentScan(value = { "controllers", "services" })
@Import(value = { ThymeleafConfig.class, i18nConfig.class })
public class WebConfig extends WebMvcConfigurerAdapter{
    
    @Autowired
    private LocaleChangeInterceptor localeChangeInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/403").setViewName("403");
        registry.addViewController("/about").setViewName("frontend/about");
        registry.addViewController("/admin").setViewName("admin/index");
        registry.addViewController("/admin/login").setViewName("admin/login");
        registry.addViewController("/admin/signup").setViewName("admin/signup");
    }
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // habilitar procesamiento de contenido estático
       configurer.enable();
    } 

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(new CacheControlHandlerInterceptor());
    }
    
    @Bean(name="multipartResolver")
    public MultipartResolver provideMultipartResolver(){
        return new StandardServletMultipartResolver();
    }
}
