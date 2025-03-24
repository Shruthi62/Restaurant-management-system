import java.util.ArrayList;
import java.util.List;

// Base class for Menu Items
abstract class MenuItem {
    protected String name;
    protected double price;

    public MenuItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public abstract double getPrice();
}

// Inherited class for Dish
class Dish extends MenuItem {
    public Dish(String name, double price) {
        super(name, price);
    }

    @Override
    public double getPrice() {
        return price;
    }
}

// Inherited class for Drink
class Drink extends MenuItem {
    public Drink(String name, double price) {
        super(name, price);
    }

    @Override
    public double getPrice() {
        return price;
    }
}

// Class to encapsulate Order details
class Order {
    private List<MenuItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double calculateTotal(BillingStrategy strategy) {
        return strategy.calculateTotal(items);
    }
}

// Interface for Billing Strategy
interface BillingStrategy {
    double calculateTotal(List<MenuItem> items);
}

// Concrete Billing Strategy for Regular Billing
class RegularBillingStrategy implements BillingStrategy {
    @Override
    public double calculateTotal(List<MenuItem> items) {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }
}

// Concrete Billing Strategy for Discount Billing
class DiscountBillingStrategy implements BillingStrategy {
    private double discount;

    public DiscountBillingStrategy(double discount) {
        this.discount = discount;
    }

    @Override
    public double calculateTotal(List<MenuItem> items) {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total - (total * discount);
    }
}

// Restaurant class to manage orders and menu
class Restaurant {
    private List<MenuItem> menu;

    public Restaurant() {
        menu = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

    public void takeOrder(Order order, BillingStrategy strategy) {
        double total = order.calculateTotal(strategy);
        System.out.println("Total amount for the order: $" + total);
    }
}

// Main class to demonstrate the functionality
public class RestaurantManagementSystem {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        // Adding menu items
        restaurant.addMenuItem(new Dish("Pasta", 12.99));
        restaurant.addMenuItem(new Dish("Pizza", 10.99));
        restaurant.addMenuItem(new Drink("Coke", 1.99));
        restaurant.addMenuItem(new Drink("Water", 0.99));

        // Creating an order
        Order order = new Order();
        order.addItem(restaurant.getMenu().get(0)); // Pasta
        order.addItem(restaurant.getMenu().get(1)); // Pizza
        order.addItem(restaurant.getMenu().get(2)); // Coke

        // Regular billing
        System.out.println("Regular Billing:");
        restaurant.takeOrder(order, new RegularBillingStrategy());

        // Discount billing
        System.out.println("Discount Billing (10% off):");
        restaurant.takeOrder(order, new DiscountBillingStrategy(0.10));
    }
}
