/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.bson.types.ObjectId;

/**
 *
 * @author Vijay
 */
@Embeddable
public class Point implements Serializable {
    
    
    private String productId;
    private String Outletid;
    private Double startprice;
    private Double finalprice;
    private Double userpoints;
    private int rewards_basic_points;
    private Date lastUpdatedTime;
    private int lastUpdatedUser;

    
    
    public Point() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getStartprice() {
        return startprice;
    }

    public void setStartprice(Double startprice) {
        this.startprice = startprice;
    }

    public Double getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(Double finalprice) {
        this.finalprice = finalprice;
    }

    public Double getUserpoints() {
        return userpoints;
    }

    public void setUserpoints(Double userpoints) {
        this.userpoints = userpoints;
    }

   

   
    
    
  

    public int getRewards_basic_points() {
        return rewards_basic_points;
    }

    public void setRewards_basic_points(int rewards_basic_points) {
        this.rewards_basic_points = rewards_basic_points;
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
        return Outletid;
    }

    public void setOutletid(String Outletid) {
        this.Outletid = Outletid;
    }

  
}
