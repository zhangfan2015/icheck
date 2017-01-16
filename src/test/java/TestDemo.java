import com.google.common.collect.Lists;
import com.icheck.db.CheckinLog;
import com.icheck.db.LessionForTeacher;
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
        init();
        LessionForTeacher lft = new LessionForTeacher();
        lft.setAddress("教室1");
        lft.setTeacherId("123");
        lft.setDate("2017-01-17");
        lft.setName("测试课程1");
        lft.setClassIds(Lists.newArrayList("软件1501","软件1502","软件1503"));
        lft.setStartTime(new Date().getTime());
        lft.setEndTime(new Date().getTime()+100);
        datastore.save(lft);

    }
}
