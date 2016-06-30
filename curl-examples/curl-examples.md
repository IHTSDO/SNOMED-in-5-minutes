 SNOMED CT in 5 minutes: CURL Tutorial
======================================

This tutorial focuses in using cURL, a command line utility available in most environment derived from Unix, to access SNOMED CT content from the IHTSDO Browser backend services.

If you don' have CURL in your system check the official [cURL site](https://curl.haxx.se/dlwiz/) or use any alternative.

The responses from the web service are in [JSON](http://www.json.org/) format, that you can save into a file and explore. For these examples, we use in line [python](https://www.python.org/) scripts to parse and extract meaningful information from the response.

The Browser as a terminology server
------------------------------------

The base url for the REST API of IHTSDO browser backend terminology services is:

http://browser.ihtsdotools.org/api/snomed/en-edition/v20160131

In this example its using the International Edition and the January 31st 2016 release, as stated in the last two parameters of the example URL.

This is the base URL, all the content calls we will perform will construct adding more values after that. All the operations described here will perform a GET request.

The full documentation of the REST API can be consulted here:

http://docs.snomedctsnapshotapi.apiary.io/

The IHTSDO Browser Terminology Server is an Open Source project, available here:

https://github.com/IHTSDO/sct-snapshot-rest-api

Acessing SNOMED CT Content
--------------------------

The following examples can be types into the command line of any terminal that has cURL and python configured. It should work by without requiring any installation in Unix/Linx/BSD and Apple OS X systems.

### Searching for a query strings

Searching for "heart attack":

`
curl --silent "http://browser.ihtsdotools.org/api/snomed/en-edition/v20160131/descriptions?query=heart%20attack&limit=50&searchMode=partialMatching&lang=english&statusFilter=activeOnly&skipTo=0&returnLimit=100&normalize=true" | python -c "import json,sys;obj=json.load(sys.stdin);print obj['details']['total'];"
`

This command will obtain a json response from the server, parse it, and display the property "total" in the "details", this represents the number of descriptions that match with our search query.

Output:

`
13
`

Same API call, but now we display the first match FSN:

` 
curl --silent "http://browser.ihtsdotools.org/api/snomed/en-edition/v20160131/descriptions?query=heart%20attack&limit=50&searchMode=partialMatching&lang=english&statusFilter=activeOnly&skipTo=0&returnLimit=100&normalize=true" | python -c "import json,sys;obj=json.load(sys.stdin);print obj['matches'][0]['fsn'];"
`

Output:

`
Myocardial infarction (disorder)
`

Searching for the keyword "heart", but only in the "procedure" hierarchy, this is specified in the query parameter `&semanticFilter=procedure`:

`
curl --silent "http://browser.ihtsdotools.org/api/snomed/en-edition/v20160131/descriptions?query=heart&limit=50&searchMode=partialMatching&lang=english&statusFilter=activeOnly&skipTo=0&returnLimit=100&semanticFilter=procedure&normalize=true" | python -c "import json,sys;obj=json.load(sys.stdin);print obj['details']['total'];"
`

This command will obtain a json response from the server, parse it, and display the property "total" in the "details", this represents the number of descriptions that match with our search query.

Output:

`
746
`

### Searching for a description by descriptionId
When searching by description id the "matches" property contains only one element, matching the provided identifier:

`
curl --silent "http://browser.ihtsdotools.org/api/snomed/en-edition/v20160131/descriptions/679406011" | python -c "import json,sys;obj=json.load(sys.stdin);print obj['matches'][0]['term'];"
`

Output:

`
Methylphenyltetrahydropyridine (substance)
`

### Retrieving concept information by conceptId

The API call will retrieve the complete concept information for the provided conceptId.

`
curl --silent http://browser.ihtsdotools.org/api/snomed/en-edition/v20160131/concepts/109152007 | python -c "import json,sys;obj=json.load(sys.stdin);print obj['fsn'];"
`

Output:

`
Bilirubin test kit (substance)
`

"Bilirubin test kit (substance)" is the Fully Specified Name for this concept, contained in the "fsn" property.

### Getting full concept details into a local .json file

`
curl http://browser.ihtsdotools.org/api/snomed/en-edition/v20160131/concepts/195967001 > concept.json
`

Notice that the resource identifier "concepts" and a valid conceptId "195967001" were added to the base URL to contruct the final URL. All the examples will follow this example and the details can be consultes in the full [REST API documentation](http://docs.snomedctsnapshotapi.apiary.io/).

This command will create a concept.json file that will contain the full JSON object representing the concept, you can explore the format and properties in the [API documentation](http://docs.snomedctsnapshotapi.apiary.io/).

### Show the default term for the concept

`
curl --silent http://browser.ihtsdotools.org/api/snomed/en-edition/v20160131/concepts/195967001 | python -c "import json,sys;obj=json.load(sys.stdin);print obj['defaultTerm'];"
`

Output:

`
Asthma (disorder)
`

"Asthma (disorder)" is the default term for this concept, contained in the defaultTerm property. The default term may be either the FSN or the preferred synonym, this a is a preference configuration for each language loaded in the server.

### Count the Inferred children of the concept

`
curl --silent http://browser.ihtsdotools.org/api/snomed/en-edition/v20160131/concepts/195967001/children?form=inferred | python -c "import json,sys;obj=json.load(sys.stdin);print len(obj);"
`

Output:

`
24
`

This concept has 24 children (direct subtypes, not descendants) in the inferred view. Note the "form=inferred" parameter, that can be changed to "form=stated" as necessary.

### Count the Inferred descendants of the concept

`
curl --silent http://browser.ihtsdotools.org/api/snomed/en-edition/v20160131/concepts/195967001 | python -c "import json,sys;obj=json.load(sys.stdin);print obj['inferredDescendants'];"
`

Output:

`
95
`

This concept has 95 descendants (children, and children of the children, recursively) in the inferred view, contained in the inferredDescendants property.