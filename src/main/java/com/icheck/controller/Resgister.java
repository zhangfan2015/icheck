package com.icheck.controller;

import com.google.gson.Gson;
import com.icheck.db.School;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.net.www.http.HttpClient;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/1/12
 * Time: 0:42
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/register")
public class Resgister {

    @Autowired
    public Datastore datastore;

    /**
     * 注册页面获取全国学校信息
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getSchool" ,produces = "text/json;charset=UTF-8" )
    public String getSchoolList(){

        Query<School> schoolQuery = datastore.createQuery(School.class);
        return new Gson().toJson(schoolQuery.asList());

    }
    @ResponseBody
    @RequestMapping(value="/signUp" ,produces = "text/json;charset=UTF-8" )
    public String signUp(String phoneNumber ,String name ,String pwd){

        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet get = new HttpGet("http://jw.djtu.edu.cn/academic/j_acegi_security_check");

            HttpResponse response = httpClient.execute(get);
        }catch (Exception e){
            //注册考虑校方登录校验的问题
        }


        System.out.print(phoneNumber+name+pwd);
        return "success";

    }

}
