package com.example.ancyshaj.servicekart;

import java.io.Serializable;

/**
 * Created by Ancy Sha J on 4/17/2015.
 */
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
