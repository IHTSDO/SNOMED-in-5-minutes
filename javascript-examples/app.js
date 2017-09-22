'use strict';

var myApp = angular.module('myApp', [ 'ui.bootstrap' ]).config(function($rootScopeProvider) {

  // Set recursive digest limit higher to handle very deep trees.
  $rootScopeProvider.digestTtl(17);
});

// Declare top level URL vars
var baseUrl = "http://browser.ihtsdotools.org/api/snomed/";
var edition = "en-edition";
var version = "20170731";

// Initialization of myApp
myApp.run([ '$rootScope', '$http', '$window', function($rootScope, $http, $window) {
  // n/a
} ]);

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
    $scope.findByQueryUrl = baseUrl + '/' + edition + '/v' + version + '/descriptions?query='
      + encodeURIComponent(query) + '&limit=50&searchMode=partialMatching'
      + '&lang=english&statusFilter=activeOnly&skipTo=0' + '&returnLimit=100&normalize=true';
    $http.get($scope.findByQueryUrl).then(
    // success
    function(response) {
      console.debug('  matches = ', response.data);
      $scope.findByQueryResult = JSON.stringify(response.data, null, 2);
      $scope.findByQueryCt = response.data.details.total;
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
    $scope.findByDescriptionIdUrl = baseUrl + '/' + edition + '/v' + version + '/descriptions/'
      + query;
    $http.get($scope.findByDescriptionIdUrl).then(
    // success
    function(response) {
      console.debug('  matches = ', response.data);
      $scope.findByDescriptionIdResult = JSON.stringify(response.data, null, 2);
      $scope.findByDescriptionIdCt = response.data.details.total;
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
    $scope.findByConceptIdUrl = baseUrl + '/' + edition + '/v' + version + '/concepts/' + query;
    $http.get($scope.findByConceptIdUrl).then(
    // success
    function(response) {
      console.debug('  matches = ', response.data);
      $scope.findByConceptIdResult = JSON.stringify(response.data, null, 2);
      $scope.findByConceptIdCt = response.data.details.total;
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
    $scope.findByQueryWithFilterUrl = baseUrl + '/' + edition + '/v' + version
      + '/descriptions?query=' + encodeURIComponent(query) + '&limit=50&searchMode=partialMatching'
      + '&lang=english&statusFilter=activeOnly&skipTo=0'
      + '&returnLimit=100&normalize=true&semanticFilter=' + encodeURIComponent(filter);

    $http.get($scope.findByQueryWithFilterUrl).then(
    // success
    function(response) {
      console.debug('  matches = ', response.data);
      $scope.findByQueryWithFilterResult = JSON.stringify(response.data, null, 2);
      $scope.findByQueryWithFilterCt = response.data.details.total;
    },
    // error
    function(response) {
      $scope.errorMsg = response;
    });
  }

  // end
});
