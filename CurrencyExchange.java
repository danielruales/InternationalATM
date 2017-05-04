//Daniel Ruales
//11171801
//Project 3
//Currency ATM, multiple methods practice.
import java.util.Scanner;

public class CurrencyExchange {

    private static double balance = 0;

    /**
     * Get the current balance of the account
     */
    public static double getBalance() {
        return balance;
    }

     /**
     * Update the balance of current account, will do a roundup to 2 significant digits
     *
     * @return if update succeed, will return true. If failed, return false
     */
    private static boolean updateBalance(double newBalance) {

        balance = Math.round(newBalance * 100) / 100.0;
        if (balance >= 0) {
            return true;
        } else
            return false;
    }

/****************************************************************
*        Do not modify anything above this line                 *
*****************************************************************/

    /**
     * main method, put your business logic and console input here
     */
    public static void main(String[] args) {
        //Please DO NOT create any other Scanner objects
        Scanner input = new Scanner(System.in);

        // this is where you will write the code that invokes (calls) 
        // the methods below to facilitate the successful running of 
        // your code
        
        System.out.println("Welcome to Currency Exchange 2.0");
		printConversionTable();
		double balance = 0.0;
		double newBalance = 0.0;
		boolean isDeposit;
		int currencyType;
		double amount;
		while (true){
			switch (mainMenuOptionSelector(input)){
			//Prints balance
			case 1: 
				System.out.println("Your current balance is: " + (Math.round(balance*100)/100.0));
				break;
			//Deposit
			case 2: 
				currencyType = currencyMenuOptionSelector(input);
				System.out.println("Please enter the deposit amount:");
				amount = input.nextDouble();
				boolean depositCheck = deposit(amount, currencyType);
				if (depositCheck == false){
					System.out.println("Logging Error");
				}
				else{
					boolean isConvertToUSD = true;
					double newCurrency = convertCurrency(amount, currencyType, isConvertToUSD);
					newBalance = balance + newCurrency;
					updateBalance(newBalance);
					balance+= newCurrency;
					isDeposit = true;
					System.out.println(logTransaction(amount, currencyType, isDeposit));
				}
				break;
			//Withdrawal
			case 3: 
				currencyType = currencyMenuOptionSelector(input);
				System.out.println("Please enter the withdrawal amount:");
				amount = input.nextDouble();
				boolean withdrawalCheck = withdraw(amount, currencyType);
				if (withdrawalCheck == false){
					if (amount > balance){
						System.out.println("Error: Insufficient funds.");
					}
					System.out.println("Logging Error");
				}
				else {
					boolean isConvertToUSD = false;
					double newCurrency = convertCurrency(amount, currencyType, isConvertToUSD);
					newBalance = balance - newCurrency;
					updateBalance(newBalance);
					balance -= newCurrency;
					isDeposit = false;
					System.out.println(logTransaction(amount, currencyType, isDeposit));
				}	
				break;
			//Ends the program by stating how much balance is left and if any is withdrawn.
			case 4:
				if (balance ==0){
					System.out.println("Your remaining balance is " + balance + " U.S. Dollars \nGoodbye");
				}
				else {
					System.out.println("You successfully withdrew " 
				+ (Math.round(balance*100)/100.0) + " U.S. Dollars \nGoodbye");
				}
				System.exit(0); 
				break;
			default: 
				System.out.println("Input failed validation. Please try again.");
			}
			}
		

    }


    /**
     * deposit the amount of a specific currency to the account
     *
     * @param amount       the amount to be deposited.
     * @param currencyType the currency type will be the same as the type number used
     *                     in the convertCurrency(double, int, boolean) method. An Invalid type will result in a
     *                     deposit failure.
     * @return if deposit succeed, will return true. If failed, return false
     */
    public static boolean deposit(double amount, int currencyType) {
        //TODO: implementation here
    	//States whether the deposit succeeds or fails.
    	if (amount> 0 && currencyType>= 1 && currencyType <= 11){
			return true;
		}
		else return false;
    }

    /**
     * withdraw the value amount with a specific currency from the account. The withdraw amount should never exceed the current account balance, or the withdrawal will fail. If the currency is other than USD, a 0.5% convenience fee will be charged
     *
     * @param amount       the amount to be withdrawn.
     * @param currencyType the currency type will be the same as the type number used
     *                     in the convertCurrency(double, int, boolean) method. An invalid
     * 		         type will result a withdraw failure.
     * @return if withdraw succeed, will return true. If failed, return false
     */
    public static boolean withdraw(double amount, int currencyType) {
        //TODO: implementation here
    	//States whether the withdrawal succeeds or fails.
    	if (amount < 0 || currencyType< 1 || currencyType> 11){
    		return false;
    	}
    	else if (balance - amount < 0){
    		return false;
    	}
    	else return true;
    }

    /**
     * Convert the value amount between U.S. dollars and a specific currency.
     *
     * @param amount         The amount of the currency to be converted.
     * @param currencyType   The integer currencyType works as follows:
     *                       1 for usd (U.S. Dollars)
     *                       2 for eur (Euros)
     *                       3 for bri (British Pounds)
     *                       4 for inr (Indian Rupees)
     *                       5 for aus (Australian Dollars)
     *                       6 for cnd (Canadian Dollars)
     *                       7 for sid (Singapore Dollars)
     *                       8 for swf (Swiss Francs)
     *                       9 for mar (Malaysian Ringgits)
     *                       10 for jpy (Japanese Yen)
     *                       11 for cyr (Chinese Yuan Renminbi)
     * @param isConvertToUSD Tells the direction of the conversion. If the value is true, then the conversion is from a
     *                       foreign currency to USD. If the value is false, then the conversion is from USD to the
     *                       foreign currency
     * @return the converted amount
     */
    public static double convertCurrency(double amount, int currencyType, boolean isConvertToUSD) {
        //TODO: implementation here
    	// Converts the currency entered either into USD or into a different currency.
    	double newCurrency = 0;
    	
    	if (isConvertToUSD = false){
    		switch (currencyType){
    		case 1:
    			//USD
    			newCurrency = amount;
    			break;
    		case 2:
    			//Euros
    			newCurrency = 0.89 * amount; 
    			break;
    		case 3:
    			//British Pounds
    			newCurrency = 0.78 * amount;
    			break;
    		case 4:
    			//Indian Rupees
    			newCurrency = 66.53 * amount;
    			break;
    		case 5:
    			//Australian Dollars
    			newCurrency = 1.31 * amount;
    			break;
    		case 6:
    			//Canadian Dollars
    			newCurrency = 1.31 * amount;
    			break; 
    		case 7:
    			//Singapore Dollars
    			newCurrency = 1.37 * amount;
    			break; 
    		case 8:
    			//Swiss Franc
    			newCurrency = 0.97 * amount;
    			break; 
    		case 9:
    			//Malaysian Ringgit
    			newCurrency = 4.12 * amount;
    			break; 
    		case 10:
    			//Japanese Yen
    			newCurrency = 101.64 * amount;
    			break; 
    		case 11:
    			//Chinese Yuan Renminbi
    			newCurrency = 6.67 * amount;
    			break;
    		}
    	}
    	else {
    		switch (currencyType){
    		case 1:
    			//USD
    			newCurrency = amount;
    			break;
    		case 2:
    			//Euros
    			newCurrency = amount / 0.89;
    			break; 
    		case 3:
    			//British Pounds
    			newCurrency = amount / 0.78;
    			break; 
    		case 4:        
    			//Indian Rupees
    			newCurrency = amount / 66.53;
    			break; 
    		case 5:
    			//Australian Dollars
    			newCurrency = amount / 1.31;
    			break; 
    		case 6:
    			//Canadian Dollars
    			newCurrency = amount / 1.31;
    			break; 
    		case 7:
    			//Singapore Dollars
    			newCurrency = amount / 1.37;
    			break; 
    		case 8:
    			//Swiss Franc
    			newCurrency = amount / 0.97;
    			break; 
    		case 9:
    			//Malaysian Ringgit
    			newCurrency = amount / 4.12;
    			break; 
    		case 10:
    			//Japanese Yen
    			newCurrency = amount / 101.64;
    			break; 
    		case 11:
    			//Chinese Yuan Renminbi
    			newCurrency = amount / 6.67;
    			break;
    		}
    	}
    	return newCurrency;
    }

    /**
     * Displays message at the end of the withdraw, deposit, and endTransaction stating
     * how much the user just withdrew/deposited and what type (this will be used in both features B, C and D of the
     * main menu).
     *
     * @param amount       the amount of currency withdrew/deposited
     * @param currencyType the currency type will be the same as the type number used
     *                     in {@link #convertCurrency(double, int, boolean)}
     * @param isDeposit    if true log the deposit transaction, false log the withdraw transaction
     * @return Return the formatted log message as following examples:
     * You successfully withdrew 10.0 U.S. Dollars
     * You successfully deposited 30.0 Japanese Yen
     * <p>
     * The invalid input like invalid currencyType or negative amount,
     * will return a “Logging Error”
     */
    private static String logTransaction(double amount, int currencyType, boolean isDeposit) {
        //TODO: implementation here
    	//Prints the string at the end of the transaction after each selection.
    	String currencyWord = null;
    	switch (currencyType)
    	{
    	case 1: currencyWord = "U.S. dollars"; break; 
    	case 2: currencyWord = "Euros"; break;
    	case 3: currencyWord = "British Pounds"; break; 
    	case 4: currencyWord = "Indian Rupees"; break;
    	case 5: currencyWord = "Australian Dollars"; break; 
    	case 6: currencyWord = "Canadian Dollars"; break; 
    	case 7: currencyWord = "Singapore Dollars"; break; 
    	case 8: currencyWord = "Swiss Francs"; break;
    	case 9: currencyWord = "Malaysian Ringgit"; break;
    	case 10: currencyWord = "Japanese Yen"; break;
    	case 11: currencyWord = "Chinese Yuan Renminbi"; break; 
    	}
    	String message = "You successfully deposited " + amount + " " + currencyWord;
    	String message2 = "You successfully withdrew " + amount + " " + currencyWord;
    	String message3 = "Logging Error";
    	
    	if (isDeposit == true){
    		return message;
    	}
    	if (isDeposit == false){
    		return message2;
    	}
    	else {
    		return message3;
    	}
    	

    }

    /**
     * Prints the currency menu (see output examples), allows the user to make a selection from available currencies
     *
     * @param input the Scanner object you created at the beginning of the main method. Any value other than the
     *              11 valid valid currency types should generate an invalid value prompt. Print the list again
     *              and prompt user to select a valid value from the list. the currency type will be the same as
     *              the type number used in {@link #convertCurrency(double, int, boolean)}
     * @return an integer from 1-11 inclusive representing the user’s selection.
     */
    private static int currencyMenuOptionSelector(Scanner input) {
        //TODO: implementation here
    	// Prints the options of currency, and allows the user to input their choice.
    	int currencyType = 0;
    	while (true){
    	System.out.println("Please select the currency type:"
				+ "\n1. U.S. Dollars"
				+ "\n2. Euros"
				+ "\n3. British Pounds"
				+ "\n4. Indian Rupees"
				+ "\n5. Australian Dollars"
				+ "\n6. Canadian Dollars"
				+ "\n7. Singapore Dollars"
				+ "\n8. Swiss Francs"
				+ "\n9. Malaysian Ringgits"
				+ "\n10. Japanese Yen"
				+ "\n11. Chinese Yuan Renminbi");
		currencyType = input.nextInt();
		if (currencyType >= 1 && currencyType <= 11){
			break;
		}
		else {
			System.out.println("Input failed validation. Please try again.");
		}
    	}
		return currencyType;
    }

    /**
     * Prints the main menu (see output examples), allows the user to make a selection from available operations
     *
     * @param input the Scanner object you created at the beginning of the main method. Any value other than the 4
     *              valid selections should generate an invalid value prompt. Print the list again and prompt user to
     *              select a valid value from the list.
     * @return an integer from 1-4 inclusive representing the user’s selection.
     */
    private static int mainMenuOptionSelector(Scanner input) {
        //TODO: implementation here
		while (true){
			System.out.println("\nPlease select an option from the list below:"
					+ "\n1. Check the balance of your account"
					+ "\n2. Make a deposit"
					+ "\n3. Withdraw an amount in a specific currency"
					+ "\n4. End your session (and withdraw all remaining currency in U.S. Dollars)");
			int selection = input.nextInt();
			switch (selection){
			//Prints balance
			case 1: 
				return 1;
			//Deposit
			case 2: 
				return 2;
			//Withdrawal
			case 3: 
				return 3;
			//Ends the program by stating how much balance is left and if any is withdrawn.
			case 4:
				return 4;
			default: 
				System.out.println("Input failed validation. Please try again.");
			}
			}
    }

    /**
     * Prints the conversion table at the start of the program (see the output examples).
     */
    private static void printConversionTable() {
        //TODO: implementation here
    	// Prints the beginning menu.
    	System.out.println("\nCurrent rates are as follows:"
				+ "\n\n1 - U.S. Dollar - 1.00 "
				+ "\n2 - Euro - 0.89"
				+ "\n3 - British Pound - 0.78"
				+ "\n4 - Indian Rupee - 66.53"
				+ "\n5 - Australian Dollar - 1.31"
				+ "\n6 - Canadian Dollar - 1.31"
				+ "\n7 - Singapore Dollar - 1.37"
				+ "\n8 - Swiss Franc - 0.97"
				+ "\n9 - Malaysian Ringgit - 4.12"
				+ "\n10 - Japanese Yen - 101.64"
				+ "\n11 - Chinese Yuan Renminbi - 6.67");
    }
}
