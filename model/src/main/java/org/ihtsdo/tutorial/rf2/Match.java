package org.ihtsdo.tutorial.rf2;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a description match from a search.
 * 
 * <pre>
 * { "term": "Heart attack",
 *   "conceptId":"22298006",
 *   "active":true,
 *   "conceptActive":true,
 *   "fsn":"Myocardial infarction (disorder)",
 *   "module":"900000000000207008",
 *   "definitionStatus":"Fully defined"}
 * </pre>
 * 
 */
@XmlRootElement(name = "match")
public class Match {

  /** The term. */
  private String term;

  /** The concept id. */
  private String conceptId;

  /** The active. */
  private boolean active;

  /** The concept active. */
  private boolean conceptActive;

  /** The fsn. */
  private String fsn;

  /** The module. */
  private String module;

  /** The definition status. */
  private String definitionStatus;

  /**
   * Instantiates an empty {@link Match}.
   */
  public Match() {
    // n/a
  }

  /**
   * Instantiates a {@link Match} from the specified parameters.
   *
   * @param match the match
   */
  public Match(Match match) {
    term = match.getTerm();
    conceptId = match.getConceptId();
    active = match.isActive();
    conceptActive = match.isConceptActive();
    fsn = match.getFsn();
    module = match.getModule();
    definitionStatus = match.getDefinitionStatus();
  }

  /**
   * Returns the term.
   *
   * @return the term
   */
  public String getTerm() {
    return term;
  }

  /**
   * Sets the term.
   *
   * @param term the term
   */
  public void setTerm(String term) {
    this.term = term;
  }

  /**
   * Returns the concept id.
   *
   * @return the concept id
   */
  public String getConceptId() {
    return conceptId;
  }

  /**
   * Sets the concept id.
   *
   * @param conceptId the concept id
   */
  public void setConceptId(String conceptId) {
    this.conceptId = conceptId;
  }

  /**
   * Indicates whether or not active is the case.
   *
   * @return <code>true</code> if so, <code>false</code> otherwise
   */
  public boolean isActive() {
    return active;
  }

  /**
   * Sets the active.
   *
   * @param active the active
   */
  public void setActive(boolean active) {
    this.active = active;
  }

  /**
   * Indicates whether or not concept active is the case.
   *
   * @return <code>true</code> if so, <code>false</code> otherwise
   */
  public boolean isConceptActive() {
    return conceptActive;
  }

  /**
   * Sets the concept active.
   *
   * @param conceptActive the concept active
   */
  public void setConceptActive(boolean conceptActive) {
    this.conceptActive = conceptActive;
  }

  /**
   * Returns the fsn.
   *
   * @return the fsn
   */
  public String getFsn() {
    return fsn;
  }

  /**
   * Sets the fsn.
   *
   * @param fsn the fsn
   */
  public void setFsn(String fsn) {
    this.fsn = fsn;
  }

  /**
   * Returns the module.
   *
   * @return the module
   */
  public String getModule() {
    return module;
  }

  /**
   * Sets the module.
   *
   * @param module the module
   */
  public void setModule(String module) {
    this.module = module;
  }

  /**
   * Returns the definition status.
   *
   * @return the definition status
   */
  public String getDefinitionStatus() {
    return definitionStatus;
  }

  /**
   * Sets the definition status.
   *
   * @param definitionStatus the definition status
   */
  public void setDefinitionStatus(String definitionStatus) {
    this.definitionStatus = definitionStatus;
  }

  /* see superclass */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (active ? 1231 : 1237);
    result = prime * result + (conceptActive ? 1231 : 1237);
    result = prime * result + ((conceptId == null) ? 0 : conceptId.hashCode());
    result =
        prime * result
            + ((definitionStatus == null) ? 0 : definitionStatus.hashCode());
    result = prime * result + ((fsn == null) ? 0 : fsn.hashCode());
    result = prime * result + ((module == null) ? 0 : module.hashCode());
    result = prime * result + ((term == null) ? 0 : term.hashCode());
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
    Match other = (Match) obj;
    if (active != other.active)
      return false;
    if (conceptActive != other.conceptActive)
      return false;
    if (conceptId == null) {
      if (other.conceptId != null)
        return false;
    } else if (!conceptId.equals(other.conceptId))
      return false;
    if (definitionStatus == null) {
      if (other.definitionStatus != null)
        return false;
    } else if (!definitionStatus.equals(other.definitionStatus))
      return false;
    if (fsn == null) {
      if (other.fsn != null)
        return false;
    } else if (!fsn.equals(other.fsn))
      return false;
    if (module == null) {
      if (other.module != null)
        return false;
    } else if (!module.equals(other.module))
      return false;
    if (term == null) {
      if (other.term != null)
        return false;
    } else if (!term.equals(other.term))
      return false;
    return true;
  }

  /* see superclass */
  @Override
  public String toString() {
    return "Match [term=" + term + ", conceptId=" + conceptId + ", active="
        + active + ", conceptActive=" + conceptActive + ", fsn=" + fsn
        + ", module=" + module + ", definitionStatus=" + definitionStatus + "]";
  }

}
