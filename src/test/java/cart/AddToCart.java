package cart;

import java.sql.*;
import java.util.Scanner;

public class AddToCart {

    static Connection con = null;
    static String url = "jdbc:mysql://localhost/shop";
    static String username = "root";
    static String password = "will12boskowski1999";
    static Scanner inputD = new Scanner(System.in);
    static int prod_id;
    static int cust_id;

    public static void main(String [] args){
        runAddCart();
    }
    public static void runAddCart(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            addToCart();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void addToCart() throws Exception{

        PreparedStatement ps;
        ResultSet rs;

        menu();
        con = DriverManager.getConnection(url, username, password);
        inputToCart(inputD);

        ps = con.prepareStatement("select * from product where Product_Id=?");
        ps.setInt(1, prod_id);

        rs = ps.executeQuery();
        while (rs.next()) {
            int prod_id = rs.getInt("Product_Id");
            String prod_name = rs.getString("Product_Name");
            double prod_price = rs.getDouble("Product_Price");
            int num_stock = rs.getInt("Quantity_of_Stock");

            System.out.println("ID: " + prod_id);
            System.out.println("Product Name: " + prod_name);
            System.out.println("Product Price: R" + prod_price);
            System.out.println("Number of Stock: " + num_stock);


            PreparedStatement ps1;
            ResultSet rs1;

            try {
                con = DriverManager.getConnection(url, username, password);
                readInTransact(inputD);

                ps1 = con.prepareStatement("select * from customer where Customer_Id=?");
                ps1.setInt(1, cust_id);

                rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    int cust_id = rs1.getInt("Customer_Id");
                    String cust_name = rs1.getString("Customer_Name");
                    String cust_surname = rs1.getString("Customer_Surname");
                    int cust_balance = rs1.getInt("Customer_Balance");

                    System.out.println("Customer ID: " + cust_id);
                    System.out.println("Customer Name: " + cust_name);
                    System.out.println("Customer Surname: " + cust_surname);
                    System.out.println("Customer Balance: " + cust_balance);

                    String sqls = "INSERT INTO payment"
                            + "(Customer_ID, Total_Price, Customer_Balance)"
                            + "VALUES (?, ?, ?)";

                    PreparedStatement state = con.prepareStatement(sqls);

                    state.setInt(1, cust_id);
                    state.setInt(2, (int) prod_price);
                    state.setInt(3,  cust_balance - (int) prod_price);

                    int ci = state.executeUpdate();
                    if (ci > 0) {
                        System.out.println("Payment Update Successful" +
                                "\nEnter 'quit' to leave Store: ");
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
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

    public static void inputToCart(Scanner inputD) {
        System.out.print("Product Id: ");
        prod_id = inputD.nextInt();
    }
    public static void readInTransact(Scanner inputD) {
        System.out.print("Enter Customer ID: ");
        cust_id = inputD.nextInt();
    }
}
