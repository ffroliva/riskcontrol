
'use strict'
angular.module('app.service', []).factory('TextService', ["$http", "CONSTANTS", function($http, CONSTANTS) {
    var service = {};
    service.readFile = function() {
        return $http.get(CONSTANTS.readFile);
    }

    service.replaceWordsFromFile = function(word1, word2) {
        var url = CONSTANTS.replaceWordsFromFile + word1 + "/" + word2;
        return $http.get(url);
    }
    return service;
}]);