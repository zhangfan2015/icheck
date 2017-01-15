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

/**
 * Created by Administrator on 2017/1/2.
 */

@Controller
@RequestMapping(value="/biz")
public class Lession {



    @Autowired
    private Datastore datastore;


    @ResponseBody
    @RequestMapping(value="/getLessionForTeacher")
    public String getLessionForTeacher(String date,String teacherId) {
        try {

            Query<LessionForTeacher> checkinLogQuery = datastore.createQuery(LessionForTeacher.class);
            checkinLogQuery.criteria(LessionForTeacher.FIELD_teacherId).equal(teacherId)
                    .criteria(LessionForTeacher.FIELD_date).equal(date);
            return  new Gson().toJson(checkinLogQuery.asList());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @ResponseBody
    @RequestMapping(value="/demo")
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
