package java.manning.tap;

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

 /*
  *FieldStructure is an interface for both edges
  *and properties.
  */
  protected static interface FieldStructure {
    public boolean isValidTarget(String[] dirs);
    public void fillTarget(List<String> ret, Object val);
  }
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

 /*
  *Clase para el particionaminto vertical de los Struct o Edges
  *o Relaciones. Clase trivial porque los struct no se particionan
  */
  protected static class EdgeStructure implements FieldStructure {
    public boolean isValidTarget(String[] dirs) { return true; }
    public void fillTarget(List<String> ret, Object val) { }
  }

 /*
  *Clase para hacer el paricionado verrical a las propiedades
  *Esta crea un conjunto de IDs de Thrift para cada clase propiedad
  */
  protected static class PropertyStructure implements FieldStructure {

   /*
    *Property is a Thrift struct containing a property value
    *field; this is the ID for that field.
    */
    private TFieldIdEnum valueId;

   /*
    *The set of Thrift IDs of the property
    *value types
    */
    private HashSet<Short> validIds;

  /*
   *getMetadataMap and getIdForClass are helper functions
   *for inspecting Thrift objects.
   */
   private static TFieldIdEnum getIdForClass(
      Map<TFieldIdEnum, FieldMetaData> meta,
      Class toFind) 
    {
      for(TFieldIdEnum k: meta.keySet()) {
        FieldValueMetaData md = meta.get(k).valueMetaData;
        if(md instanceof StructMetaData) {
          if(toFind.equals(((StructMetaData) md).structClass)) {
            return k;
          }
        }
      }
      throw new RuntimeException("Could not find " + toFind.toString() +
                                 " in " + meta.toString());
    }

    public PropertyStructure(Class prop) {
      try {
        Map<TFieldIdEnum, FieldMetaData> propMeta = getMetadataMap(prop);
        Class valClass = Class.forName(prop.getName() + "Value");
       /*
        *Parses the Thrift metadata to get the field ID of the
        *property value
        */
        valueId = getIdForClass(propMeta, valClass);

        validIds = new HashSet<Short>();
        Map<TFieldIdEnum, FieldMetaData> valMeta = getMetadataMap(valClass);
        for(TFieldIdEnum valId: valMeta.keySet()) {
         /*
          *Parses the metadata to get all valid field IDs
          *of the property value
          */
          validIds.add(valId.getThriftFieldId());
        }
      } catch(Exception e) {
        throw new RuntimeException(e);
      }
    }

    public boolean isValidTarget(String[] dirs) {
     /*
      *The vertical partitioning of a property value has a
      *depth of at least two.
      */
      if(dirs.length<2) return false;
      try {
        short s = Short.parseShort(dirs[1]);
        return validIds.contains(s);
      } catch(NumberFormatException e) {
        return false;
      }
    }
  
    public void fillTarget(List<String> ret, Object val) {
     /*
      *Uses the Thrift IDs to create the directory
      *path for the current fact
      */
      ret.add("" + ((TUnion) ((TBase)val)
              .getFieldValue(valueId))
              .getSetField()
              .getThriftFieldId());
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
