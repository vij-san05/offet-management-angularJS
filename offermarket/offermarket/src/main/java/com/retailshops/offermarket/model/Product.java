/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.bson.types.ObjectId;


/**
 *
 * @author Vijay
 */
@Embeddable
public class Product implements Serializable {
   
   
    private String productid;
    private String productname;
    private String productsno;
    private int productquantity;
    private int productprice;
    
    
    private String productdescription;
    private String productstatus;
    private String productimagea;
    
    
 // offer
 ///////
    private String offername;
    private String offerdetails;
    private String offerdescription;
    private String offerstatus;
 
          
 //////
 //reward
    private int rewardpoints;
    private String rewardsdetails;
     
 
 //points          
    private int userearningpoints;
 
    
//OutletId
    private String outletid;
    
    private String hiddenstatus;
    
    private Date lastUpdatedTime;
    private int lastUpdatedUser;

    public Product() {
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public String getHiddenstatus() {
        return hiddenstatus;
    }

    public void setHiddenstatus(String hiddenstatus) {
        this.hiddenstatus = hiddenstatus;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductsno() {
        return productsno;
    }

    public void setProductsno(String productsno) {
        this.productsno = productsno;
    }

    public int getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(int productquantity) {
        this.productquantity = productquantity;
    }

    public int getProductprice() {
        return productprice;
    }

    public void setProductprice(int productprice) {
        this.productprice = productprice;
    }

    public String getProductdescription() {
        return productdescription;
    }

    public void setProductdescription(String productdescription) {
        this.productdescription = productdescription;
    }

    public String getProductstatus() {
        return productstatus;
    }

    public void setProductstatus(String productstatus) {
        this.productstatus = productstatus;
    }

    public String getProductimagea() {
        return productimagea;
    }

    public void setProductimagea(String productimagea) {
        this.productimagea = productimagea;
    }

    public String getOffername() {
        return offername;
    }

    public void setOffername(String offername) {
        this.offername = offername;
    }

    public String getOfferdetails() {
        return offerdetails;
    }

    public void setOfferdetails(String offerdetails) {
        this.offerdetails = offerdetails;
    }

    public String getOfferdescription() {
        return offerdescription;
    }

    public void setOfferdescription(String offerdescription) {
        this.offerdescription = offerdescription;
    }

    public String getOfferstatus() {
        return offerstatus;
    }

    public void setOfferstatus(String offerstatus) {
        this.offerstatus = offerstatus;
    }

  

    public int getRewardpoints() {
        return rewardpoints;
    }

    public void setRewardpoints(int rewardpoints) {
        this.rewardpoints = rewardpoints;
    }

    public String getRewardsdetails() {
        return rewardsdetails;
    }

    public void setRewardsdetails(String rewardsdetails) {
        this.rewardsdetails = rewardsdetails;
    }

    public int getUserearningpoints() {
        return userearningpoints;
    }

    public void setUserearningpoints(int userearningpoints) {
        this.userearningpoints = userearningpoints;
    }

  

    

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public int getLastUpdatedUser() {
        return lastUpdatedUser;
    }

    public void setLastUpdatedUser(int lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    public String getOutletid() {
        return outletid;
    }

    public void setOutletid(String outletid) {
        this.outletid = outletid;
    }



  
    
    
    
}
