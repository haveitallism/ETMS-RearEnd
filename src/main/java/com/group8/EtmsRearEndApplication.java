package com.group8;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.group8.dao")
public class EtmsRearEndApplication {

    public static void main(String[] args) {
        SpringApplication.run(EtmsRearEndApplication.class, args);
    }

}
