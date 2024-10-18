package dto;

public class TransactionDTO {
    // Static variable to maintain a unique transaction ID.
    private static int transaction_id = 0;

    // Instance variables to hold transaction details.
    private String customerDetails;  // Stores the details of the customer involved in the transaction.
    private String description;      // Stores a description of the transaction (e.g., deposit, withdrawal).

    // Constructor that initializes the transaction with customer details and description.
    public TransactionDTO(String customerDetails, String description) {
        // Increment the static transaction_id for each new transaction to ensure uniqueness.
        this.transaction_id = ++TransactionDTO.transaction_id;
        this.customerDetails = customerDetails;
        this.description = description;
    }

    // Overriding the toString method to return the transaction details as a formatted string.
    // Spaces are replaced with special characters ("@" for customerDetails, "#" for description) for safe file storage.
    @Override
    public String toString() {
        return transaction_id + " " +
                customerDetails.replace(" ", "@") + " " +  // Replacing spaces with "@" for customerDetails.
                description.replace(" ", "#");             // Replacing spaces with "#" for description.
    }
}
