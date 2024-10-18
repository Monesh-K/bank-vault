package screens;

import dto.CustomerDTO;
import db.Repository;
import dto.TransactionDTO;

import java.util.Scanner;

public class CustomerView {
    public static final double INITIAL_BALANCE = 10000.0;

    // Method to create a new customer account
    public void createAccount(Repository repository, Scanner scanner) {
        // Prompt the user to enter a name for the new account
        System.out.println("Enter User Name: ");
        String name = scanner.nextLine();

        // Check if a customer with the same name already exists
        if (repository.getCustomer(name) == null) {
            String confirmPassword;

            // Loop until the password and its confirmation match
            while (true) {
                System.out.println("Enter Password: ");
                String password = scanner.nextLine();
                System.out.println("Re-enter the password: ");
                confirmPassword = scanner.nextLine();

                // If passwords match, break out of the loop
                if (confirmPassword.equals(password)) {
                    break;
                } else {
                    // If passwords don't match, prompt the user to try again
                    System.out.println("Password doesn't match! Try again.");
                }
            }

            // Create a new CustomerDTO object with the user's input and initial balance
            CustomerDTO customerDTO = new CustomerDTO(name, INITIAL_BALANCE, repository.encryptPassword(confirmPassword));

            // Create a new TransactionDTO object to log the account creation event
            TransactionDTO transactionDTO = new TransactionDTO(customerDTO.getCustomerDetails(), "Account creation");

            // Add the customer and transaction to the repository
            repository.addCustomer(customerDTO, transactionDTO);

            // Notify the user that the account has been successfully created
            System.out.println("Customer Created.");
        } else {
            // If a customer with the same name already exists, notify the user
            System.out.println("User already exists with same name. Try other name.");
        }
    }
}
