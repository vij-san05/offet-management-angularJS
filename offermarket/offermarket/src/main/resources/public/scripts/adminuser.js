var app = angular.module('adminhome', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: '/views/admin/main.html',
       controller: 'users'
    }).when('/salesdetails', {
        templateUrl: '/views/admin/sales.html',
       controller: 'salesshops'
    })
         
            
            
            .otherwise({
        redirectTo: '/'
    })
});


app.controller('users', function ($scope, $http) {
    $http.post('/api/v1/userlistforadmin').success(function (data) {
        console.log(data);
        $scope.users = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

   
});



app.controller('usermanger', function ($scope, $http) {
   
    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.data = [];
    $scope.numberOfPages=function(){
        return Math.ceil($scope.data.length/$scope.pageSize);                
    }
    for (var i=0; i<45; i++) {
        $scope.data.push("Item "+i);
    }
   
   
   
   $scope.changepermission =function ($index)
   {
   
      var myArray = $scope.users.splice($index,1);
      var arrayOfObjects = eval(myArray);
      console.log(arrayOfObjects[0].email);
      
      $scope.finaldata =
              {
                  "email":arrayOfObjects[0].email,
                   "role":arrayOfObjects[0].role
              }
      
       $http.post('/api/v1/edituserpermissions',$scope.finaldata).success(function (data) {
      $scope.users[$index] = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })
   
  
    $http.post('/api/v1/userlistforadmin').success(function (data) {
        $scope.users = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

   

   
   
   }
});


app.controller('salesshops', function ($scope, $http,$cookieStore, $cookies) {
     console.log("data activated ssss>>>" +$cookies.useremail);
    console.log("data activated >>>" + $cookieStore.get("useremail"));
    
$http.post('/api/v1/salesshopsdetails',{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
     console.log(data);
        $scope.outlets = data;
         console.log(data);
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

   
});

app.controller('MyControlleredituserlogout1', function ($scope, $http,$cookieStore, $cookies,$window) {
 
    
    $scope.logout = function () {
    if($cookieStore.get("useremail")!="")
{
 
}
else
{
    //$cookieStore.put("useremail","");
}
   $window.location.href= "/";
   }


   
});



///////////pagination /////////////////////////////////////////////////
app.controller('pager', function ($scope, $http) {
    $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.data = [];
    $scope.numberOfPages=function(){
        return Math.ceil($scope.data.length/$scope.pageSize);                
    }
    for (var i=0; i<45; i++) {
        $scope.data.push("Item "+i);
    }
});


//We already have a limitTo filter built-in to angular,
//let's make a startFrom filter
app.filter('startFrom', function() {
    return function(input, start) {
        start = +start; //parse to int
        return input.slice(start);
    }
});


