package com.project.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {



    /**
     * 静态资源加载设置
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/meta-inf/resources/swagger-ui.html");
        registry.
                addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/meta-inf/resources/webjars/");
    }
}
