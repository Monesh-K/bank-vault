package dto;

public class CustomerDTO {
    // Static variables to maintain the default customer and account IDs.
    private static int defaultCustId = 0;
    private static int defaultAccId = 0;

    // Instance variables for each customer.
    private int customerId = 0;
    private int accountId = 0;
    private String name;
    private double balance;
    private String password;

    // Constructor that initializes a customer with specific details (used when loading data from file).
    public CustomerDTO(int customerId, int accountId, String name, double balance, String password) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.name = name;
        this.balance = balance;
        this.password = password;

        // Update the default customer and account IDs to ensure unique IDs for future customers.
        CustomerDTO.defaultCustId = Math.max(customerId, CustomerDTO.defaultCustId);
        CustomerDTO.defaultAccId = Math.max(accountId, CustomerDTO.defaultAccId);
    }

    // Constructor that generates a new customer with unique IDs and initializes other fields.
    public CustomerDTO(String name, double balance, String password) {
        this.customerId = ++CustomerDTO.defaultCustId;  // Generate a unique customer ID.
        this.accountId = ++CustomerDTO.defaultAccId;    // Generate a unique account ID.
        this.name = name;
        this.balance = balance;
        this.password = password;
    }

    // Getter for customerId.
    public int getCustomerId() {
        return customerId;
    }

    // Getter for accountId.
    public int getAccountId() {
        return accountId;
    }

    // Getter for customer name.
    public String getName() {
        return name;
    }

    // Getter for customer balance.
    public double getBalance() {
        return balance;
    }

    // Getter for customer password.
    public String getPassword() {
        return password;
    }

    // Method that returns a string containing customer details excluding the password.
    public String getCustomerDetails() {
        return customerId + " " + accountId + " " + name + " " + balance;
    }

    // Overriding the toString method to return customer details along with the password.
    @Override
    public String toString() {
        return customerId + " " + accountId + " " + name + " " + balance + " " + password;
    }

    // Method to withdraw a certain amount from the customer's balance.
    public void withdraw(int amount) {
        this.balance -= amount;
    }

    // Method to deposit a certain amount to the customer's balance.
    public void deposit(int amount) {
        this.balance += amount;
    }
}
