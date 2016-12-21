/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Vijay
 */



public class EntityManagerFactoryClass {
 
  public static final boolean DEBUG = true;
  
  private static final EntityManagerFactoryClass singleton = new EntityManagerFactoryClass();
  
  protected EntityManagerFactory emf;
  
  public static EntityManagerFactoryClass getInstance() {
    
    return singleton;
  }
  
  private EntityManagerFactoryClass() {
  }
 
  public EntityManagerFactory getEntityManagerFactory() 
  {
    
    if (emf == null)
    createEntityManagerFactory();
    return emf;
    
  }
  
  public void closeEntityManagerFactory() {
    
    if (emf != null) {
      emf.close();
      emf = null;
      if (DEBUG)
        System.out.println("n*** Persistence finished at " + new java.util.Date());
    }
  }
  
  protected void createEntityManagerFactory() {
    
    this.emf = Persistence.createEntityManagerFactory("offermanager");
    if (DEBUG)
      System.out.println("n*** Persistence started at " + new java.util.Date());
  }
}


