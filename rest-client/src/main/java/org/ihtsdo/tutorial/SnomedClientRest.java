/**
 * Copyright 2016 West Coast Informatics, LLC
 */
package org.ihtsdo.tutorial;

import java.net.URLEncoder;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.apache.log4j.Logger;
import org.ihtsdo.tutorial.rf2.Concept;
import org.ihtsdo.tutorial.rf2.MatchResults;
import org.ihtsdo.tutorial.util.Utility;

/**
 * A client for connecting to a SNOMED REST service. NOTE: the URL parameters
 * are hardcoded here.
 */
public class SnomedClientRest {

  /** The base url. */
  private final String baseUrl = "http://browser.ihtsdotools.org/api/v1/snomed/";

  /** The edition. */
  private final String edition = "en-edition";

  /** The version. */
  private final String version = "20190131";

  /**
   * Instantiates an empty {@link SnomedClientRest}.
   */
  public SnomedClientRest() {

  }

  /**
   * Returns the url.
   *
   * @return the url
   */
  private String getUrl() {
    return baseUrl + "/" + edition + "/v" + version;
  }

  /**
   * Returns description matches for the specified query.
   *
   * @param query the query
   * @return the matches for query
   * @throws Exception the exception
   */
  public MatchResults findByQuery(String query) throws Exception {
    Logger.getLogger(getClass()).debug(
        "Snomed Client - get description matches by query " + query);

    validateNotEmpty(query, "query");

    final Client client = ClientBuilder.newClient();
    final WebTarget target =
        client.target(getUrl()
            + "/descriptions?query="
            + URLEncoder.encode(query == null ? "" : query, "UTF-8")
                .replaceAll("\\+", "%20")
            + "&limit=50&searchMode=partialMatching"
            + "&lang=english&statusFilter=activeOnly&skipTo=0"
            + "&returnLimit=100&normalize=true");

    final Response response = target.request(MediaType.APPLICATION_JSON).get();
    final String resultString = response.readEntity(String.class);
    if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
      // n/a
    } else {
      throw new Exception(response.toString());
    }

    // converting to object
    return Utility.getGraphForJson(resultString, MatchResults.class);
  }

  /**
   * Returns description matches for the specified parameters.
   *
   * @param query the query
   * @param limit the limit
   * @param searchMode the search mode
   * @param lang the lang
   * @param statusFilter the status filter
   * @param skipTo the skip to
   * @param returnLimit the return limit
   * @param normalize the normalize
   * @param semanticFilter the semantic filter
   * @return the matches for query
   * @throws Exception the exception
   */
  public MatchResults findByQuery(String query, Long limit, String searchMode,
    String lang, String statusFilter, Long skipTo, Long returnLimit,
    Boolean normalize, String semanticFilter) throws Exception {
    Logger.getLogger(getClass()).debug(
        "Snomed Client - get description matches by query " + query);

    validateNotEmpty(query, "query");

    // Use defaults if null is passed
    final long localLimit = limit == null ? 50L : limit;
    final String localSearchMode =
        searchMode == null ? "partialMatching" : searchMode;
    final String localLang = lang == null ? "english" : lang;
    final String localStatusFilter =
        statusFilter == null ? "activeOnly" : statusFilter;
    final long localSkipTo = skipTo == null ? 0L : skipTo;
    final long localReturnLimit = returnLimit == null ? 100L : returnLimit;
    final boolean localNormalize = normalize == null ? true : normalize;
    final String localSemanticFilter =
        semanticFilter == null ? "" : "&semanticFilter=" + semanticFilter;

    final Client client = ClientBuilder.newClient();
    final WebTarget target =
        client.target(getUrl()
            + "/descriptions?query="
            + URLEncoder.encode(query == null ? "" : query, "UTF-8")
                .replaceAll("\\+", "%20") + "&limit=" + localLimit
            + "&searchMode=" + localSearchMode + "&lang=" + localLang
            + "&statusFilter=" + localStatusFilter + "&skipTo=" + localSkipTo
            + "&returnLimit=" + localReturnLimit + "&normalize="
            + localNormalize + localSemanticFilter);

    final Response response = target.request(MediaType.APPLICATION_JSON).get();
    final String resultString = response.readEntity(String.class);
    if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
      // n/a
    } else {
      throw new Exception(response.toString());
    }

    // converting to object
    return Utility.getGraphForJson(resultString, MatchResults.class);
  }

  /**
   * Returns description matches for the specified description id.
   *
   * @param descriptionId the description id
   * @return the matches for description id
   * @throws Exception the exception
   */
  public MatchResults findByDescriptionId(String descriptionId)
    throws Exception {
    Logger.getLogger(getClass()).debug(
        "Snomed Client - find description matches by description id "
            + descriptionId);

    validateNotEmpty(descriptionId, "descriptionId");

    final Client client = ClientBuilder.newClient();
    final WebTarget target =
        client.target(getUrl() + "/descriptions/" + descriptionId);
    final Response response = target.request(MediaType.APPLICATION_JSON).get();
    final String resultString = response.readEntity(String.class);
    if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
      // n/a
    } else {
      throw new Exception(response.toString());
    }

    // converting to object
    return Utility.getGraphForJson(resultString, MatchResults.class);
  }

  /**
   * Returns the concept for the specified concept id.
   *
   * @param conceptId the concept id
   * @return the concept for id
   * @throws Exception the exception
   */
  public Concept findByConceptId(String conceptId) throws Exception {
    Logger.getLogger(getClass()).debug(
        "Snomed Client - find concept by concept id " + conceptId);

    validateNotEmpty(conceptId, "conceptId");

    final Client client = ClientBuilder.newClient();
    final WebTarget target = client.target(getUrl() + "/concepts/" + conceptId);
    final Response response = target.request(MediaType.APPLICATION_JSON).get();
    final String resultString = response.readEntity(String.class);
    if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
      // n/a
    } else {
      throw new Exception(response.toString());
    }

    // converting to object
    return Utility.getGraphForJson(resultString, Concept.class);
  }

  /**
   * Validate not empty.
   *
   * @param parameter the parameter
   * @param parameterName the parameter name
   */
  @SuppressWarnings("static-method")
  protected void validateNotEmpty(String parameter, String parameterName) {
    if (parameter == null) {
      throw new IllegalArgumentException("Parameter " + parameterName
          + " must not be null.");
    }
    if (parameter.isEmpty()) {
      throw new IllegalArgumentException("Parameter " + parameterName
          + " must not be empty.");
    }
  }

  /**
   * Validate not empty.
   *
   * @param parameter the parameter
   * @param parameterName the parameter name
   */
  @SuppressWarnings("static-method")
  protected void validateNotEmpty(Long parameter, String parameterName) {
    if (parameter == null) {
      throw new IllegalArgumentException("Parameter " + parameterName
          + " must not be null.");
    }
  }

  /**
   * Returns the matches for query.
   *
   * @param query the query
   * @param semanticFilter the semantic filter
   * @return the matches for query
   * @throws Exception the exception
   */
  public MatchResults findByQueryWithFilter(String query, String semanticFilter)
    throws Exception {
    Logger.getLogger(getClass()).debug(
        "Snomed Client - find description matches for query and filter " + query + ", "
            + semanticFilter);

    validateNotEmpty(query, "query");
    validateNotEmpty(semanticFilter, "semanticFilter");

    final Client client = ClientBuilder.newClient();
    final WebTarget target =
        client.target(getUrl()
            + "/descriptions?query="
            + URLEncoder.encode(query == null ? "" : query, "UTF-8")
                .replaceAll("\\+", "%20")
            + "&limit=50&searchMode=partialMatching"
            + "&lang=english&statusFilter=activeOnly&skipTo=0"
            + "&returnLimit=100&normalize=true&semanticFilter="
            + URLEncoder.encode(semanticFilter == null ? "" : semanticFilter,
                "UTF-8").replaceAll("\\+", "%20"));

    final Response response = target.request(MediaType.APPLICATION_JSON).get();
    final String resultString = response.readEntity(String.class);
    if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
      // n/a
    } else {
      throw new Exception(response.toString());
    }

    // converting to object
    return Utility.getGraphForJson(resultString, MatchResults.class);
  }
}
