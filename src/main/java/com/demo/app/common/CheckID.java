package com.demo.app.common;

import com.demo.app.dao.MqygtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@Component
public class CheckID {

    @Autowired
    private MqygtMapper mqygtMapper;

    /**
     * 检查当前用户的验证串是否超时，未超时返回当前验证串，超时返回
     * @param guid
     * @return 未超时返回当前用户名，超时返回"2005"
     */
    public String checkGuid(String guid){
        Map map = mqygtMapper.getGuidByGuid(guid);
        if(map != null && map.size() > 0) {
            String uuid = map.get("guid") == null ? "" : map.get("guid").toString();
            String certCode = map.get("certCode") == null ? "" : map.get("certCode").toString();
            String userName = map.get("userName") == null ? "" : map.get("userName").toString();
            if(uuid == null || map.get("updateTime") == null){
                return "2005";
            }
            Date updateTime = (Date) map.get("updateTime");

            //当前时间
            long curren = System.currentTimeMillis();
            curren -= 60 * 60 * 1000;
            Date currenTime = new Date(curren);

            if (updateTime.compareTo(currenTime) < 0) {
                //System.out.println("身份验证不通过或GUID过期");
                return "2005";
            }
            //验证通过
            return userName;
        } else {
            return "2005";
        }
    }
}
