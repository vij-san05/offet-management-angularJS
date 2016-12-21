/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.resource;


import com.google.gson.Gson;
import com.retailshops.offermarket.service.LogService;

import com.retailshops.offermarket.util.JsonTransformer;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
/**
 *
 * @author Vijay
 */
public class LogResource {
    
    
     private static final String API_CONTEXT = "/api/v1";
     private final LogService logservice;

    public LogResource(LogService todoService) {
        this.logservice = todoService;
        setupEndpoints();
    }

    private void setupEndpoints() {
        post(API_CONTEXT + "/logs", "application/json", (request, response) -> {
            logservice.createLog(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());

        get(API_CONTEXT + "/logs/:id", "application/json", (request, response)

                -> logservice.find(request.params(":id")), new JsonTransformer());

        get(API_CONTEXT + "/logslist", "application/json", (request, response)

                -> logservice.findAll(), new JsonTransformer());

        put(API_CONTEXT + "/logs/:id", "application/json", (request, response)

                -> logservice.update(request.params(":id"), request.body()), new JsonTransformer());
    }
    
}
