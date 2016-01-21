package es.manning.tap;
import java.util.List;
 /*
  *FieldStructure is an interface for both edges
  *and properties.
  */
public interface FieldStructure {
  public boolean isValidTarget(String[] dirs);
  public void fillTarget(List<String> ret, Object val);
}
