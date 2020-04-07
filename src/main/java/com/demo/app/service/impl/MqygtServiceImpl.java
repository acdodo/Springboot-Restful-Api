package com.demo.app.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.app.common.AESSecurityUtil;
import com.demo.app.common.MD5Util;
import com.demo.app.common.MqygtProcessService;
import com.demo.app.dao.MqygtMapper;
import com.demo.app.entity.Notice;
import com.demo.app.entity.OfficialDoc;
import com.demo.app.entity.ReceiptOffice;
import com.grid.mqygtapp.service.MqygtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.util.*;

@Component
public class MqygtServiceImpl implements MqygtService {

    @Autowired
    private MqygtMapper mqygtMapper;

    @Autowired
    private MqygtProcessService mqygtProcessService;

    /**
     * 用户登陆
     * @param userName
     * @param pwd
     * @return {"result":1,"guid":""} result的值为"-1",表示账号不存在；"0"表示密码错误；"1"表示账号、密码正确，同时返回验证串(guid)
     */
    public JSONObject getGuid(String userName, String pwd){
        JSONObject jot = new JSONObject();
        Map userMap = mqygtMapper.checkUser(userName);
        if(userMap != null && userMap.size() > 0) {
            String username = userMap.get("USERNAME") == null ? "" : userMap.get("USERNAME").toString();
            String password = userMap.get("PASSWORD") == null ? "" : userMap.get("PASSWORD").toString();
            String realname = userMap.get("REALNAME") == null ? "" : userMap.get("REALNAME").toString();
            BASE64Encoder encoder = new BASE64Encoder();
            boolean flag=true;
            try {
                flag=password.equalsIgnoreCase(AESSecurityUtil.encrypt(MD5Util.toHex(MD5Util.hash(pwd))));
                pwd = encoder.encode(pwd.getBytes("utf-8"));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                flag=false;
                jot.put("result", "1999");
                return jot;
            }
            if (flag) {
                //账号、密码正确
                jot.put("result", "1");
                String guid = UUID.randomUUID().toString().replace("-", "");
                jot.put("guid", guid);
                Map map = mqygtMapper.getGuidByuserName(userName);
                if (map != null && map.size() > 0) {
                    mqygtMapper.updateGuid(guid, userName);
                } else {
                    pwd = MD5Util.hash(pwd).toString();
                    mqygtMapper.insertGuid(userName, guid, pwd, "");
                }
            } else {
                //密码错误
                jot.put("result", "2004");
            }
        }else {
            //账号不存在
            jot.put("result", "2003");
        }
        return jot;
    }


    /**
     *获取待办列表
     * @param userName
     * @return {"result":1,"num":3,data:[]}  result的值为0 表示获取待办列表失败；1表示获取待办列表成功,同时返回记录条数num和记录内容data；
     */
    public JSONObject getdbList(String userName,String dateStr,String dateEnd) {
        JSONObject jot = new JSONObject();
        
            Map<String, List<String>> strings = mqygtProcessService.queryTask(userName);
            if (strings != null && strings.size() > 0) {
                /*String sql1 = "";
                StringBuffer sb = new StringBuffer();
                String sql = "";*/
                List docIds = new ArrayList();
                for (Map.Entry<String, List<String>> str : strings.entrySet()) {
                    docIds.add(str.getKey().substring(str.getKey().lastIndexOf(".") + 1));
                }
                /*String str1 = sb.toString();
                sql1 = str1.substring(0, str1.length() - 2) + ")";*/


                //docDrafter = StringUtil.getSqlValidate(docDrafter);//替换非法字符，防止注入
                /*
                if (!"".equals(docDrafter) && docDrafter != null) {
                    sql += " and b.OFFICIAL_DOC_DRAFTER like '%" + docDrafter + "%'";
                }
                if (!"".equals(docId) && docId != null) {
                    sql += " and b.official_doc_id = '" + docId + "'";
                }
                if (!"".equals(docTitle) && docTitle != null) {
                    sql += " and b.OFFICIAL_DOC_TYPE like '%" + docTitle + "%'";
                }
                if (!"".equals(logDateStr) && logDateStr != null) {
                    sql1 += " and t.OFFICIAL_DOC_DATE >= to_date('" + logDateStr + "','yyyy-mm-dd')";
                }
                if (!"".equals(logDateEnd) && logDateEnd != null) {
                    sql1 += " and t.OFFICIAL_DOC_DATE <= to_date('" + logDateEnd + " 23:59:59','yyyy-mm-dd hh24:mi:ss')";
                }
                if (!"".equals(docDpt) && docDpt != null) {
                    sql += " and b.OFFICIAL_DOC_DPT='" + docDpt + "'";
                }
                if (!"".equals(docType) && docType != null) {
                    sql += " and b.OFFICIAL_DOC_TYPE='" + docType + "'";
                }*/
                //List param=new ArrayList();
                //sql = "select row_number() over(order by b.OFFICIAL_DOC_ID) as \"srnum\",b.OFFICIAL_DOC_ID as \"docId\", b.OFFICIAL_DOC_TYPE as \"docType\",b.OFFICIAL_DOC_TITLE as \"docTitle\",b.OFFICIAL_DOC_DRAFTER as \"docDrafter\",b.OFFICIAL_DOC_DPT as \"docDpt\",to_char(b.OFFICIAL_DOC_DATE,'yyyy-mm-dd hh24:mi:ss') as \"docDate\", b.OFFICIAL_DOC_UNIT1 as \"docunit1\", b.OFFICIAL_DOC_UNIT2 as \"docunit2\", b.OFFICIAL_DOC_ZH as \"doczh\" from YWGL_OFFICIAL_DOC b where b.OFFICIAL_DOC_STATUS != 10 " + sql1 + "order by b.OFFICIAL_DOC_DATE";
                //List list = sqlDao.queryAsList(sql);
                if(dateEnd != null && !"".equals(dateEnd.trim())){
                    dateEnd += " 23:59:59";
                }
                List<Map> list = mqygtMapper.getdbList(docIds,dateStr,dateEnd);
                if(list != null && list.size() >0) {
                    JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
                    jot.put("result","1");
                    jot.put("num",list.size());
                    jot.put("data",array);
                }else {
                    jot.put("result","1");
                    jot.put("num",0);
                }
                /*if (list != null && list.size() > 0) {

                    for (int i = 0; i < list.size(); i++) {
                        Map map = (Map) list.get(i);
                        String docid = map.get("docid").toString();
                        for (Map.Entry<String, List<String>> str : strings.entrySet()) {
                            if (str.getKey().contains(docid)) {
                                map.put("operresult", "待" + str.getValue().get(0));
                                map.put("taskid", str.getValue().get(1));
                            }
                        }
                    }
                }*/
            }else {
                jot.put("result","1");
                jot.put("num",0);
            }
        
        return jot;
    }

    /**
     *
     * @param userName
     * @param docId
     * @return {"result":1,"num":3,data:[]}  result的值为0 表示获取待办列表失败；1表示获取待办列表成功,同时
     */
    public JSONObject getdbById (String userName, String docId){
        JSONObject jot = new JSONObject();
        
            OfficialDoc officialDoc = new OfficialDoc();
            officialDoc.setId(docId);
            officialDoc = (OfficialDoc)mqygtMapper.getdbById(officialDoc);
            JSONObject docDetail = new JSONObject();
            if(officialDoc != null){
                docDetail.put("id",officialDoc.getId());
                docDetail.put("comeUnit",officialDoc.getComeUnit());
                docDetail.put("content",officialDoc.getContent());
                docDetail.put("crDate",officialDoc.getCrDate());
                docDetail.put("dpt",officialDoc.getDpt());
                docDetail.put("drafter",officialDoc.getDrafter());
                docDetail.put("remark",officialDoc.getRemark());
                docDetail.put("signdate",officialDoc.getSigndate());
                docDetail.put("status",officialDoc.getStatus());
                docDetail.put("title",officialDoc.getTitle());
                docDetail.put("unit1",officialDoc.getUnit1());
                docDetail.put("unit2",officialDoc.getUnit2());
                docDetail.put("upDate",officialDoc.getUpDate());
                docDetail.put("zh",officialDoc.getZh());
                jot.put("docDetail",docDetail);
            }
            List docLogs = mqygtMapper.getDocLogs(docId);
            if(docLogs != null && docLogs.size() >0) {
                JSONArray array = JSONArray.parseArray(JSON.toJSONString(docLogs));
                jot.put("num",docLogs.size());
                jot.put("docLogs",array);
            }else {
                jot.put("num",0);
            }
            jot.put("result","1");
        
        return jot;
    }

    /**
     *获取待办列表
     * @param userName
     * @return {"result":1,"num":3,data:[]}  result的值为0 表示获取待办列表失败；1表示获取待办列表成功,同时返回记录条数num和记录内容data；
     */
    public JSONObject getybList(String userName,String dateStr,String dateEnd) {
        JSONObject jot = new JSONObject();
        
            if(dateEnd != null && !"".equals(dateEnd.trim())){
                dateEnd += " 23:59:59";
            }
            List<Map> list = mqygtMapper.getybList(userName,dateStr,dateEnd);
            if(list != null && list.size() >0) {
                JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
                jot.put("result","1");
                jot.put("num",list.size());
                jot.put("data",array);
            }else {
                jot.put("result","1");
                jot.put("num",0);
            }

        
        return jot;
    }

    /**
     *获取待办收文列表
     * @param userName
     * @return {"result":1,"num":3,data:[]}  result的值为0 表示获取待办列表失败；1表示获取待办列表成功,同时返回记录条数num和记录内容data；
     */
    public JSONObject readyReadList(String userName,String dateStr,String dateEnd) {
        JSONObject jot = new JSONObject();
        
            if(dateEnd != null && !"".equals(dateEnd.trim())){
                dateEnd += " 23:59:59";
            }
            List<Map> list = mqygtMapper.readyReadList(userName,dateStr,dateEnd);
            if(list != null && list.size() >0) {
                JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
                jot.put("result","1");
                jot.put("num",list.size());
                jot.put("data",array);
            }else {
                jot.put("result","1");
                jot.put("num",0);
            }
        
        return jot;
    }

    /**
     *获取待办收文列表
     * @param userName
     * @return {"result":1,"num":3,data:[]}  result的值为0 表示获取待办列表失败；1表示获取待办列表成功,同时返回记录条数num和记录内容data；
     */
    public JSONObject getReadyRead(String userName, String taskId) {
        JSONObject jot = new JSONObject();
        
            Map map = mqygtMapper.checkId(taskId);
            if(map != null){
                String businessKey = map.get("BUSINESS_KEY_") == null ? "" : map.get("BUSINESS_KEY_").toString();
                if(businessKey != null && !"".equals(businessKey.trim())) {
                    // 收文表的id
                    String receiptId = businessKey.substring(businessKey.indexOf(".") + 1);
                    if(receiptId != null && !"".equals(receiptId.trim())) {
                        ReceiptOffice receiptOffice = new ReceiptOffice();
                        receiptOffice.setId(Integer.parseInt(receiptId));
                        receiptOffice = (ReceiptOffice)mqygtMapper.getReadyRead(receiptOffice);
                        JSONObject receiptOfficeDetail = new JSONObject();

                        receiptOfficeDetail.put("id", receiptOffice.getId());
                        receiptOfficeDetail.put("title", receiptOffice.getTitle());
                        receiptOfficeDetail.put("identifier", receiptOffice.getIdentifier());
                        receiptOfficeDetail.put("createdate", receiptOffice.getCreatedate());
                        receiptOfficeDetail.put("type", receiptOffice.getType());
                        receiptOfficeDetail.put("urgent", receiptOffice.getUrgent());
                        receiptOfficeDetail.put("keyword", receiptOffice.getKeyword());
                        receiptOfficeDetail.put("unit", receiptOffice.getUnit());
                        receiptOfficeDetail.put("enddate", receiptOffice.getEnddate());
                        receiptOfficeDetail.put("dutysubject", receiptOffice.getDutysubject());
                        receiptOfficeDetail.put("booker", receiptOffice.getBooker());
                        receiptOfficeDetail.put("bookdate", receiptOffice.getBookdate());
                        receiptOfficeDetail.put("flow", receiptOffice.getFlow());
                        receiptOfficeDetail.put("imitate", receiptOffice.getImitate());
                        receiptOfficeDetail.put("passview", receiptOffice.getPassview());
                        receiptOfficeDetail.put("daoview", receiptOffice.getDaoview());
                        receiptOfficeDetail.put("passround", receiptOffice.getPassround());
                        receiptOfficeDetail.put("remark", receiptOffice.getRemark());
                        receiptOfficeDetail.put("status", receiptOffice.getStatus());
                        receiptOfficeDetail.put("account", receiptOffice.getAccount());
                        receiptOfficeDetail.put("content", receiptOffice.getContent());
                        jot.put("receiptOfficeDetail",receiptOfficeDetail);


                    }else {
                        jot.put("num",0);
                    }
                }else {
                    jot.put("num",0);
                }
            }else {
                jot.put("num",0);
            }
            jot.put("result","1");
        
        return jot;
    }

    /**
     *固定资产列表
     * @param userName
     * @return
     */
    public JSONObject getAllDocTables(String userName,String dateStr,String dateEnd) {
        JSONObject jot = new JSONObject();
        
            if(dateEnd != null && !"".equals(dateEnd.trim())){
                dateEnd += " 23:59:59";
            }
            List<Map> list = mqygtMapper.getAllDocTables(userName,dateStr,dateEnd);
            if(list != null && list.size() >0) {
                JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
                jot.put("result","1");
                jot.put("num",list.size());
                jot.put("data",array);
            }else {
                jot.put("result","1");
                jot.put("num",0);
            }
        
        return jot;
    }

    /**
     *获取通知公告列表
     * @param userName
     * @return
     */
    public JSONObject getNotice(String userName) {
        JSONObject jot = new JSONObject();
        
        List<Notice> list = mqygtMapper.getNotice(new Notice());
            if(list != null && list.size() >0) {
                JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
                jot.put("result","1");
                jot.put("num",list.size());
                jot.put("data",array);
            }else {
                jot.put("result","1");
                jot.put("num",0);
            }
        
        return jot;
    }

    /**
     *获取通知公告列表
     * @param userName
     * @return
     */
    public JSONObject getAllDeTables(String userName) {
        JSONObject jot = new JSONObject();

        List<Map> list = mqygtMapper.getAllDeTables();
            if(list != null && list.size() >0) {
                JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
                jot.put("result","1");
                jot.put("num",list.size());
                jot.put("data",array);
            }else {
                jot.put("result","1");
                jot.put("num",0);
            }
        
        return jot;
    }

    /**
     *获取个人工资明细
     * @param userName
     * @return
     */
    public JSONObject getPersonWage(String userName,String dateStr,String dateEnd) {
        JSONObject jot = new JSONObject();
        
            if(dateEnd != null && !"".equals(dateEnd.trim())){
                dateEnd += " 23:59:59";
            }
            List<Map> list = mqygtMapper.getPersonWage(userName,dateStr,dateEnd);
            if(list != null && list.size() >0) {
                JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
                jot.put("result","1");
                jot.put("num",list.size());
                jot.put("data",array);
            }else {
                jot.put("result","1");
                jot.put("num",0);
            }
        
        return jot;
    }

    /**
     *获取医保资金查询列表
     * @param userName
     * @return
     */
    public JSONObject listMedicalFundApply(String userName) {
        JSONObject jot = new JSONObject();
        
            List<Map> list = mqygtMapper.listMedicalFundApply();
            if(list != null && list.size() >0) {
                JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
                jot.put("result","1");
                jot.put("num",list.size());
                jot.put("data",array);
            }else {
                jot.put("result","1");
                jot.put("num",0);
            }
        
        return jot;
    }

    /**
     *获取人员绩效信息
     * @param userName
     * @return
     */
    public JSONObject getAllEvaluation(String userName) {
        JSONObject jot = new JSONObject();
        
            List<Map> list = mqygtMapper.getAllEvaluation(userName);
            if(list != null && list.size() >0) {
                JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
                jot.put("result","1");
                jot.put("num",list.size());
                jot.put("data",array);
            }else {
                jot.put("result","1");
                jot.put("num",0);
            }
        
        return jot;
    }
}
