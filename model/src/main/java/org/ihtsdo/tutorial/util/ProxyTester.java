/**
 * Copyright 2016 West Coast Informatics, LLC
 */
package org.ihtsdo.tutorial.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.apache.log4j.Logger;

/**
 * Generates proxy classes for testing.
 */
public class ProxyTester {

  /** Set of fields to exclude. */
  protected Set<String> excludes = new TreeSet<String>();

  /** Set of fields to include. */
  protected Set<String> includes = null;

  /** Class of object under test. */
  protected Class<?> clazz;

  /** The proxy map. */
  private Map<Class<?>, Map<Integer, Object>> proxyMap = new HashMap<>();

  /**
   * Constructs a new tester for the specified class.
   * 
   * @param obj Object to test.
   */
  public ProxyTester(Object obj) {
    this.clazz = obj.getClass();
  }

  /**
   * Adds a field to the list of tested fields. If this method is called, the
   * tester will not attempt to list all the getters and setters on the object
   * under test, and will instead simply test all the fields in the include
   * list.
   * 
   * @param field Field name whose getter/setter should be tested.
   */
  public void include(String field) {
    if (includes == null)
      includes = new TreeSet<String>();
    includes.add(field.toLowerCase());
  }

  /**
   * Adds a field to the list of excluded fields.
   * 
   * @param field Field name to exclude from testing.
   */
  public void exclude(String field) {
    excludes.add(field.toLowerCase());
  }

  /**
   * Proxy the specified object for the class type.
   *
   * @param clazz the class to match
   * @param i the i the initializer key
   * @param o the o the object
   */
  public void proxy(Class<?> clazz, int i, Object o) {
    if (!proxyMap.containsKey(clazz)) {
      proxyMap.put(clazz, new HashMap<Integer, Object>());
    }
    Map<Integer, Object> initializerMap = proxyMap.get(clazz);
    initializerMap.put(i, o);
  }

  /**
   * Walks through the methods in the class looking for getters and setters that
   * are on our include list (if any) and are not on our exclude list.
   *
   * @param initializer a value that when used produces certain field values
   * @return the object
   * @throws Exception the exception
   */
  public Object createObject(int initializer) throws Exception {
    // Verify there is a no-argument constructor
    Object o = null;
    try {
      o = clazz.newInstance();
    } catch (Exception e) {
      throw new Exception("Class " + clazz
          + " unexpectedly does not have a no-argument constructor");
    }
    setFields(o, true, false, initializer);
    return o;
  }

  /**
   * Sets the fields.
   *
   * @param o the object
   * @param includeFlag the include flag
   * @param logField the log set
   * @param initializer the initializer
   * @throws Exception the exception
   */
  protected void setFields(Object o, boolean includeFlag, boolean logField,
    int initializer) throws Exception {
    Set<String> includesSeen = new HashSet<>();
    Method[] methods = clazz.getMethods();
    for (int i = 0; i < methods.length; i++) {

      /* We're looking for single-argument setters. */
      Method m = methods[i];
      if (!m.getName().startsWith("set"))
        continue;
      String fieldName = m.getName().substring(3);
      Class<?>[] args = m.getParameterTypes();
      if (args.length != 1)
        continue;

      // indicate we've seen it
      if (includes != null && includes.contains(fieldName.toLowerCase())) {
        includesSeen.add(fieldName.toLowerCase());
      }

      includesSeen.add(fieldName.toLowerCase());
      /* Check the field name against our include/exclude list. */
      if (includeFlag) {
        if (includes != null && !includes.contains(fieldName.toLowerCase()))
          continue;
        if (excludes.contains(fieldName.toLowerCase()))
          continue;
      } else {
        if (includes != null && includes.contains(fieldName.toLowerCase()))
          continue;
      }

      /* Is there a getter that returns the same type? */
      Method getter;
      try {
        getter = clazz.getMethod("get" + fieldName, new Class[] {});
        if (getter.getReturnType() != args[0])
          continue;
      } catch (NoSuchMethodException e) {
        try {
          getter = clazz.getMethod("is" + fieldName, new Class[] {});
          if (getter.getReturnType() != args[0])
            continue;
        } catch (NoSuchMethodException e2) {
          continue;
        }
      }
      if (logField) {
        Logger.getLogger(getClass()).debug("  field = " + fieldName);
      }
      setField(o, getter, m, args[0], initializer);
    }

    // If includes contains entries and not all have been seen - error\
    if (includes != null) {
      Set<String> notSeen = new HashSet<>();
      for (String field : includes) {
        if (!includesSeen.contains(field.toLowerCase())) {
          notSeen.add(field);
        }
      }
      if (notSeen.size() > 0) {
        throw new Exception("Some included fields were not found: " + notSeen);
      }
    }
  }

  /**
   * Dummy invocation handler for our proxy objects.
   */
  protected class DummyInvocationHandler implements InvocationHandler {

    /**
     * Invoke.
     *
     * @param o the o
     * @param m the m
     * @param a the a
     * @return object
     */
    @Override
    public Object invoke(Object o, Method m, Object[] a) {
      return null;
    }
  }

  /**
   * Tests a single getter/setter pair using an argument of a particular type.
   *
   * @param o the o
   * @param get the get method
   * @param set the set method
   * @param argType the data type
   * @param initializer the initializer
   * @throws Exception the exception
   */
  protected void setField(Object o, Method get, Method set, Class<?> argType,
    int initializer) throws Exception {
    Object proxy = makeProxy(argType, initializer);
    // Logger.getLogger(getClass()).debug(
    // "  " + set.getName() + " = " + proxy.toString());
    try {
      set.invoke(o, new Object[] {
        proxy
      });
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      throw new RuntimeException("Setter " + set.getDeclaringClass().getName()
          + "." + set.getName() + " threw " + e.getTargetException().toString());
    } catch (IllegalArgumentException e) {
      Logger.getLogger(getClass()).debug("o=" + o.getClass().getName());
      Logger.getLogger(getClass()).debug("proxy=" + proxy.getClass().getName());
      throw e;
    }
  }

  /**
   * Makes a proxy of a given class. If the class is an interface type, uses the
   * standard JDK proxy mechanism. If it's not, uses cglib. The use of cglib is
   * via reflection so that cglib is not required to use this library unless the
   * caller actually needs to proxy a concrete class.
   *
   * @param type the type
   * @param initializer the initializer
   * @return a class of the specified type
   * @throws Exception the exception
   */
  @SuppressWarnings({
    "rawtypes"
  })
  protected Object makeProxy(Class<?> type, int initializer) throws Exception {
    // Return anything passed in first
    if (proxyMap.containsKey(type))
      return proxyMap.get(type).get(initializer);

    /* If it's a primitive type, just create it. */
    if (type == String.class)
      return "" + initializer;
    if (type == Date.class)
      return new Date(10L + initializer);
    if (type == Boolean.class || type == boolean.class)
      return new Boolean((initializer & 1) == 0);
    if (type == Integer.class || type == int.class)
      return new Integer(initializer);
    if (type == Long.class || type == long.class)
      return new Long(initializer);
    if (type == Double.class || type == double.class)
      return new Double((initializer * 1.0) / 100);
    if (type == Float.class || type == float.class)
      return new Float((initializer * 1.0) / 100);
    if (type == Character.class || type == char.class)
      return new Character((char) ('a' + initializer));
    if (type == BigDecimal.class)
      return new BigDecimal(initializer);
    if (type == Set.class) {
      Set set = new HashSet();
      return set;
    }
    if (type == List.class) {
      List list = new ArrayList();
      return list;
    }
    if (type == Map.class) {
      Map map = new HashMap<>();
      return map;
    }
    if (type == BigInteger.class)
      return new BigInteger("" + initializer);
    // JAVA5 - Comment out or remove the next two lines on older Java versions.
    if (type.isEnum())
      return makeEnum(type, initializer);

    /* Use JDK dynamic proxy if the argument is an interface. */
    if (type.isInterface())
      return Proxy.newProxyInstance(type.getClassLoader(), new Class[] {
        type
      }, new DummyInvocationHandler());

    /* Get the CGLib classes we need. */
    Class<?> enhancerClass = null;
    Class<?> callbackClass = null;
    Class<?> fixedValueClass = null;
    try {
      enhancerClass = Class.forName("net.sf.cglib.proxy.Enhancer");
      callbackClass = Class.forName("net.sf.cglib.proxy.Callback");
      fixedValueClass = Class.forName("net.sf.cglib.proxy.FixedValue");
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Need cglib to make a dummy "
          + type.getName() + ". Make sure cglib.jar is on " + "your classpath.");
    }

    /* Make a dummy callback (proxies within proxies!) */
    Object callback;
    callback =
        Proxy.newProxyInstance(callbackClass.getClassLoader(), new Class[] {
          fixedValueClass
        }, new DummyInvocationHandler());

    Method createMethod = enhancerClass.getMethod("create", new Class[] {
        Class.class, callbackClass
    });

    return createMethod.invoke(null, new Object[] {
        type, callback
    });
  }

  /**
   * Returns an instance of an enum.
   * 
   * JAVA5 - Comment out or remove this method on older Java versions.
   *
   * @param clazz1 the class
   * @param initializer the initializer
   * @return an instance of an enum
   * @throws Exception the exception
   */
  @SuppressWarnings("static-method")
  private Object makeEnum(Class<?> clazz1, int initializer) throws Exception {
    Method m = clazz1.getMethod("values", new Class[0]);
    Object[] o = (Object[]) m.invoke(null, new Object[0]);
    return o[initializer];
  }
}
