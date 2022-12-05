angular.module('whiskers-shop')
    .controller('cartController', function ($scope, $http, $location, $localStorage, $rootScope){
    const contextPath = 'http://localhost:5555/cart';
    const apiVersion = '/api/v1';

    $rootScope.getCartProducts = function () {
        $http.get(contextPath + apiVersion + '/cart/' + $localStorage.whiskersPetShopGuestCartId)
            .then(function (response) {
            $scope.currentCart = response.data;
        });
    };

    $scope.deleteProductFromCart = function (id) {
        $http.get(contextPath  + apiVersion + '/cart/'+$localStorage.whiskersPetShopGuestCartId+'/delete/' + id)
            .then(function () {
            $scope.getCartProducts();
        });
    };

    $scope.deleteAllProductsOfTypeFromCart = function (id) {
        $http.get(contextPath  + apiVersion + '/cart/'+$localStorage.whiskersPetShopGuestCartId+'/delete/all/' + id).then(function () {
            $scope.getCartProducts();
        });
    };

    $scope.clearCart = function () {
        $http.get(contextPath + apiVersion + '/cart/'+$localStorage.whiskersPetShopGuestCartId+'/clear').then(function () {
            $scope.getCartProducts();
        });
    };

    $scope.isCartEmpty = function () {
        return ($scope.currentCart == null) || ($scope.currentCart.items.length <= 0);
    }

    $scope.formOrder = function () {
        $http.post(corePath + apiVersion + '/orders/add', null).then(function (response) {
            $scope.getCartProducts();
            alert("id заказа: " + response.data);
        })
    };

    $scope.getCartProducts();

});