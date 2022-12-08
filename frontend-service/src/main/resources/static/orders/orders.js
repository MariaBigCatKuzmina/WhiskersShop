angular.module('whiskers-shop').controller('orderController', function ($scope, $http, $location, $localStorage) {
    const contextPath = 'http://localhost:5555/core';
    const apiVersion = '/api/v1';

    $scope.loadOrders = function () {
        if ($localStorage.petshopWebuser) {
             $http.get(contextPath + apiVersion + '/orders').then(function (response) {
                 $scope.orders = response.data;
             });
        }
    }
    $scope.loadOrders();
})
