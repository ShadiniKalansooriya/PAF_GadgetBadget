package com;

import model.Customer;
//For REST Service
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readItems()
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
	@Path("/a")
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
	
}
