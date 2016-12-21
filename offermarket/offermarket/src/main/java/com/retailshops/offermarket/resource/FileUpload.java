/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.retailshops.offermarket.resource;

import com.oreilly.servlet.MultipartRequest;



import static spark.Spark.post;


import java.io.File;

import static spark.Spark.halt;
/**
 *
 * @author Vijay
 */
public class FileUpload {
    
    
      private static final String API_CONTEXT = "/api/v1";
      // location to store file uploaded
	

    public FileUpload() {
       
        setupEndpoints();
    }

    private void setupEndpoints() {
  
      
      post(API_CONTEXT+ "/uploads", (request, response) -> {
            
          try{
             final File upload = new File("/home/sarathraj/NetBeansProjects/offermarket/target/classes/public/uploads");
            if (!upload.exists() && !upload.mkdirs()) {
                throw new RuntimeException("Failed to create directory " + upload.getAbsolutePath());
            }
            
            System.out.println("uploading works");
            // this dumps all files contained in the multipart request to target directory.
            final MultipartRequest req = new MultipartRequest(request.raw(), upload.getAbsolutePath());
           // halt(200);
            return null;
          }catch(Exception e)
          {
              e.printStackTrace();
          }
            response.status(201); // 201 Created
            return 0;
        }); 
      
      
}
}
