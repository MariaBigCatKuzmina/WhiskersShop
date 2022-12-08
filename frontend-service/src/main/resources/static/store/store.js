angular.module('whiskers-shop').controller('storeController', function ($scope, $http, $location, $localStorage, $rootScope) {
    const contextPath = 'http://localhost:5555/core';
    const apiVersion = '/api/v1';
    const productsPath = apiVersion + '/products';
    const cartPath = 'http://localhost:5555/cart';

    $scope.loadProducts = function () {

        $http.get(contextPath + productsPath, {
            params: {
                title: $scope.title,
                min_price: $scope.min_price,
                max_price: $scope.max_price,
                page: $scope.page
            }
        })
            .then(function (response) {
                $scope.productsPage = response.data;
                $scope.pagesNumbersArray = $rootScope.generatedRange(response.data.number, 8, response.data.totalPages - 1);
            });
    };

    $scope.pagedProducts = function (pageNum) {
        $scope.page = pageNum;
        $scope.loadProducts();

    }
    $rootScope.generatedRange = function (currentPageNumber, visibleElementsCount, lastPageNumber) {
        let offset = Math.floor(visibleElementsCount / 2);

        visibleElementsCount = visibleElementsCount > lastPageNumber + 1 ? lastPageNumber + 1 : visibleElementsCount;

        let max = currentPageNumber + offset >= lastPageNumber ? lastPageNumber + 1 : currentPageNumber + offset + 1;

        let min = currentPageNumber - offset <= 0 ? 1 : currentPageNumber - offset + 1;

        if (max - min < visibleElementsCount - 1) {
            if (min === 1) {
                max = visibleElementsCount;
            } else {
                min = max - visibleElementsCount + 1;
            }
        }

        let pagesArray = new Array(visibleElementsCount).fill(1);

        for (let i = 0; i < visibleElementsCount; i++) {
            pagesArray[i] = min + i;
        }
        return pagesArray;
    }

    $scope.showProductInfo = function (id) {
        $http.get(contextPath + productsPath + '/' + id).then(function (response) {
            $scope.product = response.data;
            alert(response.data.title);
        })
    };

    $scope.addProductToCart = function (id) {
        $http.get(cartPath + apiVersion + '/cart/' + $localStorage.whiskersPetShopGuestCartId + '/add/' + id)
            .then(function () {
                $rootScope.getCartProducts();
            }).catch(function (err) {
            console.log(err.message)
        });
    };

    $scope.loadProducts();

});