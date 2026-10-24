import java.util.ArrayList;
import java.util.List;

public class ATM {
    private double balance;
    private final List<Transaction> transactionHistory = new ArrayList<>();

    public ATM(double initialBalance) {
        if (!Double.isFinite(initialBalance) || initialBalance < 0.0) {
            throw new IllegalArgumentException("Initial balance must be finite and non-negative.");
        }
        balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (!isValidAmount(amount)) {
            return false;
        }

        double updatedBalance = balance + amount;
        if (!Double.isFinite(updatedBalance)) {
            return false;
        }

        balance = updatedBalance;
        transactionHistory.add(new Transaction("Deposit", amount, balance));
        return true;
    }

    public boolean withdraw(double amount) {
        if (!isValidAmount(amount) || amount > balance) {
            return false;
        }

        balance -= amount;
        transactionHistory.add(new Transaction("Withdrawal", amount, balance));
        return true;
    }

    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }

    private boolean isValidAmount(double amount) {
        return Double.isFinite(amount) && amount > 0.0;
    }

    public record Transaction(String type, double amount, double resultingBalance) {
    }
}
