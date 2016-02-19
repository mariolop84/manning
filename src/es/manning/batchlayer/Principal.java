package es.manning.batchlayer;

import backtype.hadoop.pail.Pail;
import es.manning.batchlayer.DataSource;
import es.manning.batchlayer.Constants;

public class Principal {

	public static void main(String[] args) {

		try {
		
			
			Pail masterPail = new Pail(Constants.MASTER_ROOT);
			Pail newDataPail = new Pail(Constants.NEW_ROOT);
			Ingestor ing = new Ingestor();
			
			ing.ingest(masterPail, newDataPail);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
