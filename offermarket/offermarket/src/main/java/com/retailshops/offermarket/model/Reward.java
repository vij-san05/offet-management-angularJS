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
public class Reward implements Serializable {
 
     private String rewardId;
    private String productId;
    private String name;
    private int points_match;
    private String description;
     private boolean status;
    private String  reward_status;
    private Date lastUpdatedTime;
    private int lastUpdatedUser;

    public Reward() {
    }

    public String getRewardId() {
        return rewardId;
    }

    public void setRewardId(String rewardId) {
        this.rewardId = rewardId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

   

    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints_match() {
        return points_match;
    }

    public void setPoints_match(int points_match) {
        this.points_match = points_match;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getReward_status() {
        return reward_status;
    }

    public void setReward_status(String reward_status) {
        this.reward_status = reward_status;
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
   
    
}
