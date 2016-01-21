package es.manning.tap;

import java.manning.schema.Data;
/**
*A basic pail
*Clase para almacenar el objeto Data a partir de la clase
*ThriftPailStructure
*/
public class DataPailStructure extends ThriftPailStructure<Data> {
 /*Needed by ThriftPailStructure to
  *create an object for deserialization
  */
  @Override
  protected Data createThriftObject() {
    return new Data();
  }

 /*Specifies
  *that the pail
  *stores Data
  *objects*/
  public Class getType() {
    return Data.class;
  }
}
