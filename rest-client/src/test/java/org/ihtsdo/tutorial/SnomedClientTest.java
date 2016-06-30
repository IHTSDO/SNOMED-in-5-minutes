package org.ihtsdo.tutorial;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.ihtsdo.tutorial.rf2.Concept;
import org.ihtsdo.tutorial.rf2.Description;
import org.ihtsdo.tutorial.rf2.Language;
import org.ihtsdo.tutorial.rf2.Match;
import org.ihtsdo.tutorial.rf2.MatchResults;
import org.ihtsdo.tutorial.rf2.Membership;
import org.ihtsdo.tutorial.rf2.Relationship;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Unit test for SNOMED REST Client.
 */
public class SnomedClientTest {

  /**
   * Setup class.
   */
  @BeforeClass
  public static void setupClass() {
    // do nothing
  }

  /**
   * Setup.
   */
  @Before
  public void setup() {
    // n/a
  }

  /**
   * Test search for "heart attack"
   *
   * @throws Exception the exception
   */
  @Test
  public void testFind() throws Exception {
    Logger.getLogger(getClass()).info("TEST testFind");

    // Perform a "heart attack" search
    final SnomedClientRest client = new SnomedClientRest();
    final MatchResults results = client.findByQuery("heart attack");
    System.out.println("  results = " + results);
    for (final Match match : results.getMatches()) {
      System.out.println("    match = " + match);
    }
    System.out.println("    details = " + results.getDetails());
    System.out.println("    filters = " + results.getFilters());

    // Verify things about the results
    assertEquals(results.getDetails().get("total"), new Long(13));
    assertEquals(results.getMatches().get(0).getTerm(), "Heart attack");
    assertEquals(results.getMatches().get(0).getConceptId(), "22298006");
    assertTrue(results.getMatches().get(0).isActive());
    assertTrue(results.getMatches().get(0).isConceptActive());
    assertEquals(results.getMatches().get(0).getFsn(),
        "Myocardial infarction (disorder)");
    assertEquals(results.getMatches().get(0).getModule(), "900000000000207008");
    assertEquals(results.getMatches().get(0).getDefinitionStatus(),
        "Fully defined");

    assertEquals(results.getDetails().get("total"), new Long(13));
    assertEquals(results.getDetails().get("skipTo"), new Long(0));
    assertEquals(results.getDetails().get("returnLimit"), new Long(100));

    assertEquals(results.getFilters().get("lang").get("english"), new Long(13));
    assertEquals(results.getFilters().get("semTag").get("disorder"),
        new Long(1));
    assertEquals(results.getFilters().get("semTag").get("finding"), new Long(6));
    assertEquals(results.getFilters().get("semTag").get("assessment scale"),
        new Long(6));
    assertEquals(results.getFilters().get("module").get("900000000000207008"),
        new Long(13));

  }

  /**
   * Test search by description id 679406011
   *
   * @throws Exception the exception
   */
  @Test
  public void testFindByDescriptionId() throws Exception {
    Logger.getLogger(getClass()).info("TEST testFindByDescriptionId");

    // Perform a "679406011" description id search
    final SnomedClientRest client = new SnomedClientRest();
    final MatchResults results = client.findByDescriptionId("679406011");
    System.out.println("  results = " + results);
    for (final Match match : results.getMatches()) {
      System.out.println("    match = " + match);
    }
    System.out.println("    details = " + results.getDetails());
    System.out.println("    filters = " + results.getFilters());

    // Verify things about the results
    assertEquals(results.getDetails().get("total"), new Long(1));

    assertEquals(results.getMatches().get(0).getTerm(),
        "Methylphenyltetrahydropyridine (substance)");
    assertEquals(results.getMatches().get(0).getConceptId(), "285407008");
    assertTrue(results.getMatches().get(0).isActive());
    assertTrue(results.getMatches().get(0).isConceptActive());
    assertEquals(results.getMatches().get(0).getFsn(),
        "Methylphenyltetrahydropyridine (substance)");
    assertEquals(results.getMatches().get(0).getModule(), "900000000000207008");
    assertNull(results.getMatches().get(0).getDefinitionStatus());

    assertEquals(results.getDetails().get("total"), new Long(1));
    assertEquals(results.getDetails().get("skipTo"), new Long(0));
    assertEquals(results.getDetails().get("returnLimit"), new Long(100));

    assertEquals(results.getFilters().get("lang").size(), 0);
    assertEquals(results.getFilters().get("semTag").size(), 0);
    assertEquals(results.getFilters().get("module").size(), 0);

  }

  /**
   * Test search by concept id.
   *
   * @throws Exception the exception
   */
  @Test
  public void testFindByConceptId() throws Exception {
    Logger.getLogger(getClass()).info("TEST testFindByConceptId");

    // Perform a "109152007" concept id search
    final SnomedClientRest client = new SnomedClientRest();
    final Concept concept = client.findByConceptId("109152007");
    System.out.println("  concept = " + concept);
    for (final Description desc : concept.getDescriptions()) {
      System.out.println("    description = " + desc);
      for (final Language lang : desc.getLangMemberships()) {
        System.out.println("      language = " + lang);
      }
    }
    for (final Relationship rel : concept.getRelationships()) {
      System.out.println("    relationship = " + rel);
    }
    for (final Membership member : concept.getMemberships()) {
      System.out.println("    membership = " + member);
    }

    // Verify things about the results
    assertEquals(concept.get_id(), "576d5659c2097f9d35e1266a");
    assertEquals(concept.getConceptId(), "109152007");
    assertEquals(concept.getDefaultTerm(), "Bilirubin test kit (substance)");
    assertTrue(concept.isLeafInferred());
    assertTrue(concept.isLeafStated());
    assertTrue(concept.isActive());
    assertEquals(concept.getDefinitionStatus(), "Primitive");
    assertEquals(concept.getDescriptions().size(), 2);
    assertEquals(concept.getEffectiveTime(), "20020131");
    assertEquals(concept.getFsn(), "Bilirubin test kit (substance)");
    assertEquals(concept.getInferredAncestors().size(), 5);
    assertEquals(concept.getStatedAncestors().size(), 5);
    assertEquals(concept.getInferredDescendants(), 0);
    assertEquals(concept.getStatedDescendants(), 0);
    assertEquals(concept.getMemberships().size(), 2);
    assertEquals(concept.getModule(), "900000000000207008");
    assertEquals(concept.getRelationships().size(), 3);
    assertEquals(concept.getSemtag(), "substance");
    assertEquals(concept.getStatedRelationships().size(), 1);

  }

  /**
   * Test search within semantic category "procedure"
   *
   * @throws Exception the exception
   */
  @Test
  public void testFindByQueryWithFilter() throws Exception {
    Logger.getLogger(getClass()).info("TEST testFindByQueryWithFilter");

    // Perform a "heart" search within "procedure"
    final SnomedClientRest client = new SnomedClientRest();
    final MatchResults results =
        client.findByQueryWithFilter("heart", "procedure");
    System.out.println("  results = " + results);
    for (final Match match : results.getMatches()) {
      System.out.println("    match = " + match);
    }
    System.out.println("    details = " + results.getDetails());
    System.out.println("    filters = " + results.getFilters());

    // Assert things about the result
    assertEquals(results.getDetails().get("total"), new Long(746));

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
