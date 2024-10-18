package db;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.CustomerDTO;
import dto.TransactionDTO;

public class Repository {
    // File paths for customer and transaction data
    public static final String CUSTOMERS_FILE = "D:/ZSGS/Banking_Application/src/db/bank_db";
    public static final String TRANSACTIONS_FILE = "D:/ZSGS/Banking_Application/src/db/transaction_history";

    // Singleton instance of the repository
    private static Repository repository;

    // Lists to store customers and transactions in memory
    public List<CustomerDTO> customersList;
    public List<TransactionDTO> transactionsList;

    // Scanner to read data from files
    Scanner scanner;

    // Private constructor to initialize repository data
    private Repository() {
        customersList = new ArrayList<>();
        transactionsList = new ArrayList<>();

        // Ensure the customer file exists and load customers into memory
        try {
            File customerFile = new File(CUSTOMERS_FILE);
            if (!customerFile.exists()) {
                customerFile.createNewFile();  // Create the file if it does not exist
            }

            // Read the customer data from the file
            scanner = new Scanner(customerFile);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] customerData = line.split(" ");
                if (customerData.length < 2) {
                    continue; // Skip this line if it's invalid
                }
                // Create a new CustomerDTO object from the file data and add it to the list
                CustomerDTO customerDTO = new CustomerDTO(Integer.parseInt(customerData[0]),
                        Integer.parseInt(customerData[1]),
                        customerData[2],
                        Double.parseDouble(customerData[3]),
                        customerData[4]);
                customersList.add(customerDTO);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // Ensure the transaction file exists and load transactions into memory
        try {
            File transactionFile = new File(TRANSACTIONS_FILE);
            if (!transactionFile.exists()) {
                transactionFile.createNewFile();  // Create the file if it does not exist
            }

            // Read the transaction data from the file
            scanner = new Scanner(transactionFile);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] transactionData = line.split(" ");

                if (transactionData.length < 2) {
                    continue; // Skip this line if it's invalid
                }
                // Create a new TransactionDTO object from the file data and add it to the list
                TransactionDTO transactionDTO = new TransactionDTO(transactionData[0], transactionData[1]);
                transactionsList.add(transactionDTO);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // Method to return the singleton instance of the repository
    public static Repository getInstance(){
        if(repository == null){
            repository = new Repository();
        }
        return repository;
    }

    // Method to add a new customer and their first transaction (account creation) to the repository
    public void addCustomer(CustomerDTO customerDTO, TransactionDTO transactionDTO) {
        customersList.add(customerDTO);
        addCustomerToFile(customerDTO);  // Save the customer to file
        addTransaction(transactionDTO);  // Save the transaction to file
    }

    // Method to add a new transaction to the repository
    public void addTransaction(TransactionDTO transaction){
        transactionsList.add(transaction);
        addTransactionToFile(transaction);  // Save the transaction to file
    }

    // Save transaction data to the file
    public void addTransactionToFile(TransactionDTO transactionDTO) {
        File file = new File(TRANSACTIONS_FILE);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.newLine();  // Move to a new line
            bufferedWriter.write(transactionDTO.toString());  // Write the transaction data
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    // Save customer data to the file
    public void addCustomerToFile(CustomerDTO customerDTO) {
        File file = new File(CUSTOMERS_FILE);
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.newLine();  // Move to a new line
            bufferedWriter.write(customerDTO.toString());  // Write the customer data
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    // Method to get customer details by name
    public CustomerDTO getCustomer(String name) {
        for(CustomerDTO customerDTO : customersList){
            if(customerDTO.getName().equals(name)){
                return customerDTO;  // Return the customer if found
            }
        }
        return null;  // Return null if no customer is found
    }

    // Method to encrypt the customer's password (simple shift encryption)
    public String encryptPassword(String password) {
        StringBuilder encryptedPassword = new StringBuilder();
        for(int i = 0; i < password.length(); i++){
            char ch = password.charAt(i);
            if(ch == 'Z'){
                encryptedPassword.append('A');
            }
            else if(ch == 'z'){
                encryptedPassword.append('a');
            } else if (ch == '9') {
                encryptedPassword.append('0');
            }
            else if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || (ch >= '0' && ch <= '9')){
                encryptedPassword.append((char)(ch + 1));  // Shift character by one
            }
            else{
                encryptedPassword.append(ch);  // Leave non-alphanumeric characters as is
            }
        }
        return encryptedPassword.toString();
    }

    // Method to show transaction history for a specific customer
    public void showTransactions(CustomerDTO loggedInUser) {
        int customerId = loggedInUser.getCustomerId();  // Get the customer's ID
        try {
            File file = new File(TRANSACTIONS_FILE);
            scanner = new Scanner(file);

            // Read through the transaction file
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] transactionData = line.split(" ");

                if (transactionData.length < 2) {
                    continue;  // Skip invalid lines
                }

                // Check if the transaction belongs to the logged-in user
                String[] accountData = transactionData[1].split("@");
                if(accountData[0].equals(customerId + "")){
                    System.out.println();
                    System.out.println(transactionData[1].replace("@", " "));
                    System.out.println(transactionData[2].replace("#", " "));
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
