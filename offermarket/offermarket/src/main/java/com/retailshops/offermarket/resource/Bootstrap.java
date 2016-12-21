/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.resource;

import com.retailshops.offermarket.service.AdminService;
import com.retailshops.offermarket.service.LogService;
import com.retailshops.offermarket.service.MobileService;
import com.retailshops.offermarket.service.RetailService;
import com.retailshops.offermarket.service.UserService;
import com.retailshops.offermarket.util.EntityManagerFactoryClass;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static spark.SparkBase.setIpAddress;
import static spark.SparkBase.setPort;
import static spark.SparkBase.staticFileLocation;

/**
 *
 * @author Vijay
 */
public class Bootstrap {
    
    
    private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "10.36.125.84";
   //private static final String IP_ADDRESS = System.getenv("OPENSHIFT_DIY_IP") != null ? System.getenv("OPENSHIFT_DIY_IP") : "192.168.0.22";
    private static final int PORT = System.getenv("OPENSHIFT_DIY_PORT") != null ? Integer.parseInt(System.getenv("OPENSHIFT_DIY_PORT")) : 8083;
 
    public static void main(String[] args) throws Exception {
        setIpAddress(IP_ADDRESS);
        setPort(PORT);
        staticFileLocation("/public");
        new LogResource(new LogService());
        new UserRegisterResource(new UserService());
        new FileUpload();
        new RetailResource(new RetailService());
        new AdminResource(new AdminService());
        new MobileResource(new MobileService());
        EntityManagerFactoryClass.getInstance().getEntityManagerFactory();
    }
}


// sudo iptables -I INPUT -p tcp --dport 80 -j ACCEPT