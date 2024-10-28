import java.util.Scanner;

public class onlinesystem{
	
	public static void main(String[] args) {
		
		// main menu
		Scanner input= new Scanner(System.in);
		String selection = "";
		while(!selection.equals("x"))
		{
			//display the menu
			System.out.println();
			System.out.println("please make your selection");
			System.out.println("1:register");
			System.out.println("2:login");
			System.out.println("x:finish");
			//get selection from user
			selection= input.nextLine();
			System.out.println();
			
			if(selection.equals("1"))
			{
				//register user
				Account.register();
			}
			else if (selection.equals("2")) 
			{
				Account.login();
			}
			
		}
		
	}
	
}
