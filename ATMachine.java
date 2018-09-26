//import statements
import java.util.*;
import java.io.*;
import java.util.Scanner;  
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//class for ATM
class ATMachine
{
	public String Inputcardno()
	{
		System.out.println("Welcome to ATM");
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter Your ATM card no.");
		String cdno=scn.nextLine();	
		return cdno;
		
	}
	public String Inputpin()
	{
		Scanner scn = new Scanner(System.in);
		System.out.println("Enter your 4 digit ATM pin");	
		String pin=scn.nextLine();
		return pin;
	}

//----------------------------------------------------------------------------------------------------------------------------------------------
	//method to compare the card no and pin entered with the data stored in files
	//if the imput data i.e card no or pin does not match with the existing customer credentials the operation is terminated
	
	public boolean compareInFile(String inputWord)
	{         
		String word = "";
		File file = new File("cdnopin.txt");
		try		
		{		
			Scanner input = new Scanner(file); 
			while(input.hasNext()) 
			{
				word = input.next();
				if(inputWord.equals(word))
				return false;
			}

		}
		catch(Exception error)
		{
			System.out.println("Exception found!enter your credential again");
		}
		return true;
	} 
	
//-------------------------------------------------------------------------------------------------------------------------------------------------
	//amount entered for withdrawl is processed
	public void withdrawl(String cdno) throws Exception
	{
		String ID, Name;
        	double balance;
        	int updation;
		int hun,ten,one,thou;

		Scanner console = new Scanner(System.in);
		System.out.print("Enter amount : ");
        	updation = console.nextInt();

		File originalFile = new File("file.txt");
        	BufferedReader br = new BufferedReader(new FileReader(originalFile));
		
		 // Construct the new file that will later be renamed to the original
        	// filename.
	
	        File tempFile = new File("tempfile.txt");
        	PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

	        String line = null;
        	// Read from the original file and write to the new
        	// unless content matches data to be removed.
       
		 while ((line = br.readLine()) != null) 
		{

	            if (line.contains(cdno)) 
			{
                		String strCurrentbalance = line.substring(line.lastIndexOf(" "), line.length());
               			if (strCurrentbalance != null || !strCurrentbalance.trim().isEmpty())
				 {
                 			int updationedbalance = Integer.parseInt(strCurrentbalance.trim()) - updation;
					if(updationedbalance < 0)
					{
						System.out.println("Insufficient Funds");
					}//if
				
		   			else
					{	
                  				System.out.println("Please Collect your cash\n\nYour Balance is " + updationedbalance);
                    				line = line.substring(0,line.lastIndexOf(" ")) +" "+updationedbalance;
			
						System.out.println("Your Cash Summary is");
								thou=updation/1000;
								System.out.println(thou+"*1000");
								updation=updation-(thou*1000);							
								hun=updation/500;
								System.out.println(hun+"*500");
								updation=updation-(hun*500);
								ten=updation/100;
								System.out.println(ten+"*100");
								updation=updation-(ten*100);
					}//else
              			}//if
		

           		}//if
            		pw.println(line);
           		pw.flush();
      	   }//while
        	pw.close();
       		br.close();

        // Delete the original file
        if (!originalFile.delete()) 
	{
            System.out.println("Could not delete file");
            return;
        }//if

        // Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(originalFile))
            System.out.println("Could not rename file");
		
	}//method

//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		//amount entered for withdrawl is processed
	public void deposit(String cdno)throws Exception
	{
		String ID, Name;
        	double balance;
        	int updation;

		Scanner console = new Scanner(System.in);
	        System.out.print("Enter updation balance: ");
        	updation = console.nextInt();
		File originalFile = new File("file.txt");
                BufferedReader br = new BufferedReader(new FileReader(originalFile));

	        // Construct the new file that will later be renamed to the original
	        // filename.
	        File tempFile = new File("tempfile.txt");
	       	PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
	
        	String line = null;
        	// Read from the original file and write to the new
        	// unless content matches data to be removed.
        	while ((line = br.readLine()) != null) 
		{

		            if (line.contains(cdno)) 
			    {
               			 String strCurrentbalance = line.substring(line.lastIndexOf(" "), line.length());
               			 if (strCurrentbalance != null || !strCurrentbalance.trim().isEmpty())
				 {
               			     int updationedbalance = Integer.parseInt(strCurrentbalance.trim()) + updation;
             			     System.out.println("updationedbalance : " + updationedbalance);
                   		     line = line.substring(0,line.lastIndexOf(" ")) +" "+updationedbalance;
               			 }
		

           		    }
           	 pw.println(line);
          	 pw.flush();
       		}	
    	    pw.close();
    	    br.close();

    	    // Delete the original file
    	    if (!originalFile.delete())
		 {
            		System.out.println("Could not delete file");
            		return;
        	}

        // Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(originalFile))
 	           System.out.println("Could not rename file");

	}
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	public void Balance(String cdno)throws Exception
	{
		String ID, Name;
        	double balance;
        	int updation;

		Scanner console = new Scanner(System.in);
	       
        	File originalFile = new File("file.txt");
                BufferedReader br = new BufferedReader(new FileReader(originalFile));

	        // Construct the new file that will later be renamed to the original
	        // filename.
	        File tempFile = new File("tempfile.txt");
	       	PrintWriter pw = new PrintWriter(new FileWriter(tempFile));
	
        	String line = null;
        	// Read from the original file and write to the new
        	// unless content matches data to be removed.
        	while ((line = br.readLine()) != null) 
		{

		            if (line.contains(cdno)) 
			    {
               			 String strCurrentbalance = line.substring(line.lastIndexOf(" "), line.length());
               			System.out.println("Your Balance is "+strCurrentbalance);
		

           		    }
           	 pw.println(line);
          	 pw.flush();
       		}	
    	    pw.close();
    	    br.close();

    	    // Delete the original file
    	    if (!originalFile.delete())
		 {
            		System.out.println("Could not delete file");
            		return;
        	}

        // Rename the new file to the filename the original file had.
        if (!tempFile.renameTo(originalFile))
 	           System.out.println("Could not rename file");

	}
//---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public static void main(String ... atm)throws Exception
	{
	
		ATMachine mach = new ATMachine();
		String cdno = mach.Inputcardno();
		String pin = mach.Inputpin();
     		if(mach.compareInFile(cdno+"@"+pin)==false)			//if a string with cd no and pin exist in file futher operations are performed
		{
			
			
				String awnser="";
			do{			
				System.out.println("Select one option");
				System.out.println("\t1. Cash Withdrawl \n\t2.Deposit \n\t3.Balance enquiry");
				Scanner scn = new Scanner(System.in);
				int a=scn.nextInt();
				switch(a)
				{
					case 1 :
							ATMachine atmw = new ATMachine();
							atmw.withdrawl(cdno);
							break;
					case 2 : 

							ATMachine atmd = new ATMachine();
							atmd.deposit(cdno);
							break;
					case 3:
							ATMachine atmb = new ATMachine();
							atmb.Balance(cdno);
							break;
					default :
							System.out.println("Invalid Option Selected - 2 more chances for session timeout!\n");
							System.out.println("Select one option \n");
							System.out.println("\t1. Cash Withdrawl \n\t2.Deposit \n\t3.Balance enquiry");
							int a1=scn.nextInt();
							switch(a1)
							{
							case 1 :
									ATMachine atmnw = new ATMachine();
									atmnw.withdrawl(cdno);
									break;
							case 2 : 

									ATMachine atmnd = new ATMachine();
									atmnd.deposit(cdno);
									break;
							case 3:
									ATMachine atmnb = new ATMachine();
									atmnb.Balance(cdno);
									break;
							default :
									System.out.println("Invalid Option Selected");	
									System.out.println("Select one option \n");
									System.out.println("\t1. Cash Withdrawl \n\t2.Deposit \n\t3.Balance enquiry");					
									int a2=scn.nextInt();
									switch(a2)
									{
										case 1 :
											ATMachine atmnnw = new ATMachine();
											atmnnw.withdrawl(cdno);
											break;
										case 2 : 

											ATMachine atmnnd = new ATMachine();
											atmnnd.deposit(cdno);
											break;
										case 3:
											ATMachine atmnnb = new ATMachine();
											atmnnb.Balance(cdno);
											break;
				                    				default :
											System.out.println("Invalid Option Selected session timeout!\n");

									}
						}
						break;
			 
				}
				
				System.out.println("do you want to continue?");
        			awnser=scn.next();
			}while(awnser.equals("y"));
				
		} 
		else
		{
		System.out.println("Enter valid credentials \n\n--------------------------------------");
		main();
		
		}
}






















}