package com.icheck.vo;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/2/11
 * Time: 21:20
 * To change this template use File | Settings | File Templates.
 */
public class PersonCheckinLogsResult {

    public String className;
    public int totalNum = 0;
    public int normalNum = 0;
    public int lateNum = 0;
    public int leaveNum = 0;
    public int absentNum = 0;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getNormalNum() {
        return normalNum;
    }

    public void setNormalNum(int normalNum) {
        this.normalNum = normalNum;
    }

    public int getLateNum() {
        return lateNum;
    }

    public void setLateNum(int lateNum) {
        this.lateNum = lateNum;
    }

    public int getLeaveNum() {
        return leaveNum;
    }

    public void setLeaveNum(int leaveNum) {
        this.leaveNum = leaveNum;
    }

    public int getAbsentNum() {
        return absentNum;
    }

    public void setAbsentNum(int absentNum) {
        this.absentNum = absentNum;
    }
}
