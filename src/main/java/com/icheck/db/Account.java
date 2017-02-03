package com.icheck.db;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;

/**
 * 用户表1老师 2 学生
 */
@Entity(value = "Account", noClassnameStored = true)
public class Account {


    final public static  String FIELD_userId = "userId";
    final public static  String FIELD_userName = "userName";
    final public static  String FIELD_phoneNum = "phoneNum";
    final public static  String FIELD_role = "role";

    final public static  String FIELD_jwUserId = "jwUserId";
    final public static  String FIELD_jwUserPWD = "jwUserPWD";

    final public static String FIELD_schoolId = "schoolId";
    final public static String FIELD_schoolName = "schoolName";

    final public static  String FIELD_id = "id";

    @Id
    @Property(FIELD_id)
    public String id;

    @Property(FIELD_userId)
    public String userId;

    @Property(FIELD_userName)
    public String userName;

    @Property(FIELD_phoneNum)
    public String phoneNum;

    @Property(FIELD_role)
    public String role;

    @Property(FIELD_jwUserId)
    public String jwUserId;

    @Property(FIELD_jwUserPWD)
    public String jwUserPWD;

    @Property(FIELD_schoolId)
    public String schoolId;

    @Property(FIELD_schoolName)
    public String schoolName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getRole() {
        return role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getJwUserId() {
        return jwUserId;
    }

    public void setJwUserId(String jwUserId) {
        this.jwUserId = jwUserId;
    }

    public String getJwUserPWD() {
        return jwUserPWD;
    }

    public void setJwUserPWD(String jwUserPWD) {
        this.jwUserPWD = jwUserPWD;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
