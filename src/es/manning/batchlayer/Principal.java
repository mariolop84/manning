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

import es.manning.schema.Data;
import es.manning.tap.DataPailStructure;
import es.manning.tap.SplitDataPailStructure;
import es.manning.batchlayer.Constants;

public class Principal {

	static Logger logger = Logger.getLogger(Principal.class);

	public static void main(String[] args) {
		logger.info("Principal.main: INICIO");
		logger.info("Principal.main.args[0]: " + args[0]);
		switch (args[0]) {
		case "initTestData":
			Principal.initTestData();
			break;
		case "ingesr":
			Principal.ingest();
			break;
		}
		logger.info("Principal.main: FIN");

	}

	@SuppressWarnings("rawtypes")
	private static void ingest() {
		logger.info("Principal.ingest: INICIO");
		try {
			Pail masterPail = new Pail(Constants.MASTER_ROOT);
			Pail newDataPail = new Pail(Constants.NEW_ROOT);
			Ingestor.ingest(masterPail, newDataPail);
			logger.info("Principal.ingest: FIN");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void initTestData() {
		logger.info("Principal.initTestData: INICIO");
		FileSystem fs;
		try {
			fs = FileSystem.get(new Configuration());
			fs.delete(new Path(Constants.DATA_ROOT), true);
			fs.delete(new Path(Constants.OUTPUTS_ROOT), true);
			fs.mkdirs(new Path(Constants.DATA_ROOT));
			fs.mkdirs(new Path(Constants.OUTPUTS_ROOT + "edb"));

			Pail masterPail = Pail.create(Constants.MASTER_ROOT, new SplitDataPailStructure());
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

			logger.info("Principal.initTestData: INICIO");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
