package com.demo.app.service;

import java.io.UnsupportedEncodingException;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.cxf.jaxrs.model.wadl.DocTarget;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.cxf.jaxrs.model.wadl.Description;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import  java.net.URLDecoder;
import java.util.Date;
import java.util.List;
@Api(value = "/worker", description = "WORKER服务")
@Path("/worker")
@Transactional(isolation=Isolation.READ_COMMITTED,propagation=Propagation.REQUIRED ,readOnly=false,rollbackFor=Exception.class)
public class WorkerWebService {

    //工单-系统 基础数据 接口
    /*@PUT
    @Path("/updateBasic")
    @Produces("application/json;charset=UTF-8")
    public String updateBasic(BasicWeb webBasic) throws UnsupportedEncodingException {
        //BasicWeb是用来接收json数据的，我这里是服务端，客户端的BasicWeb应和 这里的BasicWeb一样，否则接收不到
        //逻辑处理代码
        return "success";
    }*/

    //restful风格传递数据有四种类型  @GET   @POST  @PUT  @DELETE  客户端代码与之相对应
    @GET
    @Path("/query/{id63}")
    //@Produces("application/json;charset=utf-8")
    public String queryUser(@PathParam("id63")Integer id) {
        System.out.println("in Server query user:"+id);
        return "queryUser返回："+id.toString();
    }
    /*@POST
    @Path("/add")

    public void insertUser(User user) {
        System.out.println("in Server insert User:"+user);
    }*/
    @PUT
    @Path("/update")
    public String updateUser() {
        System.out.println("in Server update User:");
        return "updateUser返回：";
    }
    @DELETE
    @Path("/delete/{id63}")
    public String deleteUser(@PathParam("id63")Integer id) {
        System.out.println("in Server delete User:"+id);
        return "updateUser返回："+id;
    }

}

