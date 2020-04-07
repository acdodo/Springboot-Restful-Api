package com.demo.app.service;

import com.demo.app.common.ApiUtil;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;

@Service
public class LoginService {
   // @Autowired
//    private MqygtProcessService mqygtProcessService;
    public String getLogin(String username,String password) throws IOException {
        password =Base64.getEncoder().encodeToString(password.getBytes("UTF-8"));
        return ApiUtil.getString("/app/applogin?username="+username+"&password="+password);
//        return ApiUtil.getString("/ywgl/fwgl/getdbList?corstype=cors&corsusername=admin");
    }
}
