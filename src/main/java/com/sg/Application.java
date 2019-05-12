package com.sg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;


@EnableScheduling
@SpringBootApplication
@Import(value = {BeanValidatorPluginsConfiguration.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}