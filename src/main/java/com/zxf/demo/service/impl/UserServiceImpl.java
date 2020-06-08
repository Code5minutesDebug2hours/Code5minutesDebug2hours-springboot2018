package com.zxf.demo.service.impl;

import com.zxf.demo.dao.UserDao;
import com.zxf.demo.model.User;
import com.zxf.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author zxf
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> getAllUser() {
        return userDao.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                return null;
            }
        });
    }

    @Override
    public User getS(Long id) {
        return userDao.getUsersById(id);
    }

    @Override
    public User findByPwd(String pwd) {
        return userDao.getByPassWord(pwd);
    }

    @Override
    public User findByName(String name) {
        return userDao.getByPassName(name);
    }

    @Override
    public User createUser(User user) {
        return userDao.save(user);
    }
}
