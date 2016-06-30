/**
 * Copyright 2016 West Coast Informatics, LLC
 */
package org.ihtsdo.tutorial.util;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

/**
 * Automates JUnit testing of equals and hashcode methods.
 */
public class EqualsHashcodeTester extends ProxyTester {

  /**
   * Constructs a new getter/setter tester to test objects of a particular
   * class.
   * 
   * @param obj Object to test.
   */
  public EqualsHashcodeTester(Object obj) {
    super(obj);
  }

  /**
   * Creates two objects with the same field values and verifies they are equal.
   *
   * @return true, if successful
   * @throws Exception the exception
   */
  public boolean testIdentityFieldEquals() throws Exception {
    Logger.getLogger(getClass()).debug(
        "Test identity field equals - " + clazz.getName());
    Object o1 = createObject(1);
    Object o2 = createObject(1);
    return o1.equals(o2);
  }

  /**
   * Creates two objects and verifies for each that changing the non-included or
   * excluded fields makes no difference to equality testing.
   *
   * @return true, if successful
   * @throws Exception the exception
   */
  public boolean testNonIdentityFieldEquals() throws Exception {
    Logger.getLogger(getClass()).debug(
        "Test non identity field equals - " + clazz.getName());
    Object o1 = createObject(1);
    Object o2 = createObject(1);
    setFields(o2, false, true, 2);
    return o1.equals(o2);
  }

  /**
   * Creates two objects and verifies that any difference in identity fields
   * produces inequality.
   *
   * @return true, if successful
   * @throws Exception the exception
   */
  public boolean testIdentityFieldNotEquals() throws Exception {
    Logger.getLogger(getClass()).debug(
        "Test identity field not equals - " + clazz.getName());

    // Create an object
    Object o1 = createObject(1);

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
      if (includes != null && !includes.contains(fieldName.toLowerCase()))
        continue;
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

      // Create second object each time, so we can compare resetting each field
      // value
      Object o2 = createObject(1);
      // Change the field (use an initializer of 2).
      setField(o2, getter, m, args[0], 2);

      if (o1.equals(o2)) {
        // if equals, fail here
        Logger.getLogger(getClass()).debug(
            "  o1 = " + o1.hashCode() + ", " + o1);
        Logger.getLogger(getClass()).debug(
            "  o2 = " + o2.hashCode() + ", " + o2);
        throw new Exception("Equality did not change when field " + fieldName
            + " was changed");
      }

    }
    return true;
  }

  /**
   * Creates two objects with the same field values and verifies they have equal
   * hashcodes.
   *
   * @return true, if successful
   * @throws Exception the exception
   */
  public boolean testIdentityFieldHashcode() throws Exception {
    Logger.getLogger(getClass()).debug(
        "Test identity field hashcode - " + clazz.getName());
    Object o1 = createObject(1);
    Object o2 = createObject(1);
    return o1.hashCode() == o2.hashCode();
  }

  /**
   * Creates two objects and verifies for each that changing the non-included or
   * excluded fields does not affect the hashcode.
   *
   * @return true, if successful
   * @throws Exception the exception
   */
  public boolean testNonIdentityFieldHashcode() throws Exception {
    Logger.getLogger(getClass()).debug(
        "Test non identity field hashcode - " + clazz.getName());
    Object o1 = createObject(1);
    Object o2 = createObject(1);
    setFields(o2, false, true, 2);
    return o1.hashCode() == o2.hashCode();
  }

  /**
   * Creates two objects and verifies that any difference in identity fields
   * produces different hashcodes.
   *
   * @return true, if successful
   * @throws Exception the exception
   */
  public boolean testIdentityFieldDifferentHashcode() throws Exception {
    Logger.getLogger(getClass()).debug(
        "Test identity field different hashcode - " + clazz.getName());

    // Create an object
    Object o1 = createObject(1);

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
      if (includes != null && !includes.contains(fieldName.toLowerCase()))
        continue;
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

      // Create second object each time, so we can compare resetting each field
      // value
      Object o2 = createObject(1);
      Logger.getLogger(getClass()).debug("  field = " + fieldName);

      // Change the field (use an initializer of 2).
      setField(o2, getter, m, args[0], 2);

      if (o1.hashCode() == o2.hashCode()) {
        // if equals, fail here
        Logger.getLogger(getClass()).debug("  o1 = " + o1);
        Logger.getLogger(getClass()).debug("  o2 = " + o2);
        throw new Exception("Hashcode did not change when field " + fieldName
            + " was changed");
      }

    }
    return true;
  }

}
