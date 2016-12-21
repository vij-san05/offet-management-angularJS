/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.service;

import com.retailshops.offermarket.dao.UserDAO;
import com.retailshops.offermarket.model.Outlet;
import com.retailshops.offermarket.model.TransactionsLog;
import com.retailshops.offermarket.model.User;
import com.retailshops.offermarket.util.EntityManagerFactoryClass;
import java.util.List;

/**
 *
 * @author Vijay
 */
public class AdminService {
    
            
    public List<User> userlist(String email)
    {
        System.out.println("FFFF"+ email );
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        List<User> users = user.getAllUsers();
        return users;
    }
     public User edituserpermissions(User u)
    {
        System.out.println("FFFF"+ u.getEmail());
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        User users = user.edituserpermissions(u);
        return users;
    }
     
         public List<TransactionsLog> salesshopsdetails(String email)
    {
        System.out.println("FFFF"+ email );
        UserDAO user = new UserDAO(EntityManagerFactoryClass.getInstance().getEntityManagerFactory());
        List<TransactionsLog> outlets = user.salesshopsdetails(email);
        return outlets;
    }
    
    
}
