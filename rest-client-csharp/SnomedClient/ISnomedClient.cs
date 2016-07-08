// *************************************************
//
// Copyright 2016 Australian Digital Health Agency
//
// *************************************************

namespace SnomedClient
{
    interface ISnomedClient
    {
        SnomedSearch findByQuery(string text);
        SnomedSearch findByQuery(string text, string semanticFilter);
        SnomedSearch findByQuery(string text, int skipTo, int returnLimit);
        SnomedSearch findByQuery(string text, int skipTo, int returnLimit, string semanticFilter);
        SnomedSearch findByQuery(string query, string searchMode, string lang, string statusFilter, int skipTo, int returnLimit, string normalize, string semanticFilter);
        SnomedConcept findByConceptId(string id);
        SnomedSearch findByDescriptionId(string id);

    }
}
