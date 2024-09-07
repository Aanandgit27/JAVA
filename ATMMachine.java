import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ATMMachine {
    private double balance;
    private int pin;
    private List<String> transactionHistory;

    public ATMMachine(double initialBalance, int initialPin) {
        this.balance = initialBalance;
        this.pin = initialPin;
        this.transactionHistory = new ArrayList<>();
    }

    public void displayMenu() {
        System.out.println("ATM Menu:");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Cash");
        System.out.println("3. Withdraw Cash");
        System.out.println("4. Change PIN");
        System.out.println("5. View Transaction History");
        System.out.println("6. Exit");
    }

    public boolean authenticate(int enteredPin) {
        return this.pin == enteredPin;
    }

    public void checkBalance() {
        System.out.println("Your current balance is: " + balance);
        transactionHistory.add("Checked balance");
    }

    public void depositCash(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
            transactionHistory.add("Deposited " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdrawCash(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrew: " + amount);
            transactionHistory.add("Withdrew " + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient funds.");
        }
    }

    public void changePin(int newPin) {
        if (newPin > 0) {
            this.pin = newPin;
            System.out.println("PIN changed successfully.");
            transactionHistory.add("Changed PIN");
        } else {
            System.out.println("Invalid PIN.");
        }
    }

    public void viewTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public static void main(String[] args) {
        ATMMachine atm = new ATMMachine(1000.0, 1234);  // Initial balance and PIN

        Scanner scanner = new Scanner(System.in);
        int enteredPin;

        System.out.print("Enter your PIN: ");
        enteredPin = scanner.nextInt();

        if (atm.authenticate(enteredPin)) {
            int choice;
            do {
                atm.displayMenu();
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        atm.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        atm.depositCash(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdrawCash(withdrawAmount);
                        break;
                    case 4:
                        System.out.print("Enter new PIN: ");
                        int newPin = scanner.nextInt();
                        atm.changePin(newPin);
                        break;
                    case 5:
                        atm.viewTransactionHistory();
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            } while (choice != 6);

        } else {
            System.out.println("Invalid PIN. Access denied.");
        }

        scanner.close();
    }
}
