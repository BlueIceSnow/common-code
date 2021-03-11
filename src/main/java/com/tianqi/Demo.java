package com.tianqi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author: yuantianqi
 * @Date: 2021/3/5 16:42
 * @Description:
 */
@SpringBootApplication
@MapperScan(basePackages = "com.tianqi.demo.dao")
public class Demo {
    public static void main(String[] args) {
        SpringApplication.run(Demo.class);
    }
}
