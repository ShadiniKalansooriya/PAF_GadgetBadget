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
	
	//___________________________________________Sponcers Management__________________________________________________________
	
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
	
	//___________________________________________Funds Management__________________________________________________________
	
	public String insertFund(String researchpaper, String sponcernic, String fundamount, String description)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for inserting."; }
			// create a prepared statement
			String query = " insert into funds(`fundId`,`researchPaper`,`sponcerNic`,`fundAmount`,`description`)"+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, researchpaper);
			preparedStmt.setString(3, sponcernic);
			preparedStmt.setString(4, fundamount);
			preparedStmt.setString(5, description);			
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
	public String readFunds()
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for reading."; }
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>fundId</th><th>researchPaper</th>" +
					"<th>sponcerNic</th>" +
					"<th>fundAmount</th>" +
					"<th>description</th>" +
					"<th>Update</th><th>Remove</th></tr>";

			String query = "select * from funds";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next())
			{
				String fundId = Integer.toString(rs.getInt("fundId"));
				String researchPaper = rs.getString("researchPaper");
				String sponcerNic = rs.getString("sponcerNic");
				String fundAmount = rs.getString("fundAmount");
				String description = rs.getString("description");
				
				// Add into the html table
				output += "<tr><td>" + fundId + "</td>";
				output += "<td>" + researchPaper + "</td>";
				output += "<td>" + sponcerNic + "</td>";
				output += "<td>" + fundAmount + "</td>";
				output += "<td>" + description + "</td>";
				
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btn btn-secondary'></td>"
						+ "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove'class='btn btn-danger'>"
						+ "<input name='itemID' type='hidden' value='" + fundId
						+ "'>" + "</form></td></tr>";
			}
			con.close();
			// Complete the html table
			output += "</table>";
		}
		catch (Exception e)
		{
			output = "Error while reading the funds.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String updateFund(String id ,String researchpaper, String sponcernic, String fundamount, String description)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for updating."; }
			// create a prepared statement
			String query = "UPDATE funds SET researchPaper=?,sponcerNic=?,fundAmount=?,description=?WHERE fundId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(id));
			preparedStmt.setString(2, researchpaper);
			preparedStmt.setString(3, sponcernic);
			preparedStmt.setString(4, fundamount);
			preparedStmt.setString(5, description);
			
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Updated successfully";
		}
		catch (Exception e)
		{
			output = "Error while updating the fund.";
			System.err.println(e.getMessage());
		}
		return output;
	}



	public String deleteFund(String fundId)
	{
		String output = "";
		try
		{
			Connection con = connect();
			if (con == null)
			{return "Error while connecting to the database for deleting."; }
			// create a prepared statement
			String query = "delete from funds where fundId=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(fundId));
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the fund.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
} 
