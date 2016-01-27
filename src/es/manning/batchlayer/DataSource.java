package es.manning.batchlayer;

import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import com.backtype.hadoop.pail.Pail;
import com.backtype.hadoop.pail.Pail.TypedRecordOutputStream;
import es.manning.tap.SplitDataPailStructure;
import es.manning.tap.DataPailStructure;
import es.manning.test.Data;
import es.manning.schema.*;

public class DataSource {
	public static final String ROOT = "/tmp/swaroot/";
	public static final String DATA_ROOT = ROOT + "data/";
	public static final String OUTPUTS_ROOT = ROOT + "outputs/";
	public static final String MASTER_ROOT = DATA_ROOT + "master";
	public static final String NEW_ROOT = DATA_ROOT + "new";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void initTestData() throws Exception {
		FileSystem fs = FileSystem.get(new Configuration());
		fs.delete(new Path(DATA_ROOT), true);
		fs.delete(new Path(OUTPUTS_ROOT), true);
		fs.mkdirs(new Path(DATA_ROOT));
		fs.mkdirs(new Path(OUTPUTS_ROOT + "edb"));

		//Pail masterPail = Pail.create(MASTER_ROOT, new SplitDataPailStructure());
		
		//Pail<Data> newPail = Pail.create(NEW_ROOT, new DataPailStructure());
		Pail<es.manning.schema.Data> newPail = Pail.create(NEW_ROOT, new SplitDataPailStructure());

		TypedRecordOutputStream os = newPail.openWrite();
		
		os.writeObject(Data.makePageview(1, "http://foo.com/post1", 60));
		os.writeObject(Data.makePageview(3, "http://foo.com/post1", 62));
		os.writeObject(Data.makePageview(1, "http://foo.com/post1", 4000));
		os.writeObject(Data.makePageview(1, "http://foo.com/post2", 4000));
		os.writeObject(Data.makePageview(1, "http://foo.com/post2", 10000));
		os.writeObject(Data.makePageview(5, "http://foo.com/post3", 10600));
		os.writeObject(Data.makeEquiv(1, 3));
		os.writeObject(Data.makeEquiv(3, 5));

		os.writeObject(Data.makePageview(2, "http://foo.com/post1", 60));
		os.writeObject(Data.makePageview(2, "http://foo.com/post3", 62));
	        os.writeObject(Data.makePersonPropertyValueFull_name(1, "Pepito"));
	        os.writeObject(Data.makePersonPropertyValueFull_name(2, "Pepita"));
        	os.writeObject(Data.makePersonPropertyValueGender(1, GenderType.MALE));
	        os.writeObject(Data.makePersonPropertyValueGender(2, GenderType.FEMALE));
        	os.writeObject(Data.makePersonPropertyValueLocation(1, "Miami", "FL", "USA"));
	        os.writeObject(Data.makePersonPropertyValueLocation(2,  "LA", "California", "USA"));
		os.close();

	}

	public static void readLogins() throws IOException {
		System.out.println("DataSource.readLogins: INICIO");
		Pail<es.manning.schema.Data> dataPail = new Pail<es.manning.schema.Data>(NEW_ROOT);
		for (es.manning.schema.Data d : dataPail) {
			System.out.println(d.toString());
		}
		System.out.println("DataSource.readLogins: FIN");
	}
}
