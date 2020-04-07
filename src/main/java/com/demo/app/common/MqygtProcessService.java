package com.demo.app.common;

import com.demo.app.dao.MqygtMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MqygtProcessService {

    @Autowired
    private MqygtMapper mqygtMapper;

    // 查询正在运行任务
    public Map<String,List<String>> queryTask(String assignee) {
        Map<String,List<String>> map = new HashMap<String,List<String>>();
        List<Map> tasks = mqygtMapper.queryTask(assignee);
        // 遍历任务列表
        if (tasks != null && tasks.size() > 0) {
            for (Map task : tasks) {
                String currentTaskN = task.get("taskName") == null ? "" : task.get("taskName").toString();
                String taskId = task.get("taskId") == null ? "" : task.get("taskId").toString();
                String procInstId = task.get("procInstId") == null ? "" : task.get("procInstId").toString();
                Map procInsMap = mqygtMapper.getBusinessKeyByprocInstId(procInstId);
                if(procInsMap != null && procInsMap.size() >0) {
                    String businessKey = procInsMap.get("businessKey") == null ? "" : procInsMap.get("businessKey").toString();
                    if (map != null && map.get(businessKey) != null) {
                        map.get(businessKey).add(currentTaskN);
                        map.get(businessKey).add(taskId);
                        map.get(businessKey).add(procInstId);
                    } else {
                        List<String> list = new ArrayList<String>();
                        list.add(currentTaskN);
                        list.add(taskId);
                        list.add(procInstId);
                        map.put(businessKey, list);
                    }
                }
            }
        }
        return map;
    }

}
