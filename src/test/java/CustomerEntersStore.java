import facalty.CustomerOptions;
import facalty.NoInterest;
import cart.PosCart;
import facalty.ProductsOptions;

import java.util.Scanner;


public class CustomerEntersStore {


        public static void main(String[] args) {
            CustomerEntersStore pos = new CustomerEntersStore();
            pos.runPos();
        }
        public void runPos() {
            Scanner input = new Scanner(System.in);
            String action = "";
            System.out.println("Enter 'cart' to access cart information: "
                    +"\nEnter 'customer' Customer Options: "
                    +"\nEnter 'product' Product Options: "
                    +"\nEnter 'quit' to leave store(COMMENT BOX OPTIONAL): ");

            while (!action.equals("quit")) {
                action = input.next();
                switch (action) {
                    case "cart":
                        PosCart.cartOptions();
                        afterOpt();
                        break;
                    case "customer":
                        CustomerOptions.runCustomer();
                        afterOpt();
                        break;
                    case "product":
                        ProductsOptions.runProduct();
                        afterOpt();
                        break;
                    case "quit":
                        NoInterest.runComments();
                        break;
                    default:
                        System.out.println("Invalid command provided, please try again:");
                        break;
                }
            }
        }
        public static void afterOpt(){
            System.out.println("\nEnter 'cart' to access cart information: "
                    +"\nEnter 'customer' Customer Options: "
                    +"\nEnter 'product' Product Options: "
                    +"\nEnter 'quit' to leave store(COMMENT BOX OPTIONAL): ");
        }
}


