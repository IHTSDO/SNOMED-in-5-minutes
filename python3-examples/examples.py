# This script uses the Snowstorm SNOMED-CT API. A standardised FHIR API is also available.

# Note that we recommend running your own Snowstorm instance for heavy script use.
# See https://github.com/IHTSDO/snowstorm

from urllib.request import urlopen, Request
from urllib.parse import quote
import json

baseUrl = 'https://browser.ihtsdotools.org/snowstorm/snomed-ct'
edition = 'MAIN'
version = '2019-07-31'

# IMPORTANT! You must update this user agent to avoid having your IP banned for 24 hours.
# Replace with a contact email so that we can contact you if your script causes excessive load on the public server
# For example: user_agent = 'example@example.com'
user_agent = 'Python'

def urlopen_with_header(url):
    # adds User-Agent header otherwise urlopen on its own gets an IP blocked response
    req = Request(url)
    req.add_header('User-Agent', user_agent)
    return urlopen(req)

#Prints fsn of a concept
def getConceptById(id):
    url = baseUrl + '/browser/' + edition + '/' + version + '/concepts/' + id
    response = urlopen_with_header(url).read()
    data = json.loads(response.decode('utf-8'))

    print (data['fsn']['term'])

#Prints description by id
def getDescriptionById(id):
    url = baseUrl + '/' + edition + '/' + version + '/descriptions/' + id
    response = urlopen_with_header(url).read()
    data = json.loads(response.decode('utf-8'))

    print (data['term'])

#Prints number of concepts with descriptions containing the search term
def getConceptsByString(searchTerm):
    url = baseUrl + '/browser/' + edition + '/' + version + '/concepts?term=' + quote(searchTerm) + '&activeFilter=true&offset=0&limit=50'
    response = urlopen_with_header(url).read()
    data = json.loads(response.decode('utf-8'))

    print (data['total'])

#Prints number of descriptions containing the search term with a specific semantic tag
def getDescriptionsByStringFromProcedure(searchTerm, semanticTag):
    url = baseUrl + '/browser/' + edition + '/' + version + '/descriptions?term=' + quote(searchTerm) + '&conceptActive=true&semanticTag=' + quote(semanticTag) + '&groupByConcept=false&searchMode=STANDARD&offset=0&limit=50'
    response = urlopen_with_header(url).read()
    data = json.loads(response.decode('utf-8'))

    print (data['totalElements'])
    
 #Prints snomed code for searched disease or symptom
def getSnomedCodeSimilar(searchTerm):
    url = baseUrl + '/browser/' + edition + '/' + version + '/descriptions?term=' + quote(searchTerm) + '&conceptActive=true&groupByConcept=false&searchMode=STANDARD&offset=0&limit=50'
    response = urlopen_with_header(url).read()
    data = json.loads(response.decode('utf-8'))

    for term in data['items']:
      if searchTerm in term['term']:
        print("{} : {}".format(term['term'], term['concept']['conceptId']))
 
def getSnomedCode(searchTerm):
    url = baseUrl + '/browser/' + edition + '/' + version + '/descriptions?term=' + quote(searchTerm) + '&conceptActive=true&groupByConcept=false&searchMode=STANDARD&offset=0&limit=50'
    response = urlopen_with_header(url).read()
    data = json.loads(response.decode('utf-8'))

    for term in data['items']:
      if searchTerm == term['term']:
        print("{} : {}".format(term['term'], term['concept']['conceptId']))

getConceptById('109152007')
getDescriptionById('679406011')
getConceptsByString('heart attack')
getDescriptionsByStringFromProcedure('heart', 'procedure')
getSnomedCodeSimilar('Headache')
getSnomedCode('Bleeding from nose')
