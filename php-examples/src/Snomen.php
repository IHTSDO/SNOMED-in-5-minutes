<?php
/**
 * User: me2resh
 * Date: 29/11/2018
 * Time: 01:26
 */

class Snomen
{

    /**
     * @var string
     */
    private $baseUrl = 'http://browser.ihtsdotools.org/api/v1/snomed/';

    /**
     * @var string
     */
    private $edition = 'en-edition';

    /**
     * @var string
     */
    private $version = 'v20180131';

    /**
     * @var \GuzzleHttp\Client
     */
    private $client;

    /**
     * Snomen constructor.
     */
    public function __construct()
    {
        $this->client = new GuzzleHttp\Client();
    }

    /**
     * Find a concept by a string (e.g. "heart attack")
     * @param $searchTerm
     *
     * @return string
     */
    function getConceptByString($searchTerm)
    {
        $url = $this->baseUrl . $this->edition . '/' . $this->version . '/descriptions?query=' . $searchTerm . '&limit=50&searchMode=partialMatching&lang=english&statusFilter=activeOnly&skipTo=0&returnLimit=100&normalize=true';
        $res = $this->client->get($url);

        return $res->getBody()->getContents();
    }

    /**
     * Find/get a concept by a description SCTID (e.g. "679406011")
     * @param $id
     *
     * @return string
     */
    function getConceptByDescription($id)
    {

        $url = $this->baseUrl . $this->edition . '/' . $this->version . '/descriptions/' . $id;
        $res = $this->client->get($url);

        return $res->getBody()->getContents();
    }

    /**
     * Find/get a concept by a concept SCTID (e.g. "109152007")
     * @param $id
     *
     * @return string
     */
    function getConceptBySCTID($id)
    {


        $url = $this->baseUrl . $this->edition . '/' . $this->version . '/concepts/' . $id;
        $res = $this->client->get($url);

        return $res->getBody()->getContents();
    }

    /**
     * Find a concept by a string (e.g. "heart") but only in the Procedures semantic tag
     * @param $searchTerm
     *
     * @return string
     */
    function getConceptByStringInProceduresSemanticTag($searchTerm)
    {

        $url = $this->baseUrl . $this->edition . '/' . $this->version . '/descriptions?query=' . $searchTerm . '&limit=50&searchMode=partialMatching&lang=english&statusFilter=activeOnly&skipTo=0&returnLimit=100&semanticFilter=procedure&normalize=true';
        $res = $this->client->get($url);

        return $res->getBody()->getContents();

    }
}

