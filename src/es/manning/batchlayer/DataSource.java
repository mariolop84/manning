package es.manning.batchlayer;

import org.apache.hadoop.fs.FileSystem;

import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;

import com.backtype.hadoop.pail.Pail;
import com.backtype.hadoop.pail.Pail.TypedRecordOutputStream;
import com.backtype.hadoop.pail.PailSpec;
import com.backtype.hadoop.pail.PailStructure;

import es.manning.tap.SplitDataPailStructure;
import es.manning.schema.*;

public class DataSource {

	public DataSource() {
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initTestData() throws Exception {
		System.out.println("Principal.initTestData: INICIO");
		Configuration conf = new Configuration();
		
		System.out.println("-----------------");
		System.out.println(conf.get("fs.defaultFS"));
		System.out.println(conf.get("fs.default.name"));
		System.out.println("-----------------");
		
		FileSystem fs = FileSystem.get(conf);
		fs.delete(new Path(Constants.DATA_ROOT), true);
		fs.delete(new Path(Constants.OUTPUTS_ROOT), true);
		fs.mkdirs(new Path(Constants.DATA_ROOT));
		fs.mkdirs(new Path(Constants.OUTPUTS_ROOT + "edb"));

		SplitDataPailStructure sdps = new SplitDataPailStructure();
		try {

			PailSpec ps = new PailSpec(sdps);
			System.out.println(ps == null);
			System.out.println(ps.getName());

			// Pail masterPail = Pail.create(Constants.MASTER_ROOT, sdps);
			//Pail masterPail = Pail.create(fs, Constants.MASTER_ROOT, sdps);
			Pail masterPail = Pail.create(fs, Constants.MASTER_ROOT, new PailSpec(sdps), true);
			System.out.println("en el error");
			Pail<es.manning.schema.Data> newPail = Pail.create(Constants.NEW_ROOT, sdps);
			TypedRecordOutputStream os = masterPail.openWrite();
			os.writeObject(es.manning.test.Data.makePageview(1, "http://foo.com/post1", 60));
			os.writeObject(es.manning.test.Data.makePageview(3, "http://foo.com/post1", 62));
			os.writeObject(es.manning.test.Data.makePageview(1, "http://foo.com/post1", 4000));
			os.writeObject(es.manning.test.Data.makePageview(1, "http://foo.com/post2", 4000));
			os.writeObject(es.manning.test.Data.makePageview(1, "http://foo.com/post2", 10000));
			os.writeObject(es.manning.test.Data.makePageview(5, "http://foo.com/post3", 10600));
			os.writeObject(es.manning.test.Data.makeEquiv(1, 3));
			os.writeObject(es.manning.test.Data.makeEquiv(3, 5));

			os.writeObject(es.manning.test.Data.makePageview(2, "http://foo.com/post1", 60));
			os.writeObject(es.manning.test.Data.makePageview(2, "http://foo.com/post3", 62));
			os.writeObject(es.manning.test.Data.makePersonPropertyValueFull_name(1, "Pepito"));
			os.writeObject(es.manning.test.Data.makePersonPropertyValueFull_name(2, "Pepita"));
			os.writeObject(es.manning.test.Data.makePersonPropertyValueGender(1, GenderType.MALE));
			os.writeObject(es.manning.test.Data.makePersonPropertyValueGender(2, GenderType.FEMALE));
			os.writeObject(es.manning.test.Data.makePersonPropertyValueLocation(1, "Miami", "FL", "USA"));
			os.writeObject(es.manning.test.Data.makePersonPropertyValueLocation(2, "LA", "California", "USA"));
			os.close();

			os = newPail.openWrite();
			os.writeObject(es.manning.test.Data.makePageview(7, "http://foo.com/post3", 60));
			os.writeObject(es.manning.test.Data.makePageview(7, "http://foo.com/post1", 62));
			os.writeObject(es.manning.test.Data.makePageview(9, "http://foo.com/post1", 60));
			os.writeObject(es.manning.test.Data.makePageview(9, "http://foo.com/post2", 4000));
			os.writeObject(es.manning.test.Data.makePersonPropertyValueFull_name(7, "Cata"));
			os.writeObject(es.manning.test.Data.makePersonPropertyValueGender(7, GenderType.FEMALE));
			os.writeObject(es.manning.test.Data.makePersonPropertyValueLocation(7, "Madrid", "MAD", "Spain"));
			os.writeObject(es.manning.test.Data.makePersonPropertyValueFull_name(9, "Mario"));
			os.writeObject(es.manning.test.Data.makePersonPropertyValueGender(9, GenderType.FEMALE));
			os.writeObject(es.manning.test.Data.makePersonPropertyValueLocation(9, "Madrid", "MAD", "Spain"));
			os.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Principal.initTestData: FIN");
	}

	// test to read
	public void readData() throws IOException {
		System.out.println("DataSource.readLogins: INICIO");
		Pail<es.manning.schema.Data> dataPail = new Pail<es.manning.schema.Data>(Constants.MASTER_ROOT);
		for (es.manning.schema.Data d : dataPail) {
			System.out.println(d.toString());
		}
		System.out.println("DataSource.readLogins: FIN");
	}

}
