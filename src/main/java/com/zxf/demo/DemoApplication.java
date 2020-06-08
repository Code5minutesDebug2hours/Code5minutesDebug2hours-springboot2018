package com.zxf.demo;

import com.zxf.demo.dao.UserDao;
import com.zxf.demo.model.User;
import com.zxf.demo.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.List;

/**
 * @author zxf
 */
@SpringBootApplication(scanBasePackages = "com.zxf")
@EnableAspectJAutoProxy
public class DemoApplication {

    public static void main(String[] args) {

        ApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        String []s = context.getBeanDefinitionNames();
        User user = (User) context.getBean("user");
        UserDao userDao = (UserDao)context.getBean("userDao");
        UserService userService = (UserService)context.getBean("userService");
        List<User> list = userService.getAllUser();
        System.out.println(user.toString());



//        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        User user= (User) context.getBean("user");
//        System.out.println(user);

    }

}
