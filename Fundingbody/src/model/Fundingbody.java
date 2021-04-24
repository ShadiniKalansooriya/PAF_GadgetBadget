package model;
import java.sql.*;
public class Fundingbody
{ //A common method to connect to the DB
	private Connection connect()
	{
		Connection con = null;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");

			//Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/fundingbody","root", "");
		}
		catch (Exception e)
		{e.printStackTrace();}
		return con;
	}
	public String insertSponcer(String nic, String fname, String lname, String email,String phoneno)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into sponcers(`sponcerId`,`sponcerNIC`,`sponcerFname`,`sponcerLname`,`sponcerEmail`,`sponcerPhoneNo`)"+ " values (?, ?, ?, ?, ?,?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, nic);
			preparedStmt.setString(3, fname);
			preparedStmt.setString(4, lname);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, phoneno);			
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		}
		catch (Exception e)
		{
			output = "Error while inserting.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String readSponcers()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>sponcerId</th><th>sponcerNIC</th>" +
					"<th>sponcerFname</th>" +
					"<th>sponcerLname</th>" +
					"<th>sponcerEmail</th>" +
					"<th>sponcerPhoneNo</th>" +
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from sponcers";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String sponcerId = Integer.toString(rs.getInt("sponcerId"));
				String sponcerNIC = rs.getString("sponcerNIC");
				String sponcerFname = rs.getString("sponcerFname");
				String sponcerLname = rs.getString("sponcerLname");
				String sponcerEmail = rs.getString("sponcerEmail");
				String sponcerPhoneNo = rs.getString("sponcerPhoneNo");
				
				// Add into the html table
				output += "<tr><td>" + sponcerId + "</td>";
				output += "<td>" + sponcerNIC + "</td>";
				output += "<td>" + sponcerFname + "</td>";
				output += "<td>" + sponcerLname + "</td>";
				output += "<td>" + sponcerEmail + "</td>";
				output += "<td>" + sponcerPhoneNo + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + sponcerId
						+ "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the sponcers.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String updateSponcer(String id ,String nic, String fname, String lname, String email,String phoneno)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE sponcers SET sponcerNIC=?,sponcerFname=?,sponcerLname=?,sponcerEmail=?,sponcerPhoneNo=?WHERE sponcerId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			preparedStmt.setString(2, nic);
			preparedStmt.setString(3, fname);
			preparedStmt.setString(4, lname);
			preparedStmt.setString(5, email);
			preparedStmt.setString(6, phoneno);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the sponcer.";
			System.err.println(e.getMessage());
		}
		return output;
	}



	public String deleteSponcer(String sponcerId)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from sponcers where sponcerId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(sponcerId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the sponcer.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
} 
