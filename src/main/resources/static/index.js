angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/petshop';
    const apiVersion = '/api/v1';
    const cartPath =  apiVersion + '/cart';
    const productsPath = apiVersion + '/products';

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
            $scope.currentCart = response.data;
        });
    };

    $scope.addProductToCart = function(id) {
        $http.get(contextPath + cartPath +'/add/' + id).then(function() {
           $scope.getCartProducts();
        });
    };

    $scope.deleteProductFromCart = function(id) {
        $http.get(contextPath + cartPath +'/delete/' + id).then(function () {
           $scope.getCartProducts();
        });
    };

    $scope.deleteAllProductsOfTypeFromCart = function(id) {
        $http.get(contextPath + cartPath +'/delete/all/' + id).then(function () {
            $scope.getCartProducts();
        });
    };

    $scope.clearCart = function() {
        $http.get(contextPath + cartPath +'/clear').then(function () {
            $scope.getCartProducts();
        });
    };

    $scope.formOrder = function () {
        $http.post(contextPath + apiVersion + '/orders/add', $scope.currentCart).then(function (response){
            $scope.clearCart();
            alert("id заказа: " + response.data);

        })
    }

    $scope.loadProducts();
    $scope.getCartProducts();

});