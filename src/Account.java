import java.sql.*;
import java.util.Scanner;

public class Account{
	
	private static String Userid="";
	private static String tag="";
	
	// to register user
	public static void register()
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("please enter your UserId:");
		String Userid = input.nextLine();
		System.out.println("please enter your password:");
		String password = input.nextLine();
		System.out.println("please select your tag from below options");
		System.out.println("History Buff,Shopping Fanatic,Beach Goer,Urban Explorer,Nature Lover,Family Vacationer");
		tag=input.nextLine();
		String type="user";
		//database url
		final String url = "jdbc:mysql://cobmysql.uhcl.edu/pareddyt8985?useSSL=false";
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try 
		{
			conn= DriverManager.getConnection(url,"pareddyt8985","2288985");
			statement = conn.createStatement();
			while(true) 
			{
				rs = statement.executeQuery("SELECT UserId from user where UserId = '"+Userid+"'");
				if(rs.next())
				{
					System.out.println("Userid has already been used.if you are user you can login or else try different user id");
					System.out.println("please enter your new UserId:");
					Userid=input.nextLine();
				}
				else
				{
					break;
				}
			}
			while(true) 
			{
				if(Userid.equals(password))
				{
					System.out.println("password should not match with the user id");
					System.out.println("please enter your  new password:");
					password=input.nextLine();
				}
				else
				{
					break;
				}
				
			}
			int r = statement.executeUpdate("Insert into user values('"+Userid+"','"+password+"','"+type+"','"+tag+"')");
			System.out.println("!!!registration Successful you can login now!!!");
			
			
		}
		catch(SQLException e)
		{
			 System.out.println("user registration failed!");
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
	public static void login()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("please enter your UserId:");
		 Userid = input.nextLine();
		System.out.println("please enter your password:");
		String password = input.nextLine();
		final String url = "jdbc:mysql://cobmysql.uhcl.edu/pareddyt8985?useSSL=false";
		Connection conn = null;
		Statement statement = null;
		ResultSet rs = null;
		try 
		{
			conn= DriverManager.getConnection(url,"pareddyt8985","2288985");
			statement = conn.createStatement();
			while(true) 
			{
				rs = statement.executeQuery("SELECT UserId from user where UserId = '"+Userid+"'");
				if(rs.next())
				{
					rs = statement.executeQuery("SELECT Password from user where UserId = '"+Userid+"' AND Password = '"+password+"'");
					if(rs.next())
					{
						Mainpage.home();
						break;
					}
					else 
					{
						System.out.println("Invalid password");
						System.out.println("please enter your password:");
						password = input.nextLine();
					}
				}
				else
				{
					System.out.println("Invalid User Id");
					System.out.println("please enter your UserId:");
					Userid = input.nextLine();
				}
			}
			
		}
		catch(SQLException e)
		{
			 System.out.println("user login failed!");
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
	public static  String getUserid()
	{
		return Userid;
	}
	public  static String gettag()
	{
		return tag;
	}
}