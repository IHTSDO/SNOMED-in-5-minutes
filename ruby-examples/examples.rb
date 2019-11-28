require 'net/http'
require 'json'

# hard-coded URLs
BASE_URL = 'https://browser.ihtsdotools.org/snowstorm/snomed-ct/'
EDITION = 'MAIN'
VERSION = '2019-07-31'

# Prints fsn of a concept
def get_concept_by_id(id)
  url = URI("#{BASE_URL}/browser/#{EDITION}/#{VERSION}/concepts/#{id}")
  response = Net::HTTP.get(url)
  data = JSON.parse(response)
  puts data['fsn']['term']
end

# Prints description by id
def get_description_by_id(id)
  url = URI("#{BASE_URL}#{EDITION}/#{VERSION}/descriptions/#{id}")
  response = Net::HTTP.get(url)
  data = JSON.parse(response)
  puts data['term']
end


# Prints number of concepts with descriptions containing the search term
def get_concepts_by_string(search_term)
  url = URI("#{BASE_URL}#{EDITION}/#{VERSION}/concepts?term=#{search_term}&activeFilter=true&offset=0&limit=50")
  response = Net::HTTP.get(url)
  data = JSON.parse(response)
  puts data['details']['total']
end

# Prints number of descriptions containing the search term with a specific semantic tag
def get_descriptions_by_string_from_procedure(search_term, semantic_tag)
  url = URI("#{BASE_URL}/browser/#{EDITION}/#{VERSION}/descriptions?term=#{search_term}&conceptActive=true&semanticTag=#{semantic_tag}&groupByConcept=false&searchMode=STANDARD&offset=0&limit=50")
  response = Net::HTTP.get(url)
  data = JSON.parse(response)
  puts data['totalElements']
end

get_concept_by_id('109152007')
get_description_by_id('679406011')
get_concepts_by_string('heart attack')
get_descriptions_by_string_from_procedure('heart', 'procedure')
