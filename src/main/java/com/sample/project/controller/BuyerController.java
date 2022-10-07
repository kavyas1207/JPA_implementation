package com.sample.project.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.project.entity.Buyer;
import com.sample.project.service.ServiceBuyer;
import static com.sample.project.constants.Buyer_URI.*;
@RestController
public class BuyerController {
	 @Autowired
     private ServiceBuyer servicebuyer;
    
     public BuyerController(ServiceBuyer servicebuyer)
     {
    	 this.servicebuyer=servicebuyer;
     }
          
          @GetMapping(BUYER_DETAILS)
          public List<Buyer> list()
            {   
        	    List<Buyer> buyerlist=servicebuyer.allBuyers();
            	return buyerlist;
            }
          @GetMapping(BUYER_DETAILS_BY_NO)
          public Buyer getbyNumber(@PathVariable String contactno)
          {     
        	  Buyer buyerByNo=servicebuyer.getbyNumber(contactno);
        	  return buyerByNo;
          }

          @PostMapping(BUYER_DETAILS_ADD)
    	   public ResponseEntity<Buyer> addBuyer(@RequestBody Buyer buyer)
    	   {   
        	  Buyer addBuyer=servicebuyer.createBuyer(buyer);
    		  return new ResponseEntity<Buyer>(addBuyer,HttpStatus.CREATED);
    	   }
          @PutMapping(BUYER_DETAILS_UPDATE)
          public ResponseEntity<Buyer> updateBuyer(@RequestBody Buyer buyer)
    	   {   
        	   Buyer updateBuyer=servicebuyer.updateBuyer(buyer);
        	   
    		   return new ResponseEntity<Buyer>(updateBuyer,HttpStatus.OK);
    	   }
          @DeleteMapping(BUYER_DETAILS_DELETE)
          public Buyer deleteBuyer(@PathVariable String contactno)
          { 
              Buyer deleteBuyer=servicebuyer.deleteBuyer(contactno);
        	  return deleteBuyer;
          }
}      
