package com.jorgegatica.snomedbrowser.service;

import com.jorgegatica.snomedbrowser.model.Child;
import com.jorgegatica.snomedbrowser.model.Concept;
import com.jorgegatica.snomedbrowser.model.MatchResults;
import com.jorgegatica.snomedbrowser.model.Parent;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.jorgegatica.snomedbrowser.service.SnomedAPI.API_BASE;
import static com.jorgegatica.snomedbrowser.service.SnomedAPI.BASE_URL;
import static com.jorgegatica.snomedbrowser.service.SnomedAPI.URL;


public interface SnomedService {

    @GET(URL + "/concepts/{id}")
    Call<Concept> getConceptById(@Path("id") String id);

    @GET(URL + "/descriptions/{id}")
    Call<MatchResults> getDescriptionById(@Path("id") String id);

    @GET(URL + "/descriptions")
    Call<MatchResults> getDescriptionsByString(@Query("query") String searchTerm,
                                               @Query("limit") int limit,
                                               @Query("searchMode") String searchMode,
                                               @Query("lang") String lang,
                                               @Query("statusFilter") String statusFilter,
                                               @Query("skipTo") int skipTo,
                                               @Query("returnLimit") int returnLimit,
                                               @Query("normalize") boolean normalize);

    @GET(URL + "/descriptions")
    Call<MatchResults> getDescriptionsByStringFromProcedure(@Query("query") String searchTerm,
                                                            @Query("limit") int limit,
                                                            @Query("searchMode") String searchMode,
                                                            @Query("lang") String lang,
                                                            @Query("statusFilter") String statusFilter,
                                                            @Query("skipTo") int skipTo,
                                                            @Query("returnLimit") int returnLimit,
                                                            @Query("semanticFilter") String semanticTag,
                                                            @Query("normalize") boolean normalize);


    /**
     * CONCEPT
     * A single Concept with all its details
     * Single<T> from RxJava2
     *
     * @param edition
     * @param release
     * @param sctid
     * @return
     */
    @GET(BASE_URL + API_BASE+"{edition}/{release}/concepts/{sctid}")
    Single<Concept> findSingleConcept(
            @Path("edition") String edition,
            @Path("release") String release,
            @Path("sctid") String sctid);


    /**
     * CHILDREN
     * Lists all children of a concept, in the SNOMED CT hierarchy.
     * Each child is retrieved in a summary form, called Concept Descriptor.
     *
     * @param edition
     * @param release
     * @param sctid
     * @param form
     * @return
     */
    @GET(BASE_URL + API_BASE+"{edition}/{release}/concepts/{sctid}/children")
    Observable<List<Child>> findAllChildrenOfConcept(@Path("edition") String edition,
                                                     @Path("release") String release,
                                                     @Path("sctid") String sctid,
                                                     @Query("form") String form); //e.g. inferred


    /**
     * PARENTS
     * Lists all parents of a concept, in the SNOMED CT hierarchy.
     * Each parent is retrieved in a summary form, called Concept Descriptor.
     *
     * @param edition
     * @param release
     * @param sctid
     * @param form
     * @return
     */
    @GET(BASE_URL + API_BASE+"{edition}/{release}/concepts/{sctid}/parents")
    Call<List<Parent>> findAllParentsOfConcept(@Path("edition") String edition,
                                               @Path("release") String release,
                                               @Path("sctid") String sctid,
                                               @Query("form") String form); //e.g. inferred


    /**
     * SEARCH
     * Performs a text search over the database, selecting mode and other limits.
     * The search results include the matching descriptions plus a filter descriptor object,
     * allowing to further refining the search by providing new limits.
     *
     * @param edition
     * @param release
     * @param query
     * @param mode
     * @param language
     * @param statusFilter
     * @param semanticFilter
     * @param moduleFilter
     * @param refsetFilter
     * @param returnLimit
     * @param skipTo
     * @param normalize
     * @return
     */
    @GET(BASE_URL + API_BASE+"{edition}/{release}/descriptions")
    Call<MatchResults> searchDescriptions(@Path("edition") String edition,
                                          @Path("release") String release,
                                          @Query("query") String query,
                                          @Query("mode") String mode,
                                          @Query("language") String language,
                                          @Query("statusFilter") String statusFilter,
                                          @Query("semanticFilter") String semanticFilter,
                                          @Query("moduleFilter") String moduleFilter,
                                          @Query("refsetFilter") String refsetFilter,
                                          @Query("returnLimit") int returnLimit,
                                          @Query("skipTo") int skipTo,
                                          @Query("normalize") boolean normalize);

}
