package model;
import java.sql.*;
public class ProjCustomer
{ //A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/projects","root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	
	public String readProjects()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Project Code</th><th>Project Name</th>" +
					"<th>Researcher Code</th>" +
					"<th>Researcher Name</th>" +
					"<th>Estimated Project Price</th>" +
					"<th>Project Description</th>" +
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from projects";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String projectID = Integer.toString(rs.getInt("projectID"));
				String projectCode = rs.getString("projectCode");
				String projectName = rs.getString("projectName");
				String researcherCode = rs.getString("researcherCode");
				String researcherName = rs.getString("researcherName");
				String estimatedProjectPrice = Double.toString(rs.getDouble("estimatedProjectPrice"));
				String projectDesc = rs.getString("projectDesc");
				// Add into the html table
				output += "<tr><td>" + projectCode + "</td>";
				output += "<td>" + projectName + "</td>";
				output += "<td>" + researcherCode + "</td>";
				output += "<td>" + researcherName + "</td>";
				output += "<td>" + estimatedProjectPrice + "</td>";
				output += "<td>" + projectDesc + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='projects.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='projectID' type='hidden' value='" + projectID
						+ "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the projects.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
} 
