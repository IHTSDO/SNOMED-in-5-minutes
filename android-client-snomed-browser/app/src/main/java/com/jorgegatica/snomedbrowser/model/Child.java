package com.jorgegatica.snomedbrowser.model;

public class Child {

    private String conceptId;

    private String defaultTerm;

    private String definitionStatus;

    private String module;

    private boolean isLeafInferred;

    private boolean isLeafStated;

    private boolean active;

    private int statedDescendants;

    private int inferredDescendants;



    public Child() {
    }

    public String getConceptId() {
        return conceptId;
    }

    public void setConceptId(String conceptId) {
        this.conceptId = conceptId;
    }

    public String getDefaultTerm() {
        return defaultTerm;
    }

    public void setDefaultTerm(String defaultTerm) {
        this.defaultTerm = defaultTerm;
    }

    public String getDefinitionStatus() {
        return definitionStatus;
    }

    public void setDefinitionStatus(String definitionStatus) {
        this.definitionStatus = definitionStatus;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public boolean isLeafInferred() {
        return isLeafInferred;
    }

    public void setLeafInferred(boolean leafInferred) {
        isLeafInferred = leafInferred;
    }

    public boolean isLeafStated() {
        return isLeafStated;
    }

    public void setLeafStated(boolean leafStated) {
        isLeafStated = leafStated;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getStatedDescendants() {
        return statedDescendants;
    }

    public void setStatedDescendants(int statedDescendants) {
        this.statedDescendants = statedDescendants;
    }

    public int getInferredDescendants() {
        return inferredDescendants;
    }

    public void setInferredDescendants(int inferredDescendants) {
        this.inferredDescendants = inferredDescendants;
    }
}
