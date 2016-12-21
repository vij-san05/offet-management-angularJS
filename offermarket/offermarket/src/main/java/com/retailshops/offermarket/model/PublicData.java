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
public class PublicData implements Serializable{
    
    
    
    
    
 // get all products and offers 
//     1) product name 
//     2) image 
//     3) city and country 
//     4) outlet name
//     5) logitude and latitude
//     5) offer name , details 
//     6) points you can earn 
//     7) reward points and details
//     8) contact outlet 
    
    public String productname;
    public String productimage;
    public String productdetails;
    
    public String outletname;
    public String outletcity;
    public String outletaddress;
    public String outletphone;
    public String outletemail;
    public String outletlatitide;
    public String outletlogitude;
    
    
    public String productprice;
    
    public String productoffer;
    public String productofferdetails;
    
    public String pointsyoucanearnperproduct;
    
    public String rewardpoints;
    public String rewarddetails;

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductimage() {
        return productimage;
    }

    public void setProductimage(String productimage) {
        this.productimage = productimage;
    }

    public String getProductdetails() {
        return productdetails;
    }

    public void setProductdetails(String productdetails) {
        this.productdetails = productdetails;
    }

    public String getOutletname() {
        return outletname;
    }

    public void setOutletname(String outletname) {
        this.outletname = outletname;
    }

    public String getOutletcity() {
        return outletcity;
    }

    public void setOutletcity(String outletcity) {
        this.outletcity = outletcity;
    }

    public String getOutletaddress() {
        return outletaddress;
    }

    public void setOutletaddress(String outletaddress) {
        this.outletaddress = outletaddress;
    }

    public String getOutletphone() {
        return outletphone;
    }

    public void setOutletphone(String outletphone) {
        this.outletphone = outletphone;
    }

    public String getOutletemail() {
        return outletemail;
    }

    public void setOutletemail(String outletemail) {
        this.outletemail = outletemail;
    }

    public String getOutletlatitide() {
        return outletlatitide;
    }

    public void setOutletlatitide(String outletlatitide) {
        this.outletlatitide = outletlatitide;
    }

    public String getOutletlogitude() {
        return outletlogitude;
    }

    public void setOutletlogitude(String outletlogitude) {
        this.outletlogitude = outletlogitude;
    }

    public String getProductprice() {
        return productprice;
    }

    public void setProductprice(String productprice) {
        this.productprice = productprice;
    }

    public String getProductoffer() {
        return productoffer;
    }

    public void setProductoffer(String productoffer) {
        this.productoffer = productoffer;
    }

    public String getProductofferdetails() {
        return productofferdetails;
    }

    public void setProductofferdetails(String productofferdetails) {
        this.productofferdetails = productofferdetails;
    }

    public String getPointsyoucanearnperproduct() {
        return pointsyoucanearnperproduct;
    }

    public void setPointsyoucanearnperproduct(String pointsyoucanearnperproduct) {
        this.pointsyoucanearnperproduct = pointsyoucanearnperproduct;
    }

    public String getRewardpoints() {
        return rewardpoints;
    }

    public void setRewardpoints(String rewardpoints) {
        this.rewardpoints = rewardpoints;
    }

    public String getRewarddetails() {
        return rewarddetails;
    }

    public void setRewarddetails(String rewarddetails) {
        this.rewarddetails = rewarddetails;
    }
    
    
    
    
    
    
}
