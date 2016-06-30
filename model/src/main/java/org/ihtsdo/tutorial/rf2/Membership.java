/*
 *    Copyright 2016 West Coast Informatics, LLC
 */
package org.ihtsdo.tutorial.rf2;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a membership in a refset.
 * 
 * <pre>
 * { type : "SIMPLEMAP",
 *   referencedComponentId : "109152007",
 *   refset:  { "conceptId":"22298006",
 *              "defaultTerm": "some term",
 *              "definitionStatus" : "Fully defined",
 *              "statedDescendants" : 0,
 *              "inferredDescendants" : 0,
 *              "active", true,
 *              "effectiveTime", "20020131",
 *              "module":"900000000000207008"
 *            },
 *   otherValue : "XU2Hx",
 *   uuid : "01a810ff-5910-5293-bd2d-565d06e74af7",
 *   active : true,
 *   effectiveTime : "20020131",
 *   module : "900000000000207008"
 * }
 * </pre>
 */
@XmlRootElement(name = "membership")
public class Membership {

  /** The type. */
  private String type;

  /** The referenced component id. */
  private String referencedComponentId;

  /** The refset. */
  private ConceptRef refset;

  /** The other value. */
  private String otherValue;

  /** The uuid. */
  private String uuid;

  /** The active. */
  private boolean active;

  /** The effective time. */
  private String effectiveTime;

  /** The module. */
  private String module;

  /**
   * Instantiates an empty {@link Membership}.
   */
  public Membership() {
    // n/a
  }

  /**
   * Instantiates a {@link Membership} from the specified parameters.
   *
   * @param member the member
   */
  public Membership(Membership member) {
    type = member.getType();
    referencedComponentId = member.getReferencedComponentId();
    refset = new ConceptRef(member.getRefset());
    otherValue = member.getOtherValue();
    uuid = member.getUuid();
    active = member.isActive();
    effectiveTime = member.getEffectiveTime();
    module = member.getModule();
  }

  /**
   * Returns the type.
   *
   * @return the type
   */
  public String getType() {
    return type;
  }

  /**
   * Sets the type.
   *
   * @param type the type
   */
  public void setType(String type) {
    this.type = type;
  }

  /**
   * Returns the referenced component id.
   *
   * @return the referenced component id
   */
  public String getReferencedComponentId() {
    return referencedComponentId;
  }

  /**
   * Sets the referenced component id.
   *
   * @param referencedComponentId the referenced component id
   */
  public void setReferencedComponentId(String referencedComponentId) {
    this.referencedComponentId = referencedComponentId;
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
   * Returns the other value.
   *
   * @return the other value
   */
  public String getOtherValue() {
    return otherValue;
  }

  /**
   * Sets the other value.
   *
   * @param otherValue the other value
   */
  public void setOtherValue(String otherValue) {
    this.otherValue = otherValue;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (active ? 1231 : 1237);
    result = prime * result + ((module == null) ? 0 : module.hashCode());
    result =
        prime * result + ((otherValue == null) ? 0 : otherValue.hashCode());
    result =
        prime
            * result
            + ((referencedComponentId == null) ? 0 : referencedComponentId
                .hashCode());
    result = prime * result + ((refset == null) ? 0 : refset.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
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
    Membership other = (Membership) obj;
    if (active != other.active)
      return false;
    if (module == null) {
      if (other.module != null)
        return false;
    } else if (!module.equals(other.module))
      return false;
    if (otherValue == null) {
      if (other.otherValue != null)
        return false;
    } else if (!otherValue.equals(other.otherValue))
      return false;
    if (referencedComponentId == null) {
      if (other.referencedComponentId != null)
        return false;
    } else if (!referencedComponentId.equals(other.referencedComponentId))
      return false;
    if (refset == null) {
      if (other.refset != null)
        return false;
    } else if (!refset.equals(other.refset))
      return false;
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type))
      return false;
    if (uuid == null) {
      if (other.uuid != null)
        return false;
    } else if (!uuid.equals(other.uuid))
      return false;
    return true;
  }

  /**
   * To string.
   *
   * @return the string
   */
  @Override
  public String toString() {
    return "Membership [type=" + type + ", referencedComponentId="
        + referencedComponentId + ", refset=" + refset + ", otherValue="
        + otherValue + ", uuid=" + uuid + ", active=" + active
        + ", effectiveTime=" + effectiveTime + ", module=" + module + "]";
  }

}
