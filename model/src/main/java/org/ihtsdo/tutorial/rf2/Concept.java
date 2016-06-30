/*
 *    Copyright 2016 West Coast Informatics, LLC
 */
package org.ihtsdo.tutorial.rf2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a concept.
 * 
 * <pre>
 * {"_id":"56c42c077df006c561020b29","memberships":[{"type":"SIMPLEMAP","referencedComponentId":"109152007","refset":{"conceptId":"900000000000497000","defaultTerm":"CTV3 simple map reference set (foundation metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"otherValue":"XU2Hx","uuid":"01a810ff-5910-5293-bd2d-565d06e74af7","active":true,"effectiveTime":"20020131","module":"900000000000207008"},{"type":"SIMPLEMAP","referencedComponentId":"109152007","refset":{"conceptId":"900000000000498005","defaultTerm":"SNOMED RT identifier simple map (foundation metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"otherValue":"C-B0104","uuid":"2cedc762-9a37-535f-a6b8-42f078a358b7","active":true,"effectiveTime":"20020131","module":"900000000000207008"}],"descriptions":[{"descriptionId":"173687013","conceptId":"109152007","type":{"conceptId":"900000000000013009","defaultTerm":"Synonym (core metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"lang":"en","term":"Bilirubin test kit","length":18,"ics":{"conceptId":"900000000000020002","defaultTerm":"Only initial character case insensitive (core metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"langMemberships":[{"descriptionId":"173687013","refset":{"conceptId":"900000000000508004","defaultTerm":"Great Britain English language reference set (foundation metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"acceptability":{"conceptId":"900000000000548007","defaultTerm":"Preferred (foundation metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"uuid":"d48cf108-f919-5c64-8371-67b16db2f156","active":true,"effectiveTime":"20020131","module":"900000000000207008"},{"descriptionId":"173687013","refset":{"conceptId":"900000000000509007","defaultTerm":"United States of America English language reference set (foundation metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"acceptability":{"conceptId":"900000000000548007","defaultTerm":"Preferred (foundation metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"uuid":"7b7bf25c-f73e-5c24-bc76-d747ed9a1535","active":true,"effectiveTime":"20020131","module":"900000000000207008"}],"words":["bilirubin","test","kit"],"active":true,"effectiveTime":"20020131","module":"900000000000207008"},{"descriptionId":"608781019","conceptId":"109152007","type":{"conceptId":"900000000000003001","defaultTerm":"Fully specified name (core metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"lang":"en","term":"Bilirubin test kit (substance)","length":30,"ics":{"conceptId":"900000000000020002","defaultTerm":"Only initial character case insensitive (core metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"langMemberships":[{"descriptionId":"608781019","refset":{"conceptId":"900000000000509007","defaultTerm":"United States of America English language reference set (foundation metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"acceptability":{"conceptId":"900000000000548007","defaultTerm":"Preferred (foundation metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"uuid":"eea94ba1-81a6-5f57-9477-50fd08d9217f","active":true,"effectiveTime":"20020131","module":"900000000000207008"},{"descriptionId":"608781019","refset":{"conceptId":"900000000000508004","defaultTerm":"Great Britain English language reference set (foundation metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"acceptability":{"conceptId":"900000000000548007","defaultTerm":"Preferred (foundation metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"uuid":"1d56476c-f73d-57ce-abb1-1f98dcf1f741","active":true,"effectiveTime":"20020131","module":"900000000000207008"}],"words":["bilirubin","test","kit","substance"],"active":true,"effectiveTime":"20020131","module":"900000000000207008"}],"relationships":[{"type":{"conceptId":"116680003","defaultTerm":"Is a (attribute)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20110131","module":"900000000000012004"},"target":{"conceptId":"2949005","defaultTerm":"Diagnostic aid (product)","definitionStatus":"Primitive","statedDescendants":1430,"inferredDescendants":1430,"active":true,"effectiveTime":"20020131","module":"900000000000207008"},"targetInferredAncestors":["138875005","373873005"],"targetStatedAncestors":["138875005","373873005"],"sourceId":"109152007","groupId":0,"charType":{"conceptId":"900000000000011006","defaultTerm":"Inferred relationship (core metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"modifier":"Existential restriction","active":false,"effectiveTime":"20020731","module":"900000000000207008"},{"type":{"conceptId":"116680003","defaultTerm":"Is a (attribute)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20110131","module":"900000000000012004"},"target":{"conceptId":"373782009","defaultTerm":"Diagnostic substance (substance)","definitionStatus":"Primitive","statedDescendants":570,"inferredDescendants":570,"active":true,"effectiveTime":"20020731","module":"900000000000207008"},"targetInferredAncestors":["138875005","105590001","410942007"],"targetStatedAncestors":["138875005","105590001","410942007"],"sourceId":"109152007","groupId":0,"charType":{"conceptId":"900000000000011006","defaultTerm":"Inferred relationship (core metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"modifier":"Existential restriction","active":false,"effectiveTime":"20030131","module":"900000000000207008"},{"type":{"conceptId":"116680003","defaultTerm":"Is a (attribute)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20110131","module":"900000000000012004"},"target":{"conceptId":"385387009","defaultTerm":"Test kit (substance)","definitionStatus":"Primitive","statedDescendants":30,"inferredDescendants":30,"active":true,"effectiveTime":"20030131","module":"900000000000207008"},"targetInferredAncestors":["138875005","105590001","410942007","373782009"],"targetStatedAncestors":["138875005","105590001","410942007","373782009"],"sourceId":"109152007","groupId":0,"charType":{"conceptId":"900000000000011006","defaultTerm":"Inferred relationship (core metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"modifier":"Existential restriction","active":true,"effectiveTime":"20030131","module":"900000000000207008"}],"statedRelationships":[{"type":{"conceptId":"116680003","defaultTerm":"Is a (attribute)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20110131","module":"900000000000012004"},"target":{"conceptId":"385387009","defaultTerm":"Test kit (substance)","definitionStatus":"Primitive","statedDescendants":30,"inferredDescendants":30,"active":true,"effectiveTime":"20030131","module":"900000000000207008"},"targetInferredAncestors":["138875005","105590001","410942007","373782009"],"targetStatedAncestors":["138875005","105590001","410942007","373782009"],"sourceId":"109152007","groupId":0,"charType":{"conceptId":"900000000000010007","defaultTerm":"Stated relationship (core metadata concept)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000012004"},"modifier":"Existential restriction","active":true,"effectiveTime":"20080731","module":"900000000000207008"}],"isLeafInferred":true,"isLeafStated":true,"fsn":"Bilirubin test kit (substance)","semtag":"substance","inferredAncestors":["138875005","105590001","410942007","373782009","385387009"],"statedAncestors":["138875005","105590001","410942007","373782009","385387009"],"conceptId":"109152007","defaultTerm":"Bilirubin test kit (substance)","definitionStatus":"Primitive","statedDescendants":0,"inferredDescendants":0,"active":true,"effectiveTime":"20020131","module":"900000000000207008"}
 * </pre>
 */
@XmlRootElement(name = "concept")
public class Concept extends ConceptRef {

  /** The _id. */
  private String _id;

  /** The memberships. */
  private List<Membership> memberships;

  /** The descriptions. */
  private List<Description> descriptions;

  /** The relationships. */
  private List<Relationship> relationships;

  /** The stated relationships. */
  private List<Relationship> statedRelationships;

  /** The is leaf inferred. */
  private boolean isLeafInferred;

  /** The is leaf stated. */
  private boolean isLeafStated;

  /** The fsn. */
  private String fsn;

  /** The semtag. */
  private String semtag;

  /** The inferred ancestors. */
  private List<String> inferredAncestors;

  /** The stated ancestors. */
  private List<String> statedAncestors;

  /**
   * Instantiates an empty {@link Concept}.
   */
  public Concept() {
    // n/a
  }

  /**
   * Instantiates a {@link Concept} from the specified parameters.
   *
   * @param concept the c
   */
  public Concept(Concept concept) {
    super(concept);
    _id = concept.get_id();
    memberships = new ArrayList<>(concept.getMemberships());
    descriptions = new ArrayList<>(concept.getDescriptions());
    relationships = new ArrayList<>(concept.getRelationships());
    statedRelationships = new ArrayList<>(concept.getStatedRelationships());
    isLeafInferred = concept.isLeafInferred();
    isLeafStated = concept.isLeafStated();
    fsn = concept.getFsn();
    semtag = concept.getSemtag();
    inferredAncestors = new ArrayList<>(concept.getInferredAncestors());
    statedAncestors = new ArrayList<>(concept.getStatedAncestors());
  }

  /**
   * Returns the _id.
   *
   * @return the _id
   */
  public String get_id() {
    return _id;
  }

  /**
   * Sets the _id.
   *
   * @param _id the _id
   */
  public void set_id(String _id) {
    this._id = _id;
  }

  /**
   * Returns the memberships.
   *
   * @return the memberships
   */
  public List<Membership> getMemberships() {
    if (memberships == null) {
      memberships = new ArrayList<>();
    }
    return memberships;
  }

  /**
   * Sets the memberships.
   *
   * @param memberships the memberships
   */
  public void setMemberships(List<Membership> memberships) {
    this.memberships = memberships;
  }

  /**
   * Returns the descriptions.
   *
   * @return the descriptions
   */
  public List<Description> getDescriptions() {
    if (descriptions == null) {
      descriptions = new ArrayList<>();
    }
    return descriptions;
  }

  /**
   * Sets the descriptions.
   *
   * @param descriptions the descriptions
   */
  public void setDescriptions(List<Description> descriptions) {
    this.descriptions = descriptions;
  }

  /**
   * Returns the relationships.
   *
   * @return the relationships
   */
  public List<Relationship> getRelationships() {
    if (relationships == null) {
      relationships = new ArrayList<>();
    }
    return relationships;
  }

  /**
   * Sets the relationships.
   *
   * @param relationships the relationships
   */
  public void setRelationships(List<Relationship> relationships) {
    this.relationships = relationships;
  }

  /**
   * Returns the stated relationships.
   *
   * @return the stated relationships
   */
  public List<Relationship> getStatedRelationships() {
    if (statedRelationships == null) {
      statedRelationships = new ArrayList<>();
    }
    return statedRelationships;
  }

  /**
   * Sets the stated relationships.
   *
   * @param statedRelationships the stated relationships
   */
  public void setStatedRelationships(List<Relationship> statedRelationships) {
    this.statedRelationships = statedRelationships;
  }

  /**
   * Indicates whether or not leaf inferred is the case.
   *
   * @return <code>true</code> if so, <code>false</code> otherwise
   */
  @XmlElement(name = "isLeafInferred")
  public boolean isLeafInferred() {
    return isLeafInferred;
  }

  /**
   * Sets the leaf inferred.
   *
   * @param isLeafInferred the leaf inferred
   */
  public void setLeafInferred(boolean isLeafInferred) {
    this.isLeafInferred = isLeafInferred;
  }

  /**
   * Indicates whether or not leaf stated is the case.
   *
   * @return <code>true</code> if so, <code>false</code> otherwise
   */
  @XmlElement(name = "isLeafStated")
  public boolean isLeafStated() {
    return isLeafStated;
  }

  /**
   * Sets the leaf stated.
   *
   * @param isLeafStated the leaf stated
   */
  public void setLeafStated(boolean isLeafStated) {
    this.isLeafStated = isLeafStated;
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
   * Returns the semtag.
   *
   * @return the semtag
   */
  public String getSemtag() {
    return semtag;
  }

  /**
   * Sets the semtag.
   *
   * @param semtag the semtag
   */
  public void setSemtag(String semtag) {
    this.semtag = semtag;
  }

  /**
   * Returns the inferred ancestors.
   *
   * @return the inferred ancestors
   */
  public List<String> getInferredAncestors() {
    if (inferredAncestors == null) {
      inferredAncestors = new ArrayList<>();
    }
    return inferredAncestors;
  }

  /**
   * Sets the inferred ancestors.
   *
   * @param inferredAncestors the inferred ancestors
   */
  public void setInferredAncestors(List<String> inferredAncestors) {
    this.inferredAncestors = inferredAncestors;
  }

  /**
   * Returns the stated ancestors.
   *
   * @return the stated ancestors
   */
  public List<String> getStatedAncestors() {
    if (statedAncestors == null) {
      statedAncestors = new ArrayList<>();
    }
    return statedAncestors;
  }

  /**
   * Sets the stated ancestors.
   *
   * @param statedAncestors the stated ancestors
   */
  public void setStatedAncestors(List<String> statedAncestors) {
    this.statedAncestors = statedAncestors;
  }

  /* see superclass */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((fsn == null) ? 0 : fsn.hashCode());
    return result;
  }

  /* see superclass */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    Concept other = (Concept) obj;
    if (fsn == null) {
      if (other.fsn != null)
        return false;
    } else if (!fsn.equals(other.fsn))
      return false;
    return true;
  }

  /* see superclass */
  @Override
  public String toString() {
    return "Concept [fsn=" + fsn + ", getConceptId()=" + getConceptId()
        + ", getDefaultTerm()=" + getDefaultTerm() + ", getDefinitionStatus()="
        + getDefinitionStatus() + ", getStatedDescendants()="
        + getStatedDescendants() + ", getInferredDescendants()="
        + getInferredDescendants() + ", isActive()=" + isActive()
        + ", getEffectiveTime()=" + getEffectiveTime() + ", getModule()="
        + getModule() + ", toString()=" + super.toString() + ", getClass()="
        + getClass() + "]";
  }

}
