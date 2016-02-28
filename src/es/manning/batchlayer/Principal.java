package es.manning.batchlayer;

import com.backtype.hadoop.pail.Pail;
import es.manning.batchlayer.Constants;

public class Principal {

	
	public static void main(String[] args) {
		System.out.println("Principal.main: INICIO");
		String entrada = args[0];
		System.out.println("Principal.main.args[0]: " + entrada);
		DataSource ds = new DataSource();
		try {
			switch (entrada) {
			case "initTestData":
				ds.initTestData();
				ds.readData();
				break;
			case "ingest":
				Principal.ingest();
				ds.readData();
				break;
			default:
				System.out.println("Principal.main.Error: Entrada no valida: " + entrada);
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Principal.main: FIN");

	}

	@SuppressWarnings("rawtypes")
	private static void ingest() {
		System.out.println("Principal.ingest: INICIO");
		try {
			Pail masterPail = new Pail(Constants.MASTER_ROOT);
			Pail newDataPail = new Pail(Constants.NEW_ROOT);
			Ingestor ing = new Ingestor();
			ing.ingest(masterPail, newDataPail);
			System.out.println("Principal.ingest: FIN");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
