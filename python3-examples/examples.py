from urllib.request import urlopen
from urllib.parse import quote
import json

baseUrl = 'https://browser.ihtsdotools.org/api/v1/snomed/'
edition = 'en-edition'
version = 'v20180131'

#Prints fsn of a concept
def getConceptById(id):
    url = baseUrl + edition + '/' + version + '/concepts/' + id
    response = urlopen(url).read()
    data = json.loads(response.decode('utf-8'))

    print (data['fsn'])

#Prints description by id
def getDescriptionById(id):
    url = baseUrl + edition + '/' + version + '/descriptions/' + id
    response = urlopen(url).read()
    data = json.loads(response.decode('utf-8'))

    print (data['matches'][0]['term'])

#Prints number of descriptions containing the search term
def getDescriptionsByString(searchTerm):
    url = baseUrl + edition + '/' + version + '/descriptions?query=' + quote(searchTerm) + '&limit=50&searchMode=partialMatching&lang=english&statusFilter=activeOnly&skipTo=0&returnLimit=100&normalize=true'
    response = urlopen(url).read()
    data = json.loads(response.decode('utf-8'))

    print (data['details']['total'])

#Prints number of descriptions containing the search term with a specific semantic tag
def getDescriptionsByStringFromProcedure(searchTerm, semanticTag):
    url = baseUrl + edition + '/' + version + '/descriptions?query=' + quote(searchTerm) + '&limit=50&searchMode=partialMatching&lang=english&statusFilter=activeOnly&skipTo=0&returnLimit=100&semanticFilter=' + quote(semanticTag) + '&normalize=true'
    response = urlopen(url).read()
    data = json.loads(response.decode('utf-8'))

    print (data['details']['total'])

getConceptById('109152007')
getDescriptionById('679406011')
getDescriptionsByString('heart attack')
getDescriptionsByStringFromProcedure('heart', 'procedure')
