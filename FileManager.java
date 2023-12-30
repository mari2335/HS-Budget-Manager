import java.io.*;
import java.util.Scanner;

public class FileManager {

    public static void save(Expenses expenses) {
        try(PrintWriter printWriter = new PrintWriter("purchases.txt")) {

            printWriter.print("Balance: ");
            printWriter.println(expenses.getBalance());

            if (!expenses.getFoodList().isEmpty()) {
                printWriter.println();
                printWriter.println("Food:");
                expenses.getFoodList().forEach((name, price) -> printWriter.println(name + "##" + price));
            }

            if (!expenses.getClothesList().isEmpty()) {
                printWriter.println();
                printWriter.println("Clothes:");
                expenses.getClothesList().forEach((name, price) -> printWriter.println(name + "##" + price));
            }

            if (!expenses.getEntertainmentList().isEmpty()) {
                printWriter.println();
                printWriter.println("Entertainment:");
                expenses.getEntertainmentList().forEach((name, price) -> printWriter.println(name + "##" + price));
            }

            if (!expenses.getOtherList().isEmpty()) {
                printWriter.println();
                printWriter.println("Other:");
                expenses.getOtherList().forEach((name, price) -> printWriter.println(name + "##" + price));
            }

            System.out.println("\nPurchases were saved!\n");

        } catch (IOException e) {
            System.out.printf("An exception occurred %s", e.getMessage());
        }
        System.out.println();
    }


    public static void load(Expenses expenses)
    {
        try (Scanner scanner = new Scanner(new File("purchases.txt"))) {

            String[] balanceFirstLine = scanner.nextLine().split(" ");
            expenses.setBalance(Double.parseDouble(balanceFirstLine[1]));

            String line;
            String currentCategory = null;

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.isEmpty()) {
                    currentCategory = null;
                }
                else if (line.contains(":")) {
                    currentCategory = line;
                }
                else {
                    String[] parts = line.split("##");
                    String itemName = parts[0];
                    double itemPrice = Double.parseDouble(parts[1]);
                    assert currentCategory != null;
                    int type = switch(currentCategory) {
                        case "Food:" -> 1;
                        case "Clothes:" -> 2;
                        case "Entertainment:" -> 3;
                        case "Other:" -> 4;
                        default -> throw new IllegalStateException("Unexpected value: " + currentCategory);
                    };
                    expenses.addPurchaseType(itemName, itemPrice, type);
                    expenses.addPurchaseAll(itemName, itemPrice);
                }
            }
            System.out.println("\nPurchases were loaded!\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
