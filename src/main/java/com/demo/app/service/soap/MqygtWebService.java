package com.demo.app.service.soap;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface MqygtWebService {

    //登陆获取验证串
    public String getGuid(@WebParam(name = "dataStr") String dataStr);

    //获取待办列表
    public String getdbList(@WebParam(name = "dataStr") String dataStr);

    //获取待办公文详细
    public String getdbById(@WebParam(name = "dataStr") String dataStr);

    //获取已办列表
    public String getybList(@WebParam(name = "dataStr") String dataStr);

    //获取收文待办列表
    public String readyReadList(@WebParam(name = "dataStr") String dataStr);

    //获取收文待办详细
    public String getReadyRead(@WebParam(name = "dataStr") String dataStr);

    //获取收文已办详细
    public String getAllDocTables(@WebParam(name = "dataStr") String dataStr);

    //获取通知公告列表
    public String getNotice(@WebParam(name = "dataStr") String dataStr);

    //获取收文已办详细
    public String getAllDeTables(@WebParam(name = "dataStr") String dataStr);

    //获取个人工资明细
    public String getPersonWage(@WebParam(name = "dataStr") String dataStr);

    //获取医保资金查询列表
    public String listMedicalFundApply(@WebParam(name = "dataStr") String dataStr);

    //获取人员绩效信息
    public String getAllEvaluation(@WebParam(name = "dataStr") String dataStr);

}
