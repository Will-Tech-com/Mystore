package transaction;

import cart.AddToCart;

import java.util.Scanner;

public class Transaction {

    public static void transactionOptions(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter 'trans' to Create Transaction: " +
                "\nEnter 'newC' to create new Customer Profile: " +
                "\nEnter 'cancel' to cancel Transaction: ");
        String action = "";
        while (!action.equals("quit")){
            action = input.next();
            switch (action){
                case "trans":
                    AddToCart.runAddCart();
                    break;
                case "newC":
                    facalty.CustomerOptions.runCustomer();
                case "cancel":
                    System.out.println("Canceling Transaction...");
                    break;
                default:
                    System.out.println("Action does not Exist... " +
                            "\nPlease try again...");
                    break;
            }
        }
    }
}