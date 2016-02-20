package es.manning.batchlayer;

import static es.manning.test.Data.makeEquiv;
import static es.manning.test.Data.makePageview;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import backtype.hadoop.pail.Pail;
import backtype.hadoop.pail.Pail.TypedRecordOutputStream;
import backtype.hadoop.pail.PailStructure;
import es.manning.batchlayer.DataSource;
import es.manning.schema.Data;
import es.manning.tap.DataPailStructure;
import es.manning.tap.SplitDataPailStructure;
import es.manning.batchlayer.Constants;

public class Principal {

	public static void main(String[] args) {

		//Principal.ingest();
		Principal.initTestData();
	}
	
	private static void ingest(){
		try {
			Pail masterPail = new Pail(Constants.MASTER_ROOT);
			Pail newDataPail = new Pail(Constants.NEW_ROOT);
			Ingestor ing = new Ingestor();
			
			ing.ingest(masterPail, newDataPail);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void initTestData()  {
        FileSystem fs;
		try {
			fs = FileSystem.get(new Configuration());
			fs.delete(new Path(Constants.DATA_ROOT), true);
	        fs.delete(new Path(Constants.OUTPUTS_ROOT), true);
	        fs.mkdirs(new Path(Constants.DATA_ROOT));
	        fs.mkdirs(new Path(Constants.OUTPUTS_ROOT + "edb"));

	        Pail masterPail = Pail.create(Constants.MASTER_ROOT,  new SplitDataPailStructure());
	        Pail<Data> newPail = Pail.create(Constants.NEW_ROOT, new DataPailStructure());

	        TypedRecordOutputStream os = newPail.openWrite();
	        os.writeObject(makePageview(1, "http://foo.com/post1", 60));
	        os.writeObject(makePageview(3, "http://foo.com/post1", 62));
	        os.writeObject(makePageview(1, "http://foo.com/post1", 4000));
	        os.writeObject(makePageview(1, "http://foo.com/post2", 4000));
	        os.writeObject(makePageview(1, "http://foo.com/post2", 10000));
	        os.writeObject(makePageview(5, "http://foo.com/post3", 10600));
	        os.writeObject(makeEquiv(1, 3));
	        os.writeObject(makeEquiv(3, 5));

	        os.writeObject(makePageview(2, "http://foo.com/post1", 60));
	        os.writeObject(makePageview(2, "http://foo.com/post3", 62));

	        os.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }

}
