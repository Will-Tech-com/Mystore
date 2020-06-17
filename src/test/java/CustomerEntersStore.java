import facalty.CustomerOptions;
import facalty.NoInterest;
import facalty.PosCart;
import facalty.ProductsOptions;

import java.sql.*;
import java.util.Scanner;


public class CustomerEntersStore {

   Connection con = null;

        public static void main(String[] args) {
            CustomerEntersStore pos = new CustomerEntersStore();
            pos.startUpPos();
        }

        void startUpPos() {
            try {
                String url = "jdbc:mysql://localhost/shop";
                String userName = "root";
                String password = "will12boskowski1999";

                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(url, userName, password);
                this.runPos();
                con.close();

            } catch (ClassNotFoundException | SQLException ex) {

                System.err.println(ex.getMessage());
                System.exit(0);
            }

        }
        public void runPos() {
            Scanner input = new Scanner(System.in);
            String action = "";
            System.out.println("Enter 'cart' to access cart information: "
                    +"\nEnter 'customer' Customer Options: "
                    +"\nEnter 'product' Product Options: "
                    +"\nEnter 'quit' to leave store(COMMENT BOX OPTIONALqui: ");

            while (!action.equals("quit")) {
                action = input.next();
                switch (action) {
                    case "cart":
                        PosCart.cart();
                        break;
                    case "customer":
                        CustomerOptions.runCustomer();
                        break;
                    case "product":
                        ProductsOptions.runProduct();
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









}


