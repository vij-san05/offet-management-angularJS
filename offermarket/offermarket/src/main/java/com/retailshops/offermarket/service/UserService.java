/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.service;

import com.google.gson.Gson;
import com.retailshops.offermarket.dao.LogDAO;
import com.retailshops.offermarket.dao.UserDAO;
import com.retailshops.offermarket.model.Log;
import com.retailshops.offermarket.model.User;
import com.retailshops.offermarket.util.EntityManagerFactoryClass;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vijay
 */
public class UserService {
     public void createUser(String body) {
     System.out.println(body);
        User u = new Gson().fromJson(body, User.class);
        u.setLastUpdatedTime("now");
        u.setDeductionscore(0);
        u.setRole(2);
        u.setStatus(0);
       // collection.insert(new BasicDBObject("title", todo.getTitle()).append("done", todo.isDone()).append("createdOn", new Date()));
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
     try {
         user.create(u);
     } catch (Exception ex) {
         Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
     int status;
     public boolean findUser(String body) {
        // status=0;
     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> email:" + body);
     //   User u = new Gson().fromJson(body, User.class);
      //  u.setLastUpdatedTime("now");
       // collection.insert(new BasicDBObject("title", todo.getTitle()).append("done", todo.isDone()).append("createdOn", new Date()));
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
     try {
        status= user.findUserByEmail(body);
     } catch (Exception ex) {
         Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
         return false;
     }
     System.out.print("####" + status);
     if(status==1)
     return true;
     else 
     return false;
        
    }
     User userdata;
     public User findUserforLogin(String body) {
        // status=0;
     System.out.println(body);
     User u = new Gson().fromJson(body, User.class);
        u.setLastUpdatedTime("now");
        
       // collection.insert(new BasicDBObject("title", todo.getTitle()).append("done", todo.isDone()).append("createdOn", new Date()));
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
     try {
        userdata = user.login(u.getEmail(),u.getPassword());
     } catch (Exception ex) {
         Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
     }
    
     if(userdata!=null)
     {
         System.out.print("####" + userdata.getEmail());
     return userdata;
     }
     else{
         
         return userdata;
     }
    // else 
    // return false;
        
    }
     
     
    
}
