/**
 * Copyright 2016 West Coast Informatics, LLC
 */
package org.ihtsdo.tutorial.util;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.ihtsdo.tutorial.util.Utility;

/**
 * Automates JUnit testing of XML Serialization.
 */
public class XmlSerializationTester extends ProxyTester {

  /**
   * Constructs a new getter/setter tester to test objects of a particular
   * class.
   * 
   * @param obj Object to test.
   */
  public XmlSerializationTester(Object obj) {
    super(obj);
  }

  /**
   * Tests XML and JSON serialization for equality,.
   *
   * @return true, if successful
   * @throws Exception the exception
   */
  public boolean testXmlSerialization() throws Exception {
    Logger.getLogger(getClass()).debug(
        "Test xml serialization - " + clazz.getName());
    Object obj = createObject(1);
    Logger.getLogger(getClass()).debug(obj);
    String xml = Utility.getStringForGraph(obj);
    Logger.getLogger(getClass()).debug("xml = " + xml);
    Object obj2 = Utility.getGraphForString(xml, obj.getClass());
    String json = Utility.getJsonForGraph(obj);
    Logger.getLogger(getClass()).debug("json = " + json);
    Object obj3 = Utility.getGraphForJson(json, obj.getClass());
    Logger.getLogger(getClass()).debug(obj);
    Logger.getLogger(getClass()).debug(obj2);
    Logger.getLogger(getClass()).debug(obj3);

    // If obj has an "id" field, compare the ids
    try {
      final Method method =
          obj.getClass().getMethod("getId", new Class<?>[] {});
      if (method != null && method.getReturnType() == Long.class) {

        final Long id1 = (Long) method.invoke(obj, new Object[] {});
        final Long id2 = (Long) method.invoke(obj2, new Object[] {});
        final Long id3 = (Long) method.invoke(obj3, new Object[] {});
        if (!id1.equals(id2) || !id2.equals(id3)) {
          Logger.getLogger(getClass()).debug(
              "  id fields do not match " + id1 + ", " + id2 + ", " + id3);
          return false;
        }
      }
    } catch (NoSuchMethodException e) {
      // this is OK
    }
    return obj.equals(obj2) && obj.equals(obj3);
  }

}
