package com.icheck.controller;

import com.google.gson.Gson;
import com.icheck.db.Account;
import com.icheck.db.School;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /**
     * 注册接口
     * @param schoolName
     * @param schoolId
     * @param jwUserId
     * @param jwPwd
     * @param phoneNumber
     * @param name
     * @param role
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/signUp" ,produces = "text/json;charset=UTF-8" )
    public String signUp(String schoolName,String schoolId,String jwUserId,String jwPwd,String phoneNumber ,String name,String role){

        try {
            Account a= new Account();
            a.setSchoolName(schoolName);
            a.setSchoolId(schoolId);
            a.setJwUserId(jwUserId);
            a.setJwUserPWD(jwPwd);
            a.setPhoneNum(phoneNumber);
            a.setRole(role);
            Key<Account> account = datastore.save(a);


        System.out.print(phoneNumber+name+role);
        return String.valueOf(account.getId());
        }catch (Exception e){
            e.printStackTrace();
            return null;
            //注册考虑校方登录校验的问题
        }
    }

}
