package com.payment;

import model.Payment;
//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON
import com.google.gson.*;

//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Payment")
public class PaymentService
{
	Payment itemObj = new Payment();
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayments()
	{
		return itemObj.readPayments();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(@FormParam("paymentCode") String paymentCode, 
			@FormParam("customerID") String customerID,
			@FormParam("project") String project,
			@FormParam("paymentAmount") String paymentAmount, 
			@FormParam("paymentDate") String paymentDate)
	{
		String output = itemObj.insertPayment(paymentCode, customerID, project, paymentAmount, paymentDate);
		return output;
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String itemData)
	{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		
		//Read the values from the JSON object
		String paymentID = itemObject.get("paymentID").getAsString();
		String paymentCode = itemObject.get("paymentCode").getAsString();
		String customerID = itemObject.get("customerID").getAsString();
		String project = itemObject.get("project").getAsString();
		String paymentAmount = itemObject.get("paymentAmount").getAsString();
		String paymentDate = itemObject.get("paymentDate").getAsString();
		
		String output = itemObj.updatePayment(paymentID, paymentCode, customerID, project, paymentAmount, paymentDate);
		
		return output;
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String itemData)
	{
		//Convert the input string to an XML document
		Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
		
		//Read the value from the element <itemID>
		String paymentID = doc.select("paymentID").text();
		String output = itemObj.deletePayment(paymentID);
		
		return output;
	}
}
