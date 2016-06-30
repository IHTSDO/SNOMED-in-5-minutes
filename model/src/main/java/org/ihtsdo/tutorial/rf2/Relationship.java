/*
 *    Copyright 2016 West Coast Informatics, LLC
 */
package org.ihtsdo.tutorial.rf2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a concept relationship.
 * 
 * <pre>
 * { 
 *   type:  { "conceptId":"22298006",
 *              "defaultTerm": "some term",
 *              "definitionStatus" : "Fully defined",
 *              "statedDescendants" : 0,
 *              "inferredDescendants" : 0,
 *              "active", true,
 *              "effectiveTime", "20020131",
 *              "module":"900000000000207008"
 *            },
 *   target:  { "conceptId":"22298006",
 *              "defaultTerm": "some term",
 *              "definitionStatus" : "Fully defined",
 *              "statedDescendants" : 0,
 *              "inferredDescendants" : 0,
 *              "active", true,
 *              "effectiveTime", "20020131",
 *              "module":"900000000000207008"
 *            },
 *   targetInferredAncestors : ["12345", "56789"],
 *   targetStatedAncestors : ["12345", "56789"],
 *   sourceId: "1089162007",
 *   groupId : 0,
 *   charType:  { "conceptId":"22298006",
 *              "defaultTerm": "some term",
 *              "definitionStatus" : "Fully defined",
 *              "statedDescendants" : 0,
 *              "inferredDescendants" : 0,
 *              "active", true,
 *              "effectiveTime", "20020131",
 *              "module":"900000000000207008"
 *            },
 *   modifier : "Existential restriction",
 *   active : true,
 *   effectiveTime : "20020131",
 *   module : "900000000000207008"
 * }
 * </pre>
 */
@XmlRootElement(name = "relationship")
public class Relationship {

  /** The type. */
  private ConceptRef type;

  /** The target. */
  private ConceptRef target;

  /** The target inferred ancestors. */
  private List<String> targetInferredAncestors;

  /** The target stated ancestors. */
  private List<String> targetStatedAncestors;

  /** The source id. */
  private String sourceId;

  /** The group id. */
  private int groupId;

  /** The char type. */
  private ConceptRef charType;

  /** The modifier. */
  private String modifier;

  /** The active. */
  private boolean active;

  /** The effective time. */
  private String effectiveTime;

  /** The module. */
  private String module;

  /**
   * Instantiates an empty {@link Relationship}.
   */
  public Relationship() {
    // n/a
  }

  /**
   * Instantiates a {@link Relationship} from the specified parameters.
   *
   * @param rel the rel
   */
  public Relationship(Relationship rel) {
    type = new ConceptRef(rel.getType());
    target = new ConceptRef(rel.getTarget());
    targetInferredAncestors = new ArrayList<>(rel.getTargetInferredAncestors());
    targetStatedAncestors = new ArrayList<>(rel.getTargetStatedAncestors());
    sourceId = rel.getSourceId();
    groupId = rel.getGroupId();
    charType = new ConceptRef(rel.getCharType());
    modifier = rel.getModifier();
    active = rel.isActive();
    effectiveTime = rel.getEffectiveTime();
    module = rel.getModule();
  }

  /**
   * Returns the type.
   *
   * @return the type
   */
  public ConceptRef getType() {
    return type;
  }

  /**
   * Sets the type.
   *
   * @param type the type
   */
  public void setType(ConceptRef type) {
    this.type = type;
  }

  /**
   * Returns the target.
   *
   * @return the target
   */
  public ConceptRef getTarget() {
    return target;
  }

  /**
   * Sets the target.
   *
   * @param target the target
   */
  public void setTarget(ConceptRef target) {
    this.target = target;
  }

  /**
   * Returns the target inferred ancestors.
   *
   * @return the target inferred ancestors
   */
  public List<String> getTargetInferredAncestors() {
    if (targetInferredAncestors == null) {
      targetInferredAncestors = new ArrayList<>();
    }
    return targetInferredAncestors;
  }

  /**
   * Sets the target inferred ancestors.
   *
   * @param targetInferredAncestors the target inferred ancestors
   */
  public void setTargetInferredAncestors(List<String> targetInferredAncestors) {
    this.targetInferredAncestors = targetInferredAncestors;
  }

  /**
   * Returns the target stated ancestors.
   *
   * @return the target stated ancestors
   */
  public List<String> getTargetStatedAncestors() {
    if (targetStatedAncestors == null) {
      targetStatedAncestors = new ArrayList<>();
    }
    return targetStatedAncestors;
  }

  /**
   * Sets the target stated ancestors.
   *
   * @param targetStatedAncestors the target stated ancestors
   */
  public void setTargetStatedAncestors(List<String> targetStatedAncestors) {
    this.targetStatedAncestors = targetStatedAncestors;
  }

  /**
   * Returns the source id.
   *
   * @return the source id
   */
  public String getSourceId() {
    return sourceId;
  }

  /**
   * Sets the source id.
   *
   * @param sourceId the source id
   */
  public void setSourceId(String sourceId) {
    this.sourceId = sourceId;
  }

  /**
   * Returns the group id.
   *
   * @return the group id
   */
  public int getGroupId() {
    return groupId;
  }

  /**
   * Sets the group id.
   *
   * @param groupId the group id
   */
  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  /**
   * Returns the char type.
   *
   * @return the char type
   */
  public ConceptRef getCharType() {
    return charType;
  }

  /**
   * Sets the char type.
   *
   * @param charType the char type
   */
  public void setCharType(ConceptRef charType) {
    this.charType = charType;
  }

  /**
   * Returns the modifier.
   *
   * @return the modifier
   */
  public String getModifier() {
    return modifier;
  }

  /**
   * Sets the modifier.
   *
   * @param modifier the modifier
   */
  public void setModifier(String modifier) {
    this.modifier = modifier;
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

  /**
   * Hash code.
   *
   * @return the int
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (active ? 1231 : 1237);
    result = prime * result + ((charType == null) ? 0 : charType.hashCode());
    result = prime * result + groupId;
    result = prime * result + ((modifier == null) ? 0 : modifier.hashCode());
    result = prime * result + ((module == null) ? 0 : module.hashCode());
    result = prime * result + ((sourceId == null) ? 0 : sourceId.hashCode());
    result = prime * result + ((target == null) ? 0 : target.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
    return result;
  }

  /**
   * Equals.
   *
   * @param obj the obj
   * @return true, if successful
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Relationship other = (Relationship) obj;
    if (active != other.active)
      return false;
    if (charType == null) {
      if (other.charType != null)
        return false;
    } else if (!charType.equals(other.charType))
      return false;
    if (groupId != other.groupId)
      return false;
    if (modifier == null) {
      if (other.modifier != null)
        return false;
    } else if (!modifier.equals(other.modifier))
      return false;
    if (module == null) {
      if (other.module != null)
        return false;
    } else if (!module.equals(other.module))
      return false;
    if (sourceId == null) {
      if (other.sourceId != null)
        return false;
    } else if (!sourceId.equals(other.sourceId))
      return false;
    if (target == null) {
      if (other.target != null)
        return false;
    } else if (!target.equals(other.target))
      return false;
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type))
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
    return "Relationship [type=" + type + ", target=" + target
        + ", targetInferredAncestors=" + targetInferredAncestors
        + ", targetStatedAncestors=" + targetStatedAncestors + ", sourceId="
        + sourceId + ", groupId=" + groupId + ", charType=" + charType
        + ", modifier=" + modifier + ", active=" + active + ", effectiveTime="
        + effectiveTime + ", module=" + module + "]";
  }

}
