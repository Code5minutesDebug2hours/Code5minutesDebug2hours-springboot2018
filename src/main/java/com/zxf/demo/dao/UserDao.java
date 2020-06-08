package com.zxf.demo.dao;

import com.zxf.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zxf
 */
@Repository
public interface UserDao extends PagingAndSortingRepository<User,Long>,
        JpaSpecificationExecutor<User> , JpaRepository<User,Long> {
     User getUsersById(Long id);

     @Query(value = "select t from User t where pass_word = :pwd")
     User getByPassWord(String pwd);

     @Query(value = "select t from User t where user_name = :name")
     User getByPassName(String name);
}
