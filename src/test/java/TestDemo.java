import com.icheck.db.CheckinLog;
import com.mongodb.MongoClient;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;


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
}
