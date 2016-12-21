/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.bson.types.ObjectId;
import org.hibernate.search.annotations.Indexed;


/**
 *
 * @author Vijay
 */


@Entity
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String street;
    private String city;
    private String postCode;
    private String country;
    private String lastUpdatedTime;
    private int lastUpdatedUser;
    private int deductionscore;
    private String userimage;
    private int role;
    private int status;
    private String mobilekey;
    private String latimobile;
    private String longimobile;
    
    
    
    // retail user hibernate ogm --- > this class - db map , ORM(Obect relation mapping ) 
    // Hibernate OGM ( Object/Grid Mapper)
   @ElementCollection
   @CollectionTable
    private List<Outlet> outlets= new ArrayList<Outlet>();
    
   @ElementCollection
   @CollectionTable
    private List<Product> products= new ArrayList<Product>();
    
     @ElementCollection
    @CollectionTable
    private List<Point> retailpoints =new ArrayList<Point>();
    
     @ElementCollection
    @CollectionTable
    private List<Reward> reward = new ArrayList<Reward>();
   
    
   @ElementCollection
    @CollectionTable
    private List<Offer> offers = new ArrayList<Offer>();
   
   
   
    // public user
    @ElementCollection
    @CollectionTable
    private List<UserPoint> userpoints =new ArrayList<UserPoint>();
    
    
     @ElementCollection
    @CollectionTable
    private List<FinalPoints> usertotalPointsInOutlet =new ArrayList<FinalPoints>();

    public List<FinalPoints> getUsertotalPointsInOutlet() {
        return usertotalPointsInOutlet;
    }

    public void setUsertotalPointsInOutlet(List<FinalPoints> usertotalPointsInOutlet) {
        this.usertotalPointsInOutlet = usertotalPointsInOutlet;
    }

     
     
     
     
    public List<Point> getRetailpoints() {
        return retailpoints;
    }

    public void setRetailpoints(List<Point> retailpoints) {
        this.retailpoints = retailpoints;
    }

    public List<Reward> getReward() {
        return reward;
    }

    public void setReward(List<Reward> reward) {
        this.reward = reward;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<UserPoint> getUserpoints() {
        return userpoints;
    }

    public void setUserpoints(List<UserPoint> userpoints) {
        this.userpoints = userpoints;
    }

    public String getUserimage() {
        return userimage;
    }

    public void setUserimage(String userimage) {
        this.userimage = userimage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMobilekey() {
        return mobilekey;
    }

    public void setMobilekey(String mobilekey) {
        this.mobilekey = mobilekey;
    }
    
    
    
    
    

    public User() {
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

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

   
    
   
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

   

    public List<Outlet> getOutlets() {
        return outlets;
    }

    public void setOutlets(List<Outlet> outlets) {
        this.outlets = outlets;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

   

   

    public String getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(String lastUpdatedTime) {
         DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss") ;
        Date today = Calendar.getInstance().getTime();        
        String reportDate = df.format(today);
        lastUpdatedTime=reportDate;
        this.lastUpdatedTime = lastUpdatedTime;
    }

    public int getLastUpdatedUser() {
        return lastUpdatedUser;
    }

    public void setLastUpdatedUser(int lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public int getDeductionscore() {
        return deductionscore;
    }

    public void setDeductionscore(int deductionscore) {
        this.deductionscore = deductionscore;
    }

    public String getLatimobile() {
        return latimobile;
    }

    public void setLatimobile(String latimobile) {
        this.latimobile = latimobile;
    }

    public String getLongimobile() {
        return longimobile;
    }

    public void setLongimobile(String longimobile) {
        this.longimobile = longimobile;
    }

    
}
