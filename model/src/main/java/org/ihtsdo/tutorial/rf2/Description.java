/*
 *    Copyright 2016 West Coast Informatics, LLC
 */
package org.ihtsdo.tutorial.rf2;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents a description.
 * 
 * <pre>
 * { descriptionId : "173687013",
 *   conceptId : "109152007",
 *   type:  { "conceptId":"22298006",
 *              "defaultTerm": "some term",
 *              "definitionStatus" : "Fully defined",
 *              "statedDescendants" : 0,
 *              "inferredDescendants" : 0,
 *              "active", true,
 *              "effectiveTime", "20020131",
 *              "module":"900000000000207008"
 *            },
 *   lang : "en",
 *   term : "Bilirubin test kit",
 *   length : 18,
 *   ics:  { "conceptId":"22298006",
 *              "defaultTerm": "some term",
 *              "definitionStatus" : "Fully defined",
 *              "statedDescendants" : 0,
 *              "inferredDescendants" : 0,
 *              "active", true,
 *              "effectiveTime", "20020131",
 *              "module":"900000000000207008"
 *            },
 *   langMemberships : [{...}, {...}, ...],
 *   words : [ "bilirubin", "test", "kit"]
 *   uuid : "01a810ff-5910-5293-bd2d-565d06e74af7",
 *   active : true,
 *   effectiveTime : "20020131",
 *   module : "900000000000207008"
 * }
 * </pre>
 */
@XmlRootElement(name = "description")
public class Description {

  /** The description id. */
  private String descriptionId;

  /** The concept id. */
  private String conceptId;

  /** The type. */
  private ConceptRef type;

  /** The lang. */
  private String lang;

  /** The term. */
  private String term;

  /** The length. */
  private int length;

  /** The ics. */
  private ConceptRef ics;

  /** The lang memberships. */
  private List<Language> langMemberships;

  /** The words. */
  private List<String> words;

  /** The active. */
  private boolean active;

  /** The effective time. */
  private String effectiveTime;

  /** The module. */
  private String module;

  /**
   * Instantiates an empty {@link Description}.
   */
  public Description() {
    // n/a
  }

  /**
   * Instantiates a {@link Description} from the specified parameters.
   *
   * @param desc the desc
   */
  public Description(Description desc) {
    descriptionId = desc.getDescriptionId();
    conceptId = desc.getConceptId();
    type = new ConceptRef(desc.getType());
    lang = desc.getLang();
    term = desc.getTerm();
    length = desc.getLength();
    ics = new ConceptRef(desc.getIcs());
    langMemberships = new ArrayList<>(desc.getLangMemberships());
    words = new ArrayList<>(desc.getWords());
    active = desc.isActive();
    effectiveTime = desc.getEffectiveTime();
    module = desc.getModule();
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
   * Returns the lang.
   *
   * @return the lang
   */
  public String getLang() {
    return lang;
  }

  /**
   * Sets the lang.
   *
   * @param lang the lang
   */
  public void setLang(String lang) {
    this.lang = lang;
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
   * Returns the length.
   *
   * @return the length
   */
  public int getLength() {
    return length;
  }

  /**
   * Sets the length.
   *
   * @param length the length
   */
  public void setLength(int length) {
    this.length = length;
  }

  /**
   * Returns the ics.
   *
   * @return the ics
   */
  public ConceptRef getIcs() {
    return ics;
  }

  /**
   * Sets the ics.
   *
   * @param ics the ics
   */
  public void setIcs(ConceptRef ics) {
    this.ics = ics;
  }

  /**
   * Returns the lang memberships.
   *
   * @return the lang memberships
   */
  public List<Language> getLangMemberships() {
    if (langMemberships == null) {
      langMemberships = new ArrayList<>();
    }
    return langMemberships;
  }

  /**
   * Sets the lang memberships.
   *
   * @param langMemberships the lang memberships
   */
  public void setLangMemberships(List<Language> langMemberships) {
    this.langMemberships = langMemberships;
  }

  /**
   * Returns the words.
   *
   * @return the words
   */
  public List<String> getWords() {
    if (words == null) {
      words = new ArrayList<>();
    }
    return words;
  }

  /**
   * Sets the words.
   *
   * @param words the words
   */
  public void setWords(List<String> words) {
    this.words = words;
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
    result = prime * result + (active ? 1231 : 1237);
    result = prime * result + ((conceptId == null) ? 0 : conceptId.hashCode());
    result =
        prime * result
            + ((descriptionId == null) ? 0 : descriptionId.hashCode());
    result = prime * result + ((ics == null) ? 0 : ics.hashCode());
    result = prime * result + ((lang == null) ? 0 : lang.hashCode());
    result = prime * result + ((module == null) ? 0 : module.hashCode());
    result = prime * result + ((term == null) ? 0 : term.hashCode());
    result = prime * result + ((type == null) ? 0 : type.hashCode());
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
    Description other = (Description) obj;
    if (active != other.active)
      return false;
    if (conceptId == null) {
      if (other.conceptId != null)
        return false;
    } else if (!conceptId.equals(other.conceptId))
      return false;
    if (descriptionId == null) {
      if (other.descriptionId != null)
        return false;
    } else if (!descriptionId.equals(other.descriptionId))
      return false;
    if (ics == null) {
      if (other.ics != null)
        return false;
    } else if (!ics.equals(other.ics))
      return false;
    if (lang == null) {
      if (other.lang != null)
        return false;
    } else if (!lang.equals(other.lang))
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
    if (type == null) {
      if (other.type != null)
        return false;
    } else if (!type.equals(other.type))
      return false;
    return true;
  }

  /* see superclass */
  @Override
  public String toString() {
    return "Description [descriptionId=" + descriptionId + ", conceptId="
        + conceptId + ", type=" + type + ", lang=" + lang + ", term=" + term
        + ", length=" + length + ", ics=" + ics + ", langMemberships="
        + langMemberships + ", words=" + words + ", active=" + active
        + ", effectiveTime=" + effectiveTime + ", module=" + module + "]";
  }

}
