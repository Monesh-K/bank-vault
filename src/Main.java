import db.Repository;
import screens.CustomerView;
import screens.LoginView;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Get the singleton instance of the repository
        Repository repository = Repository.getInstance();

        // Create a Scanner object to get user input
        Scanner scanner = new Scanner(System.in);

        // Main loop for the application
        while(true) {
            // Display the main menu
            System.out.println("-----------------------");
            System.out.println("Enter Your Choice: ");
            System.out.println("1. Create Customer Account" +
                    "\n2. Login" +
                    "\n3. Exit");
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
            // If the user chooses to exit, break the loop
            if (choice == 3) {
                System.out.println("Thank You.");
                break;
            }

            // Consume the remaining newline
            scanner.nextLine();

            // Process the user's choice
            switch(choice) {
                case 1 -> {
                    // Create a new customer account
                    CustomerView customerView = new CustomerView();
                    customerView.createAccount(repository, scanner);
                }
                case 2 -> {
                    // Login for an existing user
                    LoginView loginView = new LoginView();
                    loginView.getLoginInfo(repository, scanner);
                }
                default -> {
                    System.out.println("Invalid Choice.");
                }
            }
        }
    }
}
