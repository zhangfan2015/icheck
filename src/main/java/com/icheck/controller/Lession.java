package com.icheck.controller;

import com.google.gson.Gson;
import com.icheck.db.CheckinLog;
import com.icheck.db.LessionForTeacher;
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
    @RequestMapping(value="/getLessionForTeacher",produces = "text/json;charset=UTF-8")
    public String getLessionForTeacher(String date,String teacherId) {
        try {
            Query<LessionForTeacher> checkinLogQuery = datastore.createQuery(LessionForTeacher.class);
            checkinLogQuery.criteria(LessionForTeacher.FIELD_teacherId).equal(teacherId)
                    .criteria(LessionForTeacher.FIELD_date).equal(date);
            checkinLogQuery.order(LessionForTeacher.FIELD_startTime);
            List<LessionForTeacher> lessionForTeacherList = checkinLogQuery.asList();
            int i=1;
            for (LessionForTeacher l:lessionForTeacherList){
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
