import java.util.Scanner;

public class Mainpage{
	
	 String Userid= Account.getUserid();
	 String tag = Account.gettag();
	
	public static void home()
	{
		Scanner input= new Scanner(System.in);
		String selection = "";
		while(!selection.equals("x"))
		{
			//display of login page
			System.out.println();
			System.out.println("1:highest matched attraction");
			System.out.println("2:second highest");
			System.out.println("3:Notifications");
			System.out.println("please make your selection");
			System.out.println("c:Create An Attraction");
			System.out.println("s:Search for Attractions");
			System.out.println("f:Favorite Attractions");
			System.out.println("x:logout");
			//get selection from user
			selection= input.nextLine();
			if(selection.equals("c"))
			{
				Attraction.create();
			}
			else if(selection.equals("s"))
			{
				Attraction.search();
			}
			else if(selection.equals("f"))
			{
				favorites();
			}
			else if(selection.equals("1"))
			{
				recommend1();
			}
			else if(selection.equals("2"))
			{
				recommend2();
			}
			else if(selection.equals("3"))
			{
				notification();
			}
			
		}
	}
	public static void favorites()
	{
		
	}
	public static void notification()
	{
		
	}
	public static void recommend1()
	{
		
	}
	public static void recommend2()
	{
		
	}
}