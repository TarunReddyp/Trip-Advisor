import java.sql.*;
import java.util.Scanner;

public class Attraction {
	 static String Userid= Account.getUserid();
	 final static String url = "jdbc:mysql://cobmysql.uhcl.edu/pareddyt8985?useSSL=false";
	 private static String Attraction_name="";
	 private static String tag="";
	 private static String city="";
	 private static String desc="";
	 private static Double rate;
	 public static void home(String s)
	 {
		 Scanner input = new Scanner(System.in);
		 Connection conn =null;
		 Statement statement=null;
		 ResultSet rs= null;
		 try 
		 {
			 conn= DriverManager.getConnection(url,"pareddyt8985","2288985");
			 statement = conn.createStatement();
		     rs = statement.executeQuery("SELECT * from attraction where AttractionName = '"+s+"'");
		     Double totalRating = 0.0;
	         int count = 0;

	         if (rs.next()) {
	             // Initialize desc and rate correctly
	             desc = rs.getString("Description");
	             rate = rs.getDouble("Rating");
	             count++;
	             

	             Attraction_name = rs.getString("AttractionName");
	             tag = rs.getString("Tag");
	             city = rs.getString("City");
	             // Continue fetching more descriptions and ratings if there are multiple rows
	             while (rs.next()) 
	             {
	                    count++;
	                    desc += " " + rs.getString("Description");  // Concatenate descriptions
	                    rate += rs.getDouble("Rating");       // Accumulate ratings
	             }

                     totalRating = rate / count;
	         }

	               
		     System.out.println(Attraction_name);
		     System.out.println(tag);
		     System.out.println(city);
		     System.out.println(desc);
		     System.out.println(totalRating);
		     System.out.println("Want to know more details please select below options");
		     System.out.println("1: Question and Answers");
		     System.out.println("2:Write a review");
		     System.out.println("3:Save to my Favorites");
		     System.out.println("4:see reviews");
		     System.out.println("5:Go Back");
		     String sel = input.nextLine();
		     while(!sel.equals("5"))
		     {
		    	 if(sel.equals("1"))
		    	 {
		    		 questions(Attraction_name);
		    	 }
		    	 else if(sel.equals("2"))
		    	 {
		    		 reviews(Attraction_name);
		    	 }
		    	 else if(sel.equals("3"))
		    	 {
		    		 upfavorites(Attraction_name);
		    	 }
		    	 else if(sel.equals("4"))
		    	 {
		    		 watchreviews(Attraction_name);
		    	 }
		     }
		     
		 }
		 catch(SQLException e)
		 {
			 System.out.println("");
			 e.printStackTrace();
		 }
		 finally
			{
				try
				{
					conn.close();
					statement.close();
					rs.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
	 }
	 public static void create() 
	 {
		 Scanner input = new Scanner(System.in);
		 System.out.println("please enter the details of Attraction");
		 System.out.println("what is Attraction name?");
		 Attraction_name = input.nextLine();
		 System.out.println("please select your tag from below options");
		 System.out.println("History Buff,Shopping Fanatic,Beach Goer,Urban Explorer,Nature Lover,Family Vacationer");
		 tag = input.nextLine();
		 System.out.println("enter the city of Attraction");
		 city = input.nextLine();
		 System.out.println("explain few words about Attraction");
		 desc = input.nextLine();
		 System.out.println("Rate the Attraction out of 5");
		 rate = Double.parseDouble(input.nextLine());
		 
			Connection conn = null;
			Statement statement = null;
			
			try 
			{
				conn= DriverManager.getConnection(url,"pareddyt8985","2288985");
				statement = conn.createStatement();
				int r = statement.executeUpdate("Insert into attraction(AttractionName,Tag,City,Description,Rating,Creator) values('"+Attraction_name+"','"+tag+"','"+city+"','"+desc+"','"+rate+"','"+Userid+"')");
			}
			catch(SQLException e)
			{
				 System.out.println("attraction creation failed!");
				 e.printStackTrace();
			}
			finally
			{
				try
				{
					conn.close();
					statement.close();
					
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}

		 
	 }
	 public static void search()
	 {
		 Scanner input = new Scanner(System.in);
		 System.out.println("do you want to search by city or tag");
		 System.out.println("1:city");
		 System.out.println("2:tag");
		 String selection=input.nextLine();
		 
		 Connection conn = null;
		 Statement statement = null;
		 ResultSet rs = null;
		 try 
			{
				conn= DriverManager.getConnection(url,"pareddyt8985","2288985");
				statement = conn.createStatement();
				if(selection.equals("1"))
				 {
					 System.out.println("Enter the city");
					 String city= input.nextLine();
					 rs = statement.executeQuery("SELECT AttractionName from attraction where City = '"+city+"'");
				 }
				else if(selection.equals("2"))
				{
					System.out.println("Enter the tag");
					String tag = input.nextLine();
					rs = statement.executeQuery("SELECT AttractionName from attraction where Tag = '"+tag+"'"); 
				}
				if (rs != null) {
		            boolean found = false;
		            while (rs.next()) {
		                found = true;  // At least one result found
		                Attraction_name = rs.getString("AttractionName");
		                System.out.println( Attraction_name);
		            }
		            if(found)
		            {
		            	System.out.println("To know more about attraction type attraction name:");
		            	String s = input.nextLine();
		            	home(s);
		            }
		            else if (!found) {
		                System.out.println("No Attractions Found.");
		            }
		        }
				
			}
		 catch(SQLException e)
			{
				 System.out.println("search error");
				 e.printStackTrace();
			}
			finally
			{
				try
				{
					conn.close();
					statement.close();
					rs.close();
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		 
	 }
	 public static void questions(String q)
	 {
		 System.out.println("handle qAnd A ");
	 }
	 public static void reviews(String r)
	 {
		 System.out.println("reviews ");
	 }
	 public static void upfavorites(String f)
	 {
		 System.out.println("added to favorite");
	 }
	 public static void watchreviews(String f)
	 {
		 System.out.println("watch");
	 }
	
}