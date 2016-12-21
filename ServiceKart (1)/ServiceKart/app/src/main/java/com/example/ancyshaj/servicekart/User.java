/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.ancyshaj.servicekart;

import java.io.Serializable;


/**
 *
 * @author sarathraj
 */



public class User implements Serializable {


    private String username;

    private String email;
    private String password;
    private String confirmpassword;
    private  String mobilekey;
    private String latimobile;
    private String longimobile;



    // retail user
public User() {
}


 public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }







    public String getMobilekey() {
        return mobilekey;
    }

    public void setMobilekey() {
        this.mobilekey = mobilekey;
    }

   



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
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

    public void setMobilekey(String mobilekey) {
        this.mobilekey = mobilekey;
    }
}
