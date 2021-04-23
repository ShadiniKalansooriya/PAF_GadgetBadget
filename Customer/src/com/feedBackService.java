//package com;
//
////import model.Feedback;
//import model.Customer;
////For REST Service
//import javax.annotation.security.RolesAllowed;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.DELETE;
//import javax.ws.rs.FormParam;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.*;
//import javax.ws.rs.core.MediaType;
////For JSON
//import com.google.gson.*;
////For XML
//import org.jsoup.*;
//import org.jsoup.parser.*;
//import org.jsoup.nodes.Document;
//
//@Path("/Feedback")
//public class feedBackService {
//	Customer FeedbackObj = new 	Customer();
//	
//	@GET
//	@Path("/")
//	@Produces(MediaType.TEXT_HTML)
//	public String readFeedbacks()
//	 {
//		return FeedbackObj.readFeedbacks();
//	 } 
//	
//}
//	@POST
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String insertFeedback(@FormParam("customerID") String customerID,
//							 @FormParam("feedBack") String feedBack)
//							
//	{
//	 String output = FeedbackObj.insertFeedback(customerID, feedBack);
//	 return output;
//	}
//	
//	@PUT
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String updateFeedback(String FeedbackData)
//	{
//	//Convert the input string to a JSON object
//	 JsonObject FeedbackObject = new JsonParser().parse(FeedbackData).getAsJsonObject();
//	//Read the values from the JSON object
//	 String feedbackID = FeedbackObject.get("feedbackID").getAsString();
//	 String customerID = FeedbackObject.get("customerID").getAsString();
//	 String feedBack = FeedbackObject.get("feedBack").getAsString();
//	 
//	 String output = FeedbackObj.updateFeedback(feedbackID, customerID, feedBack);
//	 return output;
//	}
//	
//	
//	
//	@DELETE
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_XML)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String deleteFeedback(String FeedbackData)
//	{
//	//Convert the input string to an XML document
//	 Document doc = Jsoup.parse(FeedbackData, "", Parser.xmlParser());
//
//	//Read the value from the element <itemID>
//	 String FeedbackID = doc.select("feedbackID").text();
//	 String output = FeedbackObj.deleteFeedback(FeedbackID);
//	return output;
//	}
//	
//}
