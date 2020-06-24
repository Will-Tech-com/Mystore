package cart;


import java.sql.*;
import java.util.Scanner;

public class RemoveFromBasket {
    static Connection con;
    static String url = "jdbc:mysql://localhost/shop";
    static String username = "root";
    static String password = "will12boskowski1999";
    static Scanner inputD = new Scanner(System.in);
    static int prod_id;


    public static void removeItems() {
        System.out.println("\nChoose Item to remove");
        try {
            PreparedStatement ps;

            con = DriverManager.getConnection(url, username, password);
            inputToCart(inputD);//put in for loop

            ps = con.prepareStatement("DELETE from shopping_basket where Product_Id=?");
            ps.setInt(1, prod_id);

            ps.executeUpdate();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
    public static void inputToCart(Scanner inputD) {
        System.out.print("Enter Product Id: ");
        prod_id = inputD.nextInt();
    }
}
