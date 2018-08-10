package com.bleeh.database;
import java.sql.*;
public class Database {
	Connection con=null;//for connection
	    public ResultSet scores()
		{
	        String query = "SELECT * from scores order by Score desc;";     
	        
	        Statement st = null;
			ResultSet rs = null;
			System.out.println(query);
	        try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bleeh","root","");
				st = con.createStatement();
				rs = st.executeQuery(query);
			//	con.close();
			}
	        catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
	        }
	        
	       
	        return rs;
	        
	    }
	    public void con() {
	    	try {
				con.close();
			} catch (SQLException e) {
			
				e.printStackTrace();
			}
	    }
	    public void insert(int x)
	    {
	    	String query = "INSERT INTO `scores` (`Date`, `Score`) VALUES (CURRENT_DATE(), "+x+");";
	    	

     
	        Connection con=null;
	        Statement st = null;
			System.out.println(query);
	        try
			{
				Class.forName("com.mysql.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bleeh","root","");
				st = con.createStatement();
				st.executeUpdate(query);
					
			}
	        catch(Exception ex)
			{
				System.out.println("Exception : " +ex.getMessage());
	        }
	        
	    }
	}



