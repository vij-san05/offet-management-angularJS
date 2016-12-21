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
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.bson.types.ObjectId;


/**
 *
 * @author Vijay
 */
@Embeddable
public class Outlet implements Serializable {
    
   // @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
   // private ObjectId id;
  
    private String outletid;
    private String nameoutlet;
    private String street;
    private String city;
    private String postcode;
    private String country;
    private String typeoutlet;
    private String logo;
    private String latitude;
    private String logitude;
    private String description;
    private String remarks;
    private String status;
    private String outletphone;
    private String outletemail;
    private String outletstatus;
    private Double priceperpoint;
    private String hibbenstatus;
    
  //  @OneToMany
  //  private List<Product> products = new ArrayList<Product>();;
//    
////    @OneToOne
//    Point Point;
    
    
    private Date lastUpdatedTime;
    private int lastUpdatedUser;

    public Outlet() {
    }

    public String getOutletstatus() {
        return outletstatus;
    }

    public void setOutletstatus(String outletstatus) {
        this.outletstatus = outletstatus;
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

    public Double getPriceperpoint() {
        return priceperpoint;
    }

    public void setPriceperpoint(Double priceperpoint) {
        this.priceperpoint = priceperpoint;
    }

    public String getHibbenstatus() {
        return hibbenstatus;
    }

    public void setHibbenstatus(String hibbenstatus) {
        this.hibbenstatus = hibbenstatus;
    }

    

      
   

  
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

   

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLogitude() {
        return logitude;
    }

    public void setLogitude(String logitude) {
        this.logitude = logitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNameoutlet() {
        return nameoutlet;
    }

    public void setNameoutlet(String nameoutlet) {
        this.nameoutlet = nameoutlet;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTypeoutlet() {
        return typeoutlet;
    }

    public void setTypeoutlet(String typeoutlet) {
        this.typeoutlet = typeoutlet;
    }



   

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
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

   

   public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof Outlet)) 
    return false;
    Outlet o = (Outlet) obj;
    return o.outletid == this.outletid;
}
      
    
    
    
}
