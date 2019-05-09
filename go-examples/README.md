GO Example
===================

This example shows how to make simple calls against the SNOMED CT Snapshot REST API (IHTSDO/sct-snapshot-rest-api) using GO.

##To run the examples:

```
git clone https://github.com/IHTSDO/Snomed-In-5-Minutes.git
cd Snomed-In-5-Minutes/go-examples

# show command help
./snomed

# Find a concept by a string (e.g. 'heart attack')
./snomed by-string heart 1

# Find/get a concept by a description SCTID (e.g. '679406011')
./snomed by-description 679406011

# Find/get a concept by a concept SCTID (e.g. '109152007')
./snomed by-sctid 109152007

# Find a concept by a string (e.g. 'heart') but only in the Procedures semantic tag)
./snomed by-semantic-tag heart 1
```
##To modify the examples:

If you modified snomed.go and want to build a new version
First, Install golang in your machine https://golang.org/doc/install

```
# get kingpin package
go get gopkg.in/alecthomas/kingpin.v2

# Open the file snomed.go and do your modifications 
vim snomed.go

# run the following build command to create a new executable
go build snomed.go
```