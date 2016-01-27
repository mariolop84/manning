package es.manning.tap;

import es.manning.tap.FieldStructure;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.thrift.TBase;
import org.apache.thrift.TFieldIdEnum;
import org.apache.thrift.TUnion;
import org.apache.thrift.meta_data.FieldMetaData;
import org.apache.thrift.meta_data.FieldValueMetaData;
import org.apache.thrift.meta_data.StructMetaData;

/*
*Clase para hacer el paricionado verrical a las propiedades
*Esta crea un conjunto de IDs de Thrift para cada clase propiedad
*/
public class PropertyStructure implements FieldStructure {

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
    /*System.out.println("PropertyStructure.isValidTarget: ... INICIO");
    System.out.println("dirs: " + dirs.length);
    for(String val: dirs){
      System.out.println(val);
    }*/
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
    //System.out.println("PropertyStructure.fillTarget: ... INICIO");
    //System.out.println("ret: " + ret.size());
    //for(String valor: ret){
    //  System.out.println(valor);
    //}
    ret.add("" + ((TUnion) ((TBase)val)
            .getFieldValue(valueId))
            .getSetField()
            .getThriftFieldId());
  }
}
