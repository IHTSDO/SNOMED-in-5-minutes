 SNOMED CT in 5 minutes: CURL Tutorial
======================================

This tutorial focuses in using cURL, a command line utility available in most environment derived from Unix, to access SNOMED CT content from the Snowstorm SNOMED CT Terminology Server.

If you don' have CURL in your system check the official [cURL site](https://curl.haxx.se/dlwiz/) or use any alternative.

The responses from the web service are in [JSON](http://www.json.org/) format, that you can save into a file and explore. For these examples, we use in line [python](https://www.python.org/) scripts to parse and extract meaningful information from the response.

The Browser as a terminology server
------------------------------------

The base url for the REST API of IHTSDO browser backend terminology services is:

<https://browser.ihtsdotools.org/snowstorm/snomed-ct/>

This is the base URL, all the content calls we will perform will construct adding more values after that, including the edition and version. In this example, the edition is the International edition (MAIN) and the version is July 2019 (2019-07-31).

All the operations described here will perform a GET request.

The full documentation of the REST API can be consulted in the Swgger documentation at the base URL.

The Snowstorm SNOMED CT Terminology Server is an Open Source project, available here:

<https://github.com/IHTSDO/snowstorm>

Acessing SNOMED CT Content
--------------------------

The following examples can be types into the command line of any terminal that has cURL and python configured. It should work by without requiring any installation in Unix/Linx/BSD and Apple OS X systems.

### Searching for a query strings

Searching for "heart attack":

```
curl --silent "https://browser.ihtsdotools.org/snowstorm/snomed-ct/browser/MAIN/descriptions?&limit=50&term=heart%20attack&conceptActive=true&lang=english&skipTo=0&returnLimit=100" | python -c "import json,sys;obj=json.load(sys.stdin);print obj['totalElements'];"
```

This command will obtain a json response from the server, parse it, and display the property "totalElements", this represents the number of descriptions that match with our search query.

**Output**: `26`

Same API call, but now we display the first match FSN:

```
curl --silent "https://browser.ihtsdotools.org/snowstorm/snomed-ct/browser/MAIN/descriptions?&limit=50&term=heart%20attack&conceptActive=true&lang=english&skipTo=0&returnLimit=100" | python -c "import json,sys;obj=json.load(sys.stdin);print obj['items'][0]['concept']['fsn']['term'];"
```

**Output**: `Myocardial infarction (disorder)`

Searching for the keyword "heart", but only in the "procedure" hierarchy, this is specified in the query parameter `&semanticFilter=procedure`:

```
curl --silent "https://browser.ihtsdotools.org/snowstorm/snomed-ct/browser/MAIN/descriptions?&limit=50&term=heart&conceptActive=true&lang=english&skipTo=0&returnLimit=100&semanticTag=procedure" | python -c "import json,sys;obj=json.load(sys.stdin);print obj['totalElements'];"
```

This command will obtain a json response from the server, parse it, and display the property "totalElements", this represents the number of descriptions that match with our search query.

**Output**: `1126`

### Retrieving for a description by descriptionId
When retrieving by description id the "matches" property contains only one element, matching the provided identifier:

```
curl --silent "https://browser.ihtsdotools.org/snowstorm/snomed-ct/MAIN/2019-07-31/descriptions/679406011" | python -c "import json,sys;obj=json.load(sys.stdin);print obj['term'];"
```

**Output**: `Methylphenyltetrahydropyridine (substance)`

### Retrieving concept information by conceptId

The API call will retrieve the complete concept information for the provided conceptId.

```
curl --silent "https://browser.ihtsdotools.org/snowstorm/snomed-ct/browser/MAIN/2019-07-31/concepts/109152007" | python -c "import json,sys;obj=json.load(sys.stdin);print obj['fsn']['term'];"
```

**Output**: `Bilirubin test kit (substance)`

"Bilirubin test kit (substance)" is the Fully Specified Name for this concept, contained in the "[fsn][term]" property.

### Getting full concept details into a local .json file

```
curl https://browser.ihtsdotools.org/snowstorm/snomed-ct/browser/MAIN/2019-07-31/concepts/109152007 > concept.json
```

Notice that the resource identifier "concepts" and a valid conceptId "195967001" were added to the base URL to contruct the final URL. All the examples will follow this example and the details can be consultes in the full [Snowstorm swagger API documentation](https://browser.ihtsdotools.org/snowstorm/snomed-ct/).

This command will create a concept.json file that will contain the full JSON object representing the concept.

### Show the preferred term for the concept

```
curl --silent "https://browser.ihtsdotools.org/snowstorm/snomed-ct/browser/MAIN/2019-07-31/concepts/195967001" | python -c "import json,sys;obj=json.load(sys.stdin);print obj['pt']['term'];"
```

**Output**: `Asthma`

"Asthma" is the default term for this concept, contained in the preferred term property. The preferred term is a preferred synonym, configured for a language loaded in the server.

### Count the Inferred children of the concept

```
curl --silent "https://browser.ihtsdotools.org/snowstorm/snomed-ct/browser/MAIN/2019-07-31/concepts/195967001/children?form=inferred&offset=0&limit=50" | python -c "import json,sys;obj=json.load(sys.stdin);print len(obj);"
```

**Output**: `25`

This concept has 25 children (direct subtypes, not descendants) in the inferred view. Note the "form=inferred" parameter, that can be changed to "form=stated" as necessary.

### Count the Inferred descendants of the concept

```
curl --silent "https://browser.ihtsdotools.org/snowstorm/snomed-ct/MAIN%2F2019-07-31/concepts/195967001/descendants?stated=false&offset=0&limit=50" | python -c "import json,sys;obj=json.load(sys.stdin);print obj['total'];"
```

**Output**: `109`

This concept has 109 descendants (children, and children of the children, recursively) in the inferred view, using the descendants endpoint.
