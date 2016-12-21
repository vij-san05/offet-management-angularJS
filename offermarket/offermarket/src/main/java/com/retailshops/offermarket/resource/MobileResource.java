/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.resource;

import com.google.gson.Gson;
import com.retailshops.offermarket.model.FinalPoints;
import com.retailshops.offermarket.model.PublicData;
import com.retailshops.offermarket.model.User;
import com.retailshops.offermarket.service.MobileService;
import com.retailshops.offermarket.service.RetailService;
import com.retailshops.offermarket.util.JsonTransformer;
import com.retailshops.offermarket.util.NearestDistance;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.List;
import spark.Request;
import static spark.Spark.post;
import com.retailshops.offermarket.util.NearestDistance;

/**
 *
 * @author Vijay
 */
public class MobileResource {
     
      private static final String API_CONTEXT = "/api/v1";
     private final MobileService mobileservice;

    public MobileResource(MobileService mobileservice) {
        this.mobileservice = mobileservice;
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
  
  
  private void setupEndpoints() 
  {
      
       //login ok 
      // registration 
      //checkuserexisit
      //get userpoints 
      //update user
     
       //get offer details based on location 
       // if location is not sending fetch based on given location 
      
       
      post(API_CONTEXT + "/loginmobile", "application/json", (request, response) -> {
          
           System.out.print(">>>>>>>>"+request.body());
           
           if(request.body()==null || request.body()=="")
           {
               User error =new User();
                error.setEmail("null");
                return error;
           }
           try{
            User userdata = new Gson().fromJson(request.body(), User.class);
              User u =  mobileservice.userloginmob(userdata);
              if(u!=null)
              {
              User returnuser =new User();
              returnuser.setEmail(u.getEmail());
              returnuser.setMobilekey(u.getMobilekey());
              return returnuser;
              }
              else {
                  User error =new User();
                error.setEmail("null");
                return error;
              }
           }catch(Exception e)
           {
               e.printStackTrace();
                 User error =new User();
                error.setEmail("null");
                return error;
           }
          // User u = new User();
         //  u.setFirstName("sarath");
         //  u.setMobilekey("uasdasdasd212324332");
           
          // return u;
        }, new JsonTransformer());
      
      
      
      // checking user already exist
      
        post(API_CONTEXT + "/registrationcheckmobile", "application/json", (request, response) -> {
          
            if(request.body()==null || request.body()=="")
           {
               User error =new User();
                error.setEmail("null");
                return error;
           }
            try{
            User userdata = new Gson().fromJson(request.body(), User.class);
              User u =  mobileservice.usercheckmob(userdata);
              if(u!=null)
              {
              User returnuser =new User();
              returnuser.setEmail(u.getEmail());
              //returnuser.setMobilekey(u.getMobilekey());
              return returnuser;
              }
              else 
              {
                  User error =new User();
                error.setEmail("null");
                  return error;
              }
           }catch(Exception e)
           {
               e.printStackTrace();
                User error =new User();
                error.setEmail("null");
                return error;
           }
            
            
           //return "{username:null}"; 
            
        }, new JsonTransformer());
      
      
        //registrationmobiledata
                
          post(API_CONTEXT + "/registrationmobile", "application/json", (request, response) -> {
          
            if(request.body()==null || request.body()=="")
           {
               User error =new User();
                error.setEmail("null");
                return error;
           }
            try{
            User userdata = new Gson().fromJson(request.body(), User.class);
              User u =  mobileservice.registrationmobiledata(userdata);
              if(u!=null)
              {
              User returnuser =new User();
              returnuser.setEmail(u.getEmail());
              returnuser.setMobilekey(u.getMobilekey());
              return returnuser;
              }
              else 
              {
                  User error =new User();
                error.setEmail("null");
                  return error;
              }
           }catch(Exception e)
           {
               e.printStackTrace();
                User error =new User();
                error.setEmail("null");
                return error;
           }
            
            
           //return "{username:null}"; 
            
        }, new JsonTransformer());
        
          
          
          
          post(API_CONTEXT + "/editpasswordmobile", "application/json", (request, response) -> {
          
                 if(request.body()==null || request.body()=="")
           {
               User error =new User();
                error.setEmail("null");
                return error;
           }
                 
              try{
            User userdata = new Gson().fromJson(request.body(), User.class);
              User u =  mobileservice.editpasswordmobile(userdata);
              if(u!=null)
              {
              User returnuser =new User();
              returnuser.setEmail(u.getEmail());
              //returnuser.setMobilekey(u.getMobilekey());
              return returnuser;
              }
              else 
              {
                  User error =new User();
                error.setEmail("null");
                  return error;
              }
           }catch(Exception e)
           {
               e.printStackTrace();
                User error =new User();
                error.setEmail("null");
                return error;
           }   
                 
              
          });
          
          
          
        post(API_CONTEXT + "/locationchangemobile", "application/json", (request, response) -> {
               if(request.body()==null || request.body()=="")
           {
               User error =new User();
                error.setEmail("null");
                return error;
           }
               
                try{
            User userdata = new Gson().fromJson(request.body(), User.class);
             List<PublicData> u =  mobileservice.locationchangemobile(userdata);
              if(u!=null)
              {
              
              return u;
              }
              else 
              {
                  PublicData error =new PublicData();
                error.setOutletcity("null");
                
                List<PublicData> up = new ArrayList<PublicData>();
                up.add(error);
                  return up;
              }
           }catch(Exception e)
           {
               e.printStackTrace();
                User error =new User();
                error.setEmail("null");
                return error;
           } 
               
               
               
             
        });
        
     
           post(API_CONTEXT + "/getlocationservicemobile", "application/json", (request, response) -> {
               if(request.body()==null || request.body()=="")
           {
               User error =new User();
                error.setEmail("null");
                return error;
           }
               
                try{
            User userdata = new Gson().fromJson(request.body(), User.class);
             List<PublicData> u =  mobileservice.locationchangemobileAll(userdata);
              List<PublicData> udata = new ArrayList<PublicData>();
              if(u!=null)
              {
              
                  for (PublicData pd : u)
                  {
                    int d = new NearestDistance().calculateDistance(Double.parseDouble(userdata.getLatimobile()),Double.parseDouble(userdata.getLongimobile()), Double.parseDouble(pd.getOutletlatitide()),Double.parseDouble(pd.getOutletlogitude()));
                    if(d<=100)
                    {
                         System.out.print("distance working " +pd.getProductname());
                       udata.add(pd);
                    }
                  }
                  
                 if(udata.size()!=0)
                 {
                     System.out.print("up"+ udata.size() + udata.toString());
                    return udata; 
                     
                 }
                  else
                 {
                PublicData error =new PublicData();
                error.setOutletcity("null");
                
                List<PublicData> up = new ArrayList<PublicData>();
                up.add(error);
                System.out.print("up 1"+ up.size());
                  return up;
                 }
              
              }
              else 
              {
                  PublicData error =new PublicData();
                error.setOutletcity("null");
                
                List<PublicData> up = new ArrayList<PublicData>();
                up.add(error);
                  return up;
              }
           }catch(Exception e)
           {
               e.printStackTrace();
                User error =new User();
                error.setEmail("null");
                return error;
           } 
               
               
               
             
        }, new JsonTransformer());
        
     
           
           
           
        ///////////////////////listing score ///////////////////////
           
           
              post(API_CONTEXT + "/getuserscores", "application/json", (request, response) -> {
               if(request.body()==null || request.body()=="")
           {
               FinalPoints error =new FinalPoints();
                error.setOutletId("null");
                
                List<FinalPoints> up = new ArrayList<FinalPoints>();
                up.add(error);
                System.out.print("up 1"+ up.size());
                  return up;
           }
               /// user key 
                try{
                    
            User userdata = new Gson().fromJson(request.body(), User.class);
             List<FinalPoints> u =  mobileservice.getAllpointsofUserinList(userdata);
             
                  
                 if(u.size()>0)
                 {
                     System.out.print("up"+ u.size() + u.toString());
                    return u; 
                     
                 }
                  else
                 {
                FinalPoints error =new FinalPoints();
                error.setOutletId("null");
                
                List<FinalPoints> up = new ArrayList<FinalPoints>();
                up.add(error);
                System.out.print("up 1"+ up.size());
                  return up;
                 }
              
            
           }catch(Exception e)
           {
               FinalPoints error =new FinalPoints();
                error.setOutletId("null");
                
                List<FinalPoints> up = new ArrayList<FinalPoints>();
                up.add(error);
                System.out.print("up 1"+ up.size());
                return up;
           } 
               
               
               
             
        }, new JsonTransformer());
        
           
           
           
          
  }
  
  
  
  }

