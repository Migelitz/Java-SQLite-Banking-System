package BankingSystem.src;

import java.util.InputMismatchException;
import java.util.Scanner;
import BankingSystem.src.banking.BankAccount;
import BankingSystem.src.banking.DatabaseManager;

public class Main {
    public static void main(String[] args) {

        // Initialize the database table before starting the program.
        DatabaseManager.createTable();

        // Create the scanner for reading user input.
        Scanner input = new Scanner(System.in);

        // Ask the user for the account holder name.
        System.out.println("Welcome to banking system!\n");
        System.out.print("Enter name to create account or access existing account: ");
        String name = input.nextLine();

        // Create a bank account object for the entered name.
        BankAccount myAccount = new BankAccount(name);

        // Save the account only if it does not already exist in the database.
        if (!DatabaseManager.getAccount(myAccount)) {
            DatabaseManager.saveAccount(myAccount);

            System.out.println("\n=======================================");
            System.out.println("Welcome! " + myAccount.getAccountHolder());
        } else {
            System.out.println("\n=======================================");
            System.out.println("Welcome back! " + myAccount.getAccountHolder());
        }

        int userInput = 0;

        // Display the menu until the user chooses to quit.
        while (userInput != 4) {
            System.out.println("=======================================");
            System.out.println("Options:");
            System.out.println("1 - Deposit\n2 - Withdraw\n3 - Display bank account\n4 - Quit");
            System.out.print("Enter your choice: ");
            
            try {
                userInput = input.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("=======================================");
                System.out.println("!!! This only accepts numbers. Try again. !!!");
                input.nextLine(); // Clears invalid input
                continue;
            }
            
            System.out.println("=======================================");            
          
            switch (userInput) {
                case 1:
                    try {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = input.nextDouble();
                        myAccount.deposit(depositAmount);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\n!!! This only accept numbers and decimals. Try again. !!!");
                        input.nextLine(); // Clears invalid input
                        break;
                    }
                case 2:
                    try {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = input.nextDouble();
                        myAccount.withdraw(withdrawAmount);
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("\n!!! This only accept numbers and decimals. Try again. !!!");
                        input.nextLine();
                        break;
                    }
                case 3:
                    myAccount.displayAccountInfo();
                    break;
                case 4:
                    System.out.println("!!! Exiting the banking system. Goodbye! !!!");
                    System.out.println("=======================================");
                    break;
                default:
                    System.out.println("!!! Invalid option. Please try again. !!!");
                    break;
            }
        }

        input.close();
    }
}
