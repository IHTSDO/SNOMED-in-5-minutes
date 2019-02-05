require 'net/http'
require 'json'

# hard-coded URLs
BASE_URL = 'https://browser.ihtsdotools.org/api/v1/snomed/'
EDITION = 'en-edition'
VERSION = 'v20180131'

# Prints fsn of a concept
def get_concept_by_id(id)
  url = URI("#{BASE_URL}#{EDITION}/#{VERSION}/concepts/#{id}")
  response = Net::HTTP.get(url)
  data = JSON.parse(response)
  puts data['fsn']
end

# Prints description by id
def get_description_by_id(id)
  url = URI("#{BASE_URL}#{EDITION}/#{VERSION}/descriptions/#{id}")
  response = Net::HTTP.get(url)
  data = JSON.parse(response)
  puts data['matches'][0]['term']
end

# Prints number of descriptions containing the search term
def get_descriptions_by_string(search_term)
  url = URI("#{BASE_URL}#{EDITION}/#{VERSION}/descriptions?query=#{search_term}&limit=50&searchMode=partialMatching&lang=english&statusFilter=activeOnly&skipTo=0&returnLimit=100&normalize=true")
  response = Net::HTTP.get(url)
  data = JSON.parse(response)
  puts data['details']['total']
end

# Prints number of descriptions containing the search term with a specific semantic tag
def get_descriptions_by_string_from_procedure(search_term, semantic_tag)
  url = URI("#{BASE_URL}#{EDITION}/#{VERSION}/descriptions?query=#{search_term}&limit=50&searchMode=partialMatching&lang=english&statusFilter=activeOnly&skipTo=0&returnLimit=100&semanticFilter=#{semantic_tag}&normalize=true")
  response = Net::HTTP.get(url)
  data = JSON.parse(response)
  puts data['details']['total']
end

get_concept_by_id('109152007')
get_description_by_id('679406011')
get_descriptions_by_string('heart attack')
get_descriptions_by_string_from_procedure('heart', 'procedure')
