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
        PosCart.basketItemsDisplay();

        try {
            PreparedStatement ps;

            con = DriverManager.getConnection(url, username, password);


            for (int x = 0; x < 1; x++) {
                removeFromBasket(inputD);

                ps = con.prepareStatement("DELETE from shopping_basket where Product_Id=?");
                ps.setInt(1, prod_id);

                int x1 = ps.executeUpdate();
                if (x1 > 0) {
                    System.out.println("Items/ Item has been Removed...");
                }

                ps = con.prepareStatement("select * from product where Product_Id=?");
                ps.setInt(1, prod_id);

                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    int prod_id = rs.getInt("Product_Id");
                    int num_stock = rs.getInt("Quantity_of_Stock");

                    String sql = "update product set Quantity_of_Stock = ? where Product_Id = ?";
                    PreparedStatement state = con.prepareStatement(sql);

                    state.setInt(1, num_stock + 1);
                    state.setInt(2, prod_id);

                    state.executeUpdate();
                }
            }
            } catch(Exception ex){
                System.err.println(ex.getMessage());
            }
        }

    public static void removeFromBasket(Scanner inputD) {
        System.out.print("Enter Product Id: ");
        prod_id = inputD.nextInt();
    }
}
