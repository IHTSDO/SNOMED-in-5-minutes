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
        public Details details { get; set; }
        public Filters filters { get; set; }
        public Matches[] matches { get; set; }

        public class Details
        {
            public int returnLimit { get; set; }
            public int skipTo { get; set; }
            public int total { get; set; }
        }

        public class Filters
        {
            public JObject lang { get; set; }
            public JObject module { get; set; }
            public JObject refsetId { get; set; }
            public JObject semTag { get; set; }
        }

        public class Matches
        {
            public bool active { get; set; }
            public bool conceptActive { get; set; }
            public string conceptId { get; set; }
            public string definitionStatus { get; set; }
            public string fsn { get; set; }
            public string module { get; set; }
            public string term { get; set; }
        }
    }
}