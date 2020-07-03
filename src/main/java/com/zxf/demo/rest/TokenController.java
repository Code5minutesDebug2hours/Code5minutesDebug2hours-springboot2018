package com.zxf.demo.rest;

import com.alibaba.fastjson.JSONObject;
import com.zxf.demo.annotation.UserLoginToken;
import com.zxf.demo.model.User;
import com.zxf.demo.service.TokenService;
import com.zxf.demo.service.UserService;
import com.zxf.demo.utils.TokenUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zxf
 */
@RestController()
public class TokenController {

    @Autowired
    UserService userService;

    @Autowired
    TokenService tokenService;

    /****/
    @ApiOperation(value = "登陆", notes = "登陆")
    @RequestMapping(value = "/loginWithToken" ,method = RequestMethod.GET)
    public Object login (String name, String pwd , HttpServletResponse response){
        JSONObject jsonObject = new JSONObject();
        User user = userService.findByName(name);
        if (user == null){
            jsonObject.put("message", "no user");
            return jsonObject;
        }
        if (!user.getPassWord().equals(pwd)) {
            jsonObject.put("message", "登录失败,密码错误");
            return jsonObject;
        } else {
            String token = tokenService.getToken(user);
            jsonObject.put("token", token);

            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);

            return jsonObject;
        }
    }

    /***
     * 这个请求需要验证token才能访问
     * 这个实现通过token解析出user 再验证数据库user 一致则验证通过
     */
    @UserLoginToken
    @ApiOperation(value = "获取信息", notes = "获取信息")
    @RequestMapping(value = "/getMessage" ,method = RequestMethod.GET)
    public String getMessage() {

        // 取出token中带的用户id 进行操作
        System.out.println(TokenUtil.getTokenUserId());

        return "您已通过验证";
    }


}
