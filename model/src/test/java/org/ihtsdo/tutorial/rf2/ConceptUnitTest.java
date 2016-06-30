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
 * Unit test for Concept.
 */
public class ConceptUnitTest extends TestSupport {

  /** The model object to test. */
  private Concept object;

  /** The fixture c1. */
  private ConceptRef c1;

  /** The fixture c2. */
  private ConceptRef c2;

  /** The fixture l1. */
  private Language l1;

  /** The fixture l2. */
  private Language l2;

  /** The fixture m1. */
  private Membership m1;

  /** The fixture m2. */
  private Membership m2;

  /** The fixture d1. */
  private Description d1;

  /** The fixture d2. */
  private Description d2;

  /** The fixture r1. */
  private Relationship r1;

  /** The fixture r2. */
  private Relationship r2;

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
    object = new Concept();
    ProxyTester tester = new ProxyTester(new ConceptRef());
    c1 = (ConceptRef) tester.createObject(1);
    c2 = (ConceptRef) tester.createObject(2);

    ProxyTester tester2 = new ProxyTester(new Membership());
    m1 = (Membership) tester2.createObject(1);
    m2 = (Membership) tester2.createObject(2);

    ProxyTester tester3 = new ProxyTester(new Language());
    l1 = (Language) tester3.createObject(1);
    l2 = (Language) tester3.createObject(2);

    ProxyTester tester4 = new ProxyTester(new Description());
    d1 = (Description) tester4.createObject(1);
    d2 = (Description) tester4.createObject(2);

    ProxyTester tester5 = new ProxyTester(new Relationship());
    r1 = (Relationship) tester5.createObject(1);
    r2 = (Relationship) tester5.createObject(2);

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
    tester.include("conceptId");
    tester.include("defaultTerm");
    tester.include("definitionStatus");
    tester.include("active");
    tester.include("module");
    tester.include("fsn");

    tester.proxy(ConceptRef.class, 1, c1);
    tester.proxy(ConceptRef.class, 2, c2);
    tester.proxy(Membership.class, 1, m1);
    tester.proxy(Membership.class, 2, m2);
    tester.proxy(Language.class, 1, l1);
    tester.proxy(Language.class, 2, l2);
    tester.proxy(Description.class, 1, d1);
    tester.proxy(Description.class, 2, d2);
    tester.proxy(Relationship.class, 1, r1);
    tester.proxy(Relationship.class, 2, r2);

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
    tester.proxy(Membership.class, 1, m1);
    tester.proxy(Membership.class, 2, m2);
    tester.proxy(Language.class, 1, l1);
    tester.proxy(Language.class, 2, l2);
    tester.proxy(Description.class, 1, d1);
    tester.proxy(Description.class, 2, d2);
    tester.proxy(Relationship.class, 1, r1);
    tester.proxy(Relationship.class, 2, r2);

    assertTrue(tester.testCopyConstructor(Concept.class));
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
    tester.proxy(Membership.class, 1, m1);
    tester.proxy(Membership.class, 2, m2);
    tester.proxy(Language.class, 1, l1);
    tester.proxy(Language.class, 2, l2);
    tester.proxy(Description.class, 1, d1);
    tester.proxy(Description.class, 2, d2);
    tester.proxy(Relationship.class, 1, r1);
    tester.proxy(Relationship.class, 2, r2);
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
