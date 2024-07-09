package com.sdut.parking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className: WebConfig
 * @description: TODO 类描述
 * @author: faithererer
 * @date: 2024/7/9 0009
 **/
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${pic_path}")
    private String PATH;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 拼接路径并打印以便调试
        String path = "file:" + PATH;
        System.out.println("配置的文件路径: " + path);
        registry.addResourceHandler("/pic/**").addResourceLocations(path);
    }
}
