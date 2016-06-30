/**
 * Copyright 2016 West Coast Informatics, LLC
 */
package org.ihtsdo.tutorial.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/**
 * Automates JUnit testing of simple getter/setter methods.
 * 
 * <p>
 * It may be used in exclusive or inclusive mode. In exclusive mode, which is
 * the default, all JavaBeans properties (getter/setter method pairs with
 * matching names) are tested unless they are excluded beforehand. For example:
 * 
 * <pre>
 * MyClass objectToTest = new MyClass();
 * GetterSetterTester gst = new GetterSetterTester(objectToTest);
 * gst.exclude(&quot;complexProperty&quot;);
 * gst.exclude(&quot;anotherProperty&quot;);
 * gst.test();
 * </pre>
 * 
 * <p>
 * In inclusive mode, only properties that are explicitly listed are tested. For
 * example:
 * 
 * <pre>
 * new GetterSetterTester(new MyClass()).include(&quot;aSimpleProperty&quot;)
 *     .include(&quot;secondProperty&quot;).test();
 * </pre>
 * 
 * <p>
 * The second example also illustrates how to call this class in as terse a way
 * as possible.
 * 
 * <p>
 * The following property types are supported:
 * 
 * <ul>
 * <li>All Java primitive types.
 * <li>Interfaces.
 * <li>All non-final classes if <a href="http://cglib.sourceforge.net">cglib</a>
 * is on your classpath -- this uses cglib even when a no-argument constructor
 * is available because a constructor might have side effects that you wouldn.t
 * want to trigger in a unit test.
 * <li>Java 5 enums.
 * </ul>
 * 
 * <p>
 * Properties whose types are classes declared <code>final</code> are not
 * supported; neither are non-primitive, non-interface properties if you don't
 * have cglib.
 * 
 * <p>
 * Copyright (c) 2005, Steven Grimm.<br>
 * This software may be used for any purpose, commercial or noncommercial, so
 * long as this copyright notice is retained. If you make improvements to the
 * code, you're encouraged (but not required) to send them to me so I can make
 * them available to others. For updates, please check <a
 * href="http://www.plaintivemewling.com/?p=34">here</a>.
 * 
 * @author Steven Grimm, koreth@midwinter.com
 * @version 1.0 (2005/11/08).
 */
public class GetterSetterTester extends ProxyTester {
  /** Object under test. */
  private Object obj;

  /** If true, output trace information. */
  private boolean verbose = false;

  /**
   * Constructs a new getter/setter tester to test objects of a particular
   * class.
   * 
   * @param obj Object to test.
   */
  public GetterSetterTester(Object obj) {
    super(obj);
    this.obj = obj;
  }

  /**
   * Sets the verbosity flag.
   * @param verbose the verbose flag
   * @return this
   */
  public GetterSetterTester setVerbose(boolean verbose) {
    this.verbose = verbose;
    return this;
  }

  /**
   * Walks through the methods in the class looking for getters and setters that
   * are on our include list (if any) and are not on our exclude list.
   *
   * @throws Exception the exception
   */
  public void test() throws Exception {
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

      /* Check the field name against our include/exclude list. */
      if (includes != null && !includes.contains(fieldName.toLowerCase())) {
        continue;
      }
      if (excludes.contains(fieldName.toLowerCase()))
        continue;

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

      Logger.getLogger(getClass()).debug("  field = " + fieldName);
      testGetterSetter(getter, m, args[0]);
    }
  }

  /**
   * Tests a single getter/setter pair using an argument of a particular type.
   *
   * @param get the get method
   * @param set the set method
   * @param argType the data type
   * @throws Exception the exception
   */
  private void testGetterSetter(Method get, Method set, Class<?> argType)
    throws Exception {
    if (this.verbose)
      Logger.getLogger(getClass()).debug(
          "Testing " + get.getDeclaringClass().getName() + "." + get.getName());
    Object proxy = makeProxy(argType, 1);
    try {
      set.invoke(this.obj, new Object[] {
        proxy
      });
    } catch (InvocationTargetException e) {
      e.printStackTrace();
      throw new RuntimeException("Setter " + set.getDeclaringClass().getName()
          + "." + set.getName() + " threw " + e.getTargetException().toString());
    }

    Object getResult;
    try {
      getResult = get.invoke(this.obj, new Object[] {});
    } catch (InvocationTargetException e) {
      throw new RuntimeException("Setter " + set.getDeclaringClass().getName()
          + "." + set.getName() + " threw " + e.getTargetException().toString());
    }

    if (getResult == proxy || proxy.equals(getResult))
      return;
    throw new RuntimeException("Getter " + get.getName()
        + " did not return value from setter: " + proxy + ", " + getResult);
  }

}
