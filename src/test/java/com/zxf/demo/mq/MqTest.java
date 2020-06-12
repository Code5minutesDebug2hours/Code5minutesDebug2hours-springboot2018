package com.zxf.demo.mq;

import com.zxf.demo.model.User;
import com.zxf.demo.mq.hello.HelloSender;
import com.zxf.demo.mq.many.NeoSender;
import com.zxf.demo.mq.many.NeoSender2;
import com.zxf.demo.mq.obj.ObjectSender;
import com.zxf.demo.mq.topic.TopicSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MqTest {
    @Autowired
    private HelloSender helloSender;

    @Autowired
    private NeoSender neoSender;

    @Autowired
    private NeoSender2 neoSender2;

    @Autowired
    private ObjectSender objectSender;

    @Autowired
    private TopicSender sender;

    @Test
    public void hello() throws Exception {
        helloSender.send();
    }
    @Test
    public void oneToMany() throws Exception {
        for (int i=0;i<100;i++){
            neoSender.send(i);
        }
    }
    @Test
    public void manyToMany() throws Exception {
        for (int i=0;i<100;i++){
            neoSender.send(i);
            neoSender2.send(i);
        }
    }
    @Test
    public void sendObj() throws Exception {
        User u = new User();
        u.setUserName("zxf");
        u.setPassWord("123");
        objectSender.send(u);
    }
    @Test
    public void topic() throws Exception {
        sender.send();
    }

    @Test
    public void topic1() throws Exception {
        sender.send1();
    }

    @Test
    public void topic2() throws Exception {
        sender.send2();
    }

}
