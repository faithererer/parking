package com.sdut.parking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@EnableAsync//开启异步调用
@SpringBootApplication
@MapperScan("com.sdut.parking.*.mapper")
public class ParkingApplication {
    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class, args);
        System.out.println("启动成功！！！！！！！！！！");
    }

    //密码加密
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
