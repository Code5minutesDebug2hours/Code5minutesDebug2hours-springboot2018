package com.zxf.demo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zxf
 */
@Component
public class TaskTest  {
    @Scheduled(cron = "0/5 * * * * ?")
    public void jobTest(){
        System.out.println("jobTest");
    }
}
