package screens;

import db.Repository;
import dto.CustomerDTO;
import dto.TransactionDTO;

import java.util.InputMismatchException;
import java.util.Scanner;

public class LoginView {

    // Method to get login information from the user
    public void getLoginInfo(Repository repository, Scanner scanner) {
        // Prompt the user to enter their username
        System.out.println("Enter User Name: ");
        String customerName = scanner.nextLine();

        // Fetch the customer details from the repository
        CustomerDTO customer = repository.getCustomer(customerName);

        // If the customer exists, prompt for the password
        if (customer != null) {
            System.out.println("Enter Password");
            String password = scanner.nextLine();

            // Encrypt the entered password
            String encryptedPassword = repository.encryptPassword(password);

            // Check if the encrypted password matches the stored password
            if (customer.getPassword().equals(encryptedPassword)) {
                System.out.println("Welcome " + customerName);

                // Display the user options menu after successful login
                displayUserOptions(repository, scanner, customer);
            } else {
                // If the password is incorrect, notify the user
                System.out.println("Invalid Password!");
            }
        } else {
            // If the username is invalid, notify the user
            System.out.println("Invalid user!");
        }
    }

    // Method to display the user options after login
    private void displayUserOptions(Repository repository, Scanner scanner, CustomerDTO loggedInUser) {
        // Loop to keep showing the menu until the user logs out
        while (true) {
            System.out.println("-----------------------");
            System.out.println("Enter Your Choice: ");
            System.out.println("1. Withdraw" +
                    "\n2. Deposit" +
                    "\n3. Transfer" +
                    "\n4. Show Transaction History" +
                    "\n5. Logout");
            System.out.println("-----------------------");

            // Get the user's choice
            int choice;
            while(true) {
                try {
                    choice = scanner.nextInt();
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a number");
                    e.printStackTrace();
                }
            }
            // If the choice is to log out, break the loop
            if (choice == 5) {
                loggedInUser = null;
                break;
            }
            scanner.nextLine();

            // Process the user's choice
            switch (choice) {
                case 1 -> {
                    // Withdrawal process
                    System.out.println("Enter withdrawal amount: ");
                    int amount;
                    while(true) {
                        try {
                            amount = scanner.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a number");
                            e.printStackTrace();
                        }
                    }
                    scanner.nextLine();

                    // Check if the balance is sufficient for withdrawal
                    if (loggedInUser.getBalance() - amount > 1000) {
                        loggedInUser.withdraw(amount);

                        // Log the transaction
                        TransactionDTO transactionDTO = new TransactionDTO(loggedInUser.getCustomerDetails(), "Amount Withdraw");
                        repository.addTransaction(transactionDTO);

                        // Display updated balance
                        System.out.println("Withdrawal successful. Your Balance: " + loggedInUser.getBalance());
                    } else {
                        // If the balance is insufficient, notify the user
                        System.out.println("Insufficient Balance. Your Balance: " + loggedInUser.getBalance());
                    }
                }
                case 2 -> {
                    // Deposit process
                    System.out.println("Enter deposit amount: ");
                    int amount;
                    while(true) {
                        try {
                            amount = scanner.nextInt();
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Please enter a number");
                            e.printStackTrace();
                        }
                    }
                    scanner.nextLine();

                    // Add the deposited amount to the user's balance
                    loggedInUser.deposit(amount);

                    // Log the transaction
                    TransactionDTO transactionDTO = new TransactionDTO(loggedInUser.getCustomerDetails(), "Amount Deposit");
                    repository.addTransaction(transactionDTO);

                    // Display updated balance
                    System.out.println("Deposit successful. Your Balance: " + loggedInUser.getBalance());
                }
                case 3 -> {
                    // Transfer process
                    System.out.println("Enter Receiver's User name: ");
                    String toName = scanner.nextLine();

                    // Fetch the receiver's details from the repository
                    CustomerDTO receiver = repository.getCustomer(toName);

                    if (receiver != null) {
                        System.out.println("Enter amount to transfer: ");
                        int amount;
                        while(true) {
                            try {
                                amount = scanner.nextInt();
                                break;
                            } catch (InputMismatchException e) {
                                System.out.println("Please enter a number");
                                e.printStackTrace();
                            }
                        }
                        scanner.nextLine();

                        // Check if the balance is sufficient for the transfer
                        if (loggedInUser.getBalance() - amount > 1000) {
                            // Deduct the amount from the sender's balance
                            loggedInUser.withdraw(amount);

                            // Log the transfer from the sender's account
                            TransactionDTO fromTransactionDTO = new TransactionDTO(loggedInUser.getCustomerDetails(), "Amount Transfer to " + toName);
                            repository.addTransaction(fromTransactionDTO);

                            // Add the amount to the receiver's balance
                            receiver.deposit(amount);

                            // Log the transaction for the receiver
                            TransactionDTO toTransactionDTO = new TransactionDTO(receiver.getCustomerDetails(), "Amount Received from " + loggedInUser.getName());
                            repository.addTransaction(toTransactionDTO);
                            System.out.println("Transfer Successful.");
                        } else {
                            // If the balance is insufficient, notify the user
                            System.out.println("Insufficient Balance. Your Balance: " + loggedInUser.getBalance());
                        }
                    } else {
                        // If the receiver does not exist, notify the user
                        System.out.println("Invalid User!");
                    }
                }
                case 4 -> {
                    // Show transaction history for the logged-in user
                    repository.showTransactions(loggedInUser);
                }
            }
        }
    }
}
