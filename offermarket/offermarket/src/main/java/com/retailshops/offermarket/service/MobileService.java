/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.service;

import com.retailshops.offermarket.dao.UserDAO;
import com.retailshops.offermarket.model.FinalPoints;
import com.retailshops.offermarket.model.PublicData;
import com.retailshops.offermarket.model.User;
import com.retailshops.offermarket.util.EntityManagerFactoryClass;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vijay
 */
public class MobileService {
    
    
    
     User statuslogin;
     public User  userloginmob(User u) {
        // status=0;
     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> email:" + u.getEmail());
    
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
     try {
        statuslogin= user.userloginmob(u);
     } catch (Exception ex) {
         Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
         return null;
     }
     return statuslogin;
        
    }
     
       User statuscheck;
     public User  usercheckmob(User u) {
        // status=0;
     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> email:" + u.getEmail());
    
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
     try {
        statuscheck= user.usercheckmob(u);
     } catch (Exception ex) {
         Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
         return null;
     }
     return statuscheck;
        
    }
     
       User statuscheckreg;
     public User  registrationmobiledata(User u) {
        // status=0;
     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> email:" + u.getEmail());
    
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
     try {
        statuscheckreg= user.registrationmobiledata(u);
     } catch (Exception ex) {
         Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
         return null;
     }
     return statuscheckreg;
        
    }
     
     
      public User  editpasswordmobile(User u) {
        // status=0;
     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> email:" + u.getEmail());
    
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
     try {
        statuscheckreg= user.editpasswordmobile(u);
     } catch (Exception ex) {
         Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
         return null;
     }
     return statuscheckreg;
        
    }
      
      List<PublicData> resultlocations;
      
       public List<PublicData>  locationchangemobile(User u) {
        // status=0;
     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> email:" + u.getEmail());
    
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
     try {
        resultlocations= user.locationchangemobile(u);
     } catch (Exception ex) {
         Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
         return null;
     }
     return resultlocations;
        
    }
         List<FinalPoints>  resultlocationsdata;
       public List<FinalPoints>  getAllpointsofUserinList (User u)
       {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> email:" + u.getEmail());
    
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
     try {
       resultlocationsdata= user.getAllpointsofUserinList(u);
     } catch (Exception ex) {
         Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
         return null;
     }
     return resultlocationsdata;
       }
       
       //  List<PublicData> resultlocations;
      
       public List<PublicData>  locationchangemobileAll(User u) {
        // status=0;
     System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>> email:" + u.getEmail());
    
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
     try {
        resultlocations= user.locationchangemobileAll(u);
     } catch (Exception ex) {
         Logger.getLogger(LogService.class.getName()).log(Level.SEVERE, null, ex);
         return null;
     }
     return resultlocations;
        
    }
       
       
     
     
     
     
     
     
    
}
