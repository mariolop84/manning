package es.manning.batchlayer;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import com.backtype.hadoop.pail.Pail;
import com.backtype.hadoop.pail.Pail.TypedRecordOutputStream;
import es.manning.tap.SplitDataPailStructure;
import es.manning.tap.DataPailStructure;
import es.manning.test.Data;

public class DataSource {
    public static final String ROOT = "/tmp/swaroot/";
    public static final String DATA_ROOT = ROOT + "data/";
    public static final String OUTPUTS_ROOT = ROOT + "outputs/";
    public static final String MASTER_ROOT = DATA_ROOT + "master";
    public static final String NEW_ROOT = DATA_ROOT + "new";


    public static void initTestData() throws Exception {
        FileSystem fs = FileSystem.get(new Configuration());
        fs.delete(new Path(DATA_ROOT), true);
        fs.delete(new Path(OUTPUTS_ROOT), true);
        fs.mkdirs(new Path(DATA_ROOT));
        fs.mkdirs(new Path(OUTPUTS_ROOT + "edb"));

        Pail masterPail = Pail.create(MASTER_ROOT, new SplitDataPailStructure());
        Pail<Data> newPail = Pail.create(NEW_ROOT, new DataPailStructure());

        TypedRecordOutputStream os = newPail.openWrite();
        Data data = new Data();
        os.writeObject(data.makePageview(1, "http://foo.com/post1", 60));
        os.writeObject(data.makePageview(3, "http://foo.com/post1", 62));
        os.writeObject(data.makePageview(1, "http://foo.com/post1", 4000));
        os.writeObject(data.makePageview(1, "http://foo.com/post2", 4000));
        os.writeObject(data.makePageview(1, "http://foo.com/post2", 10000));
        os.writeObject(data.makePageview(5, "http://foo.com/post3", 10600));
        os.writeObject(data.makeEquiv(1, 3));
        os.writeObject(data.makeEquiv(3, 5));

        os.writeObject(data.makePageview(2, "http://foo.com/post1", 60));
        os.writeObject(data.makePageview(2, "http://foo.com/post3", 62));

        os.close();

    }
    
    public static void main(String [ ] args){
    	try{
    	DataSource.initTestData();
    	}catch(Exception e){
    		
    	}
    }
}
