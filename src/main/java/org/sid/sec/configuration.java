package org.sid.sec;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class configuration implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        
      registry.addViewController("/login").setViewName("login");
    }

}