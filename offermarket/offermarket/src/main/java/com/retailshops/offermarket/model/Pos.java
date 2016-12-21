/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.model;

import java.io.Serializable;

/**
 *
 * @author Vijay
 */
public class Pos implements Serializable{
   private String email;
   private String outletid;
   private String usrPoints;
   
   private String productid;
   private int quantity;
   private Double totalprice;
   private int pointacquired;
   private int pointsdeducted;
   private Double finalprice;
   private String dateandtime;

    public Double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public int getPointacquired() {
        return pointacquired;
    }

    public void setPointacquired(int pointacquired) {
        this.pointacquired = pointacquired;
    }

    public Double getFinalprice() {
        return finalprice;
    }

    public void setFinalprice(Double finalprice) {
        this.finalprice = finalprice;
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

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

 

  

    public String getUsrPoints() {
        return usrPoints;
    }

    public void setUsrPoints(String usrPoints) {
        this.usrPoints = usrPoints;
    }



  
    public String getDateandtime() {
        return dateandtime;
    }

    public void setDateandtime(String dateandtime) {
        this.dateandtime = dateandtime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPointsdeducted() {
        return pointsdeducted;
    }

    public void setPointsdeducted(int pointsdeducted) {
        this.pointsdeducted = pointsdeducted;
    }
   
   
   
   
}
