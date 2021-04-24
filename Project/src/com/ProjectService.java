package com;

import model.Project;
//For REST Service
//import javax.annotation.security.RolesAllowed;
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
//import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/Projects")
public class ProjectService {
	Project projectObj = new Project();
	
	//get all projects
	@GET
	@Path("/projects")
	@Produces(MediaType.TEXT_HTML)
	public String readProjects()
	 {
		return projectObj.readProjects();
	 } 
	
	
	//get all schedules
	
	@GET
	@Path("/schedules")
	@Produces(MediaType.TEXT_HTML)
	public String readSchedules()
	 {
		return projectObj.readSchedules();
	 } 
	
	
	//Add Projects
	
	@POST
	@Path("/projects")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertProject(@FormParam("projectCode") String projectCode,
							 @FormParam("projectName") String projectName,
							 @FormParam("researcherCode") String researcherCode,
							 @FormParam("researcherName") String researcherName,
							 @FormParam("estimatedProjectPrice") String estimatedProjectPrice,
							 @FormParam("projectDesc") String projectDesc)
	{
	 String output = projectObj.insertProject(projectCode, projectName,researcherCode,researcherName,estimatedProjectPrice, projectDesc);
	 return output;
	}
	
	
	
	
	//Add Schedules
	
	
	@POST
	@Path("/schedules")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertSchedule(@FormParam("startDate") String startDate,
							 @FormParam("estimatedEndDate") String estimatedEndDate,
							 @FormParam("projectID") String projectID)
	{
	 String output = projectObj.insertSchedule(startDate,estimatedEndDate,projectID);
	 return output;
	}
	
	
	//Update Types
	
	@PUT
	@Path("/projects")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateProject(String projectData)
	{
	//Convert the input string to a JSON object
	 JsonObject projectObject = new JsonParser().parse(projectData).getAsJsonObject();
	//Read the values from the JSON object
	 String projectID = projectObject.get("projectID").getAsString();
	 String projectCode = projectObject.get("projectCode").getAsString();
	 String projectName = projectObject.get("projectName").getAsString();
	 String researcherCode = projectObject.get("researcherCode").getAsString();
	 String researcherName = projectObject.get("researcherName").getAsString();
	 String estimatedProjectPrice = projectObject.get("estimatedProjectPrice").getAsString();
	 String projectDesc = projectObject.get("projectDesc").getAsString();
	 String output = projectObj.updateProject(projectID, projectCode,projectName,researcherCode,researcherName,estimatedProjectPrice, projectDesc);
	return output;
	}
	
	//Update Schedules
	
	@PUT
	@Path("/schedules")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateSchedule(String scheduleData)
	{
	//Convert the input string to a JSON object
	 JsonObject scheduleObject = new JsonParser().parse(scheduleData).getAsJsonObject();
	//Read the values from the JSON object
	 String scheduleID = scheduleObject.get("scheduleID").getAsString();
	 String startDate = scheduleObject.get("startDate").getAsString();
	 String estimatedEndDate = scheduleObject.get("estimatedEndDate").getAsString();
	 String projectID = scheduleObject.get("projectID").getAsString();
	
	 String output = projectObj.updateSchedule(scheduleID, startDate,estimatedEndDate,projectID);
	return output;
	}
	
	
	
	//Delete Projects
	
	@DELETE
	@Path("/projects")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteProject(String projectData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(projectData, "", Parser.xmlParser());

	//Read the value from the element <projectID>
	 String projectID = doc.select("projectID").text();
	 String output = projectObj.deleteProject(projectID);
	return output;
	}
	
	
	
	//Delete Types

	@DELETE
	@Path("/schedules")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteSchedule(String scheduleData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(scheduleData, "", Parser.xmlParser());

	//Read the value from the element <projectID>
	 String scheduleID = doc.select("scheduleID").text();
	 String output = projectObj.deleteSchedule(scheduleID);
	return output;
	}
	
}
