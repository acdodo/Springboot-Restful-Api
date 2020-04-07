package com.demo.app.service.soap.impl;

import com.alibaba.fastjson.JSONObject;
import com.grid.mqygtapp.service.MqygtService;
import com.demo.app.service.soap.MqygtWebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jws.WebService;
@Component
@WebService(targetNamespace = "http://soap.service.mqygtapp.grid.com/",endpointInterface = "com.demo.app.service.soap.MqygtWebService")
public class MqygtWebServiceImpl implements MqygtWebService {

    @Autowired
    MqygtService mqygtService;

    /**
     *
     * @param dataStr
     * @return {"result":1,"userName":""} result的值为"2002",表示缺少参数；"2005"表示验证串过期；"1005"数据库异常；"-1"表示账号不存在；"0"表示密码错误；"1"表示账号、密码正确，同时返回验证串(userName);
     */
    public String getGuid(String dataStr){
        JSONObject jot = new JSONObject();
        JSONObject jso = JSONObject.parseObject(dataStr);
        String userName = jso.getString("userName") == null ? "" : jso.getString("userName").toString();
        String pwd = jso.getString("pwd") == null ? "" : jso.getString("pwd").toString();
        if("".equals(userName.trim()) || "".equals(pwd.trim())){
            jot.put("result","2002");
            return jot.toString();
        }
        jot = mqygtService.getGuid(userName, pwd);
        
        return jot.toString();
    }

    public String getdbList(String dataStr){
        JSONObject jot = new JSONObject();
        JSONObject jso = JSONObject.parseObject(dataStr);
        String userName = jso.getString("userName") == null ? "" : jso.getString("userName").toString();
        String dateStr = jso.getString("dateStr");
        String dateEnd = jso.getString("dateEnd");
        if(dateStr != null && "".equals(dateStr.trim())){
            dateStr = null;
        }
        if(dateEnd != null && "".equals(dateEnd.trim())){
            dateEnd = null;
        }
        jot = mqygtService.getdbList(userName, dateStr, dateEnd);
        return jot.toString();
    }

    public String getdbById(String dataStr){
        JSONObject jot = new JSONObject();
        JSONObject jso = JSONObject.parseObject(dataStr);
        String userName = jso.getString("userName") == null ? "" : jso.getString("userName").toString();
        String docId = jso.getString("docId") == null ? "" : jso.getString("docId").toString();
        if("".equals(userName.trim()) || "".equals(docId.trim())){
            jot.put("result","2002");
            return jot.toString();
        }
        jot = mqygtService.getdbById(userName,docId);
        
        return jot.toString();
    }

    public String getybList(String dataStr){
        JSONObject jot = new JSONObject();
        JSONObject jso = JSONObject.parseObject(dataStr);
        String userName = jso.getString("userName") == null ? "" : jso.getString("userName").toString();
        String dateStr = jso.getString("dateStr");
        String dateEnd = jso.getString("dateEnd");
        if(dateStr != null && "".equals(dateStr.trim())){
            dateStr = null;
        }
        if(dateEnd != null && "".equals(dateEnd.trim())){
            dateEnd = null;
        }

        jot = mqygtService.getybList(userName,dateStr,dateEnd);
        
        return jot.toString();
    }

    public String readyReadList(String dataStr){
        JSONObject jot = new JSONObject();
        JSONObject jso = JSONObject.parseObject(dataStr);
        String userName = jso.getString("userName") == null ? "" : jso.getString("userName").toString();
        String dateStr = jso.getString("dateStr");
        String dateEnd = jso.getString("dateEnd");
        if(dateStr != null && "".equals(dateStr.trim())){
            dateStr = null;
        }
        if(dateEnd != null && "".equals(dateEnd.trim())){
            dateEnd = null;
        }

        jot = mqygtService.readyReadList(userName,dateStr,dateEnd);
        
        return jot.toString();
    }

    public String getReadyRead(String dataStr){
        JSONObject jot = new JSONObject();
        JSONObject jso = JSONObject.parseObject(dataStr);
        String userName = jso.getString("userName") == null ? "" : jso.getString("userName").toString();
        String taskId = jso.getString("taskId") == null ? "" : jso.getString("taskId").toString();
        if("".equals(userName.trim()) || "".equals(taskId.trim())){
            jot.put("result","2002");
            return jot.toString();
        }
        jot = mqygtService.getReadyRead(userName,taskId);
        
        return jot.toString();
    }

    public String getAllDocTables(String dataStr){
        JSONObject jot = new JSONObject();
        JSONObject jso = JSONObject.parseObject(dataStr);
        String userName = jso.getString("userName") == null ? "" : jso.getString("userName").toString();
        String dateStr = jso.getString("dateStr");
        String dateEnd = jso.getString("dateEnd");
        if(dateStr != null && "".equals(dateStr.trim())){
            dateStr = null;
        }
        if(dateEnd != null && "".equals(dateEnd.trim())){
            dateEnd = null;
        }

        jot = mqygtService.getAllDocTables(userName,dateStr,dateEnd);
        
        return jot.toString();
    }

    public String getNotice(String dataStr){
        JSONObject jot = new JSONObject();
        JSONObject jso = JSONObject.parseObject(dataStr);
        String userName = jso.getString("userName") == null ? "" : jso.getString("userName").toString();

        jot = mqygtService.getNotice(userName);
        
        return jot.toString();
    }

    public String getAllDeTables(String dataStr){
        JSONObject jot = new JSONObject();
        JSONObject jso = JSONObject.parseObject(dataStr);
        String userName = jso.getString("userName") == null ? "" : jso.getString("userName").toString();

        jot = mqygtService.getAllDeTables(userName);
        
        return jot.toString();
    }

    public String getPersonWage(String dataStr){
        JSONObject jot = new JSONObject();
        JSONObject jso = JSONObject.parseObject(dataStr);
        String userName = jso.getString("userName") == null ? "" : jso.getString("userName").toString();
        String dateStr = jso.getString("dateStr");
        String dateEnd = jso.getString("dateEnd");
        if(dateStr != null && "".equals(dateStr.trim())){
            dateStr = null;
        }
        if(dateEnd != null && "".equals(dateEnd.trim())){
            dateEnd = null;
        }

        jot = mqygtService.getPersonWage(userName,dateStr,dateEnd);
        
        return jot.toString();
    }

    public String listMedicalFundApply(String dataStr){
        JSONObject jot = new JSONObject();
        JSONObject jso = JSONObject.parseObject(dataStr);
        String userName = jso.getString("userName") == null ? "" : jso.getString("userName").toString();

        jot = mqygtService.listMedicalFundApply(userName);
        
        return jot.toString();
    }

    public String getAllEvaluation(String dataStr){
        JSONObject jot = new JSONObject();
        JSONObject jso = JSONObject.parseObject(dataStr);
        String userName = jso.getString("userName") == null ? "" : jso.getString("userName").toString();

        jot = mqygtService.getAllEvaluation(userName);
        
        return jot.toString();
    }
}
