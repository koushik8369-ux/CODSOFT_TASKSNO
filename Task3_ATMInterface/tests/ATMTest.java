import java.util.List;

public class ATMTest {
    private ATMTest() {
    }

    public static void main(String[] args) {
        testInitialBalance();
        testSuccessfulTransactions();
        testInvalidDeposits();
        testInvalidWithdrawals();
        testHistoryIsReadOnly();
        System.out.println("All ATM tests passed.");
    }

    private static void testInitialBalance() {
        ATM atm = new ATM(1_000.00);
        checkClose(atm.getBalance(), 1_000.00, "Initial balance");
        check(atm.getTransactionHistory().isEmpty(), "History should initially be empty");
    }

    private static void testSuccessfulTransactions() {
        ATM atm = new ATM(1_000.00);
        check(atm.deposit(250.50), "A positive deposit should succeed");
        checkClose(atm.getBalance(), 1_250.50, "Balance after deposit");
        check(atm.withdraw(200.25), "A withdrawal within the balance should succeed");
        checkClose(atm.getBalance(), 1_050.25, "Balance after withdrawal");

        List<ATM.Transaction> history = atm.getTransactionHistory();
        check(history.size() == 2, "Both successful transactions should be recorded");
        check("Deposit".equals(history.get(0).type()), "Deposit type should be recorded");
        checkClose(history.get(0).amount(), 250.50, "Recorded deposit amount");
        checkClose(history.get(0).resultingBalance(), 1_250.50, "Recorded deposit balance");
        check("Withdrawal".equals(history.get(1).type()), "Withdrawal type should be recorded");
        checkClose(history.get(1).amount(), 200.25, "Recorded withdrawal amount");
        checkClose(history.get(1).resultingBalance(), 1_050.25, "Recorded withdrawal balance");
    }

    private static void testInvalidDeposits() {
        ATM atm = new ATM(500.00);
        check(!atm.deposit(0.0), "A zero deposit should fail");
        check(!atm.deposit(-10.0), "A negative deposit should fail");
        check(!atm.deposit(Double.NaN), "A NaN deposit should fail");
        check(!atm.deposit(Double.POSITIVE_INFINITY), "An infinite deposit should fail");
        checkClose(atm.getBalance(), 500.00, "Invalid deposits must not change the balance");
        check(atm.getTransactionHistory().isEmpty(), "Invalid deposits must not be recorded");

        ATM maximumBalanceAtm = new ATM(Double.MAX_VALUE);
        check(!maximumBalanceAtm.deposit(Double.MAX_VALUE),
                "A deposit that overflows the balance should fail");
        checkClose(maximumBalanceAtm.getBalance(), Double.MAX_VALUE,
                "An overflowing deposit must not change the balance");
        check(maximumBalanceAtm.getTransactionHistory().isEmpty(),
                "An overflowing deposit must not be recorded");
    }

    private static void testInvalidWithdrawals() {
        ATM atm = new ATM(500.00);
        check(!atm.withdraw(0.0), "A zero withdrawal should fail");
        check(!atm.withdraw(-10.0), "A negative withdrawal should fail");
        check(!atm.withdraw(Double.NaN), "A NaN withdrawal should fail");
        check(!atm.withdraw(Double.POSITIVE_INFINITY), "An infinite withdrawal should fail");
        check(!atm.withdraw(500.01), "A withdrawal greater than the balance should fail");
        checkClose(atm.getBalance(), 500.00, "Invalid withdrawals must not change the balance");
        check(atm.getTransactionHistory().isEmpty(), "Invalid withdrawals must not be recorded");
    }

    private static void testHistoryIsReadOnly() {
        ATM atm = new ATM(100.00);
        check(atm.deposit(1.00), "Setup deposit should succeed");
        List<ATM.Transaction> history = atm.getTransactionHistory();
        boolean rejectedModification = false;
        try {
            history.clear();
        } catch (UnsupportedOperationException exception) {
            rejectedModification = true;
        }

        check(rejectedModification, "Retrieved transaction history should be immutable");
        check(atm.getTransactionHistory().size() == 1, "History must remain intact");
    }

    private static void checkClose(double actual, double expected, String description) {
        check(Math.abs(actual - expected) < 0.000_001,
                description + ": expected " + expected + " but was " + actual);
    }

    private static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError(message);
        }
    }
}
