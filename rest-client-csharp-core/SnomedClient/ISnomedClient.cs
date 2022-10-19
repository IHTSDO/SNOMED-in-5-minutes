// *************************************************
//
// Copyright 2016 Australian Digital Health Agency
//
// *************************************************

namespace SnomedClient
{
    internal interface ISnomedClient
    {
        SnomedConcept? FindByConceptId(string id);

        SnomedSearch? FindByDescriptionId(string id);

        SnomedSearch? FindByQuery(string text);

        SnomedSearch? FindByQuery(string text, string semanticFilter);

        SnomedSearch? FindByQuery(string text, int skipTo, int returnLimit);

        SnomedSearch? FindByQuery(string text, int skipTo, int returnLimit, string semanticFilter);

        SnomedSearch? FindByQuery(string query, string searchMode, string lang, string statusFilter, int skipTo, int returnLimit, string normalize, string semanticFilter);
    }
}