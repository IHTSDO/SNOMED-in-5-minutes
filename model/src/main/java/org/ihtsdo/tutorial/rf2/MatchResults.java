/*
 *    Copyright 2016 West Coast Informatics, LLC
 */
package org.ihtsdo.tutorial.rf2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a list of description matches from a search.
 * 
 * <pre>
 * { matches: [{"term": "Heart attack",
 *   "conceptId":"22298006",
 *   "active":true,
 *   "conceptActive":true,
 *   "fsn":"Myocardial infarction (disorder)",
 *   "module":"900000000000207008",
 *   "definitionStatus":"Fully defined"}, ... ],
 *   "details":{"total":13,"skipTo":0,"returnLimit":100},
 *   "filters":{
 *     "lang":{"english":13},
 *     "semTag":{"disorder":1,"finding":6,"assessment scale":6},
 *     "module":{"900000000000207008":13},
 *     "refsetId":{"900000000000497000":13,"900000000000498005":13}}
 *    }
 * </pre>
 * 
 */
@XmlRootElement(name = "results")
public class MatchResults {

  /** The matches. */
  private List<Match> matches;

  /** The details. */
  private HashMap<String, Long> details;

  /** The filters. */
  private HashMap<String, HashMap<String, Long>> filters;

  /**
   * Instantiates an empty {@link MatchResults}.
   */
  public MatchResults() {
    // n/a
  }

  /**
   * Instantiates a {@link MatchResults} from the specified parameters.
   *
   * @param results the results
   */
  public MatchResults(MatchResults results) {
    matches = new ArrayList<>(results.getMatches());
    details = new HashMap<>(results.getDetails());
    filters = new HashMap<>(results.getFilters());
  }

  /**
   * Returns the matches.
   *
   * @return the matches
   */
  public List<Match> getMatches() {
    if (matches == null) {
      matches = new ArrayList<>();
    }
    return matches;
  }

  /**
   * Sets the matches.
   *
   * @param matches the matches
   */
  public void setMatches(List<Match> matches) {
    this.matches = matches;
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

  /**
   * Returns the filters.
   *
   * @return the filters
   */
  public HashMap<String, HashMap<String, Long>> getFilters() {
    if (filters == null) {
      filters = new HashMap<>();
    }

    return filters;
  }

  /**
   * Sets the filters.
   *
   * @param filters the filters
   */
  public void setFilters(HashMap<String, HashMap<String, Long>> filters) {
    this.filters = filters;
  }

  /* see superclass */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((details == null) ? 0 : details.hashCode());
    result = prime * result + ((filters == null) ? 0 : filters.hashCode());
    result = prime * result + ((matches == null) ? 0 : matches.hashCode());
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
    MatchResults other = (MatchResults) obj;
    if (details == null) {
      if (other.details != null)
        return false;
    } else if (!details.equals(other.details))
      return false;
    if (filters == null) {
      if (other.filters != null)
        return false;
    } else if (!filters.equals(other.filters))
      return false;
    if (matches == null) {
      if (other.matches != null)
        return false;
    } else if (!matches.equals(other.matches))
      return false;
    return true;
  }

  /* see superclass */
  @Override
  public String toString() {
    return "MatchResults [matches=" + matches + ", details=" + details
        + ", filters=" + filters + "]";
  }
}
