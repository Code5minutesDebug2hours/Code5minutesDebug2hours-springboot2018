package com.zxf.demo.config;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zxf
 */
@Configuration
public class MqConfig {
    @Bean
    Queue Queue(){
        return new Queue("hello");
    }
}
