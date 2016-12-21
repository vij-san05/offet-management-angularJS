/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.util;

/**
 *
 * @author Vijay
 */


import java.util.UUID;

public class GenerateUUID {
  
  public static String getUUID(){
    //generate random UUIDs
    UUID idOne = UUID.randomUUID();
    System.out.println("UUID One: " + idOne);
    return String.valueOf(idOne);
  }
  
  
} 
