import java.util.Locale;
import java.util.Scanner;

public class ATMInterface {
    private static final double STARTING_BALANCE = 10_000.00;

    private ATMInterface() {
    }

    public static void main(String[] args) {
        ATM atm = new ATM(STARTING_BALANCE);

        System.out.println("=== CodSoft ATM Practice Simulation ===");
        System.out.println("Simulated starting balance: " + formatMoney(atm.getBalance()));
        System.out.println("This is a practice simulation, not a real bank account.");

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            while (running) {
                printMenu();
                String choiceLine = readLine(scanner, "Choose an option (1-5): ");
                if (choiceLine == null) {
                    break;
                }

                int choice;
                try {
                    choice = Integer.parseInt(choiceLine.trim());
                } catch (NumberFormatException exception) {
                    System.out.println("Invalid choice. Enter a number from 1 to 5.");
                    continue;
                }

                switch (choice) {
                    case 1 -> showBalance(atm);
                    case 2 -> processDeposit(scanner, atm);
                    case 3 -> processWithdrawal(scanner, atm);
                    case 4 -> showTransactionHistory(atm);
                    case 5 -> running = false;
                    default -> System.out.println("Invalid choice. Enter a number from 1 to 5.");
                }
            }
        }

        System.out.println("Thank you for using the ATM Practice Simulation. Goodbye!");
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Check balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transaction history");
        System.out.println("5. Exit");
    }

    private static void showBalance(ATM atm) {
        System.out.println("Current balance: " + formatMoney(atm.getBalance()));
    }

    private static void processDeposit(Scanner scanner, ATM atm) {
        Double amount = readPositiveAmount(scanner, "Enter deposit amount: ");
        if (amount == null) {
            return;
        }

        if (atm.deposit(amount)) {
            System.out.println("Deposit successful. New balance: " + formatMoney(atm.getBalance()));
        } else {
            System.out.println("Deposit could not be completed. The amount may exceed the supported balance.");
        }
    }

    private static void processWithdrawal(Scanner scanner, ATM atm) {
        Double amount = readPositiveAmount(scanner, "Enter withdrawal amount: ");
        if (amount == null) {
            return;
        }

        if (atm.withdraw(amount)) {
            System.out.println("Withdrawal successful. New balance: " + formatMoney(atm.getBalance()));
        } else {
            System.out.println("Withdrawal failed. Check the amount and available balance.");
        }
    }

    private static Double readPositiveAmount(Scanner scanner, String prompt) {
        while (true) {
            String amountLine = readLine(scanner, prompt);
            if (amountLine == null) {
                return null;
            }

            try {
                double amount = Double.parseDouble(amountLine.trim());
                if (Double.isFinite(amount) && amount > 0.0) {
                    return amount;
                }
            } catch (NumberFormatException exception) {
                // Give the user another chance after malformed input.
            }

            System.out.println("Invalid amount. Enter a finite number greater than zero.");
        }
    }

    private static String readLine(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.hasNextLine() ? scanner.nextLine() : null;
    }

    private static void showTransactionHistory(ATM atm) {
        var transactions = atm.getTransactionHistory();
        if (transactions.isEmpty()) {
            System.out.println("No successful transactions have been recorded.");
            return;
        }

        System.out.println("Transaction history:");
        for (int index = 0; index < transactions.size(); index++) {
            ATM.Transaction transaction = transactions.get(index);
            System.out.printf(
                    Locale.US,
                    "%d. %s | Amount: %s | Balance after transaction: %s%n",
                    index + 1,
                    transaction.type(),
                    formatMoney(transaction.amount()),
                    formatMoney(transaction.resultingBalance()));
        }
    }

    private static String formatMoney(double amount) {
        String currency = System.out.charset().newEncoder().canEncode("₹") ? "₹" : "INR ";
        return String.format(Locale.US, "%s%,.2f", currency, amount);
    }
}
