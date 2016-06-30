/*
 *    Copyright 2016 West Coast Informatics, LLC
 */
package org.ihtsdo.tutorial.rf2;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a language refset entry.
 * 
 * <pre>
 * { descriptionId : "173687013",
 *   refset:  { "conceptId":"22298006",
 *              "defaultTerm": "some term",
 *              "definitionStatus" : "Fully defined",
 *              "statedDescendants" : 0,
 *              "inferredDescendants" : 0,
 *              "active", true,
 *              "effectiveTime", "20020131",
 *              "module":"900000000000207008"
 *            },
 *   acceptability:  { "conceptId":"22298006",
 *              "defaultTerm": "some term",
 *              "definitionStatus" : "Fully defined",
 *              "statedDescendants" : 0,
 *              "inferredDescendants" : 0,
 *              "active", true,
 *              "effectiveTime", "20020131",
 *              "module":"900000000000207008"
 *            },
 *   uuid : "01a810ff-5910-5293-bd2d-565d06e74af7",
 *   active : true,
 *   effectiveTime : "20020131",
 *   module : "900000000000207008"
 * }
 * </pre>
 */
@XmlRootElement(name = "language")
public class Language {

  /** The description id. */
  private String descriptionId;

  /** The refset. */
  private ConceptRef refset;

  /** The acceptability . */
  private ConceptRef acceptability;

  /** The uuid. */
  private String uuid;

  /** The active. */
  private boolean active;

  /** The effective time. */
  private String effectiveTime;

  /** The module. */
  private String module;

  /**
   * Instantiates an empty {@link Language}.
   */
  public Language() {
    // n/a
  }

  /**
   * Instantiates a {@link Language} from the specified parameters.
   *
   * @param lang the member
   */
  public Language(Language lang) {
    descriptionId = lang.getDescriptionId();
    refset = new ConceptRef(lang.getRefset());
    acceptability = new ConceptRef(lang.getAcceptability());
    uuid = lang.getUuid();
    active = lang.isActive();
    effectiveTime = lang.getEffectiveTime();
    module = lang.getModule();
  }

  /**
   * Returns the description id.
   *
   * @return the description id
   */
  public String getDescriptionId() {
    return descriptionId;
  }

  /**
   * Sets the description id.
   *
   * @param descriptionId the description id
   */
  public void setDescriptionId(String descriptionId) {
    this.descriptionId = descriptionId;
  }

  /**
   * Returns the refset.
   *
   * @return the refset
   */
  public ConceptRef getRefset() {
    return refset;
  }

  /**
   * Sets the refset.
   *
   * @param refset the refset
   */
  public void setRefset(ConceptRef refset) {
    this.refset = refset;
  }

  /**
   * Returns the acceptability.
   *
   * @return the acceptability
   */
  public ConceptRef getAcceptability() {
    return acceptability;
  }

  /**
   * Sets the acceptability.
   *
   * @param acceptability the acceptability
   */
  public void setAcceptability(ConceptRef acceptability) {
    this.acceptability = acceptability;
  }

  /**
   * Returns the uuid.
   *
   * @return the uuid
   */
  public String getUuid() {
    return uuid;
  }

  /**
   * Sets the uuid.
   *
   * @param uuid the uuid
   */
  public void setUuid(String uuid) {
    this.uuid = uuid;
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

  /* see superclass */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result =
        prime * result
            + ((acceptability == null) ? 0 : acceptability.hashCode());
    result = prime * result + (active ? 1231 : 1237);
    result =
        prime * result
            + ((descriptionId == null) ? 0 : descriptionId.hashCode());
    result = prime * result + ((module == null) ? 0 : module.hashCode());
    result = prime * result + ((refset == null) ? 0 : refset.hashCode());
    result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
    Language other = (Language) obj;
    if (acceptability == null) {
      if (other.acceptability != null)
        return false;
    } else if (!acceptability.equals(other.acceptability))
      return false;
    if (active != other.active)
      return false;
    if (descriptionId == null) {
      if (other.descriptionId != null)
        return false;
    } else if (!descriptionId.equals(other.descriptionId))
      return false;
    if (module == null) {
      if (other.module != null)
        return false;
    } else if (!module.equals(other.module))
      return false;
    if (refset == null) {
      if (other.refset != null)
        return false;
    } else if (!refset.equals(other.refset))
      return false;
    if (uuid == null) {
      if (other.uuid != null)
        return false;
    } else if (!uuid.equals(other.uuid))
      return false;
    return true;
  }

  /* see superclass */
  @Override
  public String toString() {
    return "Language [descriptionId=" + descriptionId + ", refset=" + refset
        + ", acceptability=" + acceptability + ", uuid=" + uuid + ", active="
        + active + ", effectiveTime=" + effectiveTime + ", module=" + module
        + "]";
  }

}
