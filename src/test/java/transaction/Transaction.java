package transaction;


import java.util.Scanner;

public class Transaction {

    public static void transactionOptions(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter 'trans' to Create Transaction: " +
                "\nEnter 'newC' to create new Customer Profile: " +
                "\nEnter 'quit' to cancel Transaction: ");
        String action = "";
        while (!action.equals("quit")){
            action = input.next();
            switch (action){
                case "trans":
                    Payment.runPayment();
                    transOptions();
                    break;
                case "newC":
                    facalty.CustomerOptions.runCustomer();
                    transOptions();
                    break;
                case "quit":
                    System.out.println("Canceling Transaction...");
                    break;
                default:
                    System.out.println("Action does not Exist... " +
                            "\nPlease try again...");
                    break;
            }
        }
    }
    public static void transOptions(){
        System.out.print("\nEnter 'trans' to Create Transaction: " +
                "\nEnter 'newC' to create new Customer Profile: " +
                "\nEnter 'quit' to cancel Transaction: ");
    }
}