package com.zxf.demo.mq.hello;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zxf
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello " + System.currentTimeMillis();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("user", context);
    }
}
