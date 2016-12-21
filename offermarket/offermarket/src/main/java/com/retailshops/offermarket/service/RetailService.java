/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.service;

import com.google.gson.Gson;
import com.retailshops.offermarket.dao.UserDAO;
import com.retailshops.offermarket.model.Outlet;
import com.retailshops.offermarket.model.Pos;
import com.retailshops.offermarket.model.Product;
import com.retailshops.offermarket.model.PublicData;
import com.retailshops.offermarket.model.RetailsCounts;
import com.retailshops.offermarket.model.TransactionsLog;
import com.retailshops.offermarket.model.User;
import com.retailshops.offermarket.util.EntityManagerFactoryClass;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Vijay
 */
public class RetailService {
    
    public List<Outlet> getOutlets(String email)
    {
        System.out.println("FFFF"+ email );
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        List<Outlet> outlets = user.getOutlets(email);
        return outlets;
    }
    
    public boolean useroutletcheck(Outlet o,String email)
    {
        System.out.println("FFFF"+ email );
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        boolean outlets = user.useroutletcheck(o,email);
        return outlets;
    }
    public boolean userproductcheck(Product o,String email)
    {
        System.out.println("FFFF"+ email );
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        boolean status = user.userproductcheck(o,email);
        return status;
    }
    
    
    
    
    public List<Outlet> addorupdateOutlet(Outlet outlet,String email,String operation)
    {
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        List<Outlet> outlets = user._outlet_action(outlet,email,operation);
        return outlets;
    }
    
    
     public List<Product> getProducts(String email)
    {
        System.out.println("FFFF"+ email );
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        List<Product> products = user.getProducts(email);
        return products;
    }
    
    public List<Product> addorupdateProduct(Product product,String email,String operation)
    {
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        List<Product> products = user._product_action(product,email,operation);
        return products;
    }
    
     public String findUserforPos(String email,String outletid)
    {
        System.out.println("FFFF"+ email);
        System.out.println("FFFF"+ outletid);
        UserDAO userdao = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        String data = userdao.userByemailandPosToPoints(email,outletid);
        return data;
    }
     
     
     public List<Product> viewproductsbyOutlet(String outlet , String retailemail)
     {
        System.out.println("FFFF"+ outlet);
        System.out.println("FFFF"+ retailemail);
        
        UserDAO userdao = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        List<Product> products = userdao.viewproductsbyOutlet(outlet,retailemail);
        return products;
         
         
         
     }
     
     
    public Pos  getCalcualtedResult(Pos posdata,String email)
    {
        UserDAO userdao = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
       Pos data = userdao.getCalcualtedResult(posdata,email);
        return data;
    }
    
     public Pos addUserPoints(Pos posdata,String email)
    {
        UserDAO userdao = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
       Pos data = userdao.addUserPoints(posdata,email);
        return data;
    }
     
     
       public Pos deductCalculate(Pos posdata,String email)
    {
        UserDAO userdao = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
       Pos data = userdao.deductCalculate(posdata,email);
        return data;
    }
     
      public Pos addDeletePoints(Pos posdata,String email)
    {
        UserDAO userdao = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
       Pos data = userdao.deletePoints(posdata,email);
        return data;
    }
      
        public List<TransactionsLog> salesshops(String email)
    {
        System.out.println("FFFF"+ email );
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        List<TransactionsLog> outlets = user.getsalesShops(email);
        
        
     Collections.sort(outlets, new Comparator<TransactionsLog>() {
        DateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        @Override
        public int compare(TransactionsLog o1, TransactionsLog o2) {
            try {
                System.out.println(" >> " + o1.getDateandtime());
                return f.parse(o1.getDateandtime()).compareTo(f.parse(o2.getDateandtime()));
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }   
        });
        
        
        
        return outlets;
    }
        
         public List<TransactionsLog> salesshopsgraph(String email)
    {
        boolean statusdata= false;
        System.out.println("FFFF"+ email );
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        List<TransactionsLog> outlets = user.getsalesShops(email);
        
        
     Collections.sort(outlets, new Comparator<TransactionsLog>() {
        DateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
        @Override
        public int compare(TransactionsLog o1, TransactionsLog o2) {
            try {
                System.out.println(" >> " + o1.getDateandtime());
                return f.parse(o1.getDateandtime()).compareTo(f.parse(o2.getDateandtime()));
            } catch (ParseException e) {
                throw new IllegalArgumentException(e);
            }
        }   
        });
     
     
   
     
     double price=0.0;
     Date datefg;
     List<TransactionsLog> datanew = new ArrayList<TransactionsLog>();
     List<TransactionsLog> datanewfinal = new ArrayList<TransactionsLog>();
     datefg=new Date();
      for(TransactionsLog log:outlets)
      {System.out.println("Main loop");
           DateFormat f = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
            try {
                Date datef= f.parse(log.getDateandtime());
                price=0.0;
                statusdata=false;
                  for(TransactionsLog logdata:outlets)
                    {
                        System.out.println("Sub loop "+logdata.getDateandtime());
                        
                        Date datefdata= f.parse(logdata.getDateandtime());
                        
                      
        	     
                         DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                         String dateFormatted2 = dateFormat.format(datef);
                         String dateFormatted1 = dateFormat.format(datefdata);
                        
                        
                        System.out.println("Comaprision"+dateFormatted2.trim() + "##" +dateFormatted1.trim());
                        if(dateFormatted2.trim().matches(dateFormatted1.trim()))
                        {
                             System.out.println("Comaprision Pass" );
                           price = price + logdata.getTotalprice();
                            System.out.println("price " + price );
                            
                        }
                    }
                  
                  
                   for(TransactionsLog ff :   datanew)
                   {
                        System.out.println("ckecking data in finalstore Pass" );
                       System.out.println("******"+ff.getDateandtime());
                       DateFormat fg = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss");
                         datefg= fg.parse(ff.getDateandtime());
                         DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                         String dateFormatted2 = dateFormat.format(datef);
                         String dateFormatted1 = dateFormat.format(datefg);
                       if(dateFormatted1.trim().matches(dateFormatted2.trim()))
                       {
                           statusdata=true;
                       }
                       
                   
                   }
                   if(statusdata==false)
                   {
                       
                         TransactionsLog g = new TransactionsLog();
                                         g.setDateandtime(log.getDateandtime());
                                         g.setTotalprice(price);
                         TransactionsLog g1 = new TransactionsLog();
                         DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                                         g1.setDateandtime(dateFormat.format(dateFormat.parse(log.getDateandtime())));
                                         g1.setTotalprice(price);
                      datanew.add(g);
                      datanewfinal.add(g1);
                   }
                    
                //get 1st maching item and sum add to new 
                
                 
                
            } catch (ParseException ex) {
                Logger.getLogger(RetailService.class.getName()).log(Level.SEVERE, null, ex);
            }
         
      }
        
        //datanewfinal
        
        return datanewfinal;
    }
        
        
        
        
        
        
        User userdata;
        public User findUserforEdit(String email) {
        // status=0;
     System.out.println(email);
    
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
     try {
        userdata = user.userByemail(email);
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
    }
        
          public int updateUser(User userinput)
    {
        System.out.println("FFFF"+ userinput.getEmail() );
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        int result = user.updateUser(userinput);
        return result;
    }
          
          
          
        public RetailsCounts  getMajorCounts(String email)
        {
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        RetailsCounts result = user.getMajorCounts(email);
        return result;
        }
        
      
        public ArrayList<PublicData>  getMajorPublicData()
        { 
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        ArrayList<PublicData> result = user.getMajorPublicData();
        return result;
            
        }
        
}
