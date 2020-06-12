package com.zxf.demo.service;

import com.zxf.demo.model.User;

import java.util.List;

/**
 * @author zxf
 */
public interface UserService {

    List<User> getAllUser();

    User getS(Long id);

    User findByPwd(String pwd);

    User findByName(String name);

    User createUser(User user);

    User getUserByIdEnd(Long s);
}
