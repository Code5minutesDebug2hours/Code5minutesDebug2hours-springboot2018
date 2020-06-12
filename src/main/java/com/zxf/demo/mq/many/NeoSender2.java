package com.zxf.demo.mq.many;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author zxf
 */
@Component
public class NeoSender2 {
    @Autowired
    AmqpTemplate rabbitTemplate;

    public void send(int i){
        String context = "spirng boot neo queue"+" ****** "+i;
        System.out.println("Sender2 : " + context);
        this.rabbitTemplate.convertAndSend("neo", context);
    }
}
