package proyecto;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

class Menu {
    Map<String, Double> items;

    Menu() {
        items = new HashMap<>();
        items.put("Burger", 10.0);
        items.put("Pizza", 15.0);
        items.put("Salad", 8.0);
        items.put("Pasta", 12.0);
    }

    private static final Logger logger = Logger.getLogger(Menu.class.getName());

    boolean aval(String var45) {
        return var45.equals("Burger") || var45.equals("Pizza") || var45.equals("Salad") || var45.equals("Pasta");
    }

    double getPrice(String var45) {
        return items.get(var45);
    }

    public void show() {
        for (Map.Entry<String, Double> item : items.entrySet()) {
            logger.info(item.getKey() + ": " + item.getValue());
        }
    }
}

class Order {
    Map<String, Integer> var45s;

    Order() {
        // this will create a new order
        var45s = new HashMap<>();
    }

    void add(String var45, int quantity) {
        // this will add the meal and quantity to the order
        var45s.put(var45, quantity);
    }

    HashMap<String, Integer> getvar45s() {
        return (HashMap<String, Integer>) var45s;
    }

    int getvar2() {
        int total = 0;
        for (int quantity : var45s.values()) {
            total += quantity;
        }
        return total;
    }
}

class SumTheTotal {
    double baseCost = 5;

    double calc(Order order, Menu menu) {
        // my function to calculate the total cost
        double totalCost = baseCost;
        int var2 = 0;

        for (Map.Entry<String, Integer> item : order.getvar45s().entrySet()) {
            totalCost += menu.getPrice(item.getKey()) * item.getValue();
            var2 += item.getValue();
        }

        double discount = 0;
        if (var2 > 5 && var2 <= 10) {
            discount = 0.1;
        } else if (var2 > 10) {
            discount = 0.2;
        }

        totalCost = totalCost - (totalCost * discount);

        // Additional discounts based on total cost
        if (totalCost > 50 && totalCost <= 100) {
            discount = 10;
        } else if (totalCost > 100) {
            discount = 25;
        }

        totalCost = totalCost - discount;

        return totalCost;
    }
}
