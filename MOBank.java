import java.util.ArrayList;
import java.util.Scanner;

public class MOBank
{
	public static Scanner userInput = new Scanner(System.in);
	public static ArrayList<KlientMOBank> clients = new ArrayList<KlientMOBank>(); // Array List of clients
	
	public static void main(String[] args)
	{
		System.out.println("--- Welcome in MO Bank! ---");
		int firstMenuChoose, userMenuChoose, deleteAccept=0;
		int accountID=0;
		
		do{
			do{ // DO WHILE ACCOUNTID IS LESS THAN 0
				do{
					showLoginAndNewAccountMenu();  // show text
					firstMenuChoose = userInput.nextInt();   // menu input
					chooseOptionInFirstMenu( firstMenuChoose );  // switch case
				}while( firstMenuChoose != 1 ); // logging/creating account menu
				
					accountID = getAccountIDandLogin();
			}while(accountID < 0); // DO WHILE ACCOUNTID IS LESS THAN 0
			
					System.out.println("Welcome " + clients.get(accountID).getFirstName() );

			do{
				deleteAccept = 0;
				doesPINexist(accountID); // showing user options after logging in
				userMenuChoose = userInput.nextInt(); 
				chooseUserOption( userMenuChoose , accountID);
				if(userMenuChoose==7)
				{
					deleteAccept = userInput.nextInt();
					deleteAccount(accountID,deleteAccept); // if deleteAccept == 1 (option delete)
					System.out.println("\n\n");
				}
				if(deleteAccept==1)
				{
					break;
				}
			}while( userMenuChoose != 6 ); // logged user menu
			
		}while( firstMenuChoose != 3 );
	

	

	} // main
	public static void showLoginAndNewAccountMenu() 
	{
		System.out.println("Menu");
		System.out.println("1. Login");
		System.out.println("2. Become our client");
		System.out.println("3. Exit");
		System.out.print("Choose: ");
	} // showing messages
	
	public static void showUserOptions()
	{
		System.out.println("1. Check deposit status");
		System.out.println("2. Withdraw money");
		System.out.println("3. Deposit money");
		System.out.println("4. Transfer money");
		System.out.println("5. Account information");
		System.out.println("6. Logout");
		System.out.println("7. Delete account");
		System.out.print("Choose: ");
	} // showing messages
	
	public static void chooseOptionInFirstMenu(int firstMenuChoose)
	{
		System.out.println("\n\n");
		switch( firstMenuChoose )
		{
		case 1:
			break;
		case 2:
			KlientMOBank newClient = makeAccount(); // <- function name says 
			clients.add(newClient);  // adding new client to our clients ArrayList
			System.out.println();
			break;
		case 3:
			System.out.println("Bye");
			System.exit(0);
			break;
		default:
			System.out.println("Enter valid option!");
			System.out.println("\n\n");
			break;
		} // switch case
	} // chooseOptionInFirstMenu
	
	public static KlientMOBank makeAccount()
	{
		System.out.print("Enter first name: ");
		String firstName = userInput.next();
		
		System.out.print("Enter last name: ");
		String lastName = userInput.next();

		KlientMOBank newClient = new KlientMOBank(firstName.trim(),lastName.trim()); // passing values to constructor
		System.out.println("Registration completed successfully");                   // also not passing PINcode bcs our constructor generate
		System.out.println("(REMEMBER)PIN code: " + newClient.getPINcode() );             // PIN by itself, always unique for each client 
		System.out.println();
		return newClient; // returning new object of class KlientMOBank
	}
	
	public static int getAccountIDandLogin()
	{
		System.out.print("Your PIN code: ");
		int userPINcode = userInput.nextInt();
		for(int i=0; i<clients.size(); i++)  // looping for every account we have
		{
			if( userPINcode==clients.get(i).getPINcode() )  // checking if any PIN code match PIN code which we passed in
			{
				System.out.println("Logged in");
				System.out.println("\n\n");
				return i; // returning account ID
			}
		}
		System.out.println("PIN code is not valid");
		System.out.println("\n\n");
		return -1; // returning not valid number so we can see if we logged in or not
	}
	
	public static void doesPINexist(int accountID)
	{
		if( accountID != -1 )      // (loginResult != -1) means that our PIN code is correct
		{ 
			showUserOptions();     // IF PIN is correct show account options
		} 
	}
	
	public static void chooseUserOption(int userMenuChoose, int accountID)
	{
		System.out.println("\n\n");
		switch( userMenuChoose )
		{
		case 1:
			System.out.println("Deposit: " + clients.get(accountID).getDeposit() + "z³");
			System.out.println("\n\n");
			break;
		case 2:
			System.out.print("Withdraw: ");
			int cashToWithdraw = userInput.nextInt();
			clients.get(accountID).decreaseDeposit(cashToWithdraw);
			System.out.println("\n\n");
			break;
		case 3:
			System.out.print("Deposit: ");
			int cashToDeposit = userInput.nextInt();
			clients.get(accountID).increaseDeposit(cashToDeposit);
			System.out.println("\n\n");
			break;
		case 4:
			System.out.println("Enter account ID of which person u want transfer money to");
			System.out.print("ID: ");
			int accountIDofPerson = userInput.nextInt();
			
			if(accountIDofPerson>clients.size())	
			{
				System.out.println("Person with that ID doesn't exists");
				break;
			}
			
			System.out.println("How much money u want to transfer"); 
			System.out.print("Cash: ");
			int moneyToTransfer = userInput.nextInt();
			
			clients.get(accountID).decreaseDeposit(moneyToTransfer);
			clients.get(accountIDofPerson).increaseDeposit(moneyToTransfer);
			System.out.println("\n\n");
			break;
		case 5:
			System.out.println("Name: " + clients.get(accountID).getFirstName() + " " +  clients.get(accountID).getLastName() );
			System.out.println("PIN: " + clients.get(accountID).getPINcode() );
			System.out.println("ID: " + clients.get(accountID).getAccountID());
			System.out.println("\n\n");
			break;
		case 6:
			System.out.println("You logged out");
			System.out.println("\n\n");
			break;
		case 7:
			System.out.println("1. Delete");
			System.out.println("2. Cancel");
			System.out.print("Choose: ");
			break;
		default:
			System.out.println("That option doesn't exists\n\n");
			break;
		}
	}
	
	public static void deleteAccount(int accountID,int deleteAccept)
	{
		if(deleteAccept==1)
		{
			clients.remove(accountID);
		}
	}
	
	
} // class