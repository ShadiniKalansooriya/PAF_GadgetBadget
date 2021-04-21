package com.gadgetBadget.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.gadgetBadget.model.Researcher;
import com.gadgetBadget.util.DBConnection; 

public class ImpReasercher implements IResearcher {
		
		//SQL connection variables
		private static Connection connection;
		private static Statement statement ;
		
	@Override
	public boolean addResearcher(Researcher researcher) {
		
		boolean isSuccess = false;
		
		try {
			 
			//create db connection
	       	connection = DBConnection.getConnection();
	        statement = connection.createStatement();
	        
	         //sql query
	        String sql = "INSERT INTO `reseachers` ( `name`, `projectType`, `contactNumber`, `payment`)  VALUES"
	        		+ " ( '"+researcher.getName()+"', '"+researcher.getProjectType()+"','"+researcher.getContact()+"','"+researcher.getPayment()+"')";
	    		   
	       //execute query
	         int result = statement.executeUpdate(sql);
	   		 
	   		 if(result > 0) {
	   			 isSuccess = true;
	   		 }else {
	   			 isSuccess = false;
	   		 }
	   		 
	       }catch(SQLException  e) {
	    	   e.printStackTrace();
	       } finally {
				//close connection
				try {
					if(statement != null) {
						statement.close();
					}
					
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException |NullPointerException e) {
					e.printStackTrace();
				}
			}
	    //return output
		return isSuccess;
	}

	@Override
	public boolean updateResearcher(Researcher researcher) {
		//create return statement
		boolean isSuccess = false;
		
		//set object value to local variables
        
  	  try {
  	   
	  		  //create db connection
	         connection = DBConnection.getConnection();
	         statement =  connection.createStatement();
	          //create sql query
	          String sql = "UPDATE `reseachers` SET `name` = '"+researcher.getName()+"', `projectType` = '"+researcher.getProjectType()+"', `contactNumber` = '"+researcher.getContact()+"', `payment` = '"+researcher.getPayment()+"' WHERE (`id` = '"+researcher.getId()+"')";
	         
	          int result =  statement.executeUpdate(sql);
	          
	          //if output have any value set return value true else set false
	          if(result>0) {
	              isSuccess = true;	
	          }
	          else {
	          	isSuccess = false;
	          }
          
	  	}catch(SQLException | NullPointerException  e) {  
	        e.printStackTrace();    
		} finally {
			//close connection
			try {
				if(statement != null) {
					statement.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			} catch ( SQLException e) {
				e.printStackTrace();
			}
		}
		//return isSuccess as true or false
	      return isSuccess;
	}

	@Override
	public boolean deleteResearcher(int ResearcherId) {
		boolean isSuccess = false ;
		try {
    		//create db connection
    	    connection = DBConnection.getConnection();
            statement =  connection.createStatement();
            //sql query statement
            String sql = "DELETE FROM reseachers WHERE (`id` = '"+ResearcherId+"')";
            //execute delete query
            int result =  statement.executeUpdate(sql);
            //if query execute success return true and if not return false	
            if (result > 0) {
            	isSuccess = true;
            }
            else {
            	isSuccess = false;
            }
            
    	}catch(Exception e) {
    		e.printStackTrace();
    	}finally {
			//close connection
			try {
				if(statement != null) {
					statement.close();
				}
				
				if (connection != null) {
					connection.close();
				}
			} catch ( SQLException e) {
				e.printStackTrace();
			}
    	}
    	
	return isSuccess;
	}

	@Override
	public ArrayList<Researcher> getReacherList() {

		ArrayList<Researcher> list= new ArrayList<Researcher>();
		        
		        try {
		        	
		            connection = DBConnection.getConnection();
		            statement =  connection.createStatement();
		            
		            String sql = "SELECT * FROM `reseachers`";
		            PreparedStatement statement =  connection.prepareStatement(sql);
		       
		            ResultSet result = statement.executeQuery();
		
		            while(result.next()){
		            	
		            	Researcher researcher = new Researcher();
		            	
		            	researcher.setId(result.getInt("id"));
		            	researcher.setName(result.getString("name"));
		            	researcher.setProjectType(result.getString("projectType"));
		            	researcher.setContact(result.getInt("contactNumber"));
		            	researcher.setPayment(result.getInt("payment"));
		                list.add(researcher);
		            }
		         
		        }catch(Exception e) {
		        	e.printStackTrace();
		        } finally {
					
					try {
						
						if (connection != null) {
							connection.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
		        }
		        return list;
	}

	@Override
	public Researcher getResearcher(int id) {
		Researcher researcher = new Researcher();
        
        try {
        	
            connection = DBConnection.getConnection();
            statement =  connection.createStatement();
            
            String sql = "SELECT * FROM `reseachers`";
            PreparedStatement statement =  connection.prepareStatement(sql);
       
            ResultSet result = statement.executeQuery();

            while(result.next()){
            	
            	researcher.setId(result.getInt("id"));
            	researcher.setName(result.getString("name"));
            	researcher.setProjectType(result.getString("projectType"));
            	researcher.setContact(result.getInt("contactNumber"));
            	researcher.setPayment(result.getInt("payment"));
            }
         
        }catch(Exception e) {
        	e.printStackTrace();
        } finally {
			
			try {
				
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
        return researcher;
	}

}
