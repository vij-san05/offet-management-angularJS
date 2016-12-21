var app = angular.module('retaillogin', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/login.html'
       
    }).otherwise({
        redirectTo: '/'
    })
});


app.controller('loginuser', function ($scope, $http, $location,$cookies,$cookieStore, $window) {
    
    $scope.user = {
        done: false
    };

    $scope.userlogin = function () {
        //console.log($scope.username); 
        $http.post('/api/v1/userlogin', $scope.user).success(function (data) {
            
       
             if(data == 'null' || data === undefined || data=="")
             {
                 alert("Not a valid user");
             }
             else
             {
                 
                 
                 if(data.role===2)
                 {
                       // alert(JSON.stringify(data));
                 console.log("#####"+$scope.user.email + " >>>>>>>^&*((((" + JSON.stringify(data));
               
                  //$cookies.aryanair.radx14@gmail.com = $scope.user.email;
                    $cookieStore.put("useremail",$scope.user.email);
                // $.cookie("useremail", $scope.user.email, { path : "retailuser.html/" });
                 $window.location.href = 'retailuser.html/';                  
                 }
                 else if(data.role===1)
                 {
                    // alert("getting ");
                     // alert(JSON.stringify(data));
                 console.log("#####"+$scope.user.email + " >>>>>>>^&*((((" + JSON.stringify(data));
               
                  //$cookies.aryanair.radx14@gmail.com = $scope.user.email;
                    $cookieStore.put("useremail",$scope.user.email);
                // $.cookie("useremail", $scope.user.email, { path : "retailuser.html/" });
                 $window.location.href = 'adminhome.html/';
                 }
                // $scope.user =data;
               
                // $location.absUrl() = 'http://localhost:8082/retailuser.html#/';
                //$location.url("retailuser.html/");
             }
            //$location.path('register.html');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});