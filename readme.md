# 💸 Bankrupt No More

A simple command-line based expense tracker built using core **Object-Oriented Programming (OOP)** concepts in Java. This project helps users manage personal expenses through an interactive terminal interface.

## 🚀 Features

- 📝 Add new expenses (Title, Amount, Category)
- 📂 Filter expenses by **category**
- 📅 Filter expenses by **date range**
- 🧾 View all recorded expenses
- ❌ Delete an expense by ID
- 💰 View total expenditure
- 🎨 Colorful and emoji-enhanced CLI interface

## 🧠 Concepts Practiced

This project reinforces key OOP principles:
- **Encapsulation** (`Expense`, `Category`, `ExpenseService`)
- **Abstraction** (through service layer design)
- **Polymorphism** (used in CLI behavior)
- **Separation of concerns** (CLI logic vs core logic)

## 📁 Project Structure
```
📦 ExpenseTrackerCLI
├── model/
│   ├── Expense.java           # Expense model with id, title, amount, date, category
│   └── Category.java          # Enum for expense categories (FOOD, TRAVEL, etc.)
│
├── service/
│   └── ExpenseService.java    # Core logic: add, delete, filter, and get total expenses
│
├── cli/
│   └── CliActions.java        # User interaction layer (input/output), beautified CLI
│
└── Main.java                  # Entry point, runs the CLI loop
```

## 📦 Categories Available

- `FOOD`
- `TRAVEL`
- `ENTERTAINMENT`
- `OTHER`

## ✅ Sample CLI Flow

```bash
====== Expense Tracker CLI ======
1. Add Expense
2. View All Expenses
3. Filter by Category
4. Filter by Date Range
5. Delete Expense
6. View Total Expense
0. Exit
Choose an option: 1

📝 Add New Expense
📌 Enter Expense Title: Coffee
💰 Enter Amount: 150
📂 Choose Category (FOOD, TRAVEL, ENTERTAINMENT, OTHER):
➡️  food

✅ Expense added successfully!