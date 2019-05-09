package main

import (
	"fmt"
	"gopkg.in/alecthomas/kingpin.v2"
	"io/ioutil"
	"net/http"
	"os"
)

const baseUrl string = "https://browser.ihtsdotools.org/api/v1/snomed/"
const edition string = "en-edition"
const version string = "v20190131"

var (
	// Find a concept by string commands
	searchByString = kingpin.Command("by-string", "Find a concept by a string (e.g. 'heart attack')")
	searchTerm     = searchByString.Arg("term", "search term").Required().String()
	resultLimit    = searchByString.Arg("limit", "result limit").Required().String()

	// Find a concept by description SCTID
	searchByDescription = kingpin.Command("by-description", "Find/get a concept by a description SCTID (e.g. '679406011')")
	descriptionSctId    = searchByDescription.Arg("description id", "search term").Required().String()

	// Find a concept by concept STCID
	searchBystcId = kingpin.Command("by-sctid", "Find/get a concept by a concept SCTID (e.g. '109152007')")
	conceptSctId  = searchBystcId.Arg("sct Id", "stcId").Required().String()

	// Find a concept by a string in semantic tags
	searchByStringInSemanticTag   = kingpin.Command("by-semantic-tag", "Find a concept by a string (e.g. 'heart') but only in the Procedures semantic tag)")
	semanticTagString             = searchByStringInSemanticTag.Arg("sct Id", "stcId").Required().String()
	searchByStringInSemanticLimit = searchByStringInSemanticTag.Arg("limit", "result limit").Required().String()
)

func main() {

	switch kingpin.Parse() {
	case "by-string":
		fmt.Println(getConceptByString(*searchTerm, *resultLimit))
	case "by-description":
		fmt.Println(getConceptByDescription(*descriptionSctId))
	case "by-sctid":
		fmt.Println(getConceptBySCTID(*conceptSctId))
	case "by-semantic-tag":
		fmt.Println(getConceptByStringInProceduresSemanticTag(*semanticTagString))
	}

}

// Find a concept by string commands
func getConceptByString(searchTerm string, resultLimit string) string {
	url := baseUrl + edition + "/" + version + "/descriptions?query=" + searchTerm + "&limit=50&searchMode=partialMatching&lang=english&statusFilter=activeOnly&skipTo=0&returnLimit=" + resultLimit + "&normalize=true"
	return lookup(url)
}

// Find a concept by description SCTID
func getConceptByDescription(descriptionId string) string {
	url := baseUrl + edition + "/" + version + "/descriptions/" + descriptionId
	return lookup(url)

}

// Find a concept by concept STCID
func getConceptBySCTID(stcId string) string {
	url := baseUrl + edition + "/" + version + "/concepts/" + stcId
	return lookup(url)

}

// Find a concept by a string in semantic tags
func getConceptByStringInProceduresSemanticTag(searchTerm string) string {
	url := baseUrl + edition + "/" + version + "/descriptions?query=" + searchTerm + "&limit=50&searchMode=partialMatching&lang=english&statusFilter=activeOnly&skipTo=0&returnLimit=100&semanticFilter=procedure&normalize=true"
	return lookup(url)

}

// Perform get request against an API URL
func lookup(url string) string {
	response, err := http.Get(url)
	if err != nil {
		fmt.Printf("The HTTP request failed with error %s\n", err)
		os.Exit(1)
	}
	data, _ := ioutil.ReadAll(response.Body)
	return string(data)
}
