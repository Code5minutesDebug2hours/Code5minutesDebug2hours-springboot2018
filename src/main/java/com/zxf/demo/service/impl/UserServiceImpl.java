package com.zxf.demo.service.impl;

import com.zxf.demo.dao.UserDao;
import com.zxf.demo.model.User;
import com.zxf.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zxf
 * 实现了 oauth 的 UserDetailsService重写了 loadUserByUsername
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;


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

    @Override
    public User getUserByIdEnd(Long s) {
        return userDao.getFirstByIdAfter(s);
    }

    /***oauth2使用*/
    @Override
    public UserDetails  loadUserByUsername(String username) throws UsernameNotFoundException {
        // 用户角色也应在数据库中获取
         String role = "ROLE_ADMIN";
         List<SimpleGrantedAuthority> authorities = new ArrayList<>();
         authorities.add(new SimpleGrantedAuthority(role));
         //线上环境应该通过用户名查询数据库获取加密后的密码
         User user = userDao.getByPassName(username);
         if (user == null){
             return null;
         }
         String password = passwordEncoder.encode(user.getPassWord());
         return  new org.springframework.security.core.userdetails.User(username,password,authorities);
    }
}
