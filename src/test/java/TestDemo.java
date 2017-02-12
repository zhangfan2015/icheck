import com.google.common.collect.Lists;
import com.icheck.db.CheckinLog;
import com.icheck.db.Schedule;
import com.icheck.vo.CheckinLogsResult;
import com.mongodb.MongoClient;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.Date;


public class TestDemo {

    private Datastore datastore ;

    public void init() {
        datastore =  new Morphia().createDatastore(new MongoClient(), "iCheck");
    }

    @Test
    public void test1(){
        init();
        Query<CheckinLog> checkinLogQuery = datastore.createQuery(CheckinLog.class);
        checkinLogQuery.criteria(CheckinLog.FIELD_name).equal("张三");
        for(CheckinLog i:checkinLogQuery.asList()){
            System.out.print(i.getName());
        }
    }


    /**
     * 增加教师课程
     */
    @Test
    public void savelessionsForTeacher(){

        try {
            init();
            Schedule lft = new Schedule();
            lft.setAddress("教室1");
            lft.setTeacherId("123");
            lft.setDate("2017-02-01");
            lft.setName("测试课程1");
            lft.setClassIds(Lists.newArrayList("软件1501","软件1502","软件1503"));
            lft.setStudentIds(Lists.newArrayList("0001","0002"));
            lft.setStartTime(new Date().getTime());
            lft.setEndTime(new Date().getTime()+100);
            datastore.save(lft);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 签到假数据
     */
    @Test
    public void createCheckinlogs(){
        try{
            init();
            CheckinLog log = new CheckinLog();
            log.setCreateTime(System.currentTimeMillis());
            log.setName("张三");
            log.setCheckinStatus(0);
            log.setJwUserId("0001");
            log.setScheduleId("589b517f254dbebd9485244d");
            datastore.save(log);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
