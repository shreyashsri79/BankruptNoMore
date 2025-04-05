import cli.CliActions;
import model.Category;
import model.Expense;
import service.ExpenseService;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ExpenseService expenseService = new ExpenseService();

        Expense e1 = new Expense("Groceries", 1500.0, LocalDate.of(2025, 4, 1), Category.FOOD);
        Expense e2 = new Expense("Netflix", 499.0, LocalDate.of(2025, 4, 2), Category.ENTERTAINMENT);
        Expense e3 = new Expense("Petrol", 1800.0, LocalDate.of(2025, 3, 28), Category.TRANSPORT);
        Expense e4 = new Expense("Restaurant", 700.0, LocalDate.of(2025, 4, 3), Category.FOOD);
        Expense e5 = new Expense("Uber", 300.0, LocalDate.of(2025, 4, 2), Category.TRANSPORT);

        expenseService.addExpense(e1);
        expenseService.addExpense(e2);
        expenseService.addExpense(e3);
        expenseService.addExpense(e4);
        expenseService.addExpense(e5);

        while (true) {
            final String RESET = "\u001B[0m";
            final String CYAN_BOLD = "\u001B[1;36m";
            final String YELLOW = "\u001B[0;33m";
            final String RED = "\033[1;31m";
            final String BLUE_BOLD = "\u001B[1;34m";

            System.out.println(CYAN_BOLD + "\n💸💼 ====== Expense Tracker CLI ====== 💼💸" + RESET);
            System.out.println(BLUE_BOLD + " 1⃣  Add Expense" + RESET);
            System.out.println(BLUE_BOLD + " 2⃣  View All Expenses" + RESET);
            System.out.println(BLUE_BOLD + " 3⃣  Filter by Category" + RESET);
            System.out.println(BLUE_BOLD + " 4⃣  Filter by Date Range" + RESET);
            System.out.println(BLUE_BOLD + " 5⃣  Delete Expense" + RESET);
            System.out.println(BLUE_BOLD + " 6⃣  View Total Expense" + RESET);
            System.out.println(RED + " 0⃣  Exit" + RESET);
            System.out.print(YELLOW + "\n🔘 Choose an option: " + RESET);


            int choice = scanner.nextInt();

            switch (choice){
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                case 1-> CliActions.addExpense(expenseService);
                case 2 -> CliActions.showExpense(expenseService.showExpenses());
                case 3 -> CliActions.filterCat(expenseService);
                case 4 -> CliActions.filterByDate(expenseService);
                case 5 -> CliActions.deleteExpense(expenseService);
                case 6 -> CliActions.totalExpense(expenseService);
                default -> System.out.println("Invalid choice.");

            }

        }
    }

}


