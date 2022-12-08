angular.module('whiskers-shop').controller('orderController', function ($scope, $http, $location, $localStorage) {

    $scope.getOrderList = function () {
        if ($localStorage.petshopWebuser) {
            // $http.get(corePath + apiVersion + '/orders').then(function (response) {
            //     $scope.orderList = response.data;
            // });

        }
    }
})
