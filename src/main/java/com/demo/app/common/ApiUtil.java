package com.demo.app.common;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

public class ApiUtil {
    public static String geturl() throws FileNotFoundException {
        Yaml yaml = new Yaml();
        URL url = ApiUtil.class.getClassLoader().getResource("application.yml");
        if (url != null) {
            //获取test.yaml文件中的配置数据，然后转换为obj，
            Object obj =yaml.load(new FileInputStream(url.getFile()));
            System.out.println(obj);
            //也可以将值转换为Map
            Map map =(Map)yaml.load(new FileInputStream(url.getFile()));
            System.out.println(map.get("ur"));
            return String.valueOf(map.get("ur"));
            //通过map我们取值就可以了.

        }
        return "";
    }
    public static String getString(String url) throws FileNotFoundException {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        // 创建Get请求
//        HttpGet httpGet = new HttpGet("http://localhost:8080/mqygt/manage?corstype=cors#/followUpManager/question");


//        HttpGet httpGet = new HttpGet(geturl()+"/ywgl/fwgl/getdbList?corstype=cors&corsusername="+name);
        HttpGet httpGet = new HttpGet(geturl()+url);

        // 响应模型
        CloseableHttpResponse response = null;
        String  responseStr="";
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                responseStr=EntityUtils.toString(responseEntity);
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + responseStr);

            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseStr;
    }
    public static String getPOSTString(String url,String params) throws FileNotFoundException {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(300 * 1000)
                .setConnectTimeout(300 * 1000)
                .build();
        // 创建Get请求
//        HttpGet httpGet = new HttpGet("http://localhost:8080/mqygt/manage?corstype=cors#/followUpManager/question");
        JSONObject jb=new JSONObject();

//        HttpGet httpGet = new HttpGet(geturl()+"/ywgl/fwgl/getdbList?corstype=cors&corsusername="+name);
        HttpPost httpPost = new HttpPost(geturl()+url);
        httpPost.setConfig(requestConfig);
        httpPost.setHeader("Content-Type","application/json;charset=utf-8");
        StringEntity postingString = new StringEntity(params,
                "utf-8");
        httpPost.setEntity(postingString);

        // 响应模型
        CloseableHttpResponse response = null;
        String  responseStr="";
        try {
            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpPost);
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                responseStr=EntityUtils.toString(responseEntity);
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + responseStr);

            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseStr;
    }
}
