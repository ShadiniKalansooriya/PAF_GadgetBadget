package model;
import java.sql.*;
public class Feedback
{ //A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_Feedback","root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	public String insertFeedback(String customerID, String feedBack)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into feedbacks(`feedbackID`,`customerID`,`feedBack`)"+ " values (?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerID);
			preparedStmt.setString(3, feedBack);
			 
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the feedback.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String readFeedbacks()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>FeedbackID</th><th>CustomerID</th>" +
					"<th>Feedback</th>" +
					
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from feedbacks";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String feedbackID = Integer.toString(rs.getInt("feedbackID"));
				String customerID = rs.getString("customerID");
				String feedBack = rs.getString("feedBack");
				
				// Add into the html table
				output += "<tr><td>" + feedbackID + "</td>";
				output += "<td>" + customerID + "</td>";
				output += "<td>" + feedBack + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + feedbackID
						+ "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the Feedbacks.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String updateFeedback(String feedbackID,String customerID, String feedBack)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE feedbacks SET customerID=?,feedBack=?WHERE feedbackID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(feedbackID));
			preparedStmt.setString(2, customerID);
			preparedStmt.setString(3, feedBack);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the Feedback.";
			System.err.println(e.getMessage());
		}
		return output;
	}



	public String deleteFeedback(String feedbackID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from feedbacks where feedbackID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(feedbackID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the Feedback.";
			System.err.println(e.getMessage());
		}
		return output;
	}
} 
