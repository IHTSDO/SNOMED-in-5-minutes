/*
 *    Copyright 2016 West Coast Informatics, LLC
 */
package org.ihtsdo.tutorial.rf2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a list of description members from a search.
 * 
 * <pre>
 * {"members":[{"_id":"56f658510d0da7ca21cff4a0","isLeafInferred":false,"isLeafStated":false,"conceptId":"47625008","defaultTerm":"Intravenous route (qualifier value)","definitionStatus":"Primitive","active":true,"module":"900000000000207008"},{"_id":"56f658520d0da7ca21cff6d8","isLeafInferred":false,"isLeafStated":false,"conceptId":"448491004","defaultTerm":"Intrajejunal route (qualifier value)","definitionStatus":"Primitive","active":true,"module":"900000000000207008"},{"_id":"56f658520d0da7ca21cff7bf","isLeafInferred":true,"isLeafStated":true,"conceptId":"420047004","defaultTerm":"Periosteal route (qualifier value)","definitionStatus":"Primitive","active":true,"module":"900000000000207008"},{"_id":"56f658530d0da7ca21d0056c","isLeafInferred":true,"isLeafStated":true,"conceptId":"416174007","defaultTerm":"Suborbital route (qualifier value)","definitionStatus":"Primitive","active":true,"module":"900000000000207008"},{"_id":"56f658540d0da7ca21d00d42","isLeafInferred":true,"isLeafStated":true,"conceptId":"60213007","defaultTerm":"Intramedullary route (qualifier value)","definitionStatus":"Primitive","active":true,"module":"900000000000207008"},...],"details":{"total":144,"refsetId":442311000124105}}
 * </pre>
 * 
 */
@XmlRootElement(name = "results")
public class MemberResults {

  /** The members. */
  private List<Concept> members;

  /** The details. */
  private HashMap<String, Long> details;

  /**
   * Instantiates an empty {@link MemberResults}.
   */
  public MemberResults() {
    // n/a
  }

  /**
   * Instantiates a {@link MemberResults} from the specified parameters.
   *
   * @param results the results
   */
  public MemberResults(MemberResults results) {
    members = new ArrayList<>(results.getMembers());
    details = new HashMap<>(results.getDetails());
  }

  /**
   * Returns the members.
   *
   * @return the members
   */
  public List<Concept> getMembers() {
    if (members == null) {
      members = new ArrayList<>();
    }
    return members;
  }

  /**
   * Sets the members.
   *
   * @param members the members
   */
  public void setMembers(List<Concept> members) {
    this.members = members;
  }

  /**
   * Returns the details.
   *
   * @return the details
   */
  public HashMap<String, Long> getDetails() {
    if (details == null) {
      details = new HashMap<>();
    }
    return details;
  }

  /**
   * Sets the details.
   *
   * @param details the details
   */
  public void setDetails(HashMap<String, Long> details) {
    this.details = details;
  }

  /* see superclass */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((details == null) ? 0 : details.hashCode());
    result = prime * result + ((members == null) ? 0 : members.hashCode());
    return result;
  }

  /* see superclass */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MemberResults other = (MemberResults) obj;
    if (details == null) {
      if (other.details != null)
        return false;
    } else if (!details.equals(other.details))
      return false;
    if (members == null) {
      if (other.members != null)
        return false;
    } else if (!members.equals(other.members))
      return false;
    return true;
  }

  /* see superclass */
  @Override
  public String toString() {
    return "MemberResults [members=" + members + ", details=" + details + "]";
  }
}
