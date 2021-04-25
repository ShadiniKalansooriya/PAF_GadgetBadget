package model;
import java.sql.*;
public class Customer
{ //A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/paf_customer","root", "");
			
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	public String insertCustomer(String fname, String lname, String email, String tpno,String address,String type)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into customers(`customerID`,`customerFname`,`customerLname`,`customerEmail`,`customerTpno`,`customerAddress`,`customerType`)"+ " values (?, ?, ?, ?, ?,?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, fname);
			preparedStmt.setString(3, lname);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, tpno);
			preparedStmt.setString(6, address);
			preparedStmt.setString(7, type); 
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the item.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String readCustomers()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>customerID</th><th>customerFname</th>" +
					"<th>customerLname</th>" +
					"<th>customerEmail</th>" +
					"<th>customerTpno</th>" +
					"<th>customerAddress</th>" +
					"<th>customerType</th>" +
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from customers";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String customerID = Integer.toString(rs.getInt("customerID"));
				String customerFname = rs.getString("customerFname");
				String customerLname = rs.getString("customerLname");
				String customerEmail = rs.getString("customerEmail");
				String customerTpno = rs.getString("customerTpno");
				String customerAddress = rs.getString("customerAddress");
				String customerType = rs.getString("customerType");
								
				// Add into the html table
				output += "<tr><td>" + customerID + "</td>";
				output += "<td>" + customerFname + "</td>";
				output += "<td>" + customerLname + "</td>";
				output += "<td>" + customerEmail + "</td>";
				output += "<td>" + customerTpno + "</td>";
				output += "<td>" + customerAddress + "</td>";
				output += "<td>" + customerType + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + customerID
						+ "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the customers.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String updateCustomer(String id ,String fname, String lname, String email, String tpno,String address,String type)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE customers SET customerFname=?,customerLname=?,customerEmail=?,customerTpno=?,customerAddress=?,customerType=?WHERE customerID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			preparedStmt.setString(2, fname);
			preparedStmt.setString(3, lname);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, tpno);
			preparedStmt.setString(6, address);
			preparedStmt.setString(7, type); 
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}



	public String deleteCustomer(String customerID)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from customers where customerID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(customerID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
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
	
	
	public String readProjects()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>projectCode</th><th>projectName</th>" +
					"<th>estimatedProjecPrice</th></tr>";

			String query = "select projectCode,projectName,estimatedProjecPrice from projects";
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				
				String projectCode = rs.getString("projectCode");
				String projectName = rs.getString("projectName");
				String estimatedProjecPrice = rs.getString("estimatedProjecPrice");
				
				
								
				// Add into the html table
				output += "<tr><td>" + projectCode + "</td>";
				output += "<td>" + projectName + "</td>";
				output += "<td>" + estimatedProjecPrice + "</td>";
				
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the customers.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
} 
