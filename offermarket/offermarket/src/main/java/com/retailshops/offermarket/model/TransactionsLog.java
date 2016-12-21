/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.bson.types.ObjectId;

/**
 *
 * @author Vijay
 */

@Entity
public class TransactionsLog implements  Serializable{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private ObjectId id;
   private String email;
   private String outletid;
   private String usrPoints;  
   private String productid;
   private int quantity;
   private Double totalprice;
   private Double pointacquired;
   private int pointsdeducted;
   private Double finalprice;
   private String dateandtime;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOutletid() {
        return outletid;
    }

    public void setOutletid(String outletid) {
        this.outletid = outletid;
    }

    public String getUsrPoints() {
        return usrPoints;
    }

    public void setUsrPoints(String usrPoints) {
        this.usrPoints = usrPoints;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public Double getPointacquired() {
        return pointacquired;
    }

    public void setPointacquired(Double pointacquired) {
        this.pointacquired = pointacquired;
    }

   
    public int getPointsdeducted() {
        return pointsdeducted;
    }

    public void setPointsdeducted(int pointsdeducted) {
        this.pointsdeducted = pointsdeducted;
    }

    public Double getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(Double finalprice) {
        this.finalprice = finalprice;
    }

    public String getDateandtime() {
        return dateandtime;
    }

    public void setDateandtime(String dateandtime) {
        this.dateandtime = dateandtime;
    }
   
   
    
    
    
    
    
    
    
}
