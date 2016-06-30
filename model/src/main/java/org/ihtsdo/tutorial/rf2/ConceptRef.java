/*
 *    Copyright 2016 West Coast Informatics, LLC
 */
package org.ihtsdo.tutorial.rf2;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a concept reference.
 * 
 * <pre>
 * { "conceptId":"22298006",
 *   "defaultTerm": "some term",
 *   "definitionStatus" : "Fully defined",
 *   "statedDescendants" : 0,
 *   "inferredDescendants" : 0,
 *   "active", true,
 *   "effectiveTime", "20020131",
 *   "module":"900000000000207008"
 *   }
 * </pre>
 * 
 */
@XmlRootElement(name = "conceptRef")
public class ConceptRef {

  /** The concept id. */
  private String conceptId;

  /** The default term. */
  private String defaultTerm;

  /** The definition status. */
  private String definitionStatus;

  /** The stated descendants. */
  private int statedDescendants;

  /** The inferred descendants. */
  private int inferredDescendants;

  /** The active. */
  private boolean active;

  /** The effective time. */
  private String effectiveTime;

  /** The module. */
  private String module;

  /**
   * Instantiates an empty {@link ConceptRef}.
   */
  public ConceptRef() {
    // n/a
  }

  /**
   * Instantiates a {@link ConceptRef} from the specified parameters.
   *
   * @param ref the ref
   */
  public ConceptRef(ConceptRef ref) {
    conceptId = ref.getConceptId();
    defaultTerm = ref.getDefaultTerm();
    definitionStatus = ref.getDefinitionStatus();
    statedDescendants = ref.getStatedDescendants();
    inferredDescendants = ref.getInferredDescendants();
    active = ref.isActive();
    effectiveTime = ref.getEffectiveTime();
    module = ref.getModule();
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
   * Returns the default term.
   *
   * @return the default term
   */
  public String getDefaultTerm() {
    return defaultTerm;
  }

  /**
   * Sets the default term.
   *
   * @param defaultTerm the default term
   */
  public void setDefaultTerm(String defaultTerm) {
    this.defaultTerm = defaultTerm;
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

  /**
   * Returns the stated descendants.
   *
   * @return the stated descendants
   */
  public int getStatedDescendants() {
    return statedDescendants;
  }

  /**
   * Sets the stated descendants.
   *
   * @param statedDescendants the stated descendants
   */
  public void setStatedDescendants(int statedDescendants) {
    this.statedDescendants = statedDescendants;
  }

  /**
   * Returns the inferred descendants.
   *
   * @return the inferred descendants
   */
  public int getInferredDescendants() {
    return inferredDescendants;
  }

  /**
   * Sets the inferred descendants.
   *
   * @param inferredDescendants the inferred descendants
   */
  public void setInferredDescendants(int inferredDescendants) {
    this.inferredDescendants = inferredDescendants;
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
   * Returns the effective time.
   *
   * @return the effective time
   */
  public String getEffectiveTime() {
    return effectiveTime;
  }

  /**
   * Sets the effective time.
   *
   * @param effectiveTime the effective time
   */
  public void setEffectiveTime(String effectiveTime) {
    this.effectiveTime = effectiveTime;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (active ? 1231 : 1237);
    result = prime * result + ((conceptId == null) ? 0 : conceptId.hashCode());
    result =
        prime * result + ((defaultTerm == null) ? 0 : defaultTerm.hashCode());
    result =
        prime * result
            + ((definitionStatus == null) ? 0 : definitionStatus.hashCode());
    result = prime * result + ((module == null) ? 0 : module.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    ConceptRef other = (ConceptRef) obj;
    if (active != other.active)
      return false;
    if (conceptId == null) {
      if (other.conceptId != null)
        return false;
    } else if (!conceptId.equals(other.conceptId))
      return false;
    if (defaultTerm == null) {
      if (other.defaultTerm != null)
        return false;
    } else if (!defaultTerm.equals(other.defaultTerm))
      return false;
    if (definitionStatus == null) {
      if (other.definitionStatus != null)
        return false;
    } else if (!definitionStatus.equals(other.definitionStatus))
      return false;
    if (module == null) {
      if (other.module != null)
        return false;
    } else if (!module.equals(other.module))
      return false;
    return true;
  }

  /* see superclass */
  @Override
  public String toString() {
    return "ConceptRef [conceptId=" + conceptId + ", defaultTerm="
        + defaultTerm + ", definitionStatus=" + definitionStatus
        + ", statedDescendants=" + statedDescendants + ", inferredDescendants="
        + inferredDescendants + ", active=" + active + ", effectiveTime="
        + effectiveTime + ", module=" + module + "]";
  }

}
