/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.resource;

import com.google.gson.Gson;
import com.retailshops.offermarket.model.Outlet;
import com.retailshops.offermarket.model.Pos;
import com.retailshops.offermarket.model.Product;
import com.retailshops.offermarket.model.PublicData;
import com.retailshops.offermarket.model.RetailsCounts;
import com.retailshops.offermarket.model.User;
import com.retailshops.offermarket.service.RetailService;
import com.retailshops.offermarket.service.UserService;
import com.retailshops.offermarket.util.JsonTransformer;
import java.util.ArrayList;
import java.util.List;
import spark.Request;
import static spark.Spark.get;
import static spark.Spark.post;

/**
 *
 * @author Vijay
 */
public class RetailResource {
    
      private static final String API_CONTEXT = "/api/v1";
     private final RetailService retailservice;

    public RetailResource(RetailService retailservice) {
        this.retailservice = retailservice;
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
        
        ////////////////////////////////////////////////outlet//////////////////////////////////////////////////
        // add outlets
         post(API_CONTEXT + "/addoutlets", "application/json", (request, response) -> {
              System.out.println(request.body());
             Outlet u = new Gson().fromJson(request.body(), Outlet.class);
              System.out.println(u.getCity() + u.getRemarks() + u.getTypeoutlet());
             retailservice.addorupdateOutlet(u,getUserEmailFromCookie(request),"add");
           // userservice.createUser(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());
        
        // view outlets    
         post(API_CONTEXT + "/viewshops", "application/json", (request, response) -> {
       System.out.println( ">>> %%%"  +   request.headers("email")   );
           
             System.out.println( ">>> "  + getUserEmailFromCookie(request)   );
           // User u = new Gson().fromJson(request.body(), User.class);
            return retailservice.getOutlets(getUserEmailFromCookie(request));
           // response.status(201);
           // return response;
                 
            //response.status(201);
           // return response;
        }, new JsonTransformer());
         
        // delete outlets
            post(API_CONTEXT + "/deleteoutlet", "application/json", (request, response) -> {
         System.out.println("delete outlet :" + request.body());
             Outlet u = new Gson().fromJson(request.body(), Outlet.class);
              System.out.println(u.getCity() + u.getRemarks() + u.getTypeoutlet());
             retailservice.addorupdateOutlet(u,request.headers("email"),"delete");
           // userservice.createUser(request.body());
            response.status(201);
            return response;        
         
        }, new JsonTransformer());
         
        //edit outlets
            post(API_CONTEXT + "/editoutlet", "application/json", (request, response) -> {
            System.out.println(request.body());        
            //response.status(201);
            return response;
        }, new JsonTransformer()); 
         
         
        ///////////////////////////////////////////////////////end outlets //////////////////////////////////////////// 
         ////////////////////////////////////////////////products//////////////////////////////////////////////////////
          // add outlets
         post(API_CONTEXT + "/addproducts", "application/json", (request, response) -> {
              System.out.println(request.body());
             Product u = new Gson().fromJson(request.body(), Product.class);
              System.out.println(u.getProductid() + u.getProductstatus() + u.getProductsno());
             retailservice.addorupdateProduct(u,request.headers("email"),"add");
           // userservice.createUser(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());
        
        // view outlets    
         get(API_CONTEXT + "/viewproducts", "application/json", (request, response) -> {
             
             System.out.println(request.body() + "##" + request.headers("email") );
           // User u = new Gson().fromJson(request.body(), User.class);
            return retailservice.getProducts(request.headers("email"));
           // response.status(201);
           // return response;
                 
            //response.status(201);
           // return response;
        }, new JsonTransformer());
         
        // delete outlets
            post(API_CONTEXT + "/deleteproducts", "application/json", (request, response) -> {
         System.out.println(request.body());
             Product u = new Gson().fromJson(request.body(), Product.class);
               System.out.println(u.getProductid() + u.getProductstatus() + u.getProductsno());
             retailservice.addorupdateProduct(u,request.headers("email"),"delete");
           // userservice.createUser(request.body());
            response.status(201);
            return response;        
         
        }, new JsonTransformer());
         
        //edit outlets
            post(API_CONTEXT + "/editproducts", "application/json", (request, response) -> {
            System.out.println(request.body());        
            //response.status(201);
            return response;
        }, new JsonTransformer()); 
         
        
        ///////////////////////////////////////////////end products//////////////////////////////////////////////////////
         
         
             ////////////////////////////////////////////////offers//////////////////////////////////////////////////////
         post(API_CONTEXT + "/addoffers", "application/json", (request, response) -> {
           // userservice.createUser(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());
        
        // view offers    
         post(API_CONTEXT + "/viewoffers", "application/json", (request, response) -> {
         
            
                 
            //response.status(201);
            return response;
        }, new JsonTransformer());
         
        // delete offers
         post(API_CONTEXT + "/deleteoffers", "application/json", (request, response) -> {
         
            ;         
            //response.status(201);
            return response;
        }, new JsonTransformer());
         
        //edit offers
            post(API_CONTEXT + "/editoffers", "application/json", (request, response) -> {
         
            ;         
            //response.status(201);
            return response;
        }, new JsonTransformer());
        ///////////////////////////////////////////////end offers//////////////////////////////////////////////////////
         
             ////////////////////////////////////////////////rewards//////////////////////////////////////////////////////
         post(API_CONTEXT + "/addrewards", "application/json", (request, response) -> {
           // userservice.createUser(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());
        
        // view offers    
         post(API_CONTEXT + "/viewrewards", "application/json", (request, response) -> {
         
            
                 
            //response.status(201);
            return response;
        }, new JsonTransformer());
         
        // delete rewards
         post(API_CONTEXT + "/deleterewards", "application/json", (request, response) -> {
         
            ;         
            //response.status(201);
            return response;
        }, new JsonTransformer());
         
        //edit rewards
            post(API_CONTEXT + "/editrewards", "application/json", (request, response) -> {
         
            ;         
            //response.status(201);
            return response;
        }, new JsonTransformer());
        ///////////////////////////////////////////////end rewards//////////////////////////////////////////////////////
         
             ////////////////////////////////////////////////Points//////////////////////////////////////////////////////
          post(API_CONTEXT + "/addpoints", "application/json", (request, response) -> {
              
               
             
              
              
           // userservice.createUser(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());
        
        // view points    
         post(API_CONTEXT + "/viewpoints", "application/json", (request, response) -> {
         
            
                 
            //response.status(201);
            return response;
        }, new JsonTransformer());
         
        // delete points
         post(API_CONTEXT + "/deletepoints", "application/json", (request, response) -> {
         
            ;         
            //response.status(201);
            return response;
        }, new JsonTransformer());
         
        //edit points
            post(API_CONTEXT + "/editpoints", "application/json", (request, response) -> {
         
            ;         
            //response.status(201);
            return response;
        }, new JsonTransformer());
        ///////////////////////////////////////////////end Points//////////////////////////////////////////////////////
         
         
         
         ////////////////////////////////////////////////pos//////////////////////////////////////////////////////
       post(API_CONTEXT + "/getuserbyemail", "application/json", (request, response) -> {
           
             Pos u = new Gson().fromJson(request.body(), Pos.class);
           
             String data = retailservice.findUserforPos(u.getEmail(),u.getOutletid());
          
          System.out.print(">>>>>>>>"+u.getEmail());
                 if(data==null)
                 {
                     //response.cookie("useremail", "null",10);
                     //response.status(201);
                      u.setUsrPoints(String.valueOf(0));
             
                 }
                 else
                 {
                    u.setUsrPoints(data);  
                    
                 }
           return u; 
           // userservice.createUser(request.body());
            //response.status(201);
            //return response;
        }, new JsonTransformer());
        
        // view pos    
         post(API_CONTEXT + "/viewproductsbyOutlet", "application/json", (request, response) -> {
            System.out.println("?????????"+request.body());
             Pos u = new Gson().fromJson(request.body(), Pos.class);
             
              System.out.println( u.getOutletid() + " ++++++++ "+ request.headers("email"));
             List<Product> data =  retailservice.viewproductsbyOutlet(u.getOutletid(),request.headers("email"));
             if(data==null)
             {
                 return "0";
             }
             else
             return data;
                 
            //response.status(201);
           // return response;
        }, new JsonTransformer());
         
        // delete pos
         post(API_CONTEXT + "/getCalcualtedResult", "application/json", (request, response) -> {
           System.out.println("?????????"+request.body());
             Pos u = new Gson().fromJson(request.body(), Pos.class); 
             
               System.out.println( u.getOutletid() + " ++++++++ "+ request.headers("email"));
            Pos data =  retailservice.getCalcualtedResult(u,request.headers("email"));
             
            //response.status(201);
            return data;
        }, new JsonTransformer());
         
          //add points    
         post(API_CONTEXT + "/adduserPoints", "application/json", (request, response) -> {
         
              System.out.println("?????????"+request.body());
             Pos u = new Gson().fromJson(request.body(), Pos.class); 
             
               System.out.println( u.getOutletid() + " ++++++++ "+ request.headers("email"));
               Pos data =  retailservice.addUserPoints(u,request.headers("email"));
             
            //response.status(201);
            return data;
                 
            //response.status(201);
           
        }, new JsonTransformer());
         
         
         
         
         
         
         // edit points    
         post(API_CONTEXT + "/deleteuserPoints", "application/json", (request, response) -> {
         
                System.out.println("?????????"+request.body());
             Pos u = new Gson().fromJson(request.body(), Pos.class); 
             
               System.out.println( u.getOutletid() + " ++++++++ "+ request.headers("email"));
            Pos data =  retailservice.addDeletePoints(u,request.headers("email"));
             
            //response.status(201);
            return data;
                 
            //response.status(201);
           // return response;
        }, new JsonTransformer());  
         
         
          // edit points    
         post(API_CONTEXT + "/deductCalculate", "application/json", (request, response) -> {
         
                System.out.println("?????????"+request.body());
             Pos u = new Gson().fromJson(request.body(), Pos.class); 
             
               System.out.println( u.getOutletid() + " ++++++++ "+ request.headers("email"));
            Pos data =  retailservice.deductCalculate(u,request.headers("email"));
             if(data.getFinalprice()==0.0)
                 data.setFinalprice(0.0);
            //response.status(201);
            return data;
                 
            //response.status(201);
           // return response;
        }, new JsonTransformer());  
         
         
        ///////////////////////////////////////////////end pos//////////////////////////////////////////////////////
         //////////////////////////sales /////////////////////////////////
         
         
         
         
           post(API_CONTEXT + "/salesshops", "application/json", (request, response) -> {
       System.out.println( ">>> %%%"  +   request.headers("email")   );
           
             System.out.println( ">>> "  + getUserEmailFromCookie(request)   );
           // User u = new Gson().fromJson(request.body(), User.class);
            return retailservice.salesshops(getUserEmailFromCookie(request));
           // response.status(201);
           // return response;
                 
            //response.status(201);
           // return response;
        }, new JsonTransformer());
         
           
           
           ///////////////////////  retail user edit //////////////////////////////////////////////////////////////////////////////////////
            post(API_CONTEXT + "/userretailedit", "application/json", (request, response) -> {
        
                
                User user = retailservice.findUserforEdit(getUserEmailFromCookie(request) );
                user.setPassword("");
                  System.out.println( ">>> "  + user.getFirstName());
                     return user;
               
               
        }, new JsonTransformer());
            
        post(API_CONTEXT + "/userupdate", "application/json", (request, response) -> {
         User u = new Gson().fromJson(request.body(), User.class); 
                
                int result = retailservice.updateUser(u);
                
                  System.out.println( ">>> "  + result);
                     return result;
               
               
        }, new JsonTransformer());
                  
            
         // main page display 
         post(API_CONTEXT + "/viewshopsmain", "application/json", (request, response) -> {
         //User u = new Gson().fromJson(request.body(), User.class); 
                
                RetailsCounts counts = retailservice.getMajorCounts(getUserEmailFromCookie(request));
                
                //  System.out.println( ">>> "  + result);
                     return counts;
               
               
        }, new JsonTransformer());
        
        
        
     // main page display 
         post(API_CONTEXT + "/getpublicdata", "application/json", (request, response) -> {
         //User u = new Gson().fromJson(request.body(), User.class); 
                System.out.println( "requesting data ");
                ArrayList<PublicData> publicata = retailservice.getMajorPublicData();
                
                // System.out.println( ">>> "  + result);
                     return publicata;
               
               
        }, new JsonTransformer());
         
         
         
         ////////////////////////////////// check the outlet id is there in db //////////////
         post(API_CONTEXT + "/useroutletcheck", "application/json", (request, response) -> {
            boolean user;
            System.out.print("??????" + request.body().toString());
              Outlet outlet = new Gson().fromJson(request.body(), Outlet.class);
                   //userdata.setDeductionscore(0);
              user = retailservice.useroutletcheck(outlet,getUserEmailFromCookie(request));
          
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
         
        ///////////////////////check productid is in db ///////////////////////////////////////
          post(API_CONTEXT + "/userproductcheck", "application/json", (request, response) -> {
            boolean user;
            System.out.print("??????" + request.body().toString());
              Product product = new Gson().fromJson(request.body(), Product.class);
                   //userdata.setDeductionscore(0);  
              System.out.print("?????###"+ product.getProductid());
              user = retailservice.userproductcheck(product,getUserEmailFromCookie(request));
          
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
         
      /////////////////////////////// graph data ////////////////////////////////////
           post(API_CONTEXT + "/salesshopsgraph", "application/json", (request, response) -> {
       System.out.println( ">>> %%%"  +   request.headers("email")   );
           
             System.out.println( ">>> "  + getUserEmailFromCookie(request)   );
           // User u = new Gson().fromJson(request.body(), User.class);
            return retailservice.salesshopsgraph(getUserEmailFromCookie(request));
           // response.status(201);
           // return response;
                 
            //response.status(201);
           // return response;
        }, new JsonTransformer());
          
         
    }
    
    
    
 //////////////////////////////// get pubic data///////////////////////////////////////
    
    
     
    
    
}
