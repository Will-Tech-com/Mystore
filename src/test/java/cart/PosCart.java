package cart;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class PosCart {


    static Connection con = null;
    static String url = "jdbc:mysql://localhost/shop";
    static String username = "root";
    static String password = "will12boskowski1999";

    public static void cartOptions() {
        Scanner input = new Scanner(System.in);
        String action = "";
        System.out.print("Enter 'add' to add item to Cart: " +
                "\nEnter 'remove' to remove items from Cart: " +
                "\nEnter 'view' to display Cart items: ");

        while (!action.equals("quit")) {
            action = input.next();
            switch (action) {
                case "add":
                    transaction.Transaction.transactionOptions();
                    break;
                case "remove":
                    basketItemsDisplay();
                    RemoveFromBasket.removeItems();
                    break;
                case "view":
                    basketItemsDisplay();
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

    public static void basketItemsDisplay() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);

            String sql2 = "select * from shopping_basket";
            Statement state = con.createStatement();
            ResultSet p2 = state.executeQuery(sql2);

            System.out.println("ITEMS\n");
            while (p2.next()) {
                int prod_id = p2.getInt("Product_Id");
                String prod_name = p2.getString("Product_Name");
                double prod_price = p2.getDouble("Product_Price");

                String p = prod_id + " " + prod_name + " R" + prod_price;
                System.out.println(p);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

