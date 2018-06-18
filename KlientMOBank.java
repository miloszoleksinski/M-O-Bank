import java.util.ArrayList;
import java.util.Random;

public class KlientMOBank
{
	private String firstName;
	private String lastName;
	private int accountID; 
	private static int accountIDcounter = 0;
	private int deposit = 0;
	private int PINcode;
	
	private static ArrayList<Integer> PINs = new ArrayList<Integer>();;
	
	public KlientMOBank(String firstName,String lastName)
	{
		setFirstName(firstName);
		setLastName(lastName);
		setPINcode();
		
		setAccountID(accountIDcounter);
		accountIDcounter++;
	}
	
	public void setFirstName(String firstName){ this.firstName = firstName; }
	public String getFirstName() { return firstName; }
	
	public void setLastName(String lastName){ this.lastName = lastName; }
	public String getLastName() { return lastName; }
	
	public void setPINcode(){this.PINcode = getRandomPIN();}
	public int getPINcode() { return PINcode; }
	private static int getRandomPIN()
	{
		Random rand = new Random();
		int randomPIN = 0, numCounter = 0, numExists = 0; 
		do
		{
			numExists = 0;
			randomPIN = rand.nextInt(9000) + 1000;
			for(int j=0; j<PINs.size(); j++)	
			{if(randomPIN == PINs.get(j)){numExists=1; break;}}
			if(numExists==1) { continue; }
			
			PINs.add(randomPIN); 
			numCounter++; 
		}while( numCounter == 1 );
		return randomPIN;
	}
	
	public void increaseDeposit(int deposit){ this.deposit += deposit; }
	public void decreaseDeposit(int deposit)
	{
		if(this.deposit>=deposit)
		{
			this.deposit -= deposit;
		}
		else
		{
			System.out.println("Not enough money in deposit");
		}
		
	}
	public int getDeposit() { return deposit; }
	//public static ArrayList<Integer> getPINs() { return PINs; }
	
	public void setAccountID(int accountID){ this.accountID = accountID; }
	public int getAccountID(){ return accountID; }
	
	
} // class
