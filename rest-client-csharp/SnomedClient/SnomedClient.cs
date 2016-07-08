// *************************************************
//
// Copyright 2016 Australian Digital Health Agency
//
// *************************************************
using System;
using System.IO;
using System.Text;

using RestSharp.Extensions.MonoHttp;
using RestSharp;
using Newtonsoft.Json;

namespace SnomedClient
{
    public class SnomedClient: ISnomedClient
    {
        // Params passed in on initiation
        private readonly string _endpoint;
        private readonly string _language;
        private readonly string _edition;
        private readonly string _release;

        // Just for debugging, set to true to capture responses
        private readonly bool _debug = false;
        private readonly string _dir = @"c:\temp\";
        
        /// <summary>
        /// Initialise Class with URL to use
        /// </summary>
        /// <param name="endpoint"></param>
        /// <param name="edition"></param>
        /// <param name="release"></param>
        public SnomedClient(string endpoint, string edition, string release)
        {
            _endpoint = endpoint + "/" + edition + "/v" + release + "/";
        }

        /// <summary>
        /// Simple  query
        /// </summary>
        /// <param name="query">the query</param>
        /// <returns>Returns description matches for the specified query</returns>
        public SnomedSearch findByQuery(string query)
        {
            return _findByQuery(query, "", "", "",  0, 25, "", "");
        }

        /// <summary>
        /// Simple  query
        /// </summary>
        /// <param name="query">the query</param>
        /// <param name="semanticFilter">whether to apply a semantic filter to the search term</param>
        /// <returns>Returns description matches for the specified query</returns>
        public SnomedSearch findByQuery(string query, string semanticFilter)
        {
            return _findByQuery(query, "", "", "", 0, 25, "", semanticFilter);
        }

        /// <summary>
        /// Simple  query with some params
        /// </summary>
        /// <param name="query">the query</param>
        /// <param name="skipTo">skip to value</param>
        /// <param name="returnLimit">the return limit</param>
        /// <returns>Returns description matches for the specified query</returns>
        public SnomedSearch findByQuery(string query, int skipTo, int returnLimit)
        {
            return _findByQuery(query, "", "", "", skipTo, returnLimit, "", "");
        }

        /// <summary>
        /// Simple  query with some params
        /// </summary>
        /// <param name="query">the query</param>
        /// <param name="skipTo">skip to value</param>
        /// <param name="returnLimit">the return limit</param>
        /// <returns>Returns description matches for the specified query</returns>
        public SnomedSearch findByQuery(string query, int skipTo, int returnLimit, string semanticFilter)
        {
            return _findByQuery(query, "", "", "", skipTo, returnLimit, "", semanticFilter);
        }

        /// <summary>
        /// Simple query with all params
        /// </summary>
        /// <param name="query">the query</param>
        /// <param name="searchMode">the search mode</param>
        /// <param name="lang">the language</param>
        /// <param name="statusFilter">the status filter</param>
        /// <param name="skipTo">skip to value</param>
        /// <param name="returnLimit">the return limit</param>
        /// <param name="normalize">whether to normalise the search term</param>
        /// <param name="semanticFilter">whether to apply a semantic filter to the search term</param>
        /// <returns>Returns description matches for the specified query</returns>
        public SnomedSearch findByQuery(string query, string searchMode, string lang, string statusFilter, int skipTo, int returnLimit, string normalize, string semanticFilter)
        {
            return _findByQuery(query, searchMode, lang, statusFilter, skipTo, returnLimit, normalize, semanticFilter);
        }

        /// <summary>
        /// The internal query
        /// </summary>
        private SnomedSearch _findByQuery(string query, string searchMode, string lang, string statusFilter, int skipTo, int returnLimit, string normalize, string semanticFilter)
        {
            RestClient restClient = new RestClient(_endpoint);

            if (string.IsNullOrEmpty(searchMode)) searchMode = "partialMatching";
            if (string.IsNullOrEmpty(lang)) lang = "english";
            if (string.IsNullOrEmpty(statusFilter)) statusFilter = "activeOnly";
            if (string.IsNullOrEmpty(normalize)) normalize = "true";
            if (string.IsNullOrEmpty(semanticFilter)) semanticFilter = "";

            // UrlEncode query but make '+' back to a space (.net turns '+' into %2B which stops it working)
            query = HttpUtility.UrlEncode(query, Encoding.UTF8).Replace("+", " ");
            semanticFilter = HttpUtility.UrlEncode(semanticFilter, Encoding.UTF8).Replace("+", " ");

            var request = new RestRequest("descriptions", Method.GET);
            request.AddHeader("Content-Type", "text/html");
            request.AddParameter("query", query);
            request.AddParameter("searchMode", searchMode);
            request.AddParameter("lang", lang);
            request.AddParameter("statusFilter", statusFilter);
            request.AddParameter("skipTo", skipTo);
            request.AddParameter("returnLimit", returnLimit);
            request.AddParameter("normalize", normalize);
            if (!string.IsNullOrEmpty(semanticFilter)) request.AddParameter("semanticFilter", semanticFilter);

            try
            {
                IRestResponse restResponse = restClient.Execute(request);
                DebugLog(restResponse, "findByQuery");

                // Convert to SnomedSearch object
                return JsonConvert.DeserializeObject<SnomedSearch>(restResponse.Content);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /// <summary>
        ///  Query by concept id
        /// </summary>
        /// <param name="id"></param>
        /// <returns>Returns concept matches for the specified concept id.</returns>
        public SnomedConcept findByConceptId(string id)
        {
            RestClient restClient = new RestClient(_endpoint);

            var request = new RestRequest("concepts/" + id, Method.GET);
            request.AddHeader("Content-Type", "text/html");

            try
            {
                IRestResponse restResponse = restClient.Execute(request);
                DebugLog(restResponse, "findByConceptId");

                // Convert to SnomedSearch object
                return JsonConvert.DeserializeObject<SnomedConcept>(restResponse.Content);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /// <summary>
        /// Query by description id
        /// </summary>
        /// <param name="id"></param>
        /// <returns>Returns description matches for the specified description id.</returns>
        public SnomedSearch findByDescriptionId(string id)
        {
            RestClient restClient = new RestClient(_endpoint);

            var request = new RestRequest("descriptions/" + id, Method.GET);
            request.AddHeader("Content-Type", "text/html");

            try
            {
                IRestResponse restResponse = restClient.Execute(request);
                DebugLog(restResponse, "findByDescriptionId");

                // Convert to SnomedSearch object
                return JsonConvert.DeserializeObject<SnomedSearch>(restResponse.Content);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }


        /// <summary>
        /// For Debug purposes only
        /// </summary>
        /// <param name="restResponse">the returned data</param>
        /// <param name="context">file name context</param>
        private void DebugLog(IRestResponse restResponse, string context)
        {
            if (_debug)
            { 
                if (!string.IsNullOrEmpty(restResponse.Content)) File.WriteAllText(_dir + context + ".json", restResponse.Content);
                if (!string.IsNullOrEmpty(restResponse.ErrorMessage)) File.WriteAllText(_dir + context + "-Error.json", restResponse.ErrorMessage);
            }
        }


    }
}


