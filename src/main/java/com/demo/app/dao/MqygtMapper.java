package com.demo.app.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface MqygtMapper<T> {
    //获取验证串
    public Map<String,Object> getGuidByuserName(@Param("userName") String userName);
    public Map<String,Object> getGuidByGuid(@Param("guid") String guid);

    //新增验证串
    public int insertGuid(@Param("userName") String userName, @Param("guid") String guid, @Param("password") String password, @Param("certCode") String certCode);

    //更新验证串
    public int updateGuid(@Param("guid") String guid, @Param("userName") String userName);

    //获取待办列表
    public List<Map> getdbList(@Param("docIds") List docIds, @Param("dateStr") String dateStr, @Param("dateEnd") String dateEnd);

    // 查询正在运行任务信息
    public List<Map> queryTask(@Param("assignee") String assignee);

    // 查询流程实例信息
    public Map getBusinessKeyByprocInstId(@Param("procInstId") String procInstId);

    // 查询流程实例信息
    public T getdbById(T t);

    // 查询公文日志信息
    public List<Map> getDocLogs(@Param("docId") String docId);

    //获取待办列表
    public List<Map> getybList(@Param("userName") String userName, @Param("dateStr") String dateStr, @Param("dateEnd") String dateEnd);

    //获取收文待办列表
    public List<Map> readyReadList(@Param("userName") String userName, @Param("dateStr") String dateStr, @Param("dateEnd") String dateEnd);

    //获取收文待办列表
    public Map checkId(@Param("id") String id);

    // 查询流程实例信息
    public T getReadyRead(T t);

    //获取收文已办列表
    public List<Map> getAllDocTables(@Param("userName") String userName, @Param("dateStr") String dateStr, @Param("dateEnd") String dateEnd);

    //获取收文已办列表
    public List<T> getNotice(T t);

    //获取收文已办列表
    public List<Map> getAllDeTables();

    //获取个人工资明细
    public List<Map> getPersonWage(@Param("userName") String userName, @Param("dateStr") String dateStr, @Param("dateEnd") String dateEnd);

    //用户登陆
    public Map checkUser(@Param("userName") String userName);

    //获取医保资金查询列表
    public List<Map> listMedicalFundApply();

    //获取医保资金查询列表
    public List<Map> getAllEvaluation(@Param("userName") String userName);
}
