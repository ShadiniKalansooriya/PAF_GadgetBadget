package model;

import java.sql.*;

public class Payment
{	//A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/payment", "root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		
		return con;
	}
	
	public String insertPayment(String code, String cID, String name, String amount, String date)
	{
		String output = "";
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			
			// create a prepared statement
			String query = " insert into payment (`paymentID`,`paymentCode`,`customerID`,`project`,`paymentAmount`,`paymentDate`)"
			+ " values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, code);
			preparedStmt.setString(3, cID);
			preparedStmt.setString(4, name);
			preparedStmt.setDouble(5, Double.parseDouble(amount));
			preparedStmt.setString(6, date);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting the Payment.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String readPayments()
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Payment Code</th><th>Customer ID</th>" +
			"<th>Project Name</th>" +
			"<th>Amount</th>" +
			"<th>Date</th>" +
			"<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from payment";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			// iterate through the rows in the result set
			while (rs.next())
			{
				String paymentID = Integer.toString(rs.getInt("paymentID"));
				String paymentCode = rs.getString("paymentCode");
				String customerID = rs.getString("customerID");
				String project = rs.getString("project");
				String paymentAmount = Double.toString(rs.getDouble("paymentAmount"));
				String paymentDate = rs.getString("paymentDate");
				
				// Add into the html table
				output += "<tr><td>" + paymentCode + "</td>";
				output += "<td>" + customerID + "</td>";
				output += "<td>" + project + "</td>";
				output += "<td>" + paymentAmount + "</td>";
				output += "<td>" + paymentDate + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						+ "<input name='paymentID' type='hidden' value='" + paymentID
						+ "'>" + "</form></td></tr>";
			}
			
			con.close();
			
			// Complete the html table
			output += "</table>";
			
		}
		catch (Exception e)
		{
			output = "Error while reading the Payment.";
			System.err.println(e.getMessage());
			
		}
		
		return output;
		
	}
	
	public String updatePayment(String pID, String code, String cID, String name, String amount, String date)
	
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			
			// create a prepared statement
			String query = "UPDATE payment SET paymentCode=?,customerID=?,project=?,paymentAmount=?,paymentDate=? WHERE paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setString(1, code);
			preparedStmt.setString(2, cID);
			preparedStmt.setString(3, name);
			preparedStmt.setDouble(4, Double.parseDouble(amount));
			preparedStmt.setString(5, date);
			preparedStmt.setInt(6, Integer.parseInt(pID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the Payment.";
			System.err.println(e.getMessage());
		}
		
		return output;
	}
	
	public String deletePayment(String paymentID)
	{
		String output = "";
		
		try
		{
			Connection con = connect();
			
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			
			// create a prepared statement
			String query = "delete from payment where paymentID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(paymentID));
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the Payment.";
			System.err.println(e.getMessage());
		}
		return output;
		
	}
}
