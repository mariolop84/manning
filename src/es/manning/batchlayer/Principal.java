package es.manning.batchlayer;

import es.manning.batchlayer.DataSource;

public class Principal {

	public static void main(String[] args) {

		try {
			System.out.println("Inicio");
			DataSource.initTestData();
			//DataSource.readLogins();
			System.out.println("fin");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
