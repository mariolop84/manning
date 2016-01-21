package java.manning.tap;

import java.manning.schema.Data;

public class DataPailStructure extends ThriftPailStructure<Data> {
  @Override
  protected Data createThriftObject() {
    return new Data();
  }

  public Class getType() {
    return Data.class;
  }
}
