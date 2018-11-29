<?php

require('vendor/autoload.php');
require('src/Snomen.php');

$snomen = new Snomen();

//Find/get a concept by a description SCTID (e.g. "679406011")
echo $snomen->getConceptByString('Heart Attack');

//Find/get a concept by a concept SCTID (e.g. "109152007")
echo $snomen->getConceptByDescription('679406011');

//Find a concept by a string (e.g. "heart") but only in the Procedures semantic tag
echo $snomen->getConceptBySCTID('109152007');

//Find a concept by a string (e.g. "heart attack")
echo $snomen->getConceptByStringInProceduresSemanticTag('heart');
