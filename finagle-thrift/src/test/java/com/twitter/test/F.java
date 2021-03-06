/**
 * Autogenerated by Thrift
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 */
package com.twitter.test;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.thrift.*;
import org.apache.thrift.async.*;
import org.apache.thrift.meta_data.*;
import org.apache.thrift.transport.*;
import org.apache.thrift.protocol.*;

import com.twitter.util.Future;
import com.twitter.util.Function;
import com.twitter.util.Function2;
import com.twitter.util.Try;
import com.twitter.util.Return;
import com.twitter.util.Throw;
import com.twitter.finagle.thrift.ThriftClientRequest;

public class F {

  public interface Iface extends com.facebook.fb303.FacebookService.Iface {

    public int another_method(int a) throws TException;

  }

  public interface AsyncIface extends com.facebook.fb303.FacebookService.AsyncIface {

    public void another_method(int a, AsyncMethodCallback<AsyncClient.another_method_call> resultHandler) throws TException;

  }

  public interface ServiceIface extends com.facebook.fb303.FacebookService.ServiceIface {

    public Future<Integer> another_method(int a);

  }

  public static class Client extends com.facebook.fb303.FacebookService.Client implements TServiceClient, Iface {
    public static class Factory implements TServiceClientFactory<Client> {
      public Factory() {}
      public Client getClient(TProtocol prot) {
        return new Client(prot);
      }
      public Client getClient(TProtocol iprot, TProtocol oprot) {
        return new Client(iprot, oprot);
      }
    }

    public Client(TProtocol prot)
    {
      this(prot, prot);
    }

    public Client(TProtocol iprot, TProtocol oprot)
    {
      super(iprot, oprot);
    }

    public int another_method(int a) throws TException
    {
      send_another_method(a);
      return recv_another_method();
    }

    public void send_another_method(int a) throws TException
    {
      oprot_.writeMessageBegin(new TMessage("another_method", TMessageType.CALL, ++seqid_));
      another_method_args args = new another_method_args();
      args.setA(a);
      args.write(oprot_);
      oprot_.writeMessageEnd();
      oprot_.getTransport().flush();
    }

    public int recv_another_method() throws TException
    {
      TMessage msg = iprot_.readMessageBegin();
      if (msg.type == TMessageType.EXCEPTION) {
        TApplicationException x = TApplicationException.read(iprot_);
        iprot_.readMessageEnd();
        throw x;
      }
      if (msg.seqid != seqid_) {
        throw new TApplicationException(TApplicationException.BAD_SEQUENCE_ID, "another_method failed: out of sequence response");
      }
      another_method_result result = new another_method_result();
      result.read(iprot_);
      iprot_.readMessageEnd();
      if (result.isSetSuccess()) {
        return result.success;
      }
      throw new TApplicationException(TApplicationException.MISSING_RESULT, "another_method failed: unknown result");
    }

  }
  public static class AsyncClient extends com.facebook.fb303.FacebookService.AsyncClient implements AsyncIface {
    public static class Factory implements TAsyncClientFactory<AsyncClient> {
      private TAsyncClientManager clientManager;
      private TProtocolFactory protocolFactory;
      public Factory(TAsyncClientManager clientManager, TProtocolFactory protocolFactory) {
        this.clientManager = clientManager;
        this.protocolFactory = protocolFactory;
      }
      public AsyncClient getAsyncClient(TNonblockingTransport transport) {
        return new AsyncClient(protocolFactory, clientManager, transport);
      }
    }

    public AsyncClient(TProtocolFactory protocolFactory, TAsyncClientManager clientManager, TNonblockingTransport transport) {
      super(protocolFactory, clientManager, transport);
    }

    public void another_method(int a, AsyncMethodCallback<another_method_call> resultHandler) throws TException {
      checkReady();
      another_method_call method_call = new another_method_call(a, resultHandler, this, protocolFactory, transport);
      manager.call(method_call);
    }

    public static class another_method_call extends TAsyncMethodCall {
      private int a;
      public another_method_call(int a, AsyncMethodCallback<another_method_call> resultHandler, TAsyncClient client, TProtocolFactory protocolFactory, TNonblockingTransport transport) throws TException {
        super(client, protocolFactory, transport, resultHandler, false);
        this.a = a;
      }

      public void write_args(TProtocol prot) throws TException {
        prot.writeMessageBegin(new TMessage("another_method", TMessageType.CALL, 0));
        another_method_args args = new another_method_args();
        args.setA(a);
        args.write(prot);
        prot.writeMessageEnd();
      }

      public int getResult() throws TException {
        if (getState() != State.RESPONSE_READ) {
          throw new IllegalStateException("Method call not finished!");
        }
        TMemoryInputTransport memoryTransport = new TMemoryInputTransport(getFrameBuffer().array());
        TProtocol prot = client.getProtocolFactory().getProtocol(memoryTransport);
        return (new Client(prot)).recv_another_method();
      }
    }

  }

  public static class ServiceToClient extends com.facebook.fb303.FacebookService.ServiceToClient implements ServiceIface {
    private com.twitter.finagle.Service<ThriftClientRequest, byte[]> service;
    private TProtocolFactory protocolFactory;
    public ServiceToClient(com.twitter.finagle.Service<ThriftClientRequest, byte[]> service, TProtocolFactory protocolFactory) {
      super(service, protocolFactory);
      this.service = service;
      this.protocolFactory = protocolFactory;
    }

    public Future<Integer> another_method(int a) {
      try {
        // TODO: size
        TMemoryBuffer __memoryTransport__ = new TMemoryBuffer(512);
        TProtocol __prot__ = this.protocolFactory.getProtocol(__memoryTransport__);
        __prot__.writeMessageBegin(new TMessage("another_method", TMessageType.CALL, 0));
        another_method_args __args__ = new another_method_args();
        __args__.setA(a);
        __args__.write(__prot__);
        __prot__.writeMessageEnd();


        byte[] __buffer__ = Arrays.copyOfRange(__memoryTransport__.getArray(), 0, __memoryTransport__.length());
        ThriftClientRequest __request__ = new ThriftClientRequest(__buffer__, false);
        Future<byte[]> __done__ = this.service.apply(__request__);
        return __done__.flatMap(new Function<byte[], Future<Integer>>() {
          public Future<Integer> apply(byte[] __buffer__) {
            TMemoryInputTransport __memoryTransport__ = new TMemoryInputTransport(__buffer__);
            TProtocol __prot__ = ServiceToClient.this.protocolFactory.getProtocol(__memoryTransport__);
            try {
              return Future.value((new Client(__prot__)).recv_another_method());
            } catch (Exception e) {
              return Future.exception(e);
            }
          }
        });
      } catch (TException e) {
        return Future.exception(e);
      }
    }
  }

  public static class Processor extends com.facebook.fb303.FacebookService.Processor implements TProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(Processor.class.getName());
    public Processor(Iface iface)
    {
      super(iface);
      iface_ = iface;
      processMap_.put("another_method", new another_method());
    }

    private Iface iface_;

    public boolean process(TProtocol iprot, TProtocol oprot) throws TException
    {
      TMessage msg = iprot.readMessageBegin();
      ProcessFunction fn = processMap_.get(msg.name);
      if (fn == null) {
        TProtocolUtil.skip(iprot, TType.STRUCT);
        iprot.readMessageEnd();
        TApplicationException x = new TApplicationException(TApplicationException.UNKNOWN_METHOD, "Invalid method name: '"+msg.name+"'");
        oprot.writeMessageBegin(new TMessage(msg.name, TMessageType.EXCEPTION, msg.seqid));
        x.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
        return true;
      }
      fn.process(msg.seqid, iprot, oprot);
      return true;
    }

    private class another_method implements ProcessFunction {
      public void process(int seqid, TProtocol iprot, TProtocol oprot) throws TException
      {
        another_method_args args = new another_method_args();
        try {
          args.read(iprot);
        } catch (TProtocolException e) {
          iprot.readMessageEnd();
          TApplicationException x = new TApplicationException(TApplicationException.PROTOCOL_ERROR, e.getMessage());
          oprot.writeMessageBegin(new TMessage("another_method", TMessageType.EXCEPTION, seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return;
        }
        iprot.readMessageEnd();
        another_method_result result = new another_method_result();
        result.success = iface_.another_method(args.a);
        result.setSuccessIsSet(true);
        oprot.writeMessageBegin(new TMessage("another_method", TMessageType.REPLY, seqid));
        result.write(oprot);
        oprot.writeMessageEnd();
        oprot.getTransport().flush();
      }

    }

  }

  public static class Service extends com.facebook.fb303.FacebookService.Service {
    private final ServiceIface iface;
    private final TProtocolFactory protocolFactory;
    public Service(final ServiceIface iface, final TProtocolFactory protocolFactory) {
      super(iface, protocolFactory);
      this.iface = iface;
      this.protocolFactory = protocolFactory;
      functionMap.put("another_method", new Function2<TProtocol, Integer, Future<byte[]>>() {
        public Future<byte[]> apply(final TProtocol iprot, final Integer seqid) {
          another_method_args args = new another_method_args();
          try {
            args.read(iprot);
          } catch (TProtocolException e) {
            try {
              iprot.readMessageEnd();
              TApplicationException x = new TApplicationException(TApplicationException.PROTOCOL_ERROR, e.getMessage());
              TMemoryBuffer memoryBuffer = new TMemoryBuffer(512);
              TProtocol oprot = protocolFactory.getProtocol(memoryBuffer);

              oprot.writeMessageBegin(new TMessage("another_method", TMessageType.EXCEPTION, seqid));
              x.write(oprot);
              oprot.writeMessageEnd();
              oprot.getTransport().flush();
              byte[] buffer = Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length());
              return Future.value(buffer);
            } catch (Exception e1) {
              return Future.exception(e1);
            }
          } catch (Exception e) {
            return Future.exception(e);
          }

          try {
            iprot.readMessageEnd();
          } catch (Exception e) {
            return Future.exception(e);
          }
          Future<Integer> future;
          try {
            future = iface.another_method(args.a);
          } catch (Exception e) {
            future = Future.exception(e);
          }
          try {
            return future.flatMap(new Function<Integer, Future<byte[]>>() {
              public Future<byte[]> apply(Integer value) {
                another_method_result result = new another_method_result();
                result.success = value;
                result.setSuccessIsSet(true);

                try {
                  TMemoryBuffer memoryBuffer = new TMemoryBuffer(512);
                  TProtocol oprot = protocolFactory.getProtocol(memoryBuffer);

                  oprot.writeMessageBegin(new TMessage("another_method", TMessageType.REPLY, seqid));
                  result.write(oprot);
                  oprot.writeMessageEnd();

                  return Future.value(Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length()));
                } catch (Exception e) {
                  return Future.exception(e);
                }
              }
            }).rescue(new Function<Throwable, Future<byte[]>>() {
              public Future<byte[]> apply(Throwable t) {
                return Future.exception(t);
              }
            });
          } catch (Exception e) {
            return Future.exception(e);
          }
        }
      });

    }

    public Future<byte[]> apply(byte[] request) {
      TTransport inputTransport = new TMemoryInputTransport(request);
      TProtocol iprot = protocolFactory.getProtocol(inputTransport);

      TMessage msg;
      try {
        msg = iprot.readMessageBegin();
      } catch (Exception e) {
        return Future.exception(e);
      }

      Function2<TProtocol, Integer, Future<byte[]>> fn = functionMap.get(msg.name);
      if (fn == null) {
        try {
          TProtocolUtil.skip(iprot, TType.STRUCT);
          iprot.readMessageEnd();
          TApplicationException x = new TApplicationException(TApplicationException.UNKNOWN_METHOD, "Invalid method name: '"+msg.name+"'");
          TMemoryBuffer memoryBuffer = new TMemoryBuffer(512);
          TProtocol oprot = protocolFactory.getProtocol(memoryBuffer);
          oprot.writeMessageBegin(new TMessage(msg.name, TMessageType.EXCEPTION, msg.seqid));
          x.write(oprot);
          oprot.writeMessageEnd();
          oprot.getTransport().flush();
          return Future.value(Arrays.copyOfRange(memoryBuffer.getArray(), 0, memoryBuffer.length()));
        } catch (Exception e) {
          return Future.exception(e);
        }
      }

      return fn.apply(iprot, msg.seqid);
    }

  }

  public static class another_method_args implements TBase<another_method_args, another_method_args._Fields>, java.io.Serializable, Cloneable   {
    private static final TStruct STRUCT_DESC = new TStruct("another_method_args");

    private static final TField A_FIELD_DESC = new TField("a", TType.I32, (short)1);

    public int a;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      A((short)1, "a");

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
          case 1: // A
            return A;
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
    private static final int __A_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap;
    static {
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.A, new FieldMetaData("a", TFieldRequirementType.DEFAULT,
          new FieldValueMetaData(TType.I32)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(another_method_args.class, metaDataMap);
    }

    public another_method_args() {
    }

    public another_method_args(
      int a)
    {
      this();
      this.a = a;
      setAIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public another_method_args(another_method_args other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      this.a = other.a;
    }

    public another_method_args deepCopy() {
      return new another_method_args(this);
    }

    @Override
    public void clear() {
      setAIsSet(false);
      this.a = 0;
    }

    public int getA() {
      return this.a;
    }

    public another_method_args setA(int a) {
      this.a = a;
      setAIsSet(true);
      return this;
    }

    public void unsetA() {
      __isset_bit_vector.clear(__A_ISSET_ID);
    }

    /** Returns true if field a is set (has been asigned a value) and false otherwise */
    public boolean isSetA() {
      return __isset_bit_vector.get(__A_ISSET_ID);
    }

    public void setAIsSet(boolean value) {
      __isset_bit_vector.set(__A_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case A:
        if (value == null) {
          unsetA();
        } else {
          setA((Integer)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case A:
        return new Integer(getA());

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case A:
        return isSetA();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof another_method_args)
        return this.equals((another_method_args)that);
      return false;
    }

    public boolean equals(another_method_args that) {
      if (that == null)
        return false;

      boolean this_present_a = true;
      boolean that_present_a = true;
      if (this_present_a || that_present_a) {
        if (!(this_present_a && that_present_a))
          return false;
        if (this.a != that.a)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return 0;
    }

    public int compareTo(another_method_args other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      another_method_args typedOther = (another_method_args)other;

      lastComparison = Boolean.valueOf(isSetA()).compareTo(typedOther.isSetA());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetA()) {
        lastComparison = TBaseHelper.compareTo(this.a, typedOther.a);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) {
          break;
        }
        switch (field.id) {
          case 1: // A
            if (field.type == TType.I32) {
              this.a = iprot.readI32();
              setAIsSet(true);
            } else {
              TProtocolUtil.skip(iprot, field.type);
            }
            break;
          default:
            TProtocolUtil.skip(iprot, field.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(A_FIELD_DESC);
      oprot.writeI32(this.a);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("another_method_args(");
      boolean first = true;

      sb.append("a:");
      sb.append(this.a);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

  public static class another_method_result implements TBase<another_method_result, another_method_result._Fields>, java.io.Serializable, Cloneable   {
    private static final TStruct STRUCT_DESC = new TStruct("another_method_result");

    private static final TField SUCCESS_FIELD_DESC = new TField("success", TType.I32, (short)0);

    public int success;

    /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
    public enum _Fields implements TFieldIdEnum {
      SUCCESS((short)0, "success");

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
          case 0: // SUCCESS
            return SUCCESS;
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
    private static final int __SUCCESS_ISSET_ID = 0;
    private BitSet __isset_bit_vector = new BitSet(1);

    public static final Map<_Fields, FieldMetaData> metaDataMap;
    static {
      Map<_Fields, FieldMetaData> tmpMap = new EnumMap<_Fields, FieldMetaData>(_Fields.class);
      tmpMap.put(_Fields.SUCCESS, new FieldMetaData("success", TFieldRequirementType.DEFAULT,
          new FieldValueMetaData(TType.I32)));
      metaDataMap = Collections.unmodifiableMap(tmpMap);
      FieldMetaData.addStructMetaDataMap(another_method_result.class, metaDataMap);
    }

    public another_method_result() {
    }

    public another_method_result(
      int success)
    {
      this();
      this.success = success;
      setSuccessIsSet(true);
    }

    /**
     * Performs a deep copy on <i>other</i>.
     */
    public another_method_result(another_method_result other) {
      __isset_bit_vector.clear();
      __isset_bit_vector.or(other.__isset_bit_vector);
      this.success = other.success;
    }

    public another_method_result deepCopy() {
      return new another_method_result(this);
    }

    @Override
    public void clear() {
      setSuccessIsSet(false);
      this.success = 0;
    }

    public int getSuccess() {
      return this.success;
    }

    public another_method_result setSuccess(int success) {
      this.success = success;
      setSuccessIsSet(true);
      return this;
    }

    public void unsetSuccess() {
      __isset_bit_vector.clear(__SUCCESS_ISSET_ID);
    }

    /** Returns true if field success is set (has been asigned a value) and false otherwise */
    public boolean isSetSuccess() {
      return __isset_bit_vector.get(__SUCCESS_ISSET_ID);
    }

    public void setSuccessIsSet(boolean value) {
      __isset_bit_vector.set(__SUCCESS_ISSET_ID, value);
    }

    public void setFieldValue(_Fields field, Object value) {
      switch (field) {
      case SUCCESS:
        if (value == null) {
          unsetSuccess();
        } else {
          setSuccess((Integer)value);
        }
        break;

      }
    }

    public Object getFieldValue(_Fields field) {
      switch (field) {
      case SUCCESS:
        return new Integer(getSuccess());

      }
      throw new IllegalStateException();
    }

    /** Returns true if field corresponding to fieldID is set (has been asigned a value) and false otherwise */
    public boolean isSet(_Fields field) {
      if (field == null) {
        throw new IllegalArgumentException();
      }

      switch (field) {
      case SUCCESS:
        return isSetSuccess();
      }
      throw new IllegalStateException();
    }

    @Override
    public boolean equals(Object that) {
      if (that == null)
        return false;
      if (that instanceof another_method_result)
        return this.equals((another_method_result)that);
      return false;
    }

    public boolean equals(another_method_result that) {
      if (that == null)
        return false;

      boolean this_present_success = true;
      boolean that_present_success = true;
      if (this_present_success || that_present_success) {
        if (!(this_present_success && that_present_success))
          return false;
        if (this.success != that.success)
          return false;
      }

      return true;
    }

    @Override
    public int hashCode() {
      return 0;
    }

    public int compareTo(another_method_result other) {
      if (!getClass().equals(other.getClass())) {
        return getClass().getName().compareTo(other.getClass().getName());
      }

      int lastComparison = 0;
      another_method_result typedOther = (another_method_result)other;

      lastComparison = Boolean.valueOf(isSetSuccess()).compareTo(typedOther.isSetSuccess());
      if (lastComparison != 0) {
        return lastComparison;
      }
      if (isSetSuccess()) {
        lastComparison = TBaseHelper.compareTo(this.success, typedOther.success);
        if (lastComparison != 0) {
          return lastComparison;
        }
      }
      return 0;
    }

    public _Fields fieldForId(int fieldId) {
      return _Fields.findByThriftId(fieldId);
    }

    public void read(TProtocol iprot) throws TException {
      TField field;
      iprot.readStructBegin();
      while (true)
      {
        field = iprot.readFieldBegin();
        if (field.type == TType.STOP) {
          break;
        }
        switch (field.id) {
          case 0: // SUCCESS
            if (field.type == TType.I32) {
              this.success = iprot.readI32();
              setSuccessIsSet(true);
            } else {
              TProtocolUtil.skip(iprot, field.type);
            }
            break;
          default:
            TProtocolUtil.skip(iprot, field.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      validate();
    }

    public void write(TProtocol oprot) throws TException {
      oprot.writeStructBegin(STRUCT_DESC);

      if (this.isSetSuccess()) {
        oprot.writeFieldBegin(SUCCESS_FIELD_DESC);
        oprot.writeI32(this.success);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

    @Override
    public String toString() {
      StringBuilder sb = new StringBuilder("another_method_result(");
      boolean first = true;

      sb.append("success:");
      sb.append(this.success);
      first = false;
      sb.append(")");
      return sb.toString();
    }

    public void validate() throws TException {
      // check for required fields
    }

  }

}
