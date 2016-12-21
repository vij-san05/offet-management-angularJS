/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.util;

/**
 *
 * @author Vijay
 */
public class NearestDistance {
    
   public final static double AVERAGE_RADIUS_OF_EARTH = 6371;
public  int  calculateDistance(double userLat, double userLng,
  double venueLat, double venueLng) {

    double latDistance = Math.toRadians(userLat - venueLat);
    double lngDistance = Math.toRadians(userLng - venueLng);

    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
      + Math.cos(Math.toRadians(userLat)) * Math.cos(Math.toRadians(venueLat))
      * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));
} 
}
