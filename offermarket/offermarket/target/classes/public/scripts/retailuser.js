var urldata= "http://10.36.125.84:8083/uploads/";
//var urldata= "http://192.168.0.22:8083/uploads/";
var rootScopeShop = null;
var outletimagescope =null;
var rootScopeShopproducts=null
var app = angular.module('retailuser', [
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngRoute',
    'angularFileUpload',
     'highcharts-ng'
]);

app.config(function ($routeProvider) {
    $routeProvider.when('/', {
        templateUrl: '/views/user/main.html',
         controller:'viewshopsmain'  // this is the graph page
    }).when('/outlets', {
        templateUrl: '/views/user/shops.html',
        controller: 'viewshops'     // view  
    }).when('/addoutlets', {
        templateUrl: '/views/user/addshops.html',
        controller: 'addshops'    // add 
    }).when('/editoutlets', {
        templateUrl: '/views/user/editshops.html',
        controller: 'editshop'     //edit 
    })
            
            
      .when('/products', {       
        templateUrl: '/views/user/products.html',
        controller: 'viewproducts'           //view products
        
    }).when('/addproducts', {
        templateUrl: '/views/user/addproducts.html',
        controller: 'addproducts'             // add products
        
    }).when('/editproducts', {
        templateUrl: '/views/user/editproducts.html',
        controller: 'editproducts'                    // edit products
        
        
    }).when('/pos', {
        templateUrl: '/views/user/pos.html',
        controller: 'posshops'
    }).when('/sales', {
        templateUrl: '/views/user/sales.html',
        controller: 'salesshops'
    }).when('/settings', {
        templateUrl: '/views/user/settings.html',
       controller: 'userretailedit'
    })
            
          .otherwise({
        redirectTo: '/'
    })
});


//file upload 
var MyCtrl = [ '$scope', '$upload', function($scope, $upload) {
  $scope.onFileSelect = function($files) {
    //$files: an array of files selected, each file has name, size, and type. 
    for (var i = 0; i < $files.length; i++) {
      var file = $files[i];
      $scope.upload = $upload.upload({
        url: '/api/v1/uploads', //upload.php script, node.js route, or servlet url 
        method: 'POST', 
        //headers: {'header-key': 'header-value'}, 
        //withCredentials: true, 
        data: {myObj: $scope.myModelObj},
        file: file, // or list of files ($files) for html5 only 
        //fileName: 'doc.jpg' or ['1.jpg', '2.jpg', ...] // to modify the name of the file(s) 
        // customize file formData name ('Content-Desposition'), server side file variable name.  
        //fileFormDataName: myFile, //or a list of names for multiple files (html5). Default is 'file'  
        // customize how data is added to formData. See #40#issuecomment-28612000 for sample code 
        //formDataAppender: function(formData, key, val){} 
      }).progress(function(evt) {
        console.log('percent: ' + parseInt(100.0 * evt.loaded / evt.total));
         console.log(">>>>>>>>" + JSON.stringify(file));
        // outletimagescope= ;
         if ($scope.outlet==undefined)
         {
             $scope.product.productimagea=file.name;
              $scope.imagelocproduct = urldata+file.name;
         }
         else if($scope.product==undefined)
         {
            // alert("here workin");
             $scope.outlet.logo=file.name;
             $scope.imageloc = urldata+file.name;
         }
          //alert("here workinsss");
      }).success(function(data, status, headers, config) {
          
          
        // file is uploaded successfully 
        console.log(data + file.filename);
      });
      //.error(...) 
      //.then(success, error, progress);  
      // access or attach event listeners to the underlying XMLHttpRequest. 
      //.xhr(function(xhr){xhr.upload.addEventListener(...)}) 
    }
    /* alternative way of uploading, send the file binary with the file's content-type.
       Could be used to upload files to CouchDB, imgur, etc... html5 FileReader is needed. 
       It could also be used to monitor the progress of a normal http post/put request with large data*/
    // $scope.upload = $upload.http({...})  see 88#issuecomment-31366487 for sample code. 
  };
}];





//model window
app.directive('fileModel', ['$parse', function ($parse) {
        alert("working");
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);




app.controller('MyCtrlda', function ($scope, $http) {
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




app.controller('shops', function ($scope, $http) {
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

//view shops
app.controller('viewshops', function ($scope, $http,$cookieStore, $cookies) {
     console.log("data activated ssss>>>" +$cookies.useremail);
    console.log("data activated >>>" + $cookieStore.get("useremail"));
    $http.post('/api/v1/viewshops',{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
     console.log(data);
        $scope.outlets = data;
         console.log(data);
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

    $scope.todoStatusChanged = function (outlet) {
        console.log(todo);
        $http.put('/api/v1/viewshops' + outlet.id, outlet).success(function (data) {
            console.log('status changed');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
    
    
    
});

// add shops 
app.controller('addshops', function ($scope, $http, $location,$cookieStore) {
    $scope.outlet = {
        done: false
    };
    $scope.urldatafinal=urldata;

    $scope.createShop = function () {
       // console.log($scope.outlet.city);
       // $scope.user.email = $cookieStore.get("useremail");
         
   
        $http.post('/api/v1/addoutlets', $scope.outlet,{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
            console.log(data);
            alert("Outlet created");
           // $location.path('/views/user/main.html');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
    
   
    
});

//edit button  and delete button 
app.controller('MyControlleredituser', function ($scope, $http, $location,$cookieStore) {
    
 $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.data = [];
    $scope.numberOfPages=function(){
        return Math.ceil($scope.data.length/$scope.pageSize);                
    }
    for (var i=0; i<45; i++) {
        $scope.data.push("Item "+i);
    }



    $scope.editShop = function ($index) {
        
        $scope.imageloc=null;
        //
        //console.log(this.outletid + ">>" + data  );
       // $scope.user.email = $cookieStore.get("useremail");
         var myArray = $scope.outlets.splice($index,1);
      var arrayOfObjects = eval(myArray);
      console.log(JSON.stringify(arrayOfObjects[0]));
       rootScopeShop = arrayOfObjects[0];
      // alert(JSON.stringify(arrayOfObjects[0]));
                 $location.path('/editoutlets');
        
        
//        $http.post('/api/v1/editoutlet', $scope.outlet,{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
//           // console.log("&&&&"+data);
//           // $scope.editoutlet = data;
//            rootScopeShop = $scope.outlet;
//            $location.path('/editoutlets');
//        }).error(function (data, status) {
//            console.log('Error ' + data)
//        })
    }
    
      $scope.deleteShop = function ($index) {
       // console.log("tet" );
       // $scope.user.email = $cookieStore.get("useremail");
         // alert("asdasdad");
          
          

var r = confirm("Do u want to delete!");
if (r == true) { 
    
                var myArray = $scope.outlets.splice($index,1);
      var arrayOfObjects = eval(myArray);
      console.log(JSON.stringify(arrayOfObjects[0]));
       rootScopeShop = arrayOfObjects[0];
    
              $http.post('/api/v1/deleteoutlet', arrayOfObjects[0],{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
              alert("Deleted !");
               $location.path('/outlets');
              }).error(function (data, status) {
              console.log('Error ' + data)
              });
    
} 
    }
  
});
//edit data view 
app.controller('editshop', function ($scope, $http, $location,$cookieStore) {
   $scope.urldatafinal=urldata;
    $scope.outlet = rootScopeShop;
    $scope.imageloc=rootScopeShop.logo;
    $scope.updateShop = function () {
        console.log($scope.outlet.city);
       // $scope.user.email = $cookieStore.get("useremail");
        
        $http.post('/api/v1/addoutlets', $scope.outlet,{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
            console.log(data);
             alert("Outlet updated");
           // $location.path('/views/user/main.html');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
  
  
});

/////////////////////////////////// product ///////////////////////////////////////////////////////////////////



//view products
app.controller('viewproducts', function ($scope, $http,$cookieStore) {
    console.log("data activated");
    $http.get('/api/v1/viewproducts',{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
     console.log(data);
        $scope.products = data;
         console.log(data);
    }).error(function (data, status) {
        console.log('Error ' + data)
    })
    
    

    $scope.todoStatusChanged = function (outlet) {
        console.log(todo);
        $http.put('/api/v1/viewproducts' + outlet.id, outlet).success(function (data) {
            console.log('status changed');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
});

// add products 
app.controller('addproducts', function ($scope, $http, $location,$cookieStore) {
    $scope.product = {
        done: false
    };
    
      $http.post('/api/v1/viewshops',{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
     console.log(data);
        $scope.outletsdata = data;
        $scope.product.productimagea = "http://localhost:8083/uploads/clean.png";
         console.log(data);
    }).error(function (data, status) {
        console.log('Error ' + data)
    });
    

    $scope.createProduct = function () {
       // console.log($scope.outlet.city);
       // $scope.user.email = $cookieStore.get("useremail");
         

        $http.post('/api/v1/addproducts', $scope.product,{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
            console.log(data);
            alert("Product created");
           // $location.path('/views/user/main.html');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
    
   
    
});

//edit button  and delete button 
app.controller('MyControllereditproducts', function ($scope, $http, $location,$cookieStore) {
    
     $scope.currentPage = 0;
    $scope.pageSize = 10;
    $scope.data = [];
    $scope.numberOfPages=function(){
        return Math.ceil($scope.data.length/$scope.pageSize);                
    }
    for (var i=0; i<45; i++) {
        $scope.data.push("Item "+i);
    }

 $http.post('/api/v1/viewshops',{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
     console.log(data);
        $scope.outletsdata = data;
        //$scope.outletsdata.logo = "http://localhost:8083/uploads/clean.png";
         console.log(data);
    }).error(function (data, status) {
        console.log('Error ' + data)
    });
    



    $scope.editproduct = function ($index) {
        //console.log(this.outletid + ">>" + data  );
       // $scope.user.email = $cookieStore.get("useremail");
        
          var myArray = $scope.products.splice($index,1);
      var arrayOfObjects = eval(myArray);
      console.log(JSON.stringify(arrayOfObjects[0]));
       rootScopeShopproducts = arrayOfObjects[0];
                 $location.path('/editproducts');

       
    }
    
      $scope.deleteproduct = function ($index) {
        //console.log(this.outletid + ">>" + data  );
       // $scope.user.email = $cookieStore.get("useremail");
        var r = confirm("Do you want to delete!");
if (r == true) {
    
      var myArray = $scope.products.splice($index,1);
      var arrayOfObjects = eval(myArray);
      console.log(JSON.stringify(arrayOfObjects[0]));
       rootScopeShopproducts = arrayOfObjects[0];
    
    
    
        $http.post('/api/v1/deleteproducts', arrayOfObjects[0],{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
           // console.log("&&&&"+data);
           // $scope.editoutlet = data;
            //rootScopeShopproducts = $scope.product;
             $location.path('/products');
        }).error(function (data, status) {
            console.log('Error ' + data)
        });
        
//          $http.get('/api/v1/viewproducts',{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
//     console.log(data);
//        $scope.products = data;
//         console.log(data);
//    }).error(function (data, status) {
//        console.log('Error ' + data)
//    })
        
        
    }
    }
    
    
    
    
});
//edit data view 
app.controller('editproducts', function ($scope, $http, $location,$cookieStore) {
    
    
           $http.post('/api/v1/viewshops',{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
     console.log(data);
        $scope.outletsdata = data;
       
        //$scope.product.productimagea = "http://localhost:8083/uploads/clean.png";
         console.log(data);
    }).error(function (data, status) {
        console.log('Error ' + data)
    });
   
    $scope.product = rootScopeShopproducts;
     $scope.imagelocproduct=rootScopeShopproducts.productimagea;
    $scope.updateproduct = function () {
        console.log($scope.product);
       // $scope.user.email = $cookieStore.get("useremail");
        
        $http.post('/api/v1/addproducts', $scope.product,{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
            console.log(data);
             alert("product updated");
           // $location.path('/views/user/main.html');
        }).error(function (data, status) {
            console.log('Error ' + data)
        })
    }
  
  
});

//////////////////////////////////////////pos operations ////////////////////////////////////////////////////////////////////

app.controller('posshops', function ($scope, $http, $location,$cookieStore) {
    $scope.product = {
        done: false
    };
    
      $http.post('/api/v1/viewshops',{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
     console.log(data);
        $scope.outletsdata = data;
       // $scope.product.productimagea = "http://localhost:8083/uploads/clean.png";
         console.log(data);
    }).error(function (data, status) {
        console.log('Error ' + data)
    });
    
    });





//pos get user point  
app.controller('MyControllergetUser', function ($scope, $http, $location,$cookieStore) {   
    $scope.getUserByEmail = function () {
        //console.log(this.outletid + ">>" + data  );
        //$scope.posdata =$scope.pos;
        
        console.log(">>>>>>>>>>>>>>> :"+ JSON.stringify($scope.pos));
        //get products of that outlet
        
    $http.post('/api/v1/viewproductsbyOutlet',$scope.pos,{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
     console.log("products>>>>>> " +JSON.stringify( data));
        $scope.productdata = data;
         console.log(data);
    }).error(function (data, status) {
        console.log('Error ' + data)
    })
        
        

        
        $http.post('/api/v1/getuserbyemail', $scope.pos,{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
            console.log("&&&&"+JSON.stringify(data));
            $scope.pos = data;
            $scope.posdata=data; 
           // rootScopeShopproducts = $scope.product;
          //  $location.path('editproducts');
        }).error(function (data, status) {
            console.log('Error ' + data)
        });
        
        
        
        
    }
    
    
    
    
     $scope.getCalcualtedResult =function (){
         
    $http.post('/api/v1/getCalcualtedResult', $scope.pos,{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
       console.log("&&&&"+JSON.stringify(data));
        $scope.pos = data;
        $scope.posdata=data; 
           // rootScopeShopproducts = $scope.product;
          //  $location.path('editproducts');
        }).error(function (data, status) {
            console.log('Error ' + data)
        });
        
         
         
     }
     
     
     
     
     $scope.adduserPoints =function (){
         console.log("&&&&  clicking..........####");
    $http.post('/api/v1/adduserPoints', $scope.pos,{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
       console.log("&&&&"+JSON.stringify(data));
        $scope.pos = data;
        $scope.posdata=data; 
           // rootScopeShopproducts = $scope.product;
          //  $location.path('editproducts');
        }).error(function (data, status) {
            console.log('Error ' + data)
        });
        
         
         
     }
     
     
      $scope.deleteuserPoints =function (){
         
    $http.post('/api/v1/deleteuserPoints', $scope.pos,{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
       console.log("&&&&"+JSON.stringify(data));
        $scope.pos = data;
        $scope.posdata=data; 
           // rootScopeShopproducts = $scope.product;
          //  $location.path('editproducts');
        }).error(function (data, status) {
            console.log('Error ' + data)
        });
        
         
         
     }
    $scope.deductCalculate=function()
     {
        $http.post('/api/v1/deductCalculate', $scope.pos,{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
       console.log("&&&&"+JSON.stringify(data));
        $scope.pos = data;
        $scope.posdata=data; 
           // rootScopeShopproducts = $scope.product;
          //  $location.path('editproducts');
        }).error(function (data, status) {
            console.log('Error ' + data)
        });
//            console.log("$$" + $scope.pos.totalprice - ($scope.outlet.priceperpoint*$scope.pos.pointsdeducted) );
//      $scope.pos.finalprice=  $scope.pos.totalprice - ($scope.outlet.priceperpoint*$scope.pos.pointsdeducted) 
     }
     
     
});

///////////////////////////////////////////////////logout//////////////////////////////////////////////////////////////////
app.controller('MyControlleredituserlogout', function ($scope, $http, $location,$cookieStore, $window) { 

$scope.logout =function (){

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


////////////////////////////////sales shop //////////////////////////////////////////////



app.controller('salesshops', function ($scope, $http,$cookieStore, $cookies) {
     console.log("data activated ssss>>>" +$cookies.useremail);
    console.log("data activated >>>" + $cookieStore.get("useremail"));
    
$http.post('/api/v1/salesshops',{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
     console.log(data);
        $scope.outlets = data;
         console.log(data);
    }).error(function (data, status) {
        console.log('Error ' + data)
    })

   
});


//salesshopsgraph


//////////////////////////////////////////////////////////////////////////////////////////



app.controller('userretailedit', function ($scope, $http,$cookieStore, $cookies) {
     console.log("data activated ssss>>>" +$cookies.useremail);
    console.log("data activated >>>" + $cookieStore.get("useremail"));
    
$http.post('/api/v1/userretailedit',{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
     console.log(data);
        $scope.user = data;
         console.log(data);
    }).error(function (data, status) {
        console.log('Error ' + data)
    })
    
    $scope.updateUser =function ()
    {
         $http.post('/api/v1/userupdate', $scope.user,{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
       console.log("&&&&"+JSON.stringify(data));
        
        alert("User Updated");
           // rootScopeShopproducts = $scope.product;
          //  $location.path('editproducts');
        }).error(function (data, status) {
            console.log('Error ' + data)
        });
    }

   
});

//// edit profile password checking 

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


////////////////////////////// view shops main ////////////////////////////////////////////////




app.controller('viewshopsmain', function ($scope, $http,$cookieStore, $cookies) {
     console.log("data activated ssss>>>" +$cookies.useremail);
    console.log("data activated >>>" + $cookieStore.get("useremail"));
    
$http.post('/api/v1/viewshopsmain',{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
     console.log(data);
        $scope.retailcounts = data;
        
    }).error(function (data, status) {
        console.log('Error ' + data)
    })





   
});

///////////////////////// check outlet is there then retun true ..



app.controller('MyCtrlshopadd', function( $scope ){
   $scope.outlet.outletid = '';
  
});

var result;
app.service('userServiceOutlet', function($q,$http,$cookieStore){
    this.isUnique = function( id){
         var deferred = $q.defer(), i;
        data ={outletid:id};
        console.log("$$$$$$ " +JSON.stringify(data) + ">>>>" + id);  
      
       $http.post("/api/v1/useroutletcheck",data,{headers: {'email': $cookieStore.get("useremail")}})
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


app.directive('checkShop', function(userServiceOutlet) {
  return {
    restrict: "A",
    require: 'ngModel',
    link: function(scope, ele, attrs, ctrl) {

      ele.bind('blur', function() {
        scope.$apply(function() {
          console.log("Run in blur!" + scope.outlet.outletid);
          
            userServiceOutlet.isUnique(scope.outlet.outletid).then(function(result) {
                 scope.refreshing = false;
                 
                  console.log("Run in blur!>>>####" +result);
                  fin =result;
                   console.log("Run in blur!>>>####bb" +fin);
                  // result = Boolean(result)
                 // ctrl.$setValidity('isDuplicatedEmail', !result);
                                  if(fin==='true')
                                       {
                                            console.log("Run in blur!>>> TRUEEEE ");
                                            alert("duplicate Id");
                                                
                                       // scope.er="valid email";
                                       ctrl.$setValidity('shoptaken', false);
                                       ctrl.$render()
                                          return scope.outlet.outletid;
                                       }
                                   else if(fin==='false')
                                      {
                                          
                                          console.log("Run in blur!>>> FALSEEE ");
                                        
                                       // scope.er="Email already used";  
                                         ctrl.$setValidity('shoptaken', true);
                                         ctrl.$render()
                                     return scope.outlet.outletid;
                                      }
                 
                 
                 
             });
            
            
           
                           
 
        });


      })
    }
  }
});


////////////////// check there is a product /////////////////////////////////


var resultp;
app.service('userServiceProduct', function($q,$http,$cookieStore){
    

    this.isUnique = function( id){
         var deferred = $q.defer(), i;
        data ={productid:id};
        console.log("$$$$$$ " +JSON.stringify(data) + ">>>>" + id);           
       $http.post("/api/v1/userproductcheck",data,{headers: {'email': $cookieStore.get("useremail")}})
      .success(function(users) {
          resultp=users;
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


app.directive('checkProduct', function(userServiceProduct) {
  return {
    restrict: "A",
    require: 'ngModel',
    link: function(scope, ele, attrs, ctrl) {

      ele.bind('blur', function() {
        scope.$apply(function() {
          console.log("Run in blur!" + scope.product.productid);
          
            userServiceProduct.isUnique(scope.product.productid).then(function(result) {
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
                                       ctrl.$setValidity('isDuplicatedProduct', false);
                                       
                                          return scope.product.productid;
                                       }
                                   else if(fin==='false')
                                      {
                                          console.log("Run in blur!>>> FALSEEE ");
                                       // scope.er="Email already used";  
                                         ctrl.$setValidity('isDuplicatedProduct', true);
                                        return scope.product.productid;
                                      }
                 
                 
                 
             });
            
            
           
                           
 
        });


      })
    }
  }
});



 
 
 ////////////////////////////graph data ////////////////////////////////////////////////
 
 
 app.controller('chartCtrl', function ($scope,$http,$cookieStore) {


//loadgraph
var obj1 ;


$http.post('/api/v1/salesshopsgraph',{headers: {'email': $cookieStore.get("useremail")}}).success(function (data) {
    // console.log(JSON.stringify(data));
   
    var jsonObj = [];
    var str =JSON.stringify(jsonObj);
     obj1 = JSON.parse(str);
     for(var i=0;i<data.length;i++){
        var obj = data[i];
        
               console.log("%%%%%"+ JSON.stringify(obj));
              //jsonObj[data[i].id] = data[i].outletid;
              var str =  obj.dateandtime;   
              var p = str.split("/");
         
              var datedd = Date.UTC(p[2],p[0]-1,p[1]);
              
              obj1.push([datedd,obj.totalprice]);
                 console.log("000"+ JSON.stringify(obj1)) ;
                 
                 
                 $scope.highchartsNG = {
     
    
     
        options: {
           chart: {
            type: 'spline'
        },
        title: {
            text: 'Sales Data'
        },
        xAxis: {
            type: 'datetime',
            dateTimeLabelFormats: { // don't display the dummy year
                month: '%e. %b',
                year: '%b'
            },
            title: {
                text: 'Date'
            }
        },
        yAxis: {
            title: {
                text: 'Price($)'
            },
            min: 0
        }
        },
        series: [{
            name: '2015-2016',
            data: obj1
        }],
        title: {
            text: 'Sales Graph'
        },
        loading: false
    }
    
    
     console.log("111"+ JSON.stringify(obj1)) ;
                 
    }
    }).error(function (data, status) {
        console.log('Error ' + data)
    });
    

});