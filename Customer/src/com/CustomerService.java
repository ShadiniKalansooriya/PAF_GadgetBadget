package com;

import model.Customer;



import model.ProjCustomer;

//For REST Service
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Customers")
public class CustomerService {
	Customer customerObj = new Customer();
	Customer FeedbackObj = new 	Customer();


	ProjCustomer ProjectObj = new 	ProjCustomer();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomers()
	 {
		return customerObj.readCustomers();
	 } 
	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(@FormParam("customerFname") String customerFname,
							 @FormParam("customerLname") String customerLname,
							 @FormParam("customerEmail") String customerEmail,
							 @FormParam("customerTpno") String customerTpno,
							 @FormParam("customerAddress") String customerAddress,
							 @FormParam("customerType") String customerType)
	{
	 String output = customerObj.insertCustomer(customerFname, customerLname, customerEmail,customerTpno,customerAddress,customerType);
	 return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customerData)
	{
	//Convert the input string to a JSON object
	 JsonObject customerObject = new JsonParser().parse(customerData).getAsJsonObject();
	//Read the values from the JSON object
	 String customerID = customerObject.get("customerID").getAsString();
	 String customerFname = customerObject.get("customerFname").getAsString();
	 String customerLname = customerObject.get("customerLname").getAsString();
	 String customerEmail = customerObject.get("customerEmail").getAsString();
	 String customerTpno = customerObject.get("customerTpno").getAsString();
	 String customerAddress = customerObject.get("customerAddress").getAsString();
	 String customerType = customerObject.get("customerType").getAsString();
	 String output = customerObj.updateCustomer(customerID, customerFname, customerLname, customerEmail, customerTpno,customerAddress,customerType);
	 return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customerData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String customerID = doc.select("customerID").text();
	 String output = customerObj.deleteCustomer(customerID);
	return output;
	}
	
	@GET
	@Path("/feed")
	@Produces(MediaType.TEXT_HTML)
	public String readFeedbacks()
	 {
		return FeedbackObj.readFeedbacks();
	 } 
	
	@POST
	@Path("/feed")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFeedback(@FormParam("customerID") String customerID,
							 @FormParam("feedBack") String feedBack)
							
	{
	 String output = FeedbackObj.insertFeedback(customerID, feedBack);
	 return output;
	}
	
	@PUT
	@Path("/feed")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFeedback(String FeedbackData)
	{
	//Convert the input string to a JSON object
	 JsonObject FeedbackObject = new JsonParser().parse(FeedbackData).getAsJsonObject();
	//Read the values from the JSON object
	 String feedbackID = FeedbackObject.get("feedbackID").getAsString();
	 String customerID = FeedbackObject.get("customerID").getAsString();
	 String feedBack = FeedbackObject.get("feedBack").getAsString();
	 
	 String output = FeedbackObj.updateFeedback(feedbackID, customerID, feedBack);
	 return output;
	}
	
	
	
	@DELETE
	@Path("/feed")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFeedback(String FeedbackData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(FeedbackData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String FeedbackID = doc.select("feedbackID").text();
	 String output = FeedbackObj.deleteFeedback(FeedbackID);
	return output;
	}
	
	@GET
	@Path("/proj")
	@Produces(MediaType.TEXT_HTML)
	public String readProjects()
	 {
		return ProjectObj.readProjects();
	 } 
	
	
}

	

