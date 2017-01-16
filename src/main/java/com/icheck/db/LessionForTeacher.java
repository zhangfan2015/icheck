package com.icheck.db;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/1/15
 * Time: 23:29
 * To change this template use File | Settings | File Templates.
 */
@Entity(value = "LessionForTeacher", noClassnameStored = true)
public class LessionForTeacher {


    final public static  String FIELD_teacherId = "teacherId";
    final public static  String FIELD_lessioinId = "lessioinId";
    final public static  String FIELD_address = "address";
    final public static  String FIELD_name = "name";
    final public static  String FIELD_startTime = "startTime";
    final public static  String FIELD_endTime = "endTime";
    final public static  String FIELD_classIds = "classIds";
    final public static  String FIELD_date = "date";
    final public static  String FIELD_time = "time";
    final public static  String FIELD_index = "index";


    @Property(FIELD_teacherId)
    public String teacherId;

    @Property(FIELD_lessioinId)
    public String lessioinId;//课程逻辑Id

    @Property(FIELD_name)
    public String name;//课程名称

    @Property(FIELD_address)
    public String address;//上课地址

    @Property(FIELD_startTime)
    public Long  startTime;

    @Property(FIELD_endTime)
    public Long endTime;

    @Property(FIELD_classIds)
    public List<String> classIds;//参课班级

    @Property(FIELD_date)
    public String date;//该课程记录日期

    @Property(FIELD_time)
    public String time;//上课起始时间

    @Property(FIELD_index)
    public Integer index;//当天课程索引


    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getLessioinId() {
        return lessioinId;
    }

    public void setLessioinId(String lessioinId) {
        this.lessioinId = lessioinId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<String> getClassIds() {
        return classIds;
    }

    public void setClassIds(List<String> classIds) {
        this.classIds = classIds;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
