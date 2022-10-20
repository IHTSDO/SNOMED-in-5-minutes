// *************************************************
//
// Copyright 2016 Australian Digital Health Agency
//
// *************************************************

namespace SnomedClient
{
    public class SnomedConcept
    {
        public string _id { get; set; }
        public bool active { get; set; }
        public string conceptId { get; set; }
        public string defaultTerm { get; set; }
        public string definitionStatus { get; set; }
        public Descriptions[] descriptions { get; set; }
        public string effectiveTime { get; set; }
        public string fsn { get; set; }
        public string[] inferredAncestors { get; set; }
        public int inferredDescendants { get; set; }
        public bool isLeafInferred { get; set; }
        public bool isLeafStated { get; set; }
        public Memberships[] memberships { get; set; }
        public string module { get; set; }
        public Relationships[] relationships { get; set; }
        public string semtag { get; set; }
        public string[] statedAncestors { get; set; }
        public int statedDescendants { get; set; }
        public Relationships[] statedRelationships { get; set; }

        public class Descriptions
        {
            public bool active { get; set; }
            public string conceptId { get; set; }
            public string descriptionId { get; set; }
            public string effectiveTime { get; set; }
            public Refset ics { get; set; }
            public string lang { get; set; }
            public LangMemberships[] langMemberships { get; set; }
            public int length { get; set; }
            public string module { get; set; }
            public string term { get; set; }
            public Refset type { get; set; }
            public string[] words { get; set; }
        }

        public class LangMemberships
        {
            public Refset acceptability { get; set; }
            public bool active { get; set; }
            public string descriptionId { get; set; }
            public string effectiveTime { get; set; }
            public string module { get; set; }
            public Refset refset { get; set; }
            public string uuid { get; set; }
        }

        public class Memberships
        {
            public bool active { get; set; }
            public string effectiveTime { get; set; }
            public string module { get; set; }
            public string otherValue { get; set; }
            public string referencedComponentId { get; set; }
            public Refset refset { get; set; }
            public string type { get; set; }
            public string uuid { get; set; }
        }

        public class Refset
        {
            public bool active { get; set; }
            public string conceptId { get; set; }
            public string defaultTerm { get; set; }
            public string definitionStatus { get; set; }
            public string effectiveTime { get; set; }
            public int inferredDescendants { get; set; }
            public string module { get; set; }
            public int statedDescendants { get; set; }
        }

        public class Relationships
        {
            public bool active { get; set; }
            public Refset charType { get; set; }
            public string effectiveTime { get; set; }
            public int groupId { get; set; }
            public string modifier { get; set; }
            public string module { get; set; }
            public string sourceId { get; set; }
            public Refset target { get; set; }
            public string[] targetInferredAncestors { get; set; }
            public string[] targetStatedAncestors { get; set; }
            public Refset type { get; set; }
        }
    }
}