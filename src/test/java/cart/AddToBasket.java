package cart;

import java.sql.*;
import java.util.Scanner;

public class AddToBasket {

    static Connection con = null;
    static String url = "jdbc:mysql://localhost/shop";
    static String username = "root";
    static String password = "will12boskowski1999";
    static Scanner inputD = new Scanner(System.in);
    static int prod_id;

    public static void runAddCart(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            addToBasket();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void addToBasket() throws Exception {

        PreparedStatement ps;
        ResultSet rs;

        menu();
        con = DriverManager.getConnection(url, username, password);

        for (int i = 0; i < 3; i++) {
            inputToShoppingBasket(inputD);

            ps = con.prepareStatement("select * from product where Product_Id=?");
            ps.setInt(1, prod_id);

            rs = ps.executeQuery();

            while (rs.next()) {
                int prod_id = rs.getInt("Product_Id");
                String prod_name = rs.getString("Product_Name");
                double prod_price = rs.getDouble("Product_Price");
                int num_stock = rs.getInt("Quantity_of_Stock");

                String sql1 = "INSERT INTO shopping_basket"
                        + "(Product_Id, Product_Name, Product_Price)"
                        + "VALUES (?, ?, ?)";

                PreparedStatement states = con.prepareStatement(sql1);

                states.setInt(1, prod_id);
                states.setString(2, prod_name);
                states.setDouble(3, prod_price);

                int p = states.executeUpdate();
                if (p > 0) {
                    System.out.println("Item added to basket...");
                }
                    String sql = "update product set Quantity_of_Stock = ? where Product_Id = ?";
                    PreparedStatement state = con.prepareStatement(sql);

                    state.setInt(1, num_stock - 1);
                    state.setInt(2, prod_id);

                    state.executeUpdate();
            }

        }
    }
    public static void menu() {
        try {
            String sql2 = "select * from product";
            Statement state = con.createStatement();
            ResultSet p2 = state.executeQuery(sql2);

            System.out.println("Menu\n");
            while (p2.next()) {
                int prod_id = p2.getInt("Product_Id");
                String prod_name = p2.getString("Product_Name");
                double prod_price = p2.getDouble("Product_Price");
                int prod_stock = p2.getInt("Quantity_of_Stock");
                String category = p2.getString("Category");

                String item = prod_id + " " + prod_name + " R" + prod_price + " " + prod_stock + " " + category;
                System.out.println(item);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void inputToShoppingBasket(Scanner inputD) {
        System.out.print("Product Id: ");
        prod_id = inputD.nextInt();
    }
}

