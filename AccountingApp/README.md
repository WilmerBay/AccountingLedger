# Java Accounting Ledger App

This is a simple command-line accounting ledger built in Java. It allows users to track deposits and payments in real time, storing each transaction with a timestamp in a persistent CSV file. The interface is intentionally minimal and user-proof — guiding even the most non-technical users with clear options and readable output.

---

## 🌱 Project Purpose & Background

This project was built to for Year Up United's Learn to Code Academy to simulate the experience of using a basic accounting ledger. From the start, the goal was to create a console-based interface that feels intuitive, fast, and transparent — no hidden logic, just smart features that get out of the way.

The system enforces good accounting practices automatically:
- Payments (debits) are always stored as negative values
- Deposits (credits) are always positive
- All entries are sorted newest first
- Each line is timestamped and formatted clearly in both the ledger view and in the saved file

From sorting logic to user prompts, the design choices are all meant to make this tool as seamless as possible.

---

## 🧭 Menu Walkthrough

When the program starts, the user is greeted with a clean home screen:

```
 Home Screen 
D) Add Deposit
P) Make Payment
L) Go to Ledger
X) Close Program
```

Each option leads to a focused flow:
- **Add Deposit / Make Payment**:  
Prompts the user for a description, vendor name, and amount. The amount is sanitized to ensure it's stored correctly (deposits positive, payments negative).

```
Description: Freelance Invoice
Vendor: Client A
Amount: 1000
  aved: 2025-04-27 | 12:45 | Freelance Invoice | Client A | 1000.0
```

- **Go to Ledger**:  
Opens the full transaction view. From here, users can:
- View all transactions
- Filter to only Deposits or Payments
- Run Reports
- Return to Home

```
Ledger
A) All
D) Deposits
P) Payments
R) Reports
H) Home
```

- **Reports Menu**:  
Allows users to generate insights based on time or vendor:
```
1) Month to Date
2) Previous Month
3) Year to Date
4) Previous Year
5) Search by Vendor
0) Back
```

Each of these filters the full transaction list using `LocalDate.now()` and Java's Stream API to show only relevant entries.

---

## 📂 File Structure

- `AccountingApp.java` – The main application file and menu logic
- `Transaction.java` – The model class that represents each individual transaction
- `transactions.csv` – The data file that stores all transactions (auto-generated)

---

## 🚀 Features

- 📅 **Auto-formatted ledger:** Every transaction is printed in the format:
```
YYYY-MM-DD | HH:MM:SS | Description | Vendor | Amount
```

- 🧾 **CSV Output Format** (pipe-separated):
```
2025-04-28|14:32:10|Coffee|Starbucks|-4.5
```

- ✅ **Debit and Credit Enforcement:**
```java
amount = -Math.abs(amount);
```

- 📊 **Sorted Ledger View:**
```java
all.sort(Comparator.comparing(Transaction::getDate)
                   .thenComparing(Transaction::getTime)
                   .reversed());
```

- 🔍 **Stream Filtering:**
```java
list.stream()
    .filter(t -> t.getAmount() > 0)
    .collect(Collectors.toList());
```

---

## 🧠 Core Java Concepts Used

| Concept              | Example from Code |
|----------------------|------------------|
| File I/O (read/write) | `Files.readAllLines(...)`, `BufferedWriter` |
| Method References | `Transaction::getDate` |
| Lambdas | `t -> t.getAmount() > 0` |
| Stream API | `stream().filter(...).collect(...)` |
| Sorting with Comparator | `.comparing().thenComparing().reversed()` |
| Unary Operator | `-Math.abs(amount)` ensures negative values |

---

## 📌 Highlights

- **`toString()`** in `Transaction` formats printed output.
- **`printTransactions()`** uses `System.out.println(t)` which calls `toString()` implicitly.
- **Enhanced switch** simplifies menu structure: `case "A" -> printTransactions(...)`.

---

## 🖥️ How to Run

1. Open in IntelliJ or any Java IDE.
2. Run `AccountingApp.java`.
3. Use the home menu to interact:
  - Add deposits/payments
  - View ledger
  - Generate reports

---