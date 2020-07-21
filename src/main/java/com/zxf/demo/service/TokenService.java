package com.zxf.demo.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.zxf.demo.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author zxf
 */
@Service(value = "simpleTokenService")
public class TokenService {
    public String getToken(User user){
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60* 60 * 1000;
        //一小时有效时间
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create().withAudience(user.getId().toString()).withExpiresAt(end).withIssuedAt(start)
                .sign(Algorithm.HMAC256(user.getPassWord()));
        return token;
    }


}
