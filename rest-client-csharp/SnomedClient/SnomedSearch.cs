// *************************************************
//
// Copyright 2016 Australian Digital Health Agency
//
// *************************************************
using Newtonsoft.Json.Linq;

namespace SnomedClient
{
    public class SnomedSearch
    {
        public Matches[] matches { get; set; }
        public Details details { get; set; }
        public Filters filters { get; set; }

        public class Matches
        {
            public string term { get; set; }
            public string conceptId { get; set; }
            public bool active { get; set; }
            public bool conceptActive { get; set; }
            public string fsn { get; set; }
            public string module { get; set; }
            public string definitionStatus { get; set; }
        }

        public class Details
        {
            public int total { get; set; }
            public int skipTo { get; set; }
            public int returnLimit { get; set; }
        }

        public class Filters
        {
            public JObject lang { get; set; }
            public JObject semTag { get; set; }
            public JObject module { get; set; }
            public JObject refsetId { get; set; }
        }

    }
}
