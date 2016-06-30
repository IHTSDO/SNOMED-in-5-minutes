package org.ihtsdo.tutorial.rf2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.ihtsdo.tutorial.util.CopyConstructorTester;
import org.ihtsdo.tutorial.util.EqualsHashcodeTester;
import org.ihtsdo.tutorial.util.GetterSetterTester;
import org.ihtsdo.tutorial.util.ProxyTester;
import org.ihtsdo.tutorial.util.Utility;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for MatchResults.
 */
public class MatchResultsUnitTest extends TestSupport {

  /** The model object to test. */
  private MatchResults object;

  /** The fixture m1. */
  private HashMap<String, Long> m1;

  /** The fixture m2. */
  private HashMap<String, Long> m2;

  /** The fixture l1. */
  private List<Match> l1;

  /** The fixture l2. */
  private List<Match> l2;

  /** The fixture f1. */
  private HashMap<String, HashMap<String, Long>> f1;

  /** The fixture f1. */
  private HashMap<String, HashMap<String, Long>> f2;

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
    object = new MatchResults();
    m1 = new HashMap<>();
    m1.put("1", 1L);
    m2 = new HashMap<>();
    m2.put("2", 2L);

    f1 = new HashMap<>();
    f1.put("1", m1);

    f2 = new HashMap<>();
    f2.put("2", m2);

    ProxyTester tester = new ProxyTester(new Match());
    l1 = new ArrayList<>();
    l1.add((Match) tester.createObject(1));
    l2 = new ArrayList<>();
    l1.add((Match) tester.createObject(2));
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
    tester.include("matches");
    tester.include("details");
    tester.include("filters");
    tester.proxy(HashMap.class, 1, m1);
    tester.proxy(HashMap.class, 2, m2);
    tester.proxy(List.class, 1, l1);
    tester.proxy(List.class, 2, l2);
    assertTrue(tester.testIdentityFieldEquals());
    assertTrue(tester.testNonIdentityFieldEquals());
    assertTrue(tester.testIdentityFieldNotEquals());
    // assertTrue(tester.testIdentityFieldHashcode());
    assertTrue(tester.testNonIdentityFieldHashcode());
    // assertTrue(tester.testIdentityFieldDifferentHashcode());
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
    tester.proxy(HashMap.class, 1, m1);
    tester.proxy(HashMap.class, 2, m2);
    tester.proxy(List.class, 1, l1);
    tester.proxy(List.class, 2, l2);
    assertTrue(tester.testCopyConstructor(MatchResults.class));
  }

  /**
   * Test XML serialization.
   *
   * @throws Exception the exception
   */
  @Test
  public void testXmlSerialization007() throws Exception {
    Logger.getLogger(getClass()).debug("TEST " + name.getMethodName());

    MatchResults results = new MatchResults();
    results.setDetails(m1);
    results.setMatches(l1);
    results.setFilters(f1);

    String json = Utility.getJsonForGraph(results);
    MatchResults results2 = Utility.getGraphForJson(json, MatchResults.class);
    assertEquals(results, results2);
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
