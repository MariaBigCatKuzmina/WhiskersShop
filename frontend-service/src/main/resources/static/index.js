(function () {
    angular
        .module('whiskers-shop', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html', controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html', controller: 'storeController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html', controller: 'cartController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html', controller: 'orderController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
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
        if (!$localStorage.whiskersPetShopGuestCartId){
            $http.get('http://localhost:5555/cart/api/v1/cart/generate')
                .then(function successCallback(response){
                    $localStorage.whiskersPetShopGuestCartId = response.data.value;
                })

        }
     }
})();
angular
    .module('whiskers-shop')
    .controller('indexController', function ($rootScope, $scope, $http, $localStorage, $location) {
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
                    $location.path('/');
                }
            }, function errorCallback(response) {

            });
    };

    $scope.clearUser = function () {
        delete $localStorage.petshopWebuser;
        $http.defaults.headers.common.Authorization = '';
    };

    $rootScope.tryToLogout = function () {
        $scope.clearUser();
        $scope.user = null;
        $location.path('/');
    };

    $rootScope.ifUserLoggedIn = function () {
        // return !!$localStorage.petshopWebuser; - simplified version
        if ($localStorage.petshopWebuser) {
            return true;
        } else {
            return false;
        }
    };

});
