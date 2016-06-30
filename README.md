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

  - curl-examples: examples with curl
  - java-examples: examples with java
  - javascript-examples: examples with javascript
  - model: JAXB-enabled classes for representing the RF2 domain model
  - rest-client: a Java client for the REST services

## Examples

The following examples will be used to demonstrate accessing the SNOMED API through Javascript, Curl, and Java (using Jersey).

- Find a concept by a string (e.g. "heart attack")
- Find/get a concept by a description SCTID (e.g. "679406011")
- Find/get a concept by a concept SCTID (e.g. "109152007")
- Find a concept by a string (e.g. "heart") but only in the Procedures semantic tag

All of the examples use a hard coded URL, edition name, and version number which point to a server hosted by IHTSDO (www.ihtsdo.org). These are the APIs that back the IHTSDO browser (<http://browser.ihtsdotools.org>)

- baseUrl = <http://browser.ihtsdotools.org/api/snomed/>
- edition = en-edition
- version = 20160131

**[Back to top](#table-of-contents)**

### Java

[Click for Java examples.](../master/java-examples/java-examples.md "Java Examples")

### Javascript

[Click for JavaScript examples.](../master/javascript-examples/javascript-examples.md "JavaScript Examples")

### Curl

[Click for Curl examples.](../master/curl-examples/curl-examples.md "Curl Examples")

**[Back to top](#table-of-contents)**

## Further Documentation

Find comprehensive documentation here: TBD

## Resources

- IHTSDO SNOMED CT browser: <http://browser.ihtsdotools.org>
- SNOMED CT Snapshot REST API: <https://github.com/IHTSDO/sct-snapshot-rest-api> (REST documentation here <http://docs.snomedctsnapshotapi.apiary.io/>)

**[Back to top](#table-of-contents)**

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

**[Back to top](#table-of-contents)**

### Current Contributors

- [West Coast Informatics](https://github.com/westcoastinformatics)
- [TermMed](https://github.com/termMed)

- [Other Contributors](https://github.com/IHTSDO/SNOMED-in-5-minutes/graphs/contributors)

**[Back to top](#table-of-contents)**

## License

See the included LICENSE.txt file.

**[Back to top](#table-of-contents)**
