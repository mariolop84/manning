package es.manning.batchlayer;

import es.manning.batchlayer.DataSource;

public class Principal {

	public static void main(String[] args) {

		try {
			DataSource.initTestData();
		} catch (Exception e) {

		}

	}

}
