package com.demo.app.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.demo.app.annotation.JwtIgnore;
import com.demo.app.common.JwtTokenUtil;
import com.demo.app.common.response.Result;
import com.demo.app.service.LoginService;
import com.demo.app.entity.Audience;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/users")
@Api(value = "登录")
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private Audience audience;
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value = "/applogin",method= RequestMethod.GET)
    @JwtIgnore
    public Result getLogin(HttpServletResponse response, String username, String password) throws IOException {

        String  str = loginService.getLogin(username,password);
        JSONObject jsonObject = JSON.parseObject(str);
        String tip = jsonObject.getString("tip");
        if(tip==null){
            String token = JwtTokenUtil.createJWT(
                    jsonObject.getString("username"),
                    jsonObject.getString("password"),
                    jsonObject.getString("wrongcount"),
                    jsonObject.getString("differencetime"),
                    jsonObject.getString("realname"),
                    jsonObject.getString("role"),audience);
            log.info("### 登录成功, token={} ###", token);

            // 将token放在响应头
            response.setHeader(JwtTokenUtil.AUTH_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX + token);
            // 将token响应给客户端
            JSONObject result = new JSONObject();
            result.put("token", token);
            result.put("userdata", jsonObject);
            return Result.SUCCESS(result);

        }else{
            return  Result.FAIL(jsonObject);
        }

//        return ;
    }
}
