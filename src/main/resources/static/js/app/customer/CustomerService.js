
'use strict'
angular.module('app.service', []).factory('CustomerService', ["$http", "CONSTANTS", function($http, CONSTANTS) {
    var service = {};
    service.getAllCustomers = function() {
        return $http.get(CONSTANTS.getAllCustomers);
    }

    service.getCustomersWithinDistance = function(distance) {
        var url = CONSTANTS.getCustomersWithinDistance + distance;
        return $http.get(url);
    }
    return service;
}]);