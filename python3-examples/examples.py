from urllib.request import urlopen
from urllib.parse import quote
import json

baseUrl = 'https://browser.ihtsdotools.org/snowstorm/snomed-ct/v2'
edition = 'MAIN'
version = '2019-07-31'

#Prints fsn of a concept
def getConceptById(id):
    url = baseUrl + '/browser/' + edition + '/' + version + '/concepts/' + id
    response = urlopen(url).read()
    data = json.loads(response.decode('utf-8'))

    print (data['fsn']['term'])

#Prints description by id
def getDescriptionById(id):
    url = baseUrl + '/' + edition + '/' + version + '/descriptions/' + id
    response = urlopen(url).read()
    data = json.loads(response.decode('utf-8'))

    print (data['term'])

#Prints number of concepts with descriptions containing the search term
def getConceptsByString(searchTerm):
    url = baseUrl + '/browser/' + edition + '/' + version + '/concepts?term=' + quote(searchTerm) + '&activeFilter=true&offset=0&limit=50'
    response = urlopen(url).read()
    data = json.loads(response.decode('utf-8'))

    print (data['total'])

#Prints number of descriptions containing the search term with a specific semantic tag
def getDescriptionsByStringFromProcedure(searchTerm, semanticTag):
    url = baseUrl + '/browser/' + edition + '/' + version + '/descriptions?term=' + quote(searchTerm) + '&conceptActive=true&semanticTag=' + quote(semanticTag) + '&groupByConcept=false&searchMode=STANDARD&offset=0&limit=50'
    response = urlopen(url).read()
    data = json.loads(response.decode('utf-8'))

    print (data['totalElements'])

getConceptById('109152007')
getDescriptionById('679406011')
getConceptsByString('heart attack')
getDescriptionsByStringFromProcedure('heart', 'procedure')
