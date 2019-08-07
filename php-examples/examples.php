<?php

require('vendor/autoload.php');
require('src/Snomed.php');

$snomed = new Snomed();

//Find a concept by a string (e.g. "heart attack")
echo $snomed->getConceptByString('Heart Attack');

//Find/get a description by a description SCTID (e.g. "679406011")
echo $snomed->getDescriptionById('679406011');

//Find/get a concept by a concept SCTID (e.g. "109152007")
echo $snomed->getConceptBySCTID('109152007');

//Find a concept by a string (e.g. "heart") but only in the Procedures semantic tag
echo $snomed->getConceptByStringInProceduresSemanticTag('heart');
