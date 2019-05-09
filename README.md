# SNOMED In 5 Minutes

This is an easy-to-use tutorial for accessing SNOMED APIs within 5 min.

## Table of Contents

1. [Project Structure](#project-structure)
2. [Examples](#examples)
3. [Resources](#resources)
4. [Contributing](#contributing)
5. [License](#license)

## Project Structure

- top-level: aggregator for sub-modules (alphabetically):

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

All of the examples use a hard coded URL, edition name, and version number which point to a server hosted by SNOMED International (www.snomed.org). These are the APIs that back the SNOMED International browser (<http://browser.ihtsdotools.org>)

- baseUrl = <http://browser.ihtsdotools.org/api/v1/snomed/>
- edition = en-edition
- version = 20190131

**[Back to top](#table-of-contents)**

### CSharp (.net)

- [Click for CSharp examples.](../csharp/csharp-examples/ "CSharp Examples")

### Java

- [Click for Java examples.](../master/java-examples/ "Java Examples")

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

## Further Documentation

Find comprehensive documentation here: TBD

## Resources

- SNOMED International SNOMED CT browser: <http://browser.ihtsdotools.org>
- SNOMED CT Snapshot REST API: <https://github.com/IHTSDO/sct-snapshot-rest-api> (REST documentation here <http://docs.snomedctsnapshotapi.apiary.io/>)

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
- [Other Contributors](https://github.com/IHTSDO/SNOMED-in-5-minutes/graphs/contributors)

**[Back to top](#table-of-contents)**

## License

Apache 2.0 

See the included LICENSE file for details.

**[Back to top](#table-of-contents)**

## Suggestions for Future Work

- 'supporting registration for a British GP' (i.e. searching within the GP/FP reference set & the UK language reference set)
- 'deriving ICD-10 codes from registered SNOMED concepts (i.e. retrieving all entries of a SNOMED-concept from the ICD-10 extended mapping reference set)
