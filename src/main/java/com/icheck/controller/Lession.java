package com.icheck.controller;

import com.google.gson.Gson;
import com.icheck.db.CheckinLog;
import com.icheck.db.Schedule;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2017/1/2.
 */

@Controller
@RequestMapping(value="/biz")
public class Lession {



    @Autowired
    private Datastore datastore;


    @ResponseBody
    @RequestMapping(value="/getLessionByRole",produces = "text/json;charset=UTF-8")
    public String getLessionByRole(String date,String userId,int role) {
        try {
            if(role==0){
                return "请重新登录";
            }
            Query<Schedule> checkinLogQuery = datastore.createQuery(Schedule.class);
            checkinLogQuery.criteria(Schedule.FIELD_date).equal(date);
            if(role==1){
                checkinLogQuery.criteria(Schedule.FIELD_teacherId).equal(userId);
            }else{
                checkinLogQuery.criteria(Schedule.FIELD_studentIds).hasThisOne(userId);
            }
            checkinLogQuery.order(Schedule.FIELD_startTime);
            List<Schedule> lessionForTeacherList = checkinLogQuery.asList();
            int i=1;
            for (Schedule l:lessionForTeacherList){
                l.setIndex(i++);
            }
            System.out.print(checkinLogQuery.toString()+"总计"+checkinLogQuery.count());
            return  new Gson().toJson(lessionForTeacherList);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @ResponseBody
    @RequestMapping(value="/demo",produces = "text/json;charset=UTF-8")
    public String aaaa() {
        try {
            Query<CheckinLog> checkinLogQuery = datastore.createQuery(CheckinLog.class);
            checkinLogQuery.criteria(CheckinLog.FIELD_name).equal("张三");
            for(CheckinLog i:checkinLogQuery.asList()){
                System.out.print(i.getName());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "aaa";
    }
}
