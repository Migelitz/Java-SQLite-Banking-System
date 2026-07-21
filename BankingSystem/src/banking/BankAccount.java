package BankingSystem.src.banking;

// Represents a single bank account with a holder name and balance.
public class BankAccount {

    // The name of the account holder.
    private String accountHolder;

    // The current balance of the account.
    private double balance;

    // Create a new account and assign a default name if the input is blank.
    public BankAccount(String name) {
        if (!name.isBlank()) {
            this.accountHolder = name;
        } else {
            this.accountHolder = "Unknown Holder";
        }
        this.balance = 0.0;
    }

    // Set the account balance directly.
    public void setBalance(double initialBalance) {
        this.balance = initialBalance;
    }

    // Return the account holder name.
    public String getAccountHolder() {
        return accountHolder;
    }

    // Return the current balance.
    public double getBalance() {
        return balance;
    }

    // Deposit money into the account if the amount is positive.
    public void deposit(double amount) {
        if (amount > 0) {
            System.out.println("\nOld balance: P" + balance);
            balance += amount;
            System.out.println("New balance: P" + balance);
            DatabaseManager.updateAccount(this);
        } else {
            System.out.println("\nCannot deposit negative amounts!");
        }
    }

    // Withdraw money from the account if the amount is valid and available.
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("\nError: Exceeded balance. Action cancelled.");
        } else if (amount <= 0) {
            System.out.println("\nInvalid withdrawal amount!");
        } else {
            System.out.println("\nOld balance: P" + balance);
            balance -= amount;
            System.out.println("New balance: P" + balance);
            DatabaseManager.updateAccount(this);
        }
    }

    // Print the account holder name and current balance.
    public void displayAccountInfo() {
        System.out.println("Account Holder: " + accountHolder);
        System.out.println("Current Balance: " + balance);
    }
}