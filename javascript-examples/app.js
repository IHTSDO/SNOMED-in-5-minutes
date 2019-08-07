'use strict';

var myApp = angular.module('myApp', ['ui.bootstrap']).config(function($rootScopeProvider) {

    // Set recursive digest limit higher to handle very deep trees.
    $rootScopeProvider.digestTtl(17);
});

// Declare top level URL vars
var baseUrl = "https://browser.ihtsdotools.org/snowstorm/snomed-ct/v2/";
var edition = "MAIN";
var version = "2019-07-31";

// Initialization of myApp
myApp.run(['$rootScope', '$http', '$window', function($rootScope, $http, $window) {
    // n/a
}]);

// Controller for the page
myApp.controller('SimpleCtrl', function($scope, $http) {

    // Scope variables
    $scope.errorMsg = null;
    $scope.findByQueryResult = null;
    $scope.findByQueryUrl = null;
    $scope.findByQueryCt = 0;
    $scope.findByDescriptionIdResult = null;
    $scope.findByDescriptionIdUrl = null;
    $scope.findByConceptIdResult = null;
    $scope.findByConceptIdUrl = null;
    $scope.findByQueryWithFitlerResult = null;
    $scope.findByQueryWithFilterUrl = null;

    // Clear error
    $scope.clearError = function() {
        $scope.errorMsg = null;
    }

    // Clear all scope vars
    $scope.clear = function() {
        $scope.errorMsg = null;
        $scope.findByQueryResult = null;
        $scope.findByQueryUrl = null;
        $scope.findByQueryCt = 0;
        $scope.findByDescriptionIdResult = null;
        $scope.findByDescriptionIdUrl = null;
        $scope.findByConceptIdResult = null;
        $scope.findByConceptIdUrl = null;
        $scope.findByQueryWithFitlerResult = null;
        $scope.findByQueryWithFilterUrl = null;
    }

    // Find by query and set the scrollable raw json result
    $scope.findByQuery = function(query) {
        console.debug('findByQuery', query);

        // Make the HTTP Call
        $scope.findByQueryUrl = baseUrl + '/' + edition + '/' + version + '/concepts?term=' +
            encodeURIComponent(query) + '&activeFilter=true&offset=0&limit=50';
        $http.get($scope.findByQueryUrl).then(
            // success
            function(response) {
                console.debug('  matches = ', response.data);
                $scope.findByQueryResult = JSON.stringify(response.data, null, 2);
                $scope.findByQueryCt = response.data.total;
            },
            // error
            function(response) {
                $scope.errorMsg = response;
            });
    }

    // Find by description id and set the scrollable raw json result
    $scope.findByDescriptionId = function(query) {
        console.debug('findByDescriptionId', query);

        // Make the HTTP Call
        $scope.findByDescriptionIdUrl = baseUrl + '/' + edition + '/' + version + '/descriptions/' +
            query;
        $http.get($scope.findByDescriptionIdUrl).then(
            // success
            function(response) {
                console.debug('  matches = ', response.data);
                $scope.findByDescriptionIdResult = JSON.stringify(response.data, null, 2);
                //$scope.findByDescriptionIdCt = response.data.details.total;
            },
            // error
            function(response) {
                $scope.errorMsg = response;
            });
    }

    // Find by concept id and set the scrollable raw json result
    $scope.findByConceptId = function(query) {
        console.debug('findByConceptId', query);

        // Make the HTTP Call
        $scope.findByConceptIdUrl = baseUrl + '/browser/' + edition + '/' + version + '/concepts/' + query;
        $http.get($scope.findByConceptIdUrl).then(
            // success
            function(response) {
                console.debug('  matches = ', response.data);
                $scope.findByConceptIdResult = JSON.stringify(response.data, null, 2);
                // $scope.findByConceptIdCt = response.data.details.total;
            },
            // error
            function(response) {
                $scope.errorMsg = response;
            });
    }

    // Find by query with filter and set the scrollable raw json result
    $scope.findByQueryWithFilter = function(query, filter) {
        console.debug('findByQueryWithFilter', query, filter);

        // Make the HTTP Call
        $scope.findByQueryWithFilterUrl = baseUrl + '/browser/' + edition + '/' + version +
            '/descriptions?term=' + encodeURIComponent(query) +
            '&conceptActive=true&semanticTag=' + encodeURIComponent(filter) +
            '&groupByConcept=false&searchMode=STANDARD&offset=0&limit=50';

        $http.get($scope.findByQueryWithFilterUrl).then(
            // success
            function(response) {
                console.debug('  matches = ', response.data);
                $scope.findByQueryWithFilterResult = JSON.stringify(response.data, null, 2);
                $scope.findByQueryWithFilterCt = response.data.totalElements;
            },
            // error
            function(response) {
                $scope.errorMsg = response;
            });
    }

    // end
});