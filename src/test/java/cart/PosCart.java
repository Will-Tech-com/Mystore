package cart;

import java.util.Scanner;

public class PosCart {

    public static void cartOptions() {
        Scanner input = new Scanner(System.in);
        String action = "";
        System.out.print("Enter 'add' to add item to Cart: " +
                "\nEnter 'remove' to remove item from Cart: " +
                "\nEnter 'view' to display Cart items: ");

        while (!action.equals("quit")) {
            action = input.next();
            switch (action) {
                case "add":
                    transaction.Transaction.transactionOptions();
                    break;
                case "remove":

                    break;
                case "view":

                    break;
                case "quit":
                    System.out.println("Exiting Cart");
                    break;
                default:
                    System.out.println("Invalid Command, please try again");
                    break;
            }
        }
    }

}

