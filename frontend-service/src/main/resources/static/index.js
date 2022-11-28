angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const corePath = 'http://localhost:5555/core';
    const authPath = 'http://localhost:5555/auth'
    const cartPath = 'http://localhost:5555/cart';
    const apiVersion = '/api/v1';
    const productsPath = apiVersion + '/products';

    $scope.tryToAuth = function () {
        $http.post(authPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.petshopWebuser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                }
            }, function errorCallback(response) {

            });
    };

    $scope.clearUser = function () {
        delete $localStorage.petshopWebuser;
        $http.defaults.headers.common.Authorization = '';
    };

    $scope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
        // $location.path('/');
    };

    $scope.ifUserLoggedIn = function () {
        // return !!$localStorage.petshopWebuser; - simplified version
        if ($localStorage.petshopWebuser) {
            return true;
        } else {
            return false;
        }
    };

    if ($localStorage.petshopWebuser) {
        try {
            let jwt = $localStorage.petshopWebuser.token;
            let payload = JSON.parse(atob(jwt.split('.')[1]));
            let currentTime = parseInt(new Date().getTime() / 1000);

            if (currentTime > payload.exp) {
                console.log('Token has expired!');
                delete $localStorage.petshopWebuser;
                $http.defaults.headers.common.Authorization = '';
            }
        } catch (e) {

        }
        $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.petshopWebuser.token;
    }

    $scope.checkAuth = function () {
        $http.get(authPath + '/check_auth').then(function (response) {
            alert(response.data.value);
        })
    }


    $scope.filterProducts = function () {
        console.log($scope.titleFilter);
        console.log($scope.minPriceFilter);
        console.log($scope.minPriceFilter);
        // $http.post(corePath+productsPath+"/filter", $scope.titleFilter).//, $scope.minPriceFilter, $scope.maxPriceFilter]).
        $http.post(corePath + productsPath + "/filter?titleFilter="+$scope.titleFilter
            +"&minPriceFilter="+$scope.minPriceFilter
            +"&maxPriceFilter="+$scope.maxPriceFilter,
            {titleFilter:$scope.titleFilter, minPriceFilter:$scope.minPriceFilter, maxPriceFilter:$scope.maxPriceFilter}).
            then(function (response) {
                $scope.productsList = response.data;
            });
    };
    $scope.loadProducts = function () {
        // $http.get(corePath + productsPath).then(function (response) {
        //     $scope.productsList = response.data;
        // });
        if ($scope.titleFilter==undefined) {
            $scope.titleFilter="";
        }
        if ($scope.minPriceFilter==undefined) {
            $scope.minPriceFilter = "";
        }
        if ($scope.maxPriceFilter==undefined) {
            $scope.maxPriceFilter="";
        }

        $scope.filterProducts();
    };

    $scope.showProductInfo = function (id) {
        $http.get(corePath + productsPath + '/' + id).then(function (response) {
            $scope.product = response.data;
            alert(response.data.title);
        })
    };

    $scope.getCartProducts = function () {
        $http.get(cartPath + apiVersion + '/cart').then(function (response) {
            $scope.currentCart = response.data;
        });
    };

    $scope.addProductToCart = function (id) {
        $http.get(cartPath  + apiVersion + '/cart/add/' + id).then(function () {
            $scope.getCartProducts();
        });
    };

    $scope.deleteProductFromCart = function (id) {
        $http.get(cartPath  + apiVersion + '/cart/delete/' + id).then(function () {
            $scope.getCartProducts();
        });
    };

    $scope.deleteAllProductsOfTypeFromCart = function (id) {
        $http.get(cartPath  + apiVersion + '/cart/delete/all/' + id).then(function () {
            $scope.getCartProducts();
        });
    };

    $scope.clearCart = function () {
        $http.get(cartPath + apiVersion + '/cart/clear').then(function () {
            $scope.getCartProducts();
        });
    };

    $scope.formOrder = function () {
        $http.post(corePath + apiVersion + '/orders/add', null).then(function (response) {
            $scope.getCartProducts();
            alert("id заказа: " + response.data);
        })
    };

    $scope.isCartEmpty = function () {
        return ($scope.currentCart == null) || ($scope.currentCart.items.length <= 0);
    }

    $scope.loadProducts();
    $scope.getCartProducts();

    $scope.getOrderList = function () {
        if ($localStorage.petshopWebuser) {
            // $http.get(corePath + apiVersion + '/orders').then(function (response) {
            //     $scope.orderList = response.data;
            // });

        }
    }



});
