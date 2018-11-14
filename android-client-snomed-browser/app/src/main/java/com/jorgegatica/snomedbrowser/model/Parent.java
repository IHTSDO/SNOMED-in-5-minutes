package com.jorgegatica.snomedbrowser.model;

public class Parent {

    private String conceptId;

    private String defaultTerm;

    private String definitionStatus;

    private String module;

    private int statedDescendants;

    private int inferredDescendants;

    public Parent() {
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

    public void setDefaultTerm(String desfaultTerm) {
        this.defaultTerm = desfaultTerm;
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
