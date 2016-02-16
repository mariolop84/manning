package es.manning.batchlayer;

import backtype.hadoop.pail.Pail;
import es.manning.batchlayer.DataSource;

public class Principal {

	public static final String ROOT = "/tmp/swaroot/";
	public static final String DATA_ROOT = ROOT + "data/";
	public static final String OUTPUTS_ROOT = ROOT + "outputs/";
	public static final String MASTER_ROOT = DATA_ROOT + "master";
	public static final String NEW_ROOT = DATA_ROOT + "new";
	
	public static void main(String[] args) {

		try {
		
			Pail masterPail = new Pail(MASTER_ROOT);
			Pail newDataPail = new Pail(NEW_ROOT);
			Ingestor ing = new Ingestor();
			
			ing.ingest(masterPail, newDataPail);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
