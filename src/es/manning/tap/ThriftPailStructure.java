package es.manning.tap;

import backtype.hadoop.pail.PailStructure;
import java.util.Collections;
import java.util.List;
import org.apache.thrift.TBase;
import org.apache.thrift.TDeserializer;
import org.apache.thrift.TException;
import org.apache.thrift.TSerializer;

/**
 *A structured pail for Thrift objects
 *Clase que modela una estructura para serializar y deserializar datos.
 *La clase generica permite usar la estructura Pail para que sea usada 
 *por cualquier objeto Thrift
*/

public abstract class ThriftPailStructure<T extends Comparable> 
  implements PailStructure<T> 
{
 /*The constructor
  *of the Thrift
  *object must be
  *implemented in
  *the child class.
  */
  protected abstract T createThriftObject();

 /*
  *TSerializer and
  *TDeserializer are Thrift
  *utilities for serializing
  *objects to and from
  */
  private transient TDeserializer des;
  private transient TSerializer ser;


 /*
  *The Thrift utilities
  *are lazily built,
  *constructed only
  *when required
  */
  private TDeserializer getDeserializer() {
    if(des==null) des = new TDeserializer();
    return des;
  }

  private TSerializer getSerializer() {
    if(ser==null) ser = new TSerializer();
    return ser;
  }

 public T deserialize(byte[] record) {
   /*A new
    *Thrift object is
    *constructed prior
    *to deserialization.
    */
    T ret = createThriftObject();
    try {
      getDeserializer().deserialize((TBase)ret, record);
    } catch (TException e) {
      throw new RuntimeException(e);
    }
    return ret;
  }


  public byte[] serialize(T obj) {
    try {
     /*
      *The object is
      *cast to a basic
      *Thrift object for
      *serialization.
      */
      return getSerializer().serialize((TBase)obj);
    } catch (TException e) {
      throw new RuntimeException(e);
    }
  }

  public boolean isValidTarget(String... dirs) {
    System.out.println("ThriftPailStructure.isValidTarget: INICIO");
    return true;
  }

  public List<String> getTarget(T object) {
    System.out.println("ThriftPailStructure.getTarget: INICIO");
    return Collections.EMPTY_LIST;
  }
}
