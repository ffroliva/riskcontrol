
'use strict'
var app = angular.module('app', ['app.service']);
app.constant("CONSTANTS", {
    getCustomersWithinDistance: "/customers/within-distance/",
    getAllCustomers: "/customers"
});