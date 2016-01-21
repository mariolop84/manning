package es.manning.tap;

import java.util.List;
import java.manning.tap.FieldStructure;
/*
 *Clase para el particionaminto vertical de los Struct o Edges
 *o Relaciones. Clase trivial porque los struct no se particionan
 */
public class EdgeStructure implements FieldStructure {
  public boolean isValidTarget(String[] dirs) { return true; }
  public void fillTarget(List<String> ret, Object val) { }
}
