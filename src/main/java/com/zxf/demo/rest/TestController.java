package com.zxf.demo.rest;

import com.zxf.demo.model.User;
import com.zxf.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @author zhangxuefng
 * */
@RestController
public class TestController {

    @Autowired
    UserService  userService;

    @Cacheable(value = "zxf")
    @RequestMapping("/test")
    public String test(){
        System.out.println("test method");
        return "testMap";
    }

    @RequestMapping("/uid")
    String uid(HttpSession session) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return session.getId();
    }

    @RequestMapping("/allUser")
    public  List<User> allUser() {
        User u = userService.findByName("zxf1");
        if (u == null){
            userService.createUser(new User(null,"zxf1","123"));
        }
        User user = userService.findByPwd("123456");
        return userService.getAllUser();
    }
}
