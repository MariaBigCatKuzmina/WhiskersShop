angular.module('whiskers-shop')
    .controller('registerController', function ($scope, $http, $location, $localStorage, $rootScope) {
        // $scope.regUser = undefined;
        const contextPath = 'http://localhost:5555/auth';

        $scope.goToRegistrationForm = function () {
            console.log('reg form open');
            $location.path('/register');
        }
        $scope.registerNewUser = function () {
            $http.post(contextPath + '/register', $scope.regUser).then(function (response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.petshopWebuser = {username: $scope.regUser.username, token: response.data.token}
                    $location.path('/');
                }
            })
        }

    });