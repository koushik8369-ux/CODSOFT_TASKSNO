# CodSoft Java Development Internship — Task 3: ATM Interface

A console-based ATM practice application with a simulated starting balance of INR 10,000.00. It is for practice only and is not a real banking system.

## Features

- Check the balance, deposit, and withdraw.
- Validate amounts and reject withdrawals above the available balance.
- Show successful transactions with their amounts and resulting balances.
- Handle invalid input and exit cleanly.
- Format amounts to two decimal places, using INR when the terminal cannot display the rupee symbol.

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

## Compile and run

From this directory in Windows PowerShell:

```powershell
javac -Xlint:all -d out src\ATM.java src\ATMInterface.java
java -cp out ATMInterface
```

## Compile and run tests

```powershell
javac -Xlint:all -cp out -d out tests\ATMTest.java
java -cp out ATMTest
```

The tests use explicit checks and need no additional libraries or JVM flags.

## Limitations

Account balance and transaction history are stored in memory and are lost when the program exits. This project is an educational simulation, not a production banking application.
