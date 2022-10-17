angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath='http://localhost:8189/petshop';

    $scope.loadProducts = function () {
        $http.get(contextPath + '/api/v1/products').then(function (response) {
            // console.log(response);
            $scope.productsList = response.data;
        });
    };

    $scope.loadProducts();

    $scope.showProductInfo = function (id) {
        $http.get(contextPath + '/api/v1/products/' + id).then(function (response) {
            $scope.product = response.data;
            alert(response.data.title);
        })
    };
});