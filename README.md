# Bank Vault

This is a console-based banking application that allows users to create accounts, login, and perform banking operations such as withdrawals, deposits, and transfers. All data is saved in two files: `bank_db.txt`, which stores customer account details, and `transaction_history.txt`, which logs all transactions. The application also ensures password encryption for added security.

## Features

- **Account Creation**: Users can create new accounts with encrypted passwords and an initial balance of 10,000.
- **Login**: Users can securely log into their accounts.
- **Withdraw, Deposit, Transfer**: Users can perform basic banking operations while ensuring minimum balance requirements.
- **Transaction History**: Users can view their transaction history stored in `transaction_history.txt`.
- **File Storage**: Customer details and transaction histories are stored persistently in text files.

## How It Works

1. **Account Creation**: A new customer is created with a unique customer ID and account ID, along with a default balance of 10,000. The password is encrypted and stored securely.
2. **Banking Operations**: Logged-in users can withdraw, deposit, or transfer money to other accounts while adhering to minimum balance requirements.
3. **Transaction Logging**: Each transaction (account creation, withdrawal, deposit, transfer) is logged in the `transaction_history.txt` file.

## Screenshots

### 1. Home Screen
When the application starts, two files are created: `bank_db.txt` for storing account details and `transaction_history.txt` for storing transaction logs.
![image](https://github.com/user-attachments/assets/48e33474-3fad-45b3-807e-e913345e48c2)

---

### 2. Creating a New Account
In this screen, the user is prompted to enter and re-enter the password for confirmation. The password is then encrypted and stored along with a unique customer ID, account ID, and the default balance of 10,000. A transaction for account creation is also logged in the `transaction_history.txt`.
![image](https://github.com/user-attachments/assets/3aae8d5c-69ad-4d0b-a2c5-fd77bbde30ab)

---

### 3. bank_db.txt File
This file stores all customer account details, including customer ID, account ID, name, balance, and encrypted password.
![image](https://github.com/user-attachments/assets/8ed102f2-d5de-4bb5-a2e7-49dc7a5a85ed)

---

### 4. Logged In: Banking Operations
Once a user is logged in, they are presented with several options, including withdrawal, deposit, transfer, and viewing transaction history.
![image](https://github.com/user-attachments/assets/fb0295e8-3497-4017-8d2c-76c48f7279a9)

---

### 5. Withdrawal Option
The user is prompted to enter an amount to withdraw. It ensures that the account maintains a minimum balance of 1,000 after withdrawal.
![image](https://github.com/user-attachments/assets/e5d50041-b55f-48f4-b028-0106034a763e)

---

### 6. Deposit Option
The user can deposit a specified amount into their account.
![image](https://github.com/user-attachments/assets/7ed96f95-aae3-4ec9-a1a7-c7201364bb6e)

---

### 7. Transfer Option
The transfer option prompts for the receiver's username and the amount to transfer, ensuring the recipient exists in the system.
![image](https://github.com/user-attachments/assets/5d14b594-8d24-4b91-bac0-8bedcbd84a3d)

---

### 8. transaction_history.txt File
This file logs all transactions, such as account creation, withdrawals, deposits, and transfers, in a structured format.
![image](https://github.com/user-attachments/assets/7adcf4c4-4b11-4b9d-bf62-b8fcafbc188b)

---
