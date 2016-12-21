/**
 * Created by shekhargulati on 10/06/14.
 */

var app = angular.module('todoapp', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: 'views/list.html',
        controller: 'ListCtrl'
    }).when('/create', {
        templateUrl: 'views/create.html',
        controller: 'CreateCtrl'
        
        
    }).when('/main',{
      templateUrl: 'views/main.html',
        controller: 'main'
        
    }).
            
    when('/login',{
      templateUrl: 'views/login.html',
       
        
    }).
             when('/register',{
      templateUrl: 'views/register.html',
       
        
    }).otherwise({
        redirectTo: '/'
    })
});

app.controller('ListCtrl', function ($scope, $http) {
    $http.get('/api/v1/logslist').success(function (data) {
        $scope.todos = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

    $scope.todoStatusChanged = function (todo) {
        console.log(todo);
        $http.put('/api/v1/logs/' + todo.id, todo).success(function (data) {
            console.log('status changed');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});


app.controller('ListCtrl', function ($scope, $http) {
    $http.get('/api/v1/logslist').success(function (data) {
        $scope.todos = data;
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

    $scope.todoStatusChanged = function (todo) {
        console.log(todo);
        $http.put('/api/v1/logs/' + todo.id, todo).success(function (data) {
            console.log('status changed');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});

app.controller('main', function ($scope, $http, $location) {
    $scope.todo = {
        done: false
    };

    $scope.createUser = function () {
        console.log($scope.todo);
        $http.post('/api/v1/logs', $scope.todo).success(function (data) {
            $location.path('/');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});