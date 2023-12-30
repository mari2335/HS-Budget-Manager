import java.util.Scanner;

public class UI {

    private Scanner scanner;
    private Expenses expenses;

    public UI(Scanner scanner, Expenses expenses) {
        this.scanner = scanner;
        this.expenses = expenses;
    }

    public void start() {
        while (true) {
            printActions();
            String line = scanner.nextLine();

            if (line.equals("0")) {
                System.out.println("\nBye!");
                return;
            }
            switch (line) {
                case "1" -> enterIncome();
                case "2" -> enterPurchase();
                case "3" -> showList();
                case "4" -> showBalance();
                case "5" -> expenses.save();
                case "6" -> expenses.load();
            }
        }
    }

    private void enterIncome() {
        System.out.println("\nEnter income:");
        String income = scanner.nextLine();
        expenses.addIncome(Double.parseDouble(income));
        System.out.println("Income was added!");
        System.out.println();
    }

    private void enterPurchase() {
        while (true) {
            System.out.println();
            printTypesToPurchase();
            int typeNumber = Integer.parseInt(scanner.nextLine());

            if (typeNumber == 5) {
                System.out.println();
                return;
            }
            System.out.println("\nEnter purchase name:");
            String name = scanner.nextLine();

            System.out.println("Enter its price:");
            double price = Double.parseDouble(scanner.nextLine());

            expenses.addPurchaseType(name, price, typeNumber);

            System.out.println("Purchase was added!");
            System.out.println();
        }
    }

    private void showList() {
        while (true){
            System.out.println();
            printTypesToList();
            int typeNumber = Integer.parseInt(scanner.nextLine());

            if (typeNumber == 6) {
                System.out.println();
                return;
            }
            System.out.println();
            switch (typeNumber) {
                case 1 -> expenses.showPurchasesFood();
                case 2 -> expenses.showPurchasesClothes();
                case 3 -> expenses.showPurchasesEntertainment();
                case 4 -> expenses.showPurchasesOther();
                case 5 -> expenses.showPurchasesAll();
            }
            System.out.println();
        }
    }

    private void showBalance() {
        System.out.printf("\nBalance: $%.2f%n", expenses.getBalance());
        System.out.println();
    }

    private void printActions() {
        System.out.println("""
                Choose your action:
                1) Add income
                2) Add purchase
                3) Show list of purchases
                4) Balance
                5) Save
                6) Load
                0) Exit""");
    }

    private void printTypesToPurchase() {
        System.out.println("""
                Choose the type of purchase
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) Back""");
    }

    private void printTypesToList() {
        System.out.println("""
                Choose the type of purchases
                1) Food
                2) Clothes
                3) Entertainment
                4) Other
                5) All
                6) Back""");
    }
}
