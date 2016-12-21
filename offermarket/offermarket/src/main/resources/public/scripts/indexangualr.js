var app = angular.module('indexapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('index/', {
         controller:'viewshopsmainoffersandproductpoints'  // this is the graph page
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('customersCtrl', function ($scope, $http,$cookieStore, $cookies) {
     console.log("data getting")
    // alert("controller working");
     
    // get all products and offers 
//     1) product name 
//     2) image 
//     3) city and country 
//     4) outlet name 
//     5) offer name , details 
//     6) points you can earn 
//     7) reward points and details
 
   $http.post('/api/v1/getpublicdata').success(function (data) {
           // $location.path('register.html');
         //  console.log(data);
           $scope.publicdata=data;
           //alert("Thank you for Registering with Us !!! , we will contact you soon");
        }).error(function (data, status) {
            console.log('Error ' + data)
        })

  
     


   
});