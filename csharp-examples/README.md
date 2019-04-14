CSharp Tutorial
===============

This tutorial focuses in using CSharp with a collection of pre-defined model objects to access SNOMED CT content from the IHTSDO Browser backend services.

If you don't have Visual Studio installed on your system download the free version from the [Microsoft site](https://www.microsoft.com/en-au/download/details.aspx?id=44914).

Prerequisites
-------------
Start by cloning the project and then opening the solution file in Visual Studio 2015, and then compile as normal. 
This should download 2 NuGet packages (Newtonsoft.Json and RestSharp) on compilation.

```
git clone https://github.com/IHTSDO/SNOMED-in-5-minutes.git
```


Running Examples from the Command Line
--------------------------------------
The application entry point is the program.cs file which can be found here:

* rest-client-csharp/SnomedClient/program.cs

The client has two defined classes for the returned data:

* rest-client-csharp/SnomedClient/SnomedSearch.cs
* rest-client-csharp/SnomedClient/SnomedConcept.cs

The 'Searching by query' and 'Searching my description id' return the SnomedSearch model, while the 'Searching my concept id' returns the SnomedConcept model.

There is a rest-client-csharp/SnomedClient/bin/Debug/RunExamples.cmd file which will run all the below queries for you:

* SnomedClient.exe findByQuery "heart attack"
* SnomedClient.exe findByDescriptionId 679406011
* SnomedClient.exe findByConceptId 109152007
* SnomedClient.exe findByQueryWithFilter "heart" "procedure"

When running rest-client-csharp/SnomedClient/bin/Debug/SnomedClient.exe on its own, it will return the Usage:

```
Usage: SnomedClient <command> <arg> [<arg2>]
Sample calls
  - findByQuery "heart attack"
  - findByDescriptionId 679406011
  - findByConceptId 109152007
  - findByQueryWithFilter "heart" "procedure"
```

To see the raw JSON response itself, you will need to edit the SnomedClient.cs and enable the debug option, and set the output directory.

```
        // Just for debugging, set to true to capture responses
        private readonly bool _debug = true;
        private readonly string _dir = @"c:\temp\";
```

### Searching by query

Searching for "heart attack":
```
SnomedClient.exe findByQuery "heart attack"
```

This call writes some output to the console:

```
Using URL                 : http://browser.ihtsdotools.org/api/snomed
Edition                   : en-edition
Release                   : 20190131

Searching for text        : heart attack
Total entries returned    : 13
``` 

### Searching by description id

Searching for "679406011":
```
SnomedClient.exe findByDescriptionId 679406011
```

This call writes some output to the console:

```
Using URL                 : http://browser.ihtsdotools.org/api/snomed
Edition                   : en-edition
Release                   : 20190131

Searching Description Id  : 679406011
FSN Found                 : Methylphenyltetrahydropyridine (substance)
``` 

### Searching by concept id

Searching for "109152007":
```
SnomedClient.exe findByConceptId 109152007
```

This call writes some output to the console:

```
Using URL                 : http://browser.ihtsdotools.org/api/snomed
Edition                   : en-edition
Release                   : 20190131

Searching Concept Id      : 109152007
FSN Found                 : Bilirubin test kit (substance)
``` 

### Searching by query string with a semantic filter

Searching for "heart" within "procedure":
```
SnomedClient.exe findByQueryWithFilter "heart" "procedure"
```

This call writes some output to the console:

```
Using URL                 : http://browser.ihtsdotools.org/api/snomed
Edition                   : en-edition
Release                   : 20190131

Searching for text        : heart
With filter               : procedure
Total entries returned    : 746
``` 
