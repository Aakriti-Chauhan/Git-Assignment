import java.util.Scanner;

public class Assignment {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String[] name = new String[6];
        int[] quantity = new int[6];
        double[] price = new double[6];
        String[] type = new String[6];
        double[] tax = new double[6];
        double[] totalPrice = new double[6];
        double[] finalPrice = new double[6];
        char ch;
        int i = 0, n = 0;

        // Input collection loop
        do {
            System.out.println("Input the name of item " + (i + 1) + ":");
            name[i] = sc.nextLine();

            System.out.println("Input the type of item " + (i + 1) + " (Raw/Manufactured/Imported):");
            type[i] = sc.nextLine();

            // Input validation loop for quantity
            while (true) {
                try {
                    System.out.println("Input the quantity of item " + (i + 1) + ":");
                    quantity[i] = Integer.parseInt(sc.nextLine());
                    if (quantity[i] < 0) {
                        System.out.println("Quantity cannot be negative! Please enter a non-negative quantity:");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid integer quantity.");
                }
            }

            // Input validation loop for price
            while (true) {
                try {
                    System.out.println("Input the price of item " + (i + 1) + ":");
                    price[i] = Double.parseDouble(sc.nextLine());
                    if (price[i] < 0) {
                        System.out.println("Price cannot be negative! Please enter a non-negative price:");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a valid numeric price.");
                }
            }

            // Calculate total price
            totalPrice[i] = quantity[i] * price[i];

            // Prompt for more details
            System.out.println("Do you want to enter more details? (Y/N)");
            ch = sc.nextLine().charAt(0);
            i++; // Increment item index
            n++; // Increment total items
        } while (ch == 'Y' || ch == 'y');

        sc.close();

        // Calculate tax and final price for each item
        for (i = 0; i < n; i++) {
            tax[i] = calculateTax(type[i], totalPrice[i]);
            finalPrice[i] = calculateFinalPrice(totalPrice[i], tax[i]);
        }

        // Display item details
        System.out.println("Item Details:");
        for (i = 0; i < n; i++) {
            System.out.println("Name: " + name[i]);
            System.out.println("Quantity: " + quantity[i]);
            System.out.println("Price: " + price[i]);
            System.out.println("Type: " + type[i]);
            System.out.println("Tax: " + tax[i]);
            System.out.println("Final Price: " + finalPrice[i]);
            System.out.println(); // Empty line for better readability
        }
    }

    // Method to calculate tax
    private static double calculateTax(String itemType, double totalPrice) {
        double tax;
        switch (itemType) {
            case "Raw":
                tax = 0.125 * totalPrice;
                break;
            case "Manufactured":
                tax = 0.125 * totalPrice + 0.02 * (totalPrice + 0.125 * totalPrice);
                break;
            case "Imported":
                tax = totalPrice <= 100 ? 5 : (totalPrice <= 200 ? 10 : 0.05 * totalPrice);
                break;
            default:
                System.out.println("Invalid item type");
                tax = 0;
                break;
        }
        return tax;
    }

    // Method to calculate final price
    private static double calculateFinalPrice(double totalPrice, double tax) {
        return totalPrice + tax;
    }
}
