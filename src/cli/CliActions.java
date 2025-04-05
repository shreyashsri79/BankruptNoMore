package cli;

import model.Category;
import model.Expense;
import service.ExpenseService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class CliActions {

    // ─────────────────────────────────────
    // Show all expenses
    public static void showExpense(ExpenseService service) {
        showExpense(service.showExpenses());
    }

    public static void showExpense(List<Expense> expenses) {
        final String RESET = "\u001B[0m";
        final String TITLE_COLOR = "\u001B[36m";    // Cyan
        final String AMOUNT_COLOR = "\u001B[32m";   // Green
        final String DATE_COLOR = "\u001B[33m";     // Yellow
        final String CATEGORY_COLOR = "\u001B[35m"; // Purple

        if (expenses.isEmpty()) {
            System.out.println("\u001B[31m📭 No expenses recorded yet.\u001B[0m");
            return;
        }

        System.out.println("\n━━━━━━━━━━━━━━━━━━ 💼 Your Expenses ━━━━━━━━━━━━━━━━━━━━━━━");
        for (Expense e : expenses) {
            System.out.println("──────────────────────────────────────────────────────────────");
            System.out.println("🆔 ID        : " + e.getId());
            System.out.println("🏷️ Title     : " + TITLE_COLOR + e.getTitle() + RESET);
            System.out.println("💰 Amount    : " + AMOUNT_COLOR + "₹" + e.getAmount() + RESET);
            System.out.println("📅 Date      : " + DATE_COLOR + e.getDate() + RESET);
            System.out.println("🗂️ Category  : " + CATEGORY_COLOR + e.getCategory() + RESET);
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    }

    // ─────────────────────────────────────
    // Add new expense
    public static void addExpense(ExpenseService service) {
        final String RESET = "\033[0m";
        final String CYAN_BOLD = "\033[1;36m";
        final String YELLOW = "\033[0;33m";
        final String GREEN_BOLD = "\033[1;32m";
        final String RED_BOLD = "\033[1;31m";

        Scanner scanner = new Scanner(System.in);

        System.out.println("\n" + CYAN_BOLD + "📝 Add New Expense" + RESET);
        System.out.println("───────────────────────────────");

        System.out.print(YELLOW + "📌 Enter Expense Title: " + RESET);
        String title = scanner.nextLine();

        System.out.print(YELLOW + "💰 Enter Amount: " + RESET);
        double amt = scanner.nextDouble();
        scanner.nextLine();

        System.out.println(YELLOW + "📂 Choose Category (FOOD, TRAVEL, ENTERTAINMENT, OTHER):" + RESET);
        System.out.print("➡️  ");
        String catStr = scanner.nextLine();

        UUID id = UUID.randomUUID();
        LocalDate date = LocalDate.now();

        try {
            Category category = Category.valueOf(catStr.toUpperCase());
            Expense expense = new Expense(title, amt, date, category);
            service.addExpense(expense);
            System.out.println(GREEN_BOLD + "\n✅ Expense added successfully!" + RESET);
        } catch (IllegalArgumentException e) {
            System.out.println(RED_BOLD + "\n❌ Invalid category. Please enter one of: FOOD, TRAVEL, ENTERTAINMENT, OTHER." + RESET);
        }

        System.out.println();
    }

    // ─────────────────────────────────────
    // Filter by Category
    public static void filterCat(ExpenseService service) {
        final String RESET = "\033[0m";
        final String CYAN = "\033[0;36m";
        final String RED_BOLD = "\033[1;31m";

        Scanner scanner = new Scanner(System.in);
        System.out.print(CYAN + "🔍 Enter category to filter: " + RESET);
        String catStr = scanner.nextLine();

        try {
            Category category = Category.valueOf(catStr.toUpperCase());
            showExpense(service.filterByCategory(category));
        } catch (IllegalArgumentException e) {
            System.out.println(RED_BOLD + "\n❌ Invalid category. Use: FOOD, TRAVEL, ENTERTAINMENT, OTHER." + RESET);
        }
    }

    // ─────────────────────────────────────
    // Show total expense
    public static void totalExpense(ExpenseService service) {
        final String GREEN = "\033[1;32m";
        final String RESET = "\033[0m";

        double total = service.getTotalExpense();
        System.out.println(GREEN + "\n💸 Total Expense: ₹" + total + RESET);
    }

    // ─────────────────────────────────────
    // Filter by date range
    public static void filterByDate(ExpenseService service) {
        final String RESET = "\033[0m";
        final String CYAN = "\033[0;36m";
        final String RED = "\033[1;31m";

        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print(CYAN + "📅 Enter start date (yyyy-mm-dd): " + RESET);
            String startStr = scanner.nextLine();
            LocalDate start = LocalDate.parse(startStr);

            System.out.print(CYAN + "📅 Enter end date (yyyy-mm-dd): " + RESET);
            String endStr = scanner.nextLine();
            LocalDate end = LocalDate.parse(endStr);

            List<Expense> result = service.filterByDate(start.minusDays(1), end.plusDays(1));
            showExpense(result);
        } catch (Exception e) {
            System.out.println(RED + "\n❌ Invalid date format. Use yyyy-mm-dd." + RESET);
        }
    }

    // ─────────────────────────────────────
    // Delete expense by ID
    public static void deleteExpense(ExpenseService service) {
        final String RESET = "\033[0m";
        final String CYAN = "\033[0;36m";
        final String GREEN = "\033[1;32m";
        final String RED = "\033[1;31m";

        Scanner scanner = new Scanner(System.in);
        System.out.print(CYAN + "🗑️ Enter ID of expense to delete: " + RESET);
        String idStr = scanner.nextLine();

        try {
            UUID id = UUID.fromString(idStr);
            service.deleteExpense(id);
            System.out.println(GREEN + "✅ Expense deleted (if found)." + RESET);
        } catch (IllegalArgumentException e) {
            System.out.println(RED + "❌ Invalid UUID format." + RESET);
        }
    }
}
