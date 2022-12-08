angular.module('whiskers-shop')
    .controller('cartController', function ($scope, $http, $location, $localStorage, $rootScope) {
        const contextPath = 'http://localhost:5555/cart';
        const apiVersion = '/api/v1';
        const corePath = 'http://localhost:5555/core'

        $rootScope.getCartProducts = function () {
            $http.get(contextPath + apiVersion + '/cart/' + $localStorage.whiskersPetShopGuestCartId)
                .then(function (response) {
                    $scope.currentCart = response.data;
                });
        };

        $scope.deleteProductFromCart = function (id) {
            $http.get(contextPath + apiVersion + '/cart/' + $localStorage.whiskersPetShopGuestCartId + '/delete/' + id)
                .then(function () {
                    $rootScope.getCartProducts();
                });
        };

        $scope.deleteAllProductsOfTypeFromCart = function (id) {
            $http.get(contextPath + apiVersion + '/cart/' + $localStorage.whiskersPetShopGuestCartId + '/delete/all/' + id).then(function () {
                $rootScope.getCartProducts();
            });
        };

        $scope.clearCart = function () {
            $http.get(contextPath + apiVersion + '/cart/' + $localStorage.whiskersPetShopGuestCartId + '/clear').then(function () {
                $rootScope.getCartProducts();
            });
        };

        $scope.isCartEmpty = function () {
            return ($scope.currentCart == null) || ($scope.currentCart.items.length <= 0);
        }

        $scope.formOrder = function () {
            $http.post(corePath + apiVersion + '/orders/add', null).then(function (response) {
                $rootScope.getCartProducts();
                alert("id заказа: " + response.data);
            })
        };

        $rootScope.getCartProducts();

    });