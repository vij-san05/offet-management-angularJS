/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.bson.types.ObjectId;


/**
 *
 * @author Vijay
 */
@Entity
public class Log implements Serializable {
    
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private ObjectId id;
    private String description;
    private String code;
    private String userid;
    private String query;
    private String lastUpdatedTime;
    private int lastUpdatedUser;
  

    public Log() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
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

    public void setLongdescription(String this_is_a_testing_data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
            
    
}
