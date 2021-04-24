package com;

import model.Fundingbody;
//For REST Service
//import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.*;

//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Fundingbodies")
public class FundingbodyService {
	Fundingbody fundingbodyObj = new Fundingbody();
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readSponcers()
	 {
		return fundingbodyObj.readSponcers();
	 } 
	

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertSponcer(@FormParam("sponcerNIC") String sponcerNIC,
							 @FormParam("sponcerFname") String sponcerFname,
							 @FormParam("sponcerLname") String sponcerLname,
							 @FormParam("sponcerEmail") String sponcerEmail,
							 @FormParam("sponcerPhoneNo") String sponcerPhoneNo)
							 
	{
	 String output = fundingbodyObj.insertSponcer(sponcerNIC, sponcerFname, sponcerLname,sponcerEmail,sponcerPhoneNo);
	 return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateSponcer(String sponcerData)
	{
	//Convert the input string to a JSON object
	 JsonObject sponcerObject = new JsonParser().parse(sponcerData).getAsJsonObject();
	//Read the values from the JSON object
	 String sponcerId = sponcerObject.get("sponcerId").getAsString();
	 String sponcerNIC = sponcerObject.get("sponcerNIC").getAsString();
	 String sponcerFname = sponcerObject.get("sponcerFname").getAsString();
	 String sponcerLname = sponcerObject.get("sponcerLname").getAsString();
	 String sponcerEmail = sponcerObject.get("sponcerEmail").getAsString();
	 String sponcerPhoneNo = sponcerObject.get("sponcerPhoneNo").getAsString();
	 
	 String output = fundingbodyObj.updateSponcer(sponcerId, sponcerNIC, sponcerFname, sponcerLname, sponcerEmail,sponcerPhoneNo);
	 return output;
	}
	
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteSponcer(String sponcerData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(sponcerData, "", Parser.xmlParser());

	//Read the value from the element <itemID>
	 String sponcerId = doc.select("sponcerId").text();
	 String output = fundingbodyObj.deleteSponcer(sponcerId);
	return output;
	}
	
	
	
}

	