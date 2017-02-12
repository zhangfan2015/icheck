package com.icheck.vo;

import com.icheck.db.CheckinLog;

import java.util.List;

/**
 * 课程信息及签到情况
 */
public class CheckinLogsResult {

    public String lessionName;
    public List<String> classNames;
    public int totalNum;
    public int checkinNum;
    public String CheckinRate="0%";
    public List<CheckinLog> checkinLogs;

    public String getLessionName() {
        return lessionName;
    }

    public void setLessionName(String lessionName) {
        this.lessionName = lessionName;
    }

    public List<String> getClassNames() {
        return classNames;
    }

    public void setClassNames(List<String> classNames) {
        this.classNames = classNames;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getCheckinNum() {
        return checkinNum;
    }

    public void setCheckinNum(int checkinNum) {
        this.checkinNum = checkinNum;
    }

    public String getCheckinRate() {
        return CheckinRate;
    }

    public void setCheckinRate(String checkinRate) {
        CheckinRate = checkinRate;
    }

    public List<CheckinLog> getCheckinLogs() {
        return checkinLogs;
    }

    public void setCheckinLogs(List<CheckinLog> checkinLogs) {
        this.checkinLogs = checkinLogs;
    }
}
