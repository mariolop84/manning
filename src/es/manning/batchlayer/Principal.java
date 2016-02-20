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
				DataSource.initTestData();
				DataSource.readData();
				break;
			case "ingest":
				Principal.ingest();
				DataSource.readData();
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

	
}
