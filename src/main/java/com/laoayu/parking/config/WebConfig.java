package com.laoayu.parking.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @className: WebConfig
 * @description: TODO 类描述
 * @author: niaonao
 * @date: 2024/7/9 0009
 **/
public class WebConfig implements WebMvcConfigurer {


    private String path = "file:F:/WorkSpace/JavaWeb/parking/car_repo/";
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置虚拟路径映射到实际文件目录
        registry.addResourceHandler("/pic/**").addResourceLocations(path);
    }
}
