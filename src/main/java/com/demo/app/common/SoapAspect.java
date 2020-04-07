package com.demo.app.common;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class SoapAspect {

    @Autowired
    private CheckID checkID;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.demo.app.service.soap.impl.MqygtWebServiceImpl)")
    public void excudeService() {
    }

    ;

    @Around("excudeService()&&args(dataStr)")
    public Object prepare(ProceedingJoinPoint pjd, String dataStr) {
        JSONObject jot = new JSONObject();
        Object result = null;
        if ((dataStr == null) || (dataStr.trim() == "") || "".equals(dataStr.trim())) {
            jot.put("result", "2002");
            return jot.toString();
        }
        String methodName = pjd.getSignature().getName();
        try {
            //dataStr = java.net.URLDecoder.decode(dataStr,"utf-8");

            if (methodName == null || "".equals(methodName.trim())) {
                jot.put("result", "1005");
                return jot.toString();
            }
            JSONObject jso = JSONObject.parseObject(dataStr);
            if (!"getGuid".equals(methodName)) {
                String guid = jso.getString("guid") == null ? "" : jso.getString("guid").toString();
                if ("".equals(guid.trim())) {
                    jot.put("result", "2002");
                    return jot.toString();
                }
                String userName = checkID.checkGuid(guid);
                if ("".equals(userName) || "2005".equals(userName)) {
                    jot.put("result", "2005");
                    return jot.toString();
                }
                jso.put("userName", userName);
                dataStr = jso.toString();
                logger.info("调用接口名称: " + methodName + "    用户名: " + userName + "    调用接口类名: " + pjd.getSignature().getDeclaringTypeName());
            } else {
                String s = jso.getString("userName") == null ? "" : jso.getString("userName").toString();
                logger.info("调用接口名称: " + methodName + "    用户名: " + s + "    调用接口类名: " + pjd.getSignature().getDeclaringTypeName());
            }
            Object[] args = pjd.getArgs();
            args[0] = dataStr;
            result = pjd.proceed(args);
            return result == null ? jot.put("result", "1005").toString() : result.toString();
            //return result == null ? jot.put("result","1005").toString() : java.net.URLEncoder.encode(result.toString(),"utf-8");
        } catch (Throwable e) {
            e.printStackTrace();
            logger.error("出错接口名: " + methodName + "    接口类名: " + pjd.getSignature().getDeclaringTypeName() + "    错误内容: " + e.getMessage());
            jot.put("result", "1005");
            return jot.toString();
        }
    }
}
