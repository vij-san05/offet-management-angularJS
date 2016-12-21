/**
 * Created by shekhargulati on 10/06/14.
 */

var app = angular.module('retailregister', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]) ;


app.config(function ($routeProvider) {
    $routeProvider.when('register.html/', {
        templateUrl: 'views/register.html',
        controller: 'register'
    }).when('/', {
        templateUrl: 'views/register.html',
        controller: 'register'
    }).
            otherwise({
        redirectTo: '/'
    })
});

app.controller('register', function ($scope, $http, $location) {
    $scope.user = {
        done: false
    };

    $scope.createUser = function () {
        console.log($scope.username);

        $http.post('/api/v1/userregister', $scope.user).success(function (data) {
           // $location.path('register.html');
           alert("Thank you for Registering with Us !!! , we will contact you soon");
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});

app.controller('mainCtrl', function( $scope ){
   $scope.email = '';
});


var result;
app.service('userService', function($q,$http){
    

    this.isUnique = function( email){
         var deferred = $q.defer(), i;
        data ={email:email,deductionscore:0};
        console.log("$$$$$$ " +JSON.stringify(data) + ">>>>" + email);           
       $http.post("/api/v1/userregistercheck",data)
      .success(function(users) {
          result=users;
            console.log("return value ===" +users);
                     
            deferred.resolve(users);
            return users;
            
      })
      
      .error(function() {
        deferred.resolve(false);
      });
    
    return deferred.promise;               
                     
                     
                     
                     
                     
                     
    }
});


app.directive('checkEmail', function(userService) {
  return {
    restrict: "A",
    require: 'ngModel',
    link: function(scope, ele, attrs, ctrl) {

      ele.bind('blur', function() {
        scope.$apply(function() {
          console.log("Run in blur!" + scope.user.email);
          
            userService.isUnique(scope.user.email).then(function(result) {
                 scope.refreshing = false;
                 
                  console.log("Run in blur!>>>####" +result);
                  fin =result;
                   console.log("Run in blur!>>>####bb" +fin);
                  // result = Boolean(result)
                 // ctrl.$setValidity('isDuplicatedEmail', !result);
                                  if(fin==='true')
                                       {
                                            console.log("Run in blur!>>> TRUEEEE ");
                                       // scope.er="valid email";
                                       ctrl.$setValidity('isDuplicatedEmail', false);
                                       
                                          return scope.user.email;
                                       }
                                   else if(fin==='false')
                                      {
                                          console.log("Run in blur!>>> FALSEEE ");
                                       // scope.er="Email already used";  
                                         ctrl.$setValidity('isDuplicatedEmail', true);
                                        return scope.user.email;
                                      }
                 
                 
                 
             });
            
            
           
                           
 
        });


      })
    }
  }
});


app.directive('passwordMatch', [function () {
    return {
        restrict: 'A',
        scope:true,
        require: 'ngModel',
        link: function (scope, elem , attrs,control) {
            var checker = function () {
 
                //get the value of the first password
                var e1 = scope.$eval(attrs.ngModel); 
    console.log("Run in blur!>>> e1 "+e1);
                //get the value of the other password  
                var e2 = scope.$eval(attrs.passwordMatch);
   console.log("Run in blur!>>> e2 "+e1);             
                
                return e1 == e2;
            };
            scope.$watch(checker, function (n) {
                console.log("Run in blur!>>> *** "+n); 
                //set the form control to valid if both 
                //passwords are the same, else invalid
                control.$setValidity("unique", n);
            });
        }
    };
}]);









