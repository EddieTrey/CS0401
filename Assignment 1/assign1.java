/* Edward Yeh  CS0401: Intermediate Programming Using java
               Section: M W 12:30PM - 2:15PM
               Lab: Th 12:00PM - 1:50PM
The purpose of this program is to simulate shopping at a candy store.
This program will ask the user which goods they would like to buy,
ask them to pay, and give them change back if necessary*/

import java.util.*;

public class assign1
{

      public static void main (String [] args)
      {
            //Declare vars.
            int option = 0;                     //Asks user for input from 1-6 for the menu options
            int acidt = 0;                      //Total number of acid
            int acid = 0;                       //Individual acis pops
            int bag_acid = 0;
            int pumpkin = 0;
            int s_beans = 0;                    //Small bag and big bag of beans
            int b_beans = 0;
            double subtotal;
            int total;
            int discount;
            int subtotalf;                      //Subtotal for converting double subtotal to int.
            int first_customer = 0;             //Counter for while loop for arbitrary amounts of customers.
            int leaveloop = 0;                  //Increment int for asking for password
	    String yes_no;                      //String for user input
            int money;
            int moneyt = 0;
            String curr;

            System.out.println("Ｗelcome to -A Little Honey-");
            //This section handles asking if there is a new customer
            //and if the answer is yes, the loop begins. if the answer
            //is no, the program quits
	    Scanner user_customer = new Scanner (System.in);
	    System.out.println("Are there any customers in line? (yes/no))");
            yes_no = user_customer.next(); user_customer.nextLine();
            while (!yes_no.equals("yes") && !yes_no.equals("no"))
            {
            	System.out.println("yes or no only");
            	yes_no = user_customer.next(); user_customer.nextLine();
            }
            if (yes_no.equals("no"))
	    {
                System.out.println("Thank you for stopping by! Good Bye!");
            	System.exit(0);
	    }
            while (yes_no.equals("yes"))
            {
            	if (first_customer >= 1)
                {
		    System.out.println("Are there any customers in line? (yes/no))");
                    yes_no = user_customer.next(); user_customer.nextLine();
                    while (!yes_no.equals("yes") && !yes_no.equals("no"))
                   {
                        System.out.println("yes or no only");
                        yes_no = user_customer.next(); user_customer.nextLine();
                   }
                        if (yes_no.equals("no"))
			{
                                System.out.println("Thank you for stopping by! Good Bye!"); //prints out ok bye and stuff
                                System.exit(0);
        		}
                  }
                //variables for different candies and also payment. They're set to 0 here to each customer gets a fresh start.
                acidt = 0;
                acid = 0;
                bag_acid = 0;
                pumpkin = 0;
                s_beans = 0;
                b_beans = 0;
                money = 0;
                moneyt = 0;
                int moneyd = 0;
                int saving = 0;


                //this section handles asking for the password. if the correct one is give, a disount menu appears
		String password = "meh";
		for (int passloop = 0; passloop < 2; passloop++)
		{
			System.out.println("What's the secret password?");
			password  = user_customer.next();
			if (password.equals("Potter"))
			{
				System.out.println("\n\n\nThank you for being a member of Honey Dukes! Enjoy our discounted menu: ");
                                System.out.println("\nAcid Pops                         11 Knuts");
                                System.out.println("Acid Pops (bag)                   50 Knuts");
                                System.out.println("Pumpkin Pasties                   100 Knuts");
                                System.out.println("Every Flavor Beans (small bag)    50 Knuts");
                                System.out.println("Every Flavor Beans (large bag)    58 Knuts");
				System.out.println("There is also an additional 10% off on all orders 10 Sickles or more!\n");
				passloop++;

			}
			else if (passloop == 0)
			{
				System.out.println("\nWRONG! We'll give you one more try though.");
			}
			else
			{
				System.out.println("\nSorry, that is also incorrect.\nPlease enjoy our goods at regular price.\n");
				System.out.println("Acid Pops                         12 Knuts");
				System.out.println("Acid Pops (bag)                   58 Knuts");
				System.out.println("Pumpkin Pasties                   116 Knuts");
				System.out.println("Every Flavor Beans (small bag)    50 Knuts");
				System.out.println("Every Flavor Beans (large bag)    70 Knuts\n");


			}
		  }
                  //this section of the code handles the main menu. will continue to loop unless the option to check out is selected
            	  option = 0;
		  while (option != 6)
		  {
		  	System.out.println("\nPlease select an option:");
			System.out.println("1) Update Acid Pops Order\n2) Update Pumpkin Pasties Order\n3) Up" +
                                                          "date Every Flavor Beans Order");
			System.out.println("4) Show Price List\n5) Show Current Subtotal\n6) Checkout");
			option = user_customer.nextInt();

		        if (option == 1)
			{
                                //This section is in charge of asking and recording how many pops the user wants

                        	System.out.println("\nYour current order of Acid Pops is: ");
                        	System.out.println("         " + bag_acid + " bags of Acid Pops");
                        	System.out.println("         " + acid + " Acid Pops\n");
                        	if (password.equals("Potter"))
                        	{
                        		System.out.println("How many Acid Pops would you like for 11 Knuts per pop and 50 Knuts per bag of 5?");
                        		System.out.println("Please only enter the total number of Acid Pops you would like to order.");
                       		}
                        	else
                        	{
                        		System.out.println("How many Acid Pops would you like for 12 Knuts per pop and 58 Knuts per bag of 5?");
                        		System.out.println("Please only enter the total number of Acid Pops you would like to order.");
                        	}


                                //do while loop for when the user wants to act smart and enter a negative int. XD
                        	do
				{
					acidt = user_customer.nextInt();
					if (acidt < 0)
					{
						System.out.println("Please enter 0 or more");
					}
				} while (acidt < 0);
                        	bag_acid = acidt / 5;
                        	acid = acidt % 5;

			}
			else if (option == 2)
			{
                        	if (password.equals("Potter"))
                        	{
                        		System.out.println("\nYou currently have " + pumpkin + " Pumpkin Pasties in your cart");
					System.out.println("How many Pumpkin Pasties would you like for 100 Knuts each?");
                        	}
                        	else
                        	{
                          		System.out.println("\nYou currently have " + pumpkin + " Pumpkin Pasties in your cart");
  					System.out.println("How many Pumpkin Pasties would you like for 116 Knuts each?");
                        	}
				do
				{
					pumpkin = user_customer.nextInt();
					if (pumpkin < 0)
					{
						System.out.println("Please enter 0 or more");
					}
				}while (pumpkin < 0);
			}
                  	else if (option == 3)
                  	{
                        	if (password.equals("Potter"))
                        	{
                              		System.out.println("\nYou currently have " + s_beans + " small bags "
                                                                +"and " + b_beans + " big bags of Every Flavor Beans in your cart");
                            		System.out.println("How many small bags of Every Flavor Beans would you like for 50 Knuts each?");
                              		s_beans = user_customer.nextInt();
                              		while (s_beans < 0)
                              		{
                                    		System.out.println("We don't provide negative number of bags of beans.");
                                    		s_beans = user_customer.nextInt();
                              		}
                              		System.out.println("How many big bags of Every Flavor Beans would you like for 58 Knuts each?");
                              		b_beans = user_customer.nextInt();
                              		while (b_beans < 0)
                              		{
                                    		System.out.println("What did I just say?! Please enter zero or more number of bags.");
                                    		b_beans = user_customer.nextInt();
                              		}
                        	}
                        	else
                        	{
                              		System.out.println("\nYou currently have " + s_beans + " small bags "
                                                                +"and " + b_beans + " big bags of Every Flavor Beans in your cart");
                              		System.out.println("How many small bags of Every Flavor Beans would you like for 50 Knuts each?");
                              		s_beans = user_customer.nextInt();
                              		while (s_beans < 0)
                              		{
                                    		System.out.println("We don't provide negative number of bags of beans.");
                                    		s_beans = user_customer.nextInt();
                              		}
                              		System.out.println("How many big bags of Every Flavor Beans would you like for 70 Knuts each?");
                              		b_beans = user_customer.nextInt();
                              		while (b_beans < 0)
                              		{
                                    		System.out.println("What did I just say?! Please enter zero or more number of bags.");
                                    		b_beans = user_customer.nextInt();
                              		}
                        	}
                  	}
                  	else if (option == 4)
                  	{
                        	if (password.equals("Potter"))
                        	{
                              		System.out.println("\nAcid Pops                         11 Knuts");
                              		System.out.println("Acid Pops (bag)                   50 Knuts");
                              		System.out.println("Pumpkin Pasties                   100 Knuts");
                              		System.out.println("Every Flavor Beans (small bag)    50 Knuts");
                              		System.out.println("Every Flavor Beans (large bag)    58 Knuts");
                              		System.out.println("*** 10% off orders 10 Sickles or more ***\n");
                        	}
                        	else
                        	{
                              		System.out.println("\nAcid Pops                         12 Knuts");
                              		System.out.println("Acid Pops (bag)                   58 Knuts");
                              		System.out.println("Pumpkin Pasties                   116 Knuts");
                              		System.out.println("Every Flavor Beans (small bag)    50 Knuts");
                              		System.out.println("Every Flavor Beans (large bag)    70 Knuts\n");
                        	}
                  	}
                  	else if (option == 5)
                  	{
                        	if (password.equals("Potter"))
                        	{
                          		System.out.println("\nHere is your subtotal:");
                          		if (bag_acid > 0)
                          		{
                                                System.out.printf("%5d bag(s) of Acid Pops at 50 Knuts each %22d\n", bag_acid, bag_acid * 50);
                          		}
                          		if (acid > 0)
                          		{
                                                System.out.printf("%5d Acid Pop(s) at 11 Knuts each %30d\n", acid, acid * 11);
                          		}
                          		if (pumpkin > 0)
                          		{
                                                System.out.printf("%5d Pumpkin Pasties at 100 Knuts each %25d\n", pumpkin, pumpkin * 100);
                          		}
                          		if (s_beans > 0)
                          		{
                                                System.out.printf("%5d small bag(s) of Every Flavor Beans at 50 Knuts each %7d\n", s_beans, s_beans * 50);
                          		}
                          		if (b_beans > 0)
                          		{
                                                System.out.printf("%5d big bag(s) of Every Flavor Beans at 58 Knuts each %9d\n", b_beans, b_beans * 58);
                          		}
                          	subtotal = ((bag_acid * 50) + (acid * 11) + (pumpkin * 100) + (s_beans * 50) + (b_beans * 58));
                          	subtotalf = (int)subtotal;
                                System.out.printf("\n    Total %55d\n", subtotalf);

                        	}
                        	else
                        	{
                          		System.out.println("\nHere is your subtotal:");
                          		if (bag_acid > 0)
                          		{
                                		System.out.printf("%5d bag(s) of Acid Pops at 58 Knuts each %22d\n", bag_acid, bag_acid * 58);
                          		}
                          		if (acid > 0)
                          		{
                                		System.out.printf("%5d Acid Pop(s) at 12 Knuts each %30d\n", acid, acid * 12);
                          		}
                          		if (pumpkin > 0)
                          		{
                                		System.out.printf("%5d Pumpkin Pasties at 116 Knuts each %25d\n", pumpkin, pumpkin * 116);
                          		}
                          		if (s_beans > 0)
                          		{
                                		System.out.printf("%5d small bag(s) of Every Flavor Beans at 50 Knuts each %7d\n", s_beans, s_beans * 50);
                          		}
                          		if (b_beans > 0)
                          		{
                                		System.out.printf("%5d big bag(s) of Every Flavor Beans at 70 Knuts each %9d\n", b_beans, b_beans * 70);
                          		}
                          	subtotal = ((bag_acid * 58) + (acid * 12) + (pumpkin * 116) + (s_beans * 50) + (b_beans * 70));
                          	subtotalf = (int)subtotal;
                          	System.out.printf("\n    Total %55d\n", subtotalf);
                        	}
                  	}
                  	else if (option == 6)
                  	{
                        	if (password.equals("Potter"))
                          	{

                                        System.out.println("\nHere is your subtotal:");
                            		if (bag_acid > 0)
                            		{
                                                System.out.printf("%5d bag(s) of Acid Pops at 50 Knuts each %22d\n", bag_acid, bag_acid * 50);
                            		}
                            		if (acid > 0)
                            		{
                                                System.out.printf("%5d Acid Pop(s) at 11 Knuts each %30d\n", acid, acid * 11);
                            		}
                            		if (pumpkin > 0)
                            		{
                                                System.out.printf("%5d Pumpkin Pasties at 100 Knuts each %25d\n", pumpkin, pumpkin * 100);
                            		}
                            		if (s_beans > 0)
                            		{
                                                System.out.printf("%5d small bag(s) of Every Flavor Beans at 50 Knuts each %7d\n", s_beans, s_beans * 50);
                            		}
                            		if (b_beans > 0)
                            		{
                                                System.out.printf("%5d big bag(s) of Every Flavor Beans at 58 Knuts each %9d\n", b_beans, b_beans * 58);
                            		}
                            	subtotal = ((bag_acid * 50) + (acid * 11) + (pumpkin * 100) + (s_beans * 50) + (b_beans * 58));
                            	subtotalf = (int)subtotal;
                                System.out.printf("\n    Total %55d\n", subtotalf);
                                if (subtotal >= 290)
                                {
                                        discount = (int)((subtotal / 10) + 0.5);
                                        subtotalf = (int)subtotal - discount;
                                        System.out.printf("    Bonus discount of 10 percent %32d\n", discount);
                                        System.out.printf("\n    New Total %51d\n", subtotalf);
                                }

                    		}
                        	else
                          	{
                                	System.out.println("\nHere is your subtotal:");
                                	if (bag_acid > 0)
                                	{
                                                System.out.printf("%5d bag(s) of Acid Pops at 58 Knuts each %22d\n", bag_acid, bag_acid * 58);
                                	}
                               		if (acid > 0)
                               		{
                                                System.out.printf("%5d Acid Pop(s) at 12 Knuts each %30d\n", acid, acid * 12);
                                	}
                                	if (pumpkin > 0)
                                	{
                                                System.out.printf("%5d Pumpkin Pasties at 116 Knuts each %25d\n", pumpkin, pumpkin * 116);
                                	}
                                	if (s_beans > 0)
                                	{
                                                System.out.printf("%5d small bag(s) of Every Flavor Beans at 50 Knuts each %7d\n", s_beans, s_beans * 50);
                                	}
                                	if (b_beans > 0)
                                	{
                                                System.out.printf("%5d big bag(s) of Every Flavor Beans at 70 Knuts each %9d\n", b_beans, b_beans * 70);
                                	}
                            		subtotal = ((bag_acid * 58) + (acid * 12) + (pumpkin * 116) + (s_beans * 50) + (b_beans * 70));
                            		subtotalf = (int)subtotal;
                                        System.out.printf("\n    Total %55d\n", subtotalf);
                          			}
                  		}

		  	}
                        //Applies a discount if subtotal is over 290 Knuts/10 Sickles
                  	do
                  	{
                          	subtotal = ((bag_acid * 58) + (acid * 12) + (pumpkin * 116) + (s_beans * 50) + (b_beans * 70));
                          	subtotalf = (int)subtotal;
                          	if (password.equals("Potter"))
                          	{
                                	subtotal = ((bag_acid * 50) + (acid * 11) + (pumpkin * 100) + (s_beans * 50) + (b_beans * 58));
                                	subtotalf = (int)subtotal;
                                  	if (subtotal >= 290)
                                  	{
                                        	discount = (int)((subtotal / 10) + 0.5);
                                        	subtotalf = (int)subtotal - discount;
                                  	}
                          	}
                        leaveloop++;
                  	} while (leaveloop == 0);

                  	first_customer++; //useless for now, but could be used for counting
                                          //number of customers. Play around with this in the future.

                        if (password.equals("Potter"))
                        {
                                saving = ((bag_acid * 58) + (acid * 12) + (pumpkin * 116) + (s_beans * 50) + (b_beans * 70)) - subtotalf;

                                System.out.printf("    Saved(Knuts): %47d\n", saving);
                        }





                  	Scanner input = new Scanner(System.in);
                  	if (subtotal == 0)
                  	{
                          	System.out.println("    You haven't ordered anything. Good bye!");

                  	}
                  	else
                  	{
                  		System.out.println("Enter your payment method in the following format:");
                  		System.out.println("\t<amount><a blank space><currency>");
                                System.out.println("Note 1: We prefer exact change (but will make change if necessary)!\n" +
                                "Note 2: No more than 50 galleons are kept in the cart (funds are transported to Gringotts on a daily basis)" +
                                "\nNote 3: Recall our currency options: \n\t29 Knuts == 1 Sickle \n\t17 Sickles == 1 Galleon == 493 Knuts");
                                System.out.println("Be sure to indicate the correct currency! Eg: Galleon/Galleon(s)");

                                //This part is for asking the user to enter the amount of money and currency type
                                //and also to calculate if they've entered enough to cover the goods.
                                do
                  		{
                       			money = input.nextInt(); curr = input.next();
                       			while (money < 1)
                       			{
                                		System.out.println("Please enter a positive amount or I'm calling the Ministry of Magic.");
                                		money = input.nextInt(); curr = input.next();
                       			}
                       			switch (curr)
                       			{
                                		case "Galleon":
                                        		moneyt += 493;
                                        		System.out.println("You have paid " + moneyt + " out of " + subtotalf + " Knuts.");
                                        		break;

                                		case "Galleons":
                                        		moneyt = moneyt + (money * 493);
                                        		System.out.println("You have paid " + moneyt + " out of " + subtotalf + " Knuts.");
                                        		break;
                                		case "Sickle":
                                        		moneyt = moneyt + 29;
                                        		System.out.println("You have paid " + moneyt + " out of " + subtotalf + " Knuts.");
                                        		break;
                                		case "Sickles":
                                        		moneyt = moneyt + (money *29);
                                        		System.out.println("You have paid " + moneyt + " out of " + subtotalf + " Knuts.");
                                        		break;
                                		case "Knuts":
                                        		moneyt += money;
                                        		System.out.println("You have paid " + moneyt + " out of " + subtotalf + " Knuts.");
                                        		break;
                                		case "Knut":
                                        		moneyt++;
                                        		System.out.println("You have paid " + moneyt + " out of " + subtotalf + " Knuts.");
                                        		break;
                                		default:
                                        		System.out.println("Enter a valid currency!");
                       			}

                  		} while (moneyt < subtotal);

                                //This section is for giving the customer change back if necessary

                                moneyd = moneyt - subtotalf;
                                if (moneyt == subtotalf)
                                {
                                        System.out.println("Thank you for shopping! Hope to see you soon!\n");
                                }
                  		if (moneyt > subtotalf)
                  		{
                                        System.out.println("You have over paid by " + moneyd + " Knuts");
                          		System.out.println("Here is your change: ");
                       			if (moneyd >= 493)
                       			{
                            			System.out.printf("%10d Galleons\n", moneyd / 493);
                                                moneyd %= 493;
                                        }
                       			if (moneyd >= 29)
                       			{
                            			System.out.printf("%10d Sickles\n", moneyd / 29);
                                                moneyd %= 29;
                                        }
                                        if (moneyd < 29)
                                        {
                            	                System.out.printf("%10d Knuts\n\n", moneyd);

                                        }
                                }
          		}
            }

    }
}
