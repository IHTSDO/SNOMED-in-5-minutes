// *************************************************
//
// Copyright 2016 Australian Digital Health Agency
//
// *************************************************

namespace SnomedClient
{
    internal class Program
    {
        private static void Main(string[] args)
        {
            // SNOMED-in-5-minutes
            SnomedClient client = new("http://browser.ihtsdotools.org/api/snomed", "en-edition", "20190131");
            if (args.Length > 1)
            {
                Console.WriteLine("");
                Console.WriteLine("Using URL                 : http://browser.ihtsdotools.org/api/snomed");
                Console.WriteLine("Edition                   : en-edition");
                Console.WriteLine("Release                   : 20190131");
                Console.WriteLine("");
            }

            if (args.Length == 2 && args[0] == "findByQuery")
            {
                // findByQuery
                Console.WriteLine("Searching for text        : " + args[1]);
                var data1 = client.FindByQuery(args[1]);
                //SnomedSearch data2 = client.findByQuery(args[1], 0, 25);
                //SnomedSearch data3 = client.findByQuery(args[1], "partialMatching", "english", "activeOnly", 0, 25, "true");
                if (data1 != null)
                    Console.WriteLine("Total entries returned    : " + data1.details.total);
            }
            else if (args.Length == 2 && args[0] == "findByDescriptionId")
            {
                //findByDescriptionId
                Console.WriteLine("Searching Description Id  : " + args[1]);
                var data4 = client.FindByDescriptionId(args[1]);
                if (data4 != null)
                    Console.WriteLine("FSN Found                 : " + data4.matches.First().fsn);
            }
            else if (args.Length == 2 && args[0] == "findByConceptId")
            {
                //findByConceptId
                Console.WriteLine("Searching Concept Id      : " + args[1]);
                var data5 = client.FindByConceptId(args[1]);
                if (data5 != null)
                    Console.WriteLine("FSN Found                 : " + data5.fsn);
                Console.WriteLine("");
            }
            else if (args.Length == 3 && args[0] == "findByQueryWithFilter")
            {
                // findByQuerywithFilter
                Console.WriteLine("Searching for text        : " + args[1]);
                Console.WriteLine("with filter               : " + args[2]);
                var data6 = client.FindByQuery(args[1], args[2]);
                if (data6 != null)
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