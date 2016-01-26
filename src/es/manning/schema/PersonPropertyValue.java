/**
 * Autogenerated by Thrift Compiler (0.9.3)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package es.manning.schema;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
public class PersonPropertyValue extends org.apache.thrift.TUnion<PersonPropertyValue, PersonPropertyValue._Fields> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PersonPropertyValue");
  private static final org.apache.thrift.protocol.TField FULL_NAME_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("full_nameType", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField GENDER_FIELD_DESC = new org.apache.thrift.protocol.TField("gender", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField LOCATION_FIELD_DESC = new org.apache.thrift.protocol.TField("location", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    FULL_NAME_TYPE((short)1, "full_nameType"),
    GENDER((short)2, "gender"),
    LOCATION((short)3, "location");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // FULL_NAME_TYPE
          return FULL_NAME_TYPE;
        case 2: // GENDER
          return GENDER;
        case 3: // LOCATION
          return LOCATION;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.FULL_NAME_TYPE, new org.apache.thrift.meta_data.FieldMetaData("full_nameType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Full_nameType.class)));
    tmpMap.put(_Fields.GENDER, new org.apache.thrift.meta_data.FieldMetaData("gender", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Gender.class)));
    tmpMap.put(_Fields.LOCATION, new org.apache.thrift.meta_data.FieldMetaData("location", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, Location.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(PersonPropertyValue.class, metaDataMap);
  }

  public PersonPropertyValue() {
    super();
  }

  public PersonPropertyValue(_Fields setField, Object value) {
    super(setField, value);
  }

  public PersonPropertyValue(PersonPropertyValue other) {
    super(other);
  }
  public PersonPropertyValue deepCopy() {
    return new PersonPropertyValue(this);
  }

  public static PersonPropertyValue full_nameType(Full_nameType value) {
    PersonPropertyValue x = new PersonPropertyValue();
    x.setFull_nameType(value);
    return x;
  }

  public static PersonPropertyValue gender(Gender value) {
    PersonPropertyValue x = new PersonPropertyValue();
    x.setGender(value);
    return x;
  }

  public static PersonPropertyValue location(Location value) {
    PersonPropertyValue x = new PersonPropertyValue();
    x.setLocation(value);
    return x;
  }


  @Override
  protected void checkType(_Fields setField, Object value) throws ClassCastException {
    switch (setField) {
      case FULL_NAME_TYPE:
        if (value instanceof Full_nameType) {
          break;
        }
        throw new ClassCastException("Was expecting value of type Full_nameType for field 'full_nameType', but got " + value.getClass().getSimpleName());
      case GENDER:
        if (value instanceof Gender) {
          break;
        }
        throw new ClassCastException("Was expecting value of type Gender for field 'gender', but got " + value.getClass().getSimpleName());
      case LOCATION:
        if (value instanceof Location) {
          break;
        }
        throw new ClassCastException("Was expecting value of type Location for field 'location', but got " + value.getClass().getSimpleName());
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected Object standardSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, org.apache.thrift.protocol.TField field) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(field.id);
    if (setField != null) {
      switch (setField) {
        case FULL_NAME_TYPE:
          if (field.type == FULL_NAME_TYPE_FIELD_DESC.type) {
            Full_nameType full_nameType;
            full_nameType = new Full_nameType();
            full_nameType.read(iprot);
            return full_nameType;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case GENDER:
          if (field.type == GENDER_FIELD_DESC.type) {
            Gender gender;
            gender = new Gender();
            gender.read(iprot);
            return gender;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        case LOCATION:
          if (field.type == LOCATION_FIELD_DESC.type) {
            Location location;
            location = new Location();
            location.read(iprot);
            return location;
          } else {
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
            return null;
          }
        default:
          throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      org.apache.thrift.protocol.TProtocolUtil.skip(iprot, field.type);
      return null;
    }
  }

  @Override
  protected void standardSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case FULL_NAME_TYPE:
        Full_nameType full_nameType = (Full_nameType)value_;
        full_nameType.write(oprot);
        return;
      case GENDER:
        Gender gender = (Gender)value_;
        gender.write(oprot);
        return;
      case LOCATION:
        Location location = (Location)value_;
        location.write(oprot);
        return;
      default:
        throw new IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected Object tupleSchemeReadValue(org.apache.thrift.protocol.TProtocol iprot, short fieldID) throws org.apache.thrift.TException {
    _Fields setField = _Fields.findByThriftId(fieldID);
    if (setField != null) {
      switch (setField) {
        case FULL_NAME_TYPE:
          Full_nameType full_nameType;
          full_nameType = new Full_nameType();
          full_nameType.read(iprot);
          return full_nameType;
        case GENDER:
          Gender gender;
          gender = new Gender();
          gender.read(iprot);
          return gender;
        case LOCATION:
          Location location;
          location = new Location();
          location.read(iprot);
          return location;
        default:
          throw new IllegalStateException("setField wasn't null, but didn't match any of the case statements!");
      }
    } else {
      throw new TProtocolException("Couldn't find a field with field id " + fieldID);
    }
  }

  @Override
  protected void tupleSchemeWriteValue(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    switch (setField_) {
      case FULL_NAME_TYPE:
        Full_nameType full_nameType = (Full_nameType)value_;
        full_nameType.write(oprot);
        return;
      case GENDER:
        Gender gender = (Gender)value_;
        gender.write(oprot);
        return;
      case LOCATION:
        Location location = (Location)value_;
        location.write(oprot);
        return;
      default:
        throw new IllegalStateException("Cannot write union with unknown field " + setField_);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TField getFieldDesc(_Fields setField) {
    switch (setField) {
      case FULL_NAME_TYPE:
        return FULL_NAME_TYPE_FIELD_DESC;
      case GENDER:
        return GENDER_FIELD_DESC;
      case LOCATION:
        return LOCATION_FIELD_DESC;
      default:
        throw new IllegalArgumentException("Unknown field id " + setField);
    }
  }

  @Override
  protected org.apache.thrift.protocol.TStruct getStructDesc() {
    return STRUCT_DESC;
  }

  @Override
  protected _Fields enumForId(short id) {
    return _Fields.findByThriftIdOrThrow(id);
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }


  public Full_nameType getFull_nameType() {
    if (getSetField() == _Fields.FULL_NAME_TYPE) {
      return (Full_nameType)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'full_nameType' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setFull_nameType(Full_nameType value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.FULL_NAME_TYPE;
    value_ = value;
  }

  public Gender getGender() {
    if (getSetField() == _Fields.GENDER) {
      return (Gender)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'gender' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setGender(Gender value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.GENDER;
    value_ = value;
  }

  public Location getLocation() {
    if (getSetField() == _Fields.LOCATION) {
      return (Location)getFieldValue();
    } else {
      throw new RuntimeException("Cannot get field 'location' because union is currently set to " + getFieldDesc(getSetField()).name);
    }
  }

  public void setLocation(Location value) {
    if (value == null) throw new NullPointerException();
    setField_ = _Fields.LOCATION;
    value_ = value;
  }

  public boolean isSetFull_nameType() {
    return setField_ == _Fields.FULL_NAME_TYPE;
  }


  public boolean isSetGender() {
    return setField_ == _Fields.GENDER;
  }


  public boolean isSetLocation() {
    return setField_ == _Fields.LOCATION;
  }


  public boolean equals(Object other) {
    if (other instanceof PersonPropertyValue) {
      return equals((PersonPropertyValue)other);
    } else {
      return false;
    }
  }

  public boolean equals(PersonPropertyValue other) {
    return other != null && getSetField() == other.getSetField() && getFieldValue().equals(other.getFieldValue());
  }

  @Override
  public int compareTo(PersonPropertyValue other) {
    int lastComparison = org.apache.thrift.TBaseHelper.compareTo(getSetField(), other.getSetField());
    if (lastComparison == 0) {
      return org.apache.thrift.TBaseHelper.compareTo(getFieldValue(), other.getFieldValue());
    }
    return lastComparison;
  }


  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();
    list.add(this.getClass().getName());
    org.apache.thrift.TFieldIdEnum setField = getSetField();
    if (setField != null) {
      list.add(setField.getThriftFieldId());
      Object value = getFieldValue();
      if (value instanceof org.apache.thrift.TEnum) {
        list.add(((org.apache.thrift.TEnum)getFieldValue()).getValue());
      } else {
        list.add(value);
      }
    }
    return list.hashCode();
  }
  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }


  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }


}
