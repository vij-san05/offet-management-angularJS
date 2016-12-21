package com.retailshops.offermarket.resource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Vijay
 */
import com.google.gson.Gson;
import com.retailshops.offermarket.model.Product;
import com.retailshops.offermarket.model.User;
import com.retailshops.offermarket.service.LogService;
import com.retailshops.offermarket.service.UserService;

import com.retailshops.offermarket.util.JsonTransformer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
public class UserRegisterResource {
    
     private static final String API_CONTEXT = "/api/v1";
     private final UserService userservice;

    public UserRegisterResource(UserService usrService) {
        this.userservice = usrService;
        setupEndpoints();
    }

    private void setupEndpoints() {
        post(API_CONTEXT + "/userregister", "application/json", (request, response) -> {
            userservice.createUser(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());
        
         post(API_CONTEXT + "/userlogin", "application/json", (request, response) -> {
          User user = userservice.findUserforLogin(request.body());
          
         
                 if(user==null)
                 {
                     return user;
                 }
                 else
                 {
                    System.out.print(">>>>>>>>"+user.getEmail());
                    User u = new User();
                    u.setEmail(user.getEmail());
                    u.setRole(user.getRole());
                     return u;
                 }
        }, new JsonTransformer());
         
          post(API_CONTEXT + "/userregistercheck", "application/json", (request, response) -> {
            boolean user;
            System.out.print("??????" + request.body().toString());
              User userdata = new Gson().fromJson(request.body(), User.class);
                   userdata.setDeductionscore(0);
              user = userservice.findUser(userdata.getEmail());
          
           System.out.print("??????" + user);
                 if(user==false)
                 {
                     return false;
                 }
                 else
                 {
                   return true;
                 }
        }, new JsonTransformer());
         
        
        
        
    }
    
    
    
    
}
