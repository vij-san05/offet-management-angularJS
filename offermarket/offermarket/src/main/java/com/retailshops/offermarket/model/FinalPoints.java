/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.model;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author Vijay
 */
@Embeddable
public class FinalPoints implements Serializable {
    
    private String outletId;
    private String outletname;
    private String location;
    private String totalpoints;

    public String getOutletId() {
        return outletId;
    }

    public void setOutletId(String outletId) {
        this.outletId = outletId;
    }

    public String getTotalpoints() {
        return totalpoints;
    }

    public void setTotalpoints(String totalpoints) {
        this.totalpoints = totalpoints;
    }

    public String getOutletname() {
        return outletname;
    }

    public void setOutletname(String outletname) {
        this.outletname = outletname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    
    
    
}
