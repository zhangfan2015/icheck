package com.icheck.controller;

import com.google.gson.Gson;
import com.icheck.db.CheckinLog;
import com.icheck.db.Schedule;
import com.icheck.service.QiniuService;
import com.icheck.vo.CheckinLogsResult;
import com.icheck.vo.PersonCheckinLogsResult;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.Sort;
import org.mongodb.morphia.query.UpdateOperations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/1/2.
 */

@Controller
@RequestMapping(value="/biz")
public class Lession {



    @Autowired
    private Datastore datastore;

    @Autowired
    private QiniuService qiniuService;


    /**
     * 根据日期角色用户id 获取课程表
     * @param date
     * @param userId
     * @param role
     * @return
     */
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
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            for (Schedule l:lessionForTeacherList){
                l.setIndex(i++);
                l.setTime(sdf.format(new Date(l.getStartTime())));
            }
            System.out.print(checkinLogQuery.toString()+"总计"+checkinLogQuery.count());
            return  new Gson().toJson(lessionForTeacherList);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }


    @ResponseBody
    @RequestMapping(value="/getcheckinLogs",produces = "text/json;charset=UTF-8" )
    public String getcheckinLogs(String scheduleId) {
        try {

            CheckinLogsResult result = new CheckinLogsResult();
            Schedule schedule = datastore.get(Schedule.class, new ObjectId(scheduleId));
            result.setLessionName(schedule.getName());
            result.setClassNames(schedule.getClassIds());

            Query<CheckinLog> checkinLogQuery = datastore.createQuery(CheckinLog.class);
            checkinLogQuery.criteria(CheckinLog.FIELD_scheduleId).equal(scheduleId);
            checkinLogQuery.order(Sort.ascending(CheckinLog.FIELD_checkinStatus) , Sort.ascending(CheckinLog.FIELD_createTime));


            List<CheckinLog> list = checkinLogQuery.asList();
            if(list.size()>0){
                result.setCheckinLogs(list);
                result.setTotalNum(list.size());
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                for (CheckinLog c:list) {
                    if(null!=c.createTime)  c.setTime(sdf.format(new Date(c.getCreateTime())));
                    if(c.getCheckinStatus()==3||c.getCheckinStatus()==4) result.checkinNum++;
                }
                result.setCheckinRate(NumberFormat.getPercentInstance().format((double)result.checkinNum/(double)result.totalNum));
                System.out.print(checkinLogQuery.toString()+"总计"+checkinLogQuery.count());
            }

            return  new Gson().toJson(result);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 教师待签到接口
     * @param checkinLogId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/checkFromTeacher",produces = "text/json;charset=UTF-8" )
    public String checkFromTeacher(String checkinLogId,int op) {
        try {

            Query<CheckinLog> logQuery = datastore.createQuery(CheckinLog.class);
            logQuery.criteria(CheckinLog.FIELD_id).equal(new ObjectId(checkinLogId));
            UpdateOperations<CheckinLog> logOps  = datastore.createUpdateOperations(CheckinLog.class);
            logOps.set(CheckinLog.FIELD_checkinStatus,op);
            logOps.set(CheckinLog.FIELD_createTime, System.currentTimeMillis());
            datastore.updateFirst(logQuery, logOps);
            return getcheckinLogs(logQuery.asList().get(0).getScheduleId());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 簽到接口
     * @param request
     * @param mfile
     * @param name
     * @param userId
     * @param scheduleId
     * @param longitude
     * @param latitude
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/createCheckin",produces = "text/json;charset=UTF-8")
    public String createCheckin(HttpServletRequest request, @RequestParam(value = "file", required = false) MultipartFile mfile, String name, String userId, String scheduleId,String longitude,String latitude){
        try {

            if(null!=mfile){
                String path=request.getSession().getServletContext().getRealPath("/upload/");
                System.out.print( path+userId+name+scheduleId);
                File f=new File(path,name);
                mfile.transferTo(f);
                qiniuService.upload(f,userId+"/"+scheduleId+"/"+name);
            }
            //查出初始化的签到记录
            Query<CheckinLog> logQuery = datastore.createQuery(CheckinLog.class);
            logQuery.criteria(CheckinLog.FIELD_scheduleId).equal(scheduleId)
                    .criteria(CheckinLog.FIELD_jwUserId).equal(userId);
            System.out.print(logQuery.toString());
            CheckinLog log = logQuery.asList().get(0);
            //获取该签到记录的课程信息，对比时间 更新签到状态
            Schedule schedule = datastore.get(Schedule.class,new ObjectId(log.getScheduleId()));
            UpdateOperations<CheckinLog> logOps = datastore.createUpdateOperations(CheckinLog.class);
            if(name!=null){
                logOps.set(CheckinLog.FIELD_picKey,userId+"/"+scheduleId+"/"+name);
            }else{
                logOps.set(CheckinLog.FIELD_lat,Double.parseDouble(latitude));
                logOps.set(CheckinLog.FIELD_lon,Double.parseDouble(longitude));
            }
            if(schedule.startTime<System.currentTimeMillis()){
                logOps.set(CheckinLog.FIELD_checkinStatus,4);
            }else if(schedule.startTime<System.currentTimeMillis()&&schedule.endTime>System.currentTimeMillis()){
                logOps.set(CheckinLog.FIELD_checkinStatus,3);
            }else{
                //课程结束 无法签到
                return "1";
            }
            logOps.set(CheckinLog.FIELD_createTime,System.currentTimeMillis());
            datastore.updateFirst(logQuery,logOps);
        }catch (Exception e){
            e.printStackTrace();
            return "1";
        }
        return "0";
    }

    /**
     * 學生簽到界面統計顯示
     * @param lessionId
     * @param userId
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getPersonCheckinLog",produces = "text/json;charset=UTF-8")
    public String getPersonCheckinLog(String lessionId,String userId){

        PersonCheckinLogsResult result = new PersonCheckinLogsResult();
        Query<CheckinLog> logQuery = datastore.createQuery(CheckinLog.class);
        logQuery.criteria(CheckinLog.FIELD_lessioinId).equal(lessionId)
                .criteria(CheckinLog.FIELD_jwUserId).equal(userId);
        //0:未签到；1缺席；2请假；3迟到；4已签
        for(CheckinLog log:logQuery.asList()){
            if(log.checkinStatus==1)result.absentNum++;
            if(log.checkinStatus==2)result.leaveNum++;
            if(log.checkinStatus==3)result.lateNum++;
            if(log.checkinStatus==2)result.normalNum++;
        }
        result.setTotalNum((int)logQuery.count());
        return new Gson().toJson(result);
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
