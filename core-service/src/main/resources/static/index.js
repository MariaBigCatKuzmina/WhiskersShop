angular.module('app', ['ngStorage']).controller('indexController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/petshop';
    const apiVersion = '/api/v1';
    const cartPath = 'http://localhost:8190/whiskers-petshop-carts' + apiVersion + '/cart';
    const productsPath = apiVersion + '/products';

    $scope.tryToAuth = function () {
        $http.post(contextPath + '/auth', $scope.user)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.petshopWebuser = {username: $scope.user.username, token: response.data.token};

                    $scope.user.username = null;
                    $scope.user.password = null;

                    // $http.get(contextPath + cartPath + '/' + $localStorage.springWebuserGuestCartId + '/merge').
                    //     then(function successCallback(response) {
                    //
                    // });
                    // $location.path('/');
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
        $http.get(contextPath + '/check_auth').then(function (response) {
            alert(response.data.value);
        })
    }

    $scope.loadProducts = function () {
        $http.get(contextPath + productsPath).then(function (response) {
            $scope.productsList = response.data;
        });
    };

    $scope.showProductInfo = function (id) {
        $http.get(contextPath + productsPath + '/' + id).then(function (response) {
            $scope.product = response.data;
            alert(response.data.title);
        })
    };

    $scope.getCartProducts = function () {
        $http.get(cartPath).then(function (response) {
            $scope.currentCart = response.data;
        });
    };

    $scope.addProductToCart = function (id) {
        $http.get(cartPath + '/add/' + id).then(function () {
            $scope.getCartProducts();
        });
    };

    $scope.deleteProductFromCart = function (id) {
        $http.get(cartPath + '/delete/' + id).then(function () {
            $scope.getCartProducts();
        });
    };

    $scope.deleteAllProductsOfTypeFromCart = function (id) {
        $http.get(cartPath + '/delete/all/' + id).then(function () {
            $scope.getCartProducts();
        });
    };

    $scope.clearCart = function () {
        $http.get(cartPath + '/clear').then(function () {
            $scope.getCartProducts();
        });
    };

    $scope.formOrder = function () {
        $http.post(contextPath + apiVersion + '/orders/add', null).then(function (response) {
         //   $scope.clearCart();
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
            // $http.get(contextPath + apiVersion + '/orders').then(function (response) {
            //     $scope.orderList = response.data;
            // });

        }
    }



});
