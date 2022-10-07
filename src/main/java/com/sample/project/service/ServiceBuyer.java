package com.sample.project.service;

import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sample.project.ExceptionResponse.BuyerAlreadyExists;
import com.sample.project.ExceptionResponse.BuyerNotFoundException;

import com.sample.project.entity.Buyer;
import com.sample.project.repository.BuyerRepository;

import static com.sample.project.constants.ExceptionMSG.*;

@Service
public class ServiceBuyer {
	@Autowired
	private BuyerRepository repo;
	//List allbuyers
	
	public List<Buyer> allBuyers()
	{
		List<Buyer> buyerList=repo.findAll();
		return buyerList;
	}
	//List specific buyer by contact_no
	public Buyer getbyNumber(String contactno)
	{
		 Buyer buyerByNo;
		 try {
			 buyerByNo=repo.findById(contactno).get();
		 }
		 catch(Exception e)
		 {
			 throw new BuyerNotFoundException(BUYER_NOT_FOUND_BY_NO+ " " + contactno); 
		 }
		 System.out.println("*************"+buyerByNo);
		 if(buyerByNo!=null)
			System.out.println(buyerByNo);
         
      	  return buyerByNo;
		
	}
	//Adding buyer
	public Buyer createBuyer(Buyer buyer) 
	{
		
		Buyer buyerAdd=null; 
		String contactno=buyer.getContactno();
		Buyer existing_buyer=null;
		try {
			existing_buyer=repo.findById(contactno).get();
		}
		catch(NoSuchElementException e)
		{
			buyerAdd=repo.save(buyer);
		}
		
		if(existing_buyer!=null)
		{
			throw new BuyerAlreadyExists(EXISTING_BUYER+" "+contactno);
		
		}
		
		
		return  buyerAdd;
	}
	//Update buyer
	public Buyer updateBuyer(Buyer buyer)
	
	{   Buyer buyerUpd=null;
		Buyer existing_buyer=null;
		try {
			existing_buyer=repo.findById(buyer.getContactno()).get();
		}
		catch(NoSuchElementException e)
		{
			throw new BuyerNotFoundException(BUYER_NOT_FOUND_BY_NO+ " " +buyer.getContactno());
		}
	    if(existing_buyer!=null)
	    {
	    	buyerUpd=repo.save(buyer);
	    	
	    }
	  	
		return buyerUpd;
	}
	 
	//Delete buyer
	public Buyer deleteBuyer(String contactno)
	{  
		
		Buyer existing_buyer=null;
		//String contactno=buyer.getContactno();
		
		try {
			existing_buyer=repo.findById(contactno).get();
		}	
		catch(NoSuchElementException e)
		{
			throw new BuyerNotFoundException(BUYER_NOT_FOUND_BY_NO+ " " +contactno);
		}
		
		if(existing_buyer!=null)
		{
		
		 repo.deleteById(contactno);
	

		}
		

		return existing_buyer ;
	}
	}
	
	
	

