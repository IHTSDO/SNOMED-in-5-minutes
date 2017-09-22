// *************************************************
//
// Copyright 2016 Australian Digital Health Agency
//
// *************************************************
using System;
using System.Linq;

namespace SnomedClient
{
    class Program
    {
        static void Main(string[] args)
        {
            // SNOMED-in-5-minutes
            SnomedClient client = new SnomedClient("http://browser.ihtsdotools.org/api/snomed", "en-edition", "20170731");
            if (args.Length > 1)
            {
                Console.WriteLine("");
                Console.WriteLine("Using URL                 : http://browser.ihtsdotools.org/api/snomed");
                Console.WriteLine("Edition                   : en-edition");
                Console.WriteLine("Release                   : 20170731");
                Console.WriteLine("");
            }

            if (args.Length == 2 && args[0] == "findByQuery")
            {
                // findByQuery
                Console.WriteLine("Searching for text        : " + args[1]);
                SnomedSearch data1 = client.findByQuery(args[1]);
                //SnomedSearch data2 = client.findByQuery(args[1], 0, 25);
                //SnomedSearch data3 = client.findByQuery(args[1], "partialMatching", "english", "activeOnly", 0, 25, "true");
                Console.WriteLine("Total entries returned    : " + data1.details.total);
            }
            else if (args.Length == 2 && args[0] == "findByDescriptionId")
            {
                //findByDescriptionId
                Console.WriteLine("Searching Description Id  : " + args[1]);
                SnomedSearch data4 = client.findByDescriptionId(args[1]);
                Console.WriteLine("FSN Found                 : " + data4.matches.First().fsn);
            }
            else if (args.Length == 2 && args[0] == "findByConceptId")
            {
                //findByConceptId
                Console.WriteLine("Searching Concept Id      : " + args[1]);
                SnomedConcept data5 = client.findByConceptId(args[1]);
                Console.WriteLine("FSN Found                 : " + data5.fsn);
                Console.WriteLine("");
            }
            else if (args.Length == 3 && args[0] == "findByQueryWithFilter")
            {
                // findByQuerywithFilter
                Console.WriteLine("Searching for text        : " + args[1]);
                Console.WriteLine("with filter               : " + args[2]);
                SnomedSearch data6 = client.findByQuery(args[1], args[2]);
                Console.WriteLine("Total entries returned    : " + data6.details.total);
                Console.WriteLine("");
            }
            else
            {
                Console.WriteLine("");
                Console.WriteLine("");
                Console.WriteLine("Usage: SnomedClient <command> <arg> [<arg2>]");
                Console.WriteLine("Sample calls");
                Console.WriteLine("  - findByQuery \"heart attack\"");
                Console.WriteLine("  - findByDescriptionId 679406011");
                Console.WriteLine("  - findByConceptId 109152007");
                Console.WriteLine("  - findByQueryWithFilter \"heart\" \"procedure\"");
                Console.WriteLine("");
            }

        }
    }
}
