import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Expenses expenses = new Expenses();
        UI ui = new UI(scanner, expenses);

        ui.start();
    }
}
