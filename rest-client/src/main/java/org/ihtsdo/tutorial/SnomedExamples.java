package org.ihtsdo.tutorial;

/**
 * Copyright 2016 West Coast Informatics, LLC
 */

import org.ihtsdo.tutorial.rf2.Concept;
import org.ihtsdo.tutorial.rf2.Description;
import org.ihtsdo.tutorial.rf2.Language;
import org.ihtsdo.tutorial.rf2.Match;
import org.ihtsdo.tutorial.rf2.MatchResults;
import org.ihtsdo.tutorial.rf2.Membership;
import org.ihtsdo.tutorial.rf2.Relationship;

/**
 * An application entry point for testing the modes of accessing REST APIs.
 */
public class SnomedExamples {

  /** The client. */
  private static SnomedClientRest client = new SnomedClientRest();

  /**
   * Application entry point.
   *
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    // args[0] = command
    // args[1..] = parameters

    try {

      // Find a concept by a string (e.g. “heart attack”)
      // java -cp rest-client/target/snomed-in-5-minutes.jar
      // org.ihtsdo.tutorial.SnomedExamples
      // findByQuery "heart attack"
      if (args[0].equals("findByQuery") && args.length == 2) {
        final MatchResults results = client.findByQuery(args[1]);
        for (final Match match : results.getMatches()) {
          System.out.println("    match = " + match);
        }
        System.out.println("    details = " + results.getDetails());
        System.out.println("    filters = " + results.getFilters());

      }

      // Find/get a concept by a description SCTID (e.g. “679406011”)
      // java -cp rest-client/target/snomed-in-5-minutes.jar
      // org.ihtsdo.tutorial.SnomedExamples
      // findByDescriptionId 679406011
      else if (args[0].equals("findByDescriptionId") && args.length == 2) {
        final MatchResults results = client.findByDescriptionId(args[1]);
        for (final Match match : results.getMatches()) {
          System.out.println("    match = " + match);
        }
        System.out.println("    details = " + results.getDetails());
        System.out.println("    filters = " + results.getFilters());
      }

      // Find/get a concept by a concept SCTID (e.g. “109152007”)
      // java -cp rest-client/target/snomed-in-5-minutes.jar
      // org.ihtsdo.tutorial.SnomedExamples
      // findByConceptId 109152007
      else if (args[0].equals("findByConceptId") && args.length == 2) {
        final Concept concept = client.findByConceptId(args[1]);
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
      }

      // Find a concept by a string (e.g. “heart”) but only in the Procedures
      // semantic tag
      // java -cp rest-client/target/snomed-in-5-minutes.jar
      // org.ihtsdo.tutorial.SnomedExamples
      // findByQueryWithFilter "heart" "procedure"
      else if (args[0].equals("findByQueryWithFilter") && args.length == 3) {
        final MatchResults results = client.findByQueryWithFilter(args[1], args[2]);
        for (final Match match : results.getMatches()) {
          System.out.println("    match = " + match);
        }
        System.out.println("    details = " + results.getDetails());
        System.out.println("    filters = " + results.getFilters());

      }

      // Error Condition
      else {
        System.out.println("Invalid call");
        System.out
            .println("Usage: java -cp rest-client/target/snomed-in-5-minutes.jar org.ihtsdo.tutorial.SnomedExamples <command> <arg> [<arg2>]");
        System.out.println("Sample calls");
        System.out.println("  - findByQuery \"heart attack\"");
        System.out.println("  - findByDescriptionId 679406011");
        System.out.println("  - findByConceptId 109152007");
        System.out.println("  - findByQueryWithFilter \"heart\" \"procedure\"");
        System.exit(1);
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.exit(1);
    }
    System.exit(0);
  }
}
