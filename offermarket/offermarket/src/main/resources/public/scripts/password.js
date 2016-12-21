var app = angular.module('retaillogin', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: '/views/forgotpass.html'
    })
});


app.controller('retailloginpass', function ($scope, $http, $location,$cookieStore, $window) { 

$scope.changepass =function (){

alert("change password");

}

});
