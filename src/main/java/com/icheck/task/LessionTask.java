package com.icheck.task;

import com.google.common.collect.Lists;
import com.icheck.db.Schedule;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.util.resources.cldr.aa.CalendarData_aa_ER;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/1/17
 * Time: 0:47
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/lessionTask")
public class LessionTask {

    /**
     * 4個老師 0001老師為測試用戶 老師每天兩堂課
     * 30個學生 0001學生為測試用戶 學生每天4堂課
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/initLession" ,produces = "text/json;charset=UTF-8" )
    public String initLession(){
        Calendar c = Calendar.getInstance();
        for(int i=0;i<30;i++){
            c.add(Calendar.DAY_OF_MONTH,i-15);
            Date day = c.getTime();
            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm");
            Schedule s = new Schedule();
            s.setDate(sdf1.format(day));
            s.setTime(sdf2.format(day));
            s.setTeacherId("0001");
            s.setName("高等数学");
            s.setAddress("主楼701教室");
            s.setClassIds(Lists.newArrayList("计算机科学1701","计算机科学1702","计算机科学1703"));
            s.setStudentIds(Lists.newArrayList("1","2","3","4","5","6","7","8","9","10","11",
                    "12","13","14","15","16","17","18","19","20",
                    "21","22","23","24","25","26","27","28","29","30"));

        }
        return null;
    }

}
