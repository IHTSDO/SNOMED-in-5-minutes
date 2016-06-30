Java Tutorial
=============

This tutorial focuses in using Java with a collection of pre-defined model objects to access SNOMED CT content from the IHTSDO Browser backend services.

If you don' have Java in your system check the official [Java site](https://java.com/).

Prerequisites
-------------
Start by cloning the project and running the maven install.  This will, among other things, create a .jar file with the local code plus all dependencies that can be easily executed from the command line

```
git clone https://github.com/WestCoastInformatics/Snomed-In-5-Minutes.git
cd Snomed-In-5-Minutes
mvn install
```


Running Examples via Unit Tests
-------------------------------
Java examples are available via JUnit tests in
* rest-client/src/main/test/org/ihtsdo/tutorial/SnomedClientTest.java

These tests make use of the Jersey client endpoint defined in:
* rest-client/src/main/java/org/ihtsdo/tutorial/SnomedClientRest

The base URL, SNOMED CT edition, and version are hardcoded into the client. To point to a different server, different edition, or different version, change these parameters.

To run the unit tests, simply invoke this command from the top level project directory:

```
mvn test
```

The console output will include a summary of the result based around the domain model.

Running Examples from the Command Line
--------------------------------------
An application entry point class with a main method was created as
* rest-client/src/main/java/org/ihtsdo/tutorial/SnomedExamples

With the rest-client/target/snomed-in-5-minutes.jar file in the classpath, a simple java command can be used to test the sample calls described in this project.  All sample java calls should be made from the top-level directory after completing the prerequisites section above.

### Searching by query

Searching for "heart attack":
```
java -cp rest-client/target/snomed-in-5-minutes.jar org.ihtsdo.tutorial.SnomedExamples findByQuery "heart attack"
```

This call writes some output to the console based on the domain model object unmarshalled from the server's JSON response.  To see the raw JSON itself, consult the [Curl Examples](../curl-examples/curl-examples.md "Curl Examples") file.

```
    match = Match [term=Heart attack, conceptId=22298006, active=true, conceptActive=true, fsn=Myocardial infarction (disorder), module=900000000000207008, definitionStatus=Fully defined]
    match = Match [term=Fear of heart attack, conceptId=102931001, active=true, conceptActive=true, fsn=Fear of heart attack (finding), module=900000000000207008, definitionStatus=Primitive]
...
    details = {total=13, returnLimit=100, skipTo=0}
    filters = {semTag={disorder=1, assessment scale=6, finding=6}, module={900000000000207008=13}, refsetId={900000000000497000=13, 900000000000498005=13}, lang={english=13}}

``` 

### Searching by description id

Searching for "679406011":
```
java -cp rest-client/target/snomed-in-5-minutes.jar org.ihtsdo.tutorial.SnomedExamples findByDescriptionId 679406011
```

This call writes some output to the console based on the domain model object unmarshalled from the server's JSON response. 

```
    match = Match [term=Methylphenyltetrahydropyridine (substance), conceptId=285407008, active=true, conceptActive=true, fsn=Methylphenyltetrahydropyridine (substance), module=900000000000207008, definitionStatus=null]
    details = {total=1, returnLimit=100, skipTo=0}
    filters = {semTag={}, module={}, refsetId={}, lang={}}
```

### Searching by concept id

Searching for "109152007":
```
java -cp rest-client/target/snomed-in-5-minutes.jar org.ihtsdo.tutorial.SnomedExamples findByConceptId 109152007
```

This call writes some output to the console based on the domain model object unmarshalled from the server's JSON response. 

```
  concept = Concept [fsn=Bilirubin test kit (substance), getConceptId()=109152007, getDefaultTerm()=Bilirubin test kit (substance), getDefinitionStatus()=Primitive, getStatedDescendants()=0, getInferredDescendants()=0, isActive()=true, getEffectiveTime()=20020131, getModule()=900000000000207008, toString()=ConceptRef [conceptId=109152007, defaultTerm=Bilirubin test kit (substance), definitionStatus=Primitive, statedDescendants=0, inferredDescendants=0, active=true, effectiveTime=20020131, module=900000000000207008], getClass()=class org.ihtsdo.tutorial.rf2.Concept]
    description = Description [descriptionId=173687013, conceptId=109152007, type=ConceptRef [conceptId=900000000000013009, defaultTerm=Synonym (core metadata concept), definitionStatus=Primitive, statedDescendants=0, inferredDescendants=0,
...
```

### Searching by query string with a semantic filter

Searching for "heart" within "procedure":
Searching for "109152007":
```
java -cp rest-client/target/snomed-in-5-minutes.jar org.ihtsdo.tutorial.SnomedExamples findByQueryWithFilter "heart" "procedure"
```

This call writes some output to the console based on the domain model object unmarshalled from the server's JSON response. 

```
    match = Match [term=CT of heart, conceptId=241547009, active=true, conceptActive=true, fsn=Computed tomography of heart (procedure), module=900000000000207008, definitionStatus=Fully defined]
    match = Match [term=MRI of heart, conceptId=241620005, active=true, conceptActive=true, fsn=Magnetic resonance imaging of heart (procedure), module=900000000000207008, definitionStatus=Fully defined]
...
    details = {total=746, returnLimit=100, skipTo=0}
    filters = {semTag={procedure=746}, module={900000000000207008=746}, refsetId={900000000000497000=746, 900000000000498005=746}, lang={english=746}}
```


