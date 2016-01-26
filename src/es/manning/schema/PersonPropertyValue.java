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
@Generated(value = "Autogenerated by Thrift Compiler (0.9.3)", date = "2016-01-26")
public class PersonPropertyValue implements org.apache.thrift.TBase<PersonPropertyValue, PersonPropertyValue._Fields>, java.io.Serializable, Cloneable, Comparable<PersonPropertyValue> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("PersonPropertyValue");

  private static final org.apache.thrift.protocol.TField FULL_NAME_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("full_nameType", org.apache.thrift.protocol.TType.STRUCT, (short)1);
  private static final org.apache.thrift.protocol.TField GENDER_FIELD_DESC = new org.apache.thrift.protocol.TField("gender", org.apache.thrift.protocol.TType.STRUCT, (short)2);
  private static final org.apache.thrift.protocol.TField LOCATION_FIELD_DESC = new org.apache.thrift.protocol.TField("location", org.apache.thrift.protocol.TType.STRUCT, (short)3);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new PersonPropertyValueStandardSchemeFactory());
    schemes.put(TupleScheme.class, new PersonPropertyValueTupleSchemeFactory());
  }

  public Full_nameType full_nameType; // required
  public Gender gender; // required
  public Location location; // required

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

  // isset id assignments
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
  }

  public PersonPropertyValue(
    Full_nameType full_nameType,
    Gender gender,
    Location location)
  {
    this();
    this.full_nameType = full_nameType;
    this.gender = gender;
    this.location = location;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public PersonPropertyValue(PersonPropertyValue other) {
    if (other.isSetFull_nameType()) {
      this.full_nameType = new Full_nameType(other.full_nameType);
    }
    if (other.isSetGender()) {
      this.gender = new Gender(other.gender);
    }
    if (other.isSetLocation()) {
      this.location = new Location(other.location);
    }
  }

  public PersonPropertyValue deepCopy() {
    return new PersonPropertyValue(this);
  }

  @Override
  public void clear() {
    this.full_nameType = null;
    this.gender = null;
    this.location = null;
  }

  public Full_nameType getFull_nameType() {
    return this.full_nameType;
  }

  public PersonPropertyValue setFull_nameType(Full_nameType full_nameType) {
    this.full_nameType = full_nameType;
    return this;
  }

  public void unsetFull_nameType() {
    this.full_nameType = null;
  }

  /** Returns true if field full_nameType is set (has been assigned a value) and false otherwise */
  public boolean isSetFull_nameType() {
    return this.full_nameType != null;
  }

  public void setFull_nameTypeIsSet(boolean value) {
    if (!value) {
      this.full_nameType = null;
    }
  }

  public Gender getGender() {
    return this.gender;
  }

  public PersonPropertyValue setGender(Gender gender) {
    this.gender = gender;
    return this;
  }

  public void unsetGender() {
    this.gender = null;
  }

  /** Returns true if field gender is set (has been assigned a value) and false otherwise */
  public boolean isSetGender() {
    return this.gender != null;
  }

  public void setGenderIsSet(boolean value) {
    if (!value) {
      this.gender = null;
    }
  }

  public Location getLocation() {
    return this.location;
  }

  public PersonPropertyValue setLocation(Location location) {
    this.location = location;
    return this;
  }

  public void unsetLocation() {
    this.location = null;
  }

  /** Returns true if field location is set (has been assigned a value) and false otherwise */
  public boolean isSetLocation() {
    return this.location != null;
  }

  public void setLocationIsSet(boolean value) {
    if (!value) {
      this.location = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case FULL_NAME_TYPE:
      if (value == null) {
        unsetFull_nameType();
      } else {
        setFull_nameType((Full_nameType)value);
      }
      break;

    case GENDER:
      if (value == null) {
        unsetGender();
      } else {
        setGender((Gender)value);
      }
      break;

    case LOCATION:
      if (value == null) {
        unsetLocation();
      } else {
        setLocation((Location)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case FULL_NAME_TYPE:
      return getFull_nameType();

    case GENDER:
      return getGender();

    case LOCATION:
      return getLocation();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case FULL_NAME_TYPE:
      return isSetFull_nameType();
    case GENDER:
      return isSetGender();
    case LOCATION:
      return isSetLocation();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof PersonPropertyValue)
      return this.equals((PersonPropertyValue)that);
    return false;
  }

  public boolean equals(PersonPropertyValue that) {
    if (that == null)
      return false;

    boolean this_present_full_nameType = true && this.isSetFull_nameType();
    boolean that_present_full_nameType = true && that.isSetFull_nameType();
    if (this_present_full_nameType || that_present_full_nameType) {
      if (!(this_present_full_nameType && that_present_full_nameType))
        return false;
      if (!this.full_nameType.equals(that.full_nameType))
        return false;
    }

    boolean this_present_gender = true && this.isSetGender();
    boolean that_present_gender = true && that.isSetGender();
    if (this_present_gender || that_present_gender) {
      if (!(this_present_gender && that_present_gender))
        return false;
      if (!this.gender.equals(that.gender))
        return false;
    }

    boolean this_present_location = true && this.isSetLocation();
    boolean that_present_location = true && that.isSetLocation();
    if (this_present_location || that_present_location) {
      if (!(this_present_location && that_present_location))
        return false;
      if (!this.location.equals(that.location))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_full_nameType = true && (isSetFull_nameType());
    list.add(present_full_nameType);
    if (present_full_nameType)
      list.add(full_nameType);

    boolean present_gender = true && (isSetGender());
    list.add(present_gender);
    if (present_gender)
      list.add(gender);

    boolean present_location = true && (isSetLocation());
    list.add(present_location);
    if (present_location)
      list.add(location);

    return list.hashCode();
  }

  @Override
  public int compareTo(PersonPropertyValue other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetFull_nameType()).compareTo(other.isSetFull_nameType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetFull_nameType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.full_nameType, other.full_nameType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetGender()).compareTo(other.isSetGender());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetGender()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.gender, other.gender);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetLocation()).compareTo(other.isSetLocation());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetLocation()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.location, other.location);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("PersonPropertyValue(");
    boolean first = true;

    sb.append("full_nameType:");
    if (this.full_nameType == null) {
      sb.append("null");
    } else {
      sb.append(this.full_nameType);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("gender:");
    if (this.gender == null) {
      sb.append("null");
    } else {
      sb.append(this.gender);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("location:");
    if (this.location == null) {
      sb.append("null");
    } else {
      sb.append(this.location);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (full_nameType != null) {
      full_nameType.validate();
    }
    if (gender != null) {
      gender.validate();
    }
    if (location != null) {
      location.validate();
    }
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

  private static class PersonPropertyValueStandardSchemeFactory implements SchemeFactory {
    public PersonPropertyValueStandardScheme getScheme() {
      return new PersonPropertyValueStandardScheme();
    }
  }

  private static class PersonPropertyValueStandardScheme extends StandardScheme<PersonPropertyValue> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, PersonPropertyValue struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // FULL_NAME_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.full_nameType = new Full_nameType();
              struct.full_nameType.read(iprot);
              struct.setFull_nameTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // GENDER
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.gender = new Gender();
              struct.gender.read(iprot);
              struct.setGenderIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // LOCATION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.location = new Location();
              struct.location.read(iprot);
              struct.setLocationIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, PersonPropertyValue struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.full_nameType != null) {
        oprot.writeFieldBegin(FULL_NAME_TYPE_FIELD_DESC);
        struct.full_nameType.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.gender != null) {
        oprot.writeFieldBegin(GENDER_FIELD_DESC);
        struct.gender.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.location != null) {
        oprot.writeFieldBegin(LOCATION_FIELD_DESC);
        struct.location.write(oprot);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class PersonPropertyValueTupleSchemeFactory implements SchemeFactory {
    public PersonPropertyValueTupleScheme getScheme() {
      return new PersonPropertyValueTupleScheme();
    }
  }

  private static class PersonPropertyValueTupleScheme extends TupleScheme<PersonPropertyValue> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, PersonPropertyValue struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetFull_nameType()) {
        optionals.set(0);
      }
      if (struct.isSetGender()) {
        optionals.set(1);
      }
      if (struct.isSetLocation()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetFull_nameType()) {
        struct.full_nameType.write(oprot);
      }
      if (struct.isSetGender()) {
        struct.gender.write(oprot);
      }
      if (struct.isSetLocation()) {
        struct.location.write(oprot);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, PersonPropertyValue struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.full_nameType = new Full_nameType();
        struct.full_nameType.read(iprot);
        struct.setFull_nameTypeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.gender = new Gender();
        struct.gender.read(iprot);
        struct.setGenderIsSet(true);
      }
      if (incoming.get(2)) {
        struct.location = new Location();
        struct.location.read(iprot);
        struct.setLocationIsSet(true);
      }
    }
  }

}

