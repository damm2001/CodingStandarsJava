package proyecto;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class MyProgram {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Order order = new Order();
        SumTheTotal calculator = new SumTheTotal();
        Scanner scanner = new Scanner(System.in);
        Integer totalQuantity = 0;

        final Logger myProgramLogger = Logger.getLogger(MyProgram.class.getName());

        while (true) {
            menu.show();

            myProgramLogger.info("Enter meal name to order or 'done' to finish: ");
            String var45 = scanner.nextLine();
            if (var45.equals("done"))
                break;

            if (!menu.aval(var45)) {
                myProgramLogger.info("meal not available. Please re-select.");
                continue;
            }

            int quantity = getQuantity(scanner, myProgramLogger, var45, totalQuantity);
            if (quantity == -1) {
                continue;
            }

            order.add(var45, quantity);
        }

        double totalC = calculator.calc(order, menu);
        int var2 = order.getvar2();

        if (var2 > 100) {
            myProgramLogger.info("Order quantity exceeds maximum limit. Please re-enter.");
            scanner.close();
            return;
        }

        myProgramLogger.info("Your Order:");
        for (Map.Entry<String, Integer> item : order.getvar45s().entrySet()) {
            myProgramLogger.info(item.getKey() + ": " + item.getValue());
        }
        String totalCosto = "Total Cost: $" + totalC;
        myProgramLogger.info(totalCosto);
        myProgramLogger.info("Confirm order (yes/no): ");
        String confirm = scanner.nextLine();

        if (!confirm.equalsIgnoreCase("yes")) {
            myProgramLogger.info("Order canceled.");
            myProgramLogger.info("-1");
            scanner.close();
            return;
        }
        String orderConfirmed = "Order confirmed. Total cost is: $" + totalC;
        myProgramLogger.info(orderConfirmed);
        scanner.close();
    }

    private static int getQuantity(Scanner scanner, Logger logger, String mealName, int totalQuantity) {
        int quantity;
        do {
            String inputQuantity = "Enter quantity for " + mealName + ": ";
            logger.info(inputQuantity);
            quantity = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            totalQuantity += quantity;
            if (totalQuantity > 100) {
                totalQuantity -= quantity;
                logger.info("Order quantity exceeds maximum limit. Please re-enter.");
            }
            if (quantity <= 0) {
                logger.info("Invalid quantity. Please re-enter greater than 0.");
            }
        } while (quantity <= 0);

        return quantity;
    }
}