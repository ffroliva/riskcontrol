'use strict'
app.controller("CustomerController", function CustomerController($scope, CustomerService) {

        var vm = this;
        // attributes
        vm.customers = [];
        // methods
        vm.getAllCustomers = function() {
            CustomerService.getAllCustomers().then(function(value) {
                console.log(value.data);
                $scope.customers = value.data;
            }, function(reason) {
                console.log("error occured");
            }, function(value) {
                console.log("no callback");
            });
        }
        vm.getCustomersWithinDistance = function() {
            CustomerService.getCustomersWithinDistance(100).then(function(value) {
                console.log(value.data);
                $scope.customers = value.data;
            }, function(reason) {
                console.log("error occured");
            }, function(value) {
                console.log("no callback");
            });
        }
});
