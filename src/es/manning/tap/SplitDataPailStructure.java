package es.manning.tap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.manning.schema.Data;
import java.manning.schema.DataUnit;
import java.manning.tap.DataPailStructure;
import org.apache.thrift.TBase;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.TUnion;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.StructMetaData;

/**
*A split pail to vertically partition the dataset
*Clase que representa una estructura Pail que implementa una 
*estrategia para el particionamient vertical del esquema de
*grafo.
*Crea un mapa entre los IDs Thrift y la clase el tipo de 
*estructura. PropertyStructure, EdgeStructure
*SplitDataPailStructure es responsable del directorio raiz
*del particionamiento vertical, y delega la responsabilidad
*de los subdirectorios a la clase FieldStructure
*/
public class SplitDataPailStructure extends DataPailStructure {

  public static HashMap<Short, FieldStructure> validFieldMap =
    new HashMap<Short, FieldStructure>();

 /*
  *getMetadataMap and getIdForClass are helper functions
  *for inspecting Thrift objects.
  */
  private static Map<TFieldIdEnum, FieldMetaData> 
    getMetadataMap(Class c) 
  {
    try {
      Object o = c.newInstance();
      return (Map) c.getField("metaDataMap").get(o);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }


  static {
 /*
  *Thrift code to inspect
  *and iterate over the
  *DataUnit object
  */
    for(DataUnit._Fields k: DataUnit.metaDataMap.keySet()) {
      FieldValueMetaData md = DataUnit.metaDataMap.get(k).valueMetaData;
      FieldStructure fieldStruct;
     /*
      *Properties are identified by the
      *class name of the inspected object.
      */
      if(md instanceof StructMetaData && ((StructMetaData) md)
         .structClass
         .getName()
         .endsWith("Property")) 
      {
        fieldStruct = new PropertyStructure(((StructMetaData) md)
                                            .structClass);
      } else {
	/*
	*If class name doesnt end with
	*Property, it must bean edge.
	*/
        fieldStruct = new EdgeStructure();
      }
      validFieldMap.put(k.getThriftFieldId(), fieldStruct);
    }
  }

  // methods are from SplitDataPailStructure
  
  @Override
  public boolean isValidTarget(String[] dirs) {
    if(dirs.length==0) return false;
    try {
      short id = Short.parseShort(dirs[0]);
      FieldStructure s = validFieldMap.get(id);
     /*
      *The validity check first verifies the
      *DataUnit field ID is in the field map.
      *Any additional checks are passed to the
      *FieldStructure.
      */
      if(s==null) return false;
      else return s.isValidTarget(dirs);
    } catch(NumberFormatException e) {
      return false;
    }
  }

  @Override
  public List<String> getTarget(Data object) {
    List<String> ret = new ArrayList<String>();
    DataUnit du = object.getDataunit();
    short id = du.getSetField().getThriftFieldId();
   /*
    *The top-level directory is determined by
    *inspecting the DataUnit
    */ 
    ret.add("" + id);
   /*
    *Any further partitioning is
    *passed to the FieldStructure.
    */
    validFieldMap.get(id).fillTarget(ret, du.getFieldValue());
    return ret;
  }
}
