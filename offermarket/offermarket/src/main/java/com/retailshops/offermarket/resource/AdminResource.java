/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.resource;

import com.google.gson.Gson;
import com.retailshops.offermarket.model.Outlet;
import com.retailshops.offermarket.model.User;
import com.retailshops.offermarket.service.AdminService;
import com.retailshops.offermarket.service.RetailService;
import com.retailshops.offermarket.util.JsonTransformer;
import spark.Request;
import static spark.Spark.post;

/**
 *
 * @author Vijay
 */
public class AdminResource {
    
          private static final String API_CONTEXT = "/api/v1";
     private final AdminService adminservice;

    public AdminResource(AdminService adminservice) {
        this.adminservice = adminservice;
        setupEndpoints();
    }
public String getUserEmailFromCookie(Request request) 
  {
    String data= request.cookie("useremail");
    if(data.isEmpty())
    {
        return null;
    }
    else
    {
      data= data.replace("%22","").trim();
      return data;
    }
  }
    private void setupEndpoints() {
        
        // users   --> approval --> block 
        // outlets -- > approval --> block 
        // transactions
        
        post(API_CONTEXT + "/userlistforadmin", "application/json", (request, response) -> {
       //System.out.println( ">>> %%%"  +   request.headers("email")   );
           
             System.out.println( ">>> "  + getUserEmailFromCookie(request)   );
           // User u = new Gson().fromJson(request.body(), User.class);
            return adminservice.userlist(getUserEmailFromCookie(request));
           // response.status(201);
           // return response;
                 
            //response.status(201);
           // return response;
        }, new JsonTransformer());
        
         post(API_CONTEXT + "/edituserpermissions", "application/json", (request, response) -> {
       
             
             System.out.println( ">>> %%%"  +   request.body()   );
             User u = new Gson().fromJson(request.body(), User.class);
             System.out.println( ">>> "  + getUserEmailFromCookie(request)   );
           // User u = new Gson().fromJson(request.body(), User.class);
            return adminservice.edituserpermissions(u);
           // response.status(201);
           // return response;
                 
            //response.status(201);
           // return response;
        }, new JsonTransformer());
        
        //edituserpermissions
         
         
              post(API_CONTEXT + "/salesshopsdetails", "application/json", (request, response) -> {
       System.out.println( ">>> %%%"  +   request.headers("email")   );
           
             System.out.println( ">>> "  + getUserEmailFromCookie(request)   );
           // User u = new Gson().fromJson(request.body(), User.class);
            return adminservice.salesshopsdetails(getUserEmailFromCookie(request));
           // response.status(201);
           // return response;
                 
            //response.status(201);
           // return response;
        }, new JsonTransformer());
         
         
        
    }
    
    
}
