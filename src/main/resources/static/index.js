angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/petshop';
    const apiVersion = 'v1';
    const cartPath = '/api/' + apiVersion + '/cart';
    const productsPath = '/api/' + apiVersion + '/products';

    $scope.loadProducts = function () {
        $http.get(contextPath + productsPath).then(function (response) {
            // console.log(response);
            $scope.productsList = response.data;
        });
    };
    $scope.showProductInfo = function(id) {
        $http.get(contextPath + productsPath +'/'+ id).then(function (response) {
            $scope.product = response.data;
            alert(response.data.title);
        })
    };

    $scope.getCartProducts = function (){
        $http.get(contextPath + cartPath).then(function (response) {
            $scope.cartProducts = response.data;
        });
    };

    $scope.addProductToCart = function(id) {
        $http.get(contextPath + cartPath +'/add/' + id).then(function() {
           $scope.getCartProducts();
        });
    };

    $scope.deleteProductFromCart = function(id) {
        $http.delete(contextPath + cartPath +'/delete/' + id).then(function () {
           $scope.getCartProducts();
        });
    };


    $scope.loadProducts();
    $scope.getCartProducts();

});