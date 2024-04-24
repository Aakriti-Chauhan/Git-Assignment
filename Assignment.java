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

        do {
            System.out.println("Input the name of item " + i + ":");
            name[i] = sc.nextLine();

            System.out.println("Input the type of item " + i + " (Raw/Manufactured/Imported):");
            type[i] = sc.nextLine();

            System.out.println("Input the quantity of item " + i + ":");
            quantity[i] = sc.nextInt();

            System.out.println("Input the price of item " + i + ":");
            price[i] = sc.nextDouble();

            totalPrice[i] = quantity[i] * price[i];

            System.out.println("Do you want to enter more details? (Y/N)");
            ch = sc.next().charAt(0);
            sc.nextLine(); // Consume newline left-over

            i++;
            n = i;
        } while (ch == 'Y' || ch == 'y');

        sc.close();

        // Calculate tax and final price for each item
        for (i = 0; i < n; i++) {
            switch (type[i]) {
                case "Raw":
                    tax[i] = 0.125 * totalPrice[i];
                    break;
                case "Manufactured":
                    tax[i] = 0.125 * totalPrice[i] + 0.02 * (totalPrice[i] + 0.125 * totalPrice[i]);
                    break;
                case "Imported":
                    tax[i] = totalPrice[i] <= 100 ? 5 : (totalPrice[i] <= 200 ? 10 : 0.05 * totalPrice[i]);
                    break;
                default:
                    System.out.println("Invalid item type for item " + i);
                    break;
            }
            finalPrice[i] = totalPrice[i] + tax[i];
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
}
