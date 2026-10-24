# CodSoft Java Development Internship — Task 3: ATM Interface

## Project overview

A console-based ATM practice simulation demonstrating basic account operations and object-oriented design. It starts with a simulated balance of ₹10,000.00. This program is for practice only and is not connected to a real bank account.

## Features

- View the current balance, formatted to two decimal places.
- Deposit and withdraw finite positive amounts.
- Reject withdrawals that exceed the available balance.
- Record successful deposits and withdrawals with the resulting balance.
- Review transaction history for the current program session.
- Recover from invalid menu and amount input.
- Exit with a friendly closing message.

## Technology requirements

- Java 21 or later.
- Standard Java libraries only; no database or external dependencies.
- Console input is read using `Scanner`.

## Project structure

```text
Task3_ATMInterface/
├── src/
│   ├── ATM.java
│   └── ATMInterface.java
├── tests/
│   └── ATMTest.java
├── out/
└── README.md
```

All Java classes use the default package.

## Compile and run

Run these commands from the `Task3_ATMInterface` directory in Windows PowerShell:

```powershell
javac -Xlint:all -d out src\ATM.java src\ATMInterface.java
java -cp out ATMInterface
```

## Compile and run tests

The tests use explicit checks and do not require JVM assertion flags:

```powershell
javac -Xlint:all -cp out -d out tests\ATMTest.java
java -cp out ATMTest
```

The test program prints `All ATM tests passed.` when every check succeeds and throws an `AssertionError` if a check fails.

## Example menu and output

```text
=== CodSoft ATM Practice Simulation ===
Simulated starting balance: ₹10,000.00
This is a practice simulation, not a real bank account.

1. Check balance
2. Deposit
3. Withdraw
4. Transaction history
5. Exit
Choose an option (1-5): 2
Enter deposit amount: 500
Deposit successful. New balance: ₹10,500.00
```

## Input validation

- Menu options must be whole numbers from 1 through 5.
- Deposit and withdrawal amounts must parse as finite numbers greater than zero.
- Withdrawals larger than the current balance are rejected.
- Invalid transactions do not change the balance or appear in transaction history.
- Invalid entries are consumed and the user is prompted again. End-of-input exits cleanly instead of repeatedly prompting.
- If a deposit would overflow the supported numeric balance, it is rejected.
- The console displays the rupee sign when its output encoding supports it; otherwise, it displays `INR`.

## Limitations

- The balance and transaction history are held in memory and are lost when the program exits.
- This is a learning exercise, not a secure or production banking application.
- Amount calculations use Java `double`, which is not intended for production financial accounting.
