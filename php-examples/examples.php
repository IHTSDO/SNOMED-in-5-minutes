<?php

require('vendor/autoload.php');
require('src/Snomed.php');

$snomed = new Snomed();

//Find/get a concept by a description SCTID (e.g. "679406011")
echo $snomed->getConceptByString('Heart Attack');

//Find/get a concept by a concept SCTID (e.g. "109152007")
echo $snomed->getConceptByDescription('679406011');

//Find a concept by a string (e.g. "heart") but only in the Procedures semantic tag
echo $snomed->getConceptBySCTID('109152007');

//Find a concept by a string (e.g. "heart attack")
echo $snomed->getConceptByStringInProceduresSemanticTag('heart');
