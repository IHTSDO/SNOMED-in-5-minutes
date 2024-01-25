# SNOMED In 5 Minutes

This is an easy-to-use tutorial for accessing SNOMED APIs within 5 min using the SNOMED International terminology server, [Snowstorm](https://github.com/IHTSDO/snowstorm).

## Consider using a FHIR API Instead!
The examples in this repository use the Snowstorm native API and although this API is open source it is tool-specific rather than part of a standard. 

If possible it's better to use a Terminology Server with a FHIR API because that is an open standard, supported by many server and client implementations in many programming langauges and libraries! Find the FHIR API of the public Snowstorm server here (for non-production use only): https://snowstorm.ihtsdotools.org/fhir

[Other terminology servers are available](https://implementation.snomed.org/terminology-services). 

## Table of Contents

1. [Project Structure](#project-structure)
2. [Examples](#examples)
3. [Resources](#resources)
4. [Contributing](#contributing)
5. [License](#license)

## Project Structure

- top-level: aggregator for sub-modules (alphabetically):

  - android-client-snomed-browser: examples for use in an Android client
  - csharp-examples: examples with csharp (.net)
  - curl-examples: examples with curl
  - java-examples: examples with java
  - javascript-examples: examples with javascript
  - model: JAXB-enabled classes for representing the RF2 domain model
  - php-examples: examples with php
  - python3-examples: examples with python
  - rest-client: a Java client for the REST services
  - rest-client-csharp: a CSharp client for the REST services
  - ruby-examples: examples with Ruby based on the Python examples
  - go-examples: examples with golang

## Examples

The following examples will be used to demonstrate accessing the SNOMED API through CSharp, Javascript, Curl, and Java (using Jersey).

- Find a concept by a string (e.g. "heart attack")
- Find/get a concept by a description SCTID (e.g. "679406011")
- Find/get a concept by a concept SCTID (e.g. "109152007")
- Find a concept by a string (e.g. "heart") but only in the Procedures semantic tag

All of the examples use a hard coded URL, edition name, and version number which point to a server hosted by SNOMED International (www.snomed.org). These are the APIs that back the SNOMED International browser (<https://browser.ihtsdotools.org>)

- baseUrl = <https://browser.ihtsdotools.org/snowstorm/snomed-ct/>

**[Back to top](#table-of-contents)**

### Javascript

- [Click for JavaScript examples.](../master/javascript-examples/ "JavaScript Examples")

### Curl

- [Click for Curl examples.](../master/curl-examples/ "Curl Examples")

### Python

- [Click for Python examples.](../master/python3-examples/ "Python Examples")

### Ruby

- [Click for Ruby examples.](../master/ruby-examples/ "Ruby Examples")

### PHP

- [Click for PHP examples.](../master/php-examples/ "PHP Examples")

### Golang

- [Click for GO examples.](../master/go-examples/ "Golang Examples")

**[Back to top](#table-of-contents)**

## Needing some TLC

The following examples are out of date and need updating  to wokr with Snowstorm. All contributions welcome!

### Android client

- [Click for Android examples.](../master/android-client-snomed-browser/ "Android Examples")

### CSharp (.net)

- [Click for CSharp examples.](../csharp/csharp-examples/ "CSharp Examples")

### Java

- [Click for Java examples.](../master/java-examples/ "Java Examples")

## Further Documentation

Find comprehensive documentation here: TBD

## Resources

- SNOMED International SNOMED CT browser: <http://browser.ihtsdotools.org>
- Snowstorm SNOMED CT Terminology Server: <https://github.com/IHTSDO/snowstorm> (REST documentation here <https://browser.ihtsdotools.org/snowstorm/snomed-ct/>)

**[Back to top](#table-of-contents)**

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request

**[Back to top](#table-of-contents)**

## Current Contributors

- [Brian Carlsen](https://github.com/bcarlsenca)
- [Alejandro Lopez Osornio](https://github.com/alopezo)
- [Jesse Efron](https://github.com/yishaiil)
- [Philip Wilford](https://github.com/philipwilford)
- [Omid Mavadati](https://github.com/mavao)
- [Ahmed Mohamed](https://github.com/me2resh)
- [Kai Kewley](https://github.com/kaicode)
- [Other Contributors](https://github.com/IHTSDO/SNOMED-in-5-minutes/graphs/contributors)

**[Back to top](#table-of-contents)**

## License

Apache 2.0 

See the included LICENSE file for details.

**[Back to top](#table-of-contents)**

## Suggestions for Future Work

- 'supporting registration for a British GP' (i.e. searching within the GP/FP reference set & the UK language reference set)
- 'deriving ICD-10 codes from registered SNOMED concepts (i.e. retrieving all entries of a SNOMED-concept from the ICD-10 extended mapping reference set)
