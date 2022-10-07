package com.sample.project;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.project.entity.*;
import com.sample.project.ExceptionResponse.ExceptionResponse;
import static com.sample.project.constants.Buyer_URI.*;
import static com.sample.project.constants.ExceptionMSG.*;
import static com.sample.project.constants.ReturnMSG.*;
@SpringBootTest(classes = ProjectApplication.class)
@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@TestMethodOrder(OrderAnnotation.class)
public class BuyerControllerTest
{
	   protected MockMvc mockMvc;
	   private static Buyer global_buyer_obj;
	   
	   @Autowired
	   WebApplicationContext webApplicationContext;
	   protected void setUp() {
	      mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	   }
	   protected String mapToJson(Object obj) throws JsonProcessingException {
		      ObjectMapper objectMapper = new ObjectMapper();
		      return objectMapper.writeValueAsString(obj);
		   }
	 /*  protected <Buyer> Buyer mapFromJson(String json, Class<Buyer> obj)
			      throws JsonParseException, JsonMappingException, IOException {
			      
			      ObjectMapper objectMapper = new ObjectMapper();
			      return objectMapper.readValue(json,obj);
			   }*/
	   protected <Buyer> Buyer mapFromJson(String json, Class<Buyer> clazz)
			      throws JsonParseException, JsonMappingException, IOException {
			      
			      ObjectMapper objectMapper = new ObjectMapper();
			      return objectMapper.readValue(json, clazz);
			   }
	
	   @Test
	   @Order(1)
	   public void getBuyerList() throws Exception {
		   setUp();
	      String uri = BUYER_DETAILS;
	  	//mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		int status = result.getResponse().getStatus();
		assertEquals(200, status);
	    String content = result.getResponse().getContentAsString();
	    Buyer[] buyerlist = mapFromJson(content, Buyer[].class);
	    System.out.println("BuyerList*****"+content);
	    assertTrue(buyerlist.length > 0);
	   /* List<Buyer> actual = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<Buyer>>() {});
			
	    System.out.println("******** list from Buyer ******** \n" + actual);
	     assertTrue(actual.size() > 0);*/
	   }
	  @Test
	  @Order(2)
	   public void createBuyer() throws Exception {
		  setUp();
	      String uri = BUYER_DETAILS_ADD;
	      Buyer buyer=new Buyer();
	       buyer.setName("Ramya");
	       buyer.setContactno("8870223492");
	       buyer.setEmail("ramya@gmail.com");
	       buyer.setAddress("Cbe");
	       buyer.setGender("Female");
	      String input =mapToJson(buyer);
	      RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(input);
	      MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	      System.out.println("RESULT in json:" +result.getResponse().getContentAsString());
	      Buyer actual =mapFromJson(result.getResponse().getContentAsString(),Buyer.class);
	      System.out.println("ACTUAL in javaclass:" + actual);
	      global_buyer_obj=actual;
	      System.out.println("Buyer buyerobj global-->" + global_buyer_obj);
	      int status = result.getResponse().getStatus();
	      System.out.println("Status--->" +status);
	      assertEquals(201, status);
	      String content =result .getResponse().getContentAsString();
	      System.out.println("content-->" +content);
	      System.out.println("actual.getContactno()------>"+actual.getContactno());
	      System.out.println("buyerobj.getContactno()=----->"+buyer.getContactno());
	      assertEquals(actual.getContactno(),buyer.getContactno()); 
	   }
	  @Test
	  @Order(3)
	  public void buyerAlreadyExists() throws Exception
	  {
		       setUp();       
	           String uri = BUYER_DETAILS_ADD;
	           System.out.println("****************Buyer already Exists**********");
               String input=mapToJson(global_buyer_obj);
	           RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(input);
	           MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	           int status = result.getResponse().getStatus();
	           System.out.println("*****status***"+status);
	           assertEquals(400, status);
	           ExceptionResponse actual =mapFromJson(result.getResponse().getContentAsString(),ExceptionResponse.class);
	           System.out.println("*******Actual in alreadyexists****"+actual);
	           assertNotNull(actual);
	           String expected = EXISTING_BUYER+" "+global_buyer_obj.getContactno();
	           System.out.println("Buyer is already exist with the given contact no 8870223497------>"+expected);
	           assertEquals(actual.getMessage(),expected);
	    }
	    @Test
		@Order(4)
	    public void getByContactno() throws Exception {
		setUp();
	    String uri =  Buyer_details + global_buyer_obj.getContactno();
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE);
	    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	    int status = result.getResponse().getStatus();
	    assertEquals(200, status);
	    Buyer actual = mapFromJson(result.getResponse().getContentAsString(),Buyer.class);
	    String content = result.getResponse().getContentAsString();
	    System.out.println("Content in getByContactno***************" +content);
	    System.out.println("actual.getContactno()" +actual.getContactno());
	     assertEquals(actual.getContactno(), global_buyer_obj.getContactno());
		}
	    @Test
	    @Order(5)
		public void updateBuyerdetails() throws Exception {
		  setUp();
		  String uri =  Buyer_details + global_buyer_obj.getContactno();
		  global_buyer_obj.setAddress("Chennai"); 
		  String inputJson = mapToJson( global_buyer_obj);
		   RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri).accept(MediaType.APPLICATION_JSON_VALUE).content(inputJson).contentType(MediaType.APPLICATION_JSON_VALUE);
		   MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		   int status = result.getResponse().getStatus();
		   assertEquals(200, status);
		   Buyer actual = mapFromJson(result.getResponse().getContentAsString(), Buyer.class);
		   String content =result .getResponse().getContentAsString();
		   System.out.println("content in update buyer details---->" +content);
		   assertEquals(actual.getAddress(), global_buyer_obj.getAddress());
	  }

	  @Test
	  @Order(6)
	  public void deleteBuyer() throws Exception {
	       setUp();
	       String uri = Buyer_details + global_buyer_obj.getContactno();
	       //String inputJson = mapToJson( global_buyer_obj);
	       MvcResult mvcResult =   mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
	       int status = mvcResult.getResponse().getStatus();
	       assertEquals(200, status);
	       String content = mvcResult.getResponse().getContentAsString();
	       System.out.println("Deleting buyer---->" +content);
	       Buyer actual = mapFromJson(mvcResult.getResponse().getContentAsString(), Buyer.class);
	       assertEquals(actual.getContactno(), global_buyer_obj.getContactno());

	    }
	    @Test
	    @Order(7)
	    public void updateNotExistingBuyer() throws Exception {
	           setUp();
	           String uri= Buyer_details+ global_buyer_obj.getContactno();
	           global_buyer_obj.setName("Ramya R");
	           global_buyer_obj.setAddress("CBE");
	           String input = mapToJson( global_buyer_obj);
	           RequestBuilder requestBuilder = MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON_VALUE).content(input);
	           MvcResult result = mockMvc.perform(requestBuilder).andReturn();
	           System.out.println("UPDATE---->result"+result);
	           int status = result.getResponse().getStatus();
	           System.out.println("STATUS-->"+status);
	           assertEquals(404,status);
	           ExceptionResponse actual =mapFromJson(result.getResponse().getContentAsString(), ExceptionResponse.class);
	           assertNotNull(actual);
	           String expected =BUYER_NOT_FOUND_BY_NO+" "+global_buyer_obj.getContactno();
	           System.out.println("EXPECTED--->"+expected);
	           assertEquals(actual.getMessage(),expected);

	    }
	    @Test
	    @Order(8)
	    public void getNotExistingBuyerbyNo() throws Exception
	    {
	    	setUp();
		    String uri =  Buyer_details + global_buyer_obj.getContactno();
		    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE);
		    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		    int status = result.getResponse().getStatus();
		    assertEquals(404, status);
		    ExceptionResponse actual =mapFromJson(result.getResponse().getContentAsString(), ExceptionResponse.class);
	        assertNotNull(actual);
	        String expected =BUYER_NOT_FOUND_BY_NO+" "+global_buyer_obj.getContactno();
	        System.out.println("EXPECTED--->"+expected);
	        assertEquals(actual.getMessage(),expected);
	    }
	    @Test
	    @Order(9)
	    public void deleteNotExistingBuyer() throws Exception
	    {
	    	setUp();
	    	String uri =  Buyer_details + global_buyer_obj.getContactno();
		    MvcResult mvcResult =mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		    int status = mvcResult.getResponse().getStatus();
		    assertEquals(404, status);
		    ExceptionResponse actual =mapFromJson(mvcResult.getResponse().getContentAsString(), ExceptionResponse.class);
	        assertNotNull(actual);
	        String expected =BUYER_NOT_FOUND_BY_NO+" "+global_buyer_obj.getContactno();
	        System.out.println("EXPECTED--->"+expected);
	        assertEquals(actual.getMessage(),expected);
	    	
	    }
}
	  

