import java.util.HashMap;
import java.util.Map;

public class Expenses {

    private Map<String, Double> purchasesAll;
    private Map<String, Double> foodList;
    private Map<String, Double> clothesList;
    private Map<String, Double> entertainmentList;
    private Map<String, Double> otherList;
    private double balance;

    public Expenses() {
        purchasesAll = new HashMap<>();
        foodList = new HashMap<>();
        clothesList = new HashMap<>();
        entertainmentList = new HashMap<>();
        otherList = new HashMap<>();
        balance = 0;
    }

    public Map<String, Double> getPurchasesAll() {
        return purchasesAll;
    }
    public Map<String, Double> getFoodList() { return foodList; }
    public Map<String, Double> getClothesList() { return clothesList; }
    public Map<String, Double> getEntertainmentList() { return entertainmentList; }
    public Map<String, Double> getOtherList() { return otherList; }

    public double getBalance() {
        if (balance < 0) {
            balance = 0;
            return balance;
        }
        return balance;
    }

    public void setBalance(double balance) { this.balance = balance; }

    public double getTotalSum(Map<String, Double> list) {
        return list.values().stream().mapToDouble(d -> d).sum(); }

    public void addIncome(double income) {
        balance = getBalance() + income;    }

    public void addPurchaseType(String purchaseName, double price, int type) {
        switch(type) {
            case 1 -> foodList.put(purchaseName, price);
            case 2 -> clothesList.put(purchaseName, price);
            case 3 -> entertainmentList.put(purchaseName, price);
            case 4 -> otherList.put(purchaseName, price);
        }
    }

    public void addPurchaseAll(String purchaseName, double price) {
        purchasesAll.put(purchaseName,price);
        setBalance(getBalance() - price);
    }

    public void showPurchasesAll() {
        System.out.println("All:");
        if (purchasesAll.isEmpty()) {
            System.out.println("The purchase list is empty!");
            return;
        }
        purchasesAll.forEach((name, price) -> System.out.printf("%s $%.2f%n", name, price));
        System.out.printf("Total sum: $%.2f%n", getTotalSum(purchasesAll));
    }

    public void showPurchasesFood() {
        System.out.println("Food:");
        if (foodList.isEmpty()) {
            System.out.println("The purchase list is empty!");
            return;
        }
        foodList.forEach((name, price) -> System.out.printf("%s $%.2f%n", name, price));
        System.out.printf("Total sum: $%.2f%n", getTotalSum(foodList));
    }

    public void showPurchasesClothes() {
        System.out.println("Clothes:");
        if (clothesList.isEmpty()) {
            System.out.println("The purchase list is empty!");
            return;
        }
        clothesList.forEach((name, price) -> System.out.printf("%s $%.2f%n", name, price));
        System.out.printf("Total sum: $%.2f%n", getTotalSum(clothesList));
    }

    public void showPurchasesEntertainment() {
        System.out.println("Entertainment:");
        if (entertainmentList.isEmpty()) {
            System.out.println("The purchase list is empty!");
            return;
        }
        entertainmentList.forEach((name, price) -> System.out.printf("%s $%.2f%n", name, price));
        System.out.printf("Total sum: $%.2f%n", getTotalSum(entertainmentList));
    }

    public void showPurchasesOther() {
        System.out.println("Other:");
        if (otherList.isEmpty()) {
            System.out.println("The purchase list is empty!");
            return;
        }
        otherList.forEach((name, price) -> System.out.printf("%s $%.2f%n", name, price));
        System.out.printf("Total sum: $%.2f%n", getTotalSum(otherList));
    }

    public void save() {
        FileManager.save(this);
    }

    public void load() {
        FileManager.load(this);
    }

}
