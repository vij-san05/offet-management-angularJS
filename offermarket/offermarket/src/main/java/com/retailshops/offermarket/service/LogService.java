/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.service;

import com.google.gson.Gson;
import com.mongodb.*;
import com.retailshops.offermarket.dao.LogDAO;
import com.retailshops.offermarket.dao.exceptions.RollbackFailureException;
import com.retailshops.offermarket.model.Log;
import com.retailshops.offermarket.util.EntityManagerFactoryClass;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vijay
 */
public class LogService {
    
   
    
 public void createLog(String body) {
     System.out.println(body);
        Log log = new Gson().fromJson(body, Log.class);
        log.setLastUpdatedTime("now");
       // collection.insert(new BasicDBObject("title", todo.getTitle()).append("done", todo.isDone()).append("createdOn", new Date()));
        LogDAO newlog = new LogDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
     try {
         newlog.create(log);
     } catch (Exception ex) {
         Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
     }
        
    }
 
  public List<Log> findAll() {
        List<Log> todos = new ArrayList<>();
         LogDAO newlog = new LogDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
         return newlog.findLogEntities();
       
    }
  
    
     public Log find(String id) {
         
          LogDAO newlog = new LogDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
         return newlog.findLog(new ObjectId(id));
    }

    public Log update(String todoId, String body) {
        
          LogDAO newlog = new LogDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
          
           Log log = new Gson().fromJson(body, Log.class);
     try {
          newlog.edit(log);
         //  collection.update(new BasicDBObject("_id", new ObjectId(todoId)), new BasicDBObject("$set", new BasicDBObject("done", todo.isDone())));
         // return this.find(todoId);
     } catch (RollbackFailureException ex) {
         Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
     } catch (Exception ex) {
         Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
     }
     
      return newlog.findLog(log.getId());
     
    }
}
