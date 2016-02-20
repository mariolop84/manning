package es.manning.batchlayer;

import static es.manning.test.Data.makeEquiv;
import static es.manning.test.Data.makePageview;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

import backtype.hadoop.pail.Pail;
import backtype.hadoop.pail.Pail.TypedRecordOutputStream;

import es.manning.schema.GenderType;
import es.manning.tap.SplitDataPailStructure;
import es.manning.batchlayer.Constants;

public class Principal {

	static Logger logger = Logger.getLogger(Principal.class);

	public static void main(String[] args) {
		logger.info("Principal.main: INICIO");
		String entrada = args[0];
		logger.info("Principal.main.args[0]: " + entrada);
		try {
			switch (entrada) {
			case "initTestData":
				Principal.initTestData();
				break;
			case "ingest":
				Principal.ingest();
				break;
			default: 
				logger.info("Principal.main.Error: Entrada no valida: " + entrada);
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("Principal.main: FIN");

	}

	@SuppressWarnings("rawtypes")
	private static void ingest() {
		logger.info("Principal.ingest: INICIO");
		try {
			Pail masterPail = new Pail(Constants.MASTER_ROOT);
			Pail newDataPail = new Pail(Constants.NEW_ROOT);
			Ingestor ing = new Ingestor();
			ing.ingest(masterPail, newDataPail);
			logger.info("Principal.ingest: FIN");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void initTestData() throws Exception {
		FileSystem fs = FileSystem.get(new Configuration());
		fs.delete(new Path(Constants.DATA_ROOT), true);
		fs.delete(new Path(Constants.OUTPUTS_ROOT), true);
		fs.mkdirs(new Path(Constants.DATA_ROOT));
		fs.mkdirs(new Path(Constants.OUTPUTS_ROOT + "edb"));

		// Pail masterPail = Pail.create(MASTER_ROOT, new
		// SplitDataPailStructure());

		// Pail<Data> newPail = Pail.create(NEW_ROOT, new DataPailStructure());
		Pail<es.manning.schema.Data> newPail = Pail.create(Constants.NEW_ROOT, new SplitDataPailStructure());

		TypedRecordOutputStream os = newPail.openWrite();

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

	}

}
