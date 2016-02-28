package es.manning.tap;

import es.manning.schema.Data;
import java.util.Collections;
import java.util.List;

/**
 * A basic pail Clase para almacenar el objeto Data a partir de la clase
 * ThriftPailStructure
 */
public class DataPailStructure extends ThriftPailStructure<Data> {

	private static final long serialVersionUID = 792950998154173675L;

	/*
	 * Needed by ThriftPailStructure to create an object for deserialization
	 */
	@Override
	protected Data createThriftObject() {
		return new Data();
	}

	/*
	 * Specifies that the pail stores Data objects
	 */
	@SuppressWarnings("rawtypes")
	public Class getType() {
		return Data.class;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getTarget(Data object) {
		System.out.println("DataPailStructure.getTarget: ... INICIO");
		return Collections.EMPTY_LIST;
	}
	
	@Override
	public boolean isValidTarget(String... dirs) {
		System.out.println("DataPailStructure.isValidTarget: ... INICIO");
		return true;
	}
}
