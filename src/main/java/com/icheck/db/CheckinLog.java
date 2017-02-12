package com.icheck.db;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;


@Entity(value = "checklog", noClassnameStored = true)
public class CheckinLog {


    final public static  String FIELD_id = "id";
    final public static String FIELD_name = "name";
    final public static  String FIELD_jwUserId = "jwUserId";
    final public static  String FIELD_createTime = "createTime";
    final public static  String FIELD_checkinStatus = "checkinStatus";
    final public static  String FIELD_scheduleId = "scheduleId";
    final public static  String FIELD_lessioinId = "lessioinId";
    final public static  String FIELD_picKey = "picKey";
    final public static  String FIELD_lat = "lat";
    final public static  String FIELD_lon = "lon";


    @Id
    @Property(FIELD_id)
    public String id;

    @Property(FIELD_name)
    public String name;

    @Property(FIELD_jwUserId)
    public String jwUserId;

    @Property(FIELD_createTime)
    public Long createTime;

    @Property(FIELD_checkinStatus)
    public int checkinStatus=0;//0:未签到；1缺席；2请假；3迟到；4已签

    @Property(FIELD_scheduleId)
    public String scheduleId;

    @Property(FIELD_picKey)
    public String picKey;

    @Property(FIELD_lat)
    public double lat;

    @Property(FIELD_lon)
    public double lon;

    @Property(FIELD_lessioinId)
    public String lessioinId;//课程逻辑Id

    public String time;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJwUserId() {
        return jwUserId;
    }

    public void setJwUserId(String jwUserId) {
        this.jwUserId = jwUserId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public int getCheckinStatus() {
        return checkinStatus;
    }

    public void setCheckinStatus(int checkinStatus) {
        this.checkinStatus = checkinStatus;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getPicKey() {
        return picKey;
    }

    public void setPicKey(String picKey) {
        this.picKey = picKey;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getLessioinId() {
        return lessioinId;
    }

    public void setLessioinId(String lessioinId) {
        this.lessioinId = lessioinId;
    }
}
