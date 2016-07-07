// *************************************************
//
// Copyright 2016 Australian Digital Health Agency
//
// *************************************************
using System;

namespace SnomedClient
{
    class Program
    {
        static void Main(string[] args)
        {
            // SNOMED-in-5-minutes

            SnomedClient client = new SnomedClient("https://sct-rest.ihtsdotools.org/api/snomed", "en-edition", "20160131");
            // findByQuery
            SnomedSearch data1 = client.findByQuery("heart attack");

            Console.WriteLine("Query          : heart attack");
            Console.WriteLine("Total returned : " + data1.details.total);

            SnomedSearch data2 = client.findByQuery("heart attack", 0, 25);
            SnomedSearch data3 = client.findByQuery("heart attack", "partialMatching", "english", "activeOnly", 0, 25, "true");

            //findByConceptId
            SnomedConcept data4 = client.findByConceptId("109152007");

            Console.WriteLine("Concept Id     : 109152007");
            Console.WriteLine("FSN            : " + data4.fsn);

            //findByDescriptionId
            SnomedSearch data5 = client.findByDescriptionId("679406011");

            Console.WriteLine("Description Id : 679406011");
            Console.WriteLine("Total returned : " + data5.details.total);

        }
    }
}
