package org.ihtsdo.tutorial.rf2;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.ihtsdo.tutorial.util.CopyConstructorTester;
import org.ihtsdo.tutorial.util.EqualsHashcodeTester;
import org.ihtsdo.tutorial.util.GetterSetterTester;
import org.ihtsdo.tutorial.util.ProxyTester;
import org.ihtsdo.tutorial.util.XmlSerializationTester;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for Membership.
 */
public class MembershipUnitTest extends TestSupport {

  /** The model object to test. */
  private Membership object;

  /** The fixture c1. */
  private ConceptRef c1;

  /** The fixture c2. */
  private ConceptRef c2;

  /**
   * Setup class.
   */
  @BeforeClass
  public static void setupClass() {
    // do nothing
  }

  /**
   * Setup.
   *
   * @throws Exception the exception
   */
  @Before
  public void setup() throws Exception {
    object = new Membership();
    ProxyTester tester = new ProxyTester(new ConceptRef());
    c1 = (ConceptRef) tester.createObject(1);
    c2 = (ConceptRef) tester.createObject(2);
  }

  /**
   * Test getter and setter methods of model object.
   *
   * @throws Exception the exception
   */
  @Test
  public void testModelGetSet() throws Exception {
    Logger.getLogger(getClass()).debug("TEST " + name.getMethodName());
    GetterSetterTester tester = new GetterSetterTester(object);
    tester.test();
  }

  /**
   * Test equals and hascode methods.
   *
   * @throws Exception the exception
   */
  @Test
  public void testEqualsHashcode() throws Exception {
    Logger.getLogger(getClass()).debug("TEST " + name.getMethodName());
    EqualsHashcodeTester tester = new EqualsHashcodeTester(object);
    tester.include("otherValue");
    tester.include("referencedComponentId");
    tester.include("refset");
    tester.include("type");
    tester.include("uuid");
    tester.include("active");
    tester.include("module");

    tester.proxy(ConceptRef.class, 1, c1);
    tester.proxy(ConceptRef.class, 2, c2);

    assertTrue(tester.testIdentityFieldEquals());
    assertTrue(tester.testNonIdentityFieldEquals());
    assertTrue(tester.testIdentityFieldNotEquals());
    assertTrue(tester.testIdentityFieldHashcode());
    assertTrue(tester.testNonIdentityFieldHashcode());
    assertTrue(tester.testIdentityFieldDifferentHashcode());
  }

  /**
   * Test copy constructor.
   *
   * @throws Exception the exception
   */
  @Test
  public void testCopy() throws Exception {
    Logger.getLogger(getClass()).debug("TEST " + name.getMethodName());
    CopyConstructorTester tester = new CopyConstructorTester(object);
    tester.proxy(ConceptRef.class, 1, c1);
    tester.proxy(ConceptRef.class, 2, c2);
    assertTrue(tester.testCopyConstructor(Membership.class));
  }

  /**
   * Test XML serialization.
   *
   * @throws Exception the exception
   */
  @Test
  public void testXmlSerialization007() throws Exception {
    Logger.getLogger(getClass()).debug("TEST " + name.getMethodName());
    XmlSerializationTester tester = new XmlSerializationTester(object);
    tester.proxy(ConceptRef.class, 1, c1);
    tester.proxy(ConceptRef.class, 2, c2);
    assertTrue(tester.testXmlSerialization());
  }

  /**
   * Teardown.
   */
  @After
  public void teardown() {
    // do nothing
  }

  /**
   * Teardown class.
   */
  @AfterClass
  public static void teardownClass() {
    // do nothing
  }

}
