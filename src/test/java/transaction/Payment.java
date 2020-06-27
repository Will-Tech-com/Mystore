package transaction;

import cart.RemoveFromBasket;

import java.sql.*;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Payment {
    static Connection con;
    static String url = "jdbc:mysql://localhost/shop";
    static String username = "root";
    static String password = "will12boskowski1999";
    static Scanner inputD = new Scanner(System.in);

    static int cust_id;

    public static void runPayment(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            payment();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void payment() {
        PreparedStatement ps1;
        ResultSet rs1;

        try{
        con = DriverManager.getConnection(url, username, password);
        readInForPayment(inputD);

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

            //part of total price calculation
                Statement stat1 = con.createStatement();
                ResultSet rs;
                String sql1 = "SELECT `Product_Price` FROM shopping_basket";

                rs = stat1.executeQuery(sql1);

                double total_price = 0.0;
                while(rs.next()){

                total_price += rs.getDouble("Product_Price");
                }
            System.out.println("Total Price :R" + total_price);

                String sqls = "INSERT INTO payment"
                        + "(Customer_ID, Total_Price, Customer_Balance)"
                        + "VALUES (?, ?, ?)";

                PreparedStatement state = con.prepareStatement(sqls);

                if (cust_balance > total_price) {
                    state.setInt(1, cust_id);
                    state.setDouble(2, total_price);
                    state.setDouble(3, cust_balance - total_price);


                    int ci = state.executeUpdate();
                    if (ci > 0) {
                        System.out.println("Payment Update Successful");
                    }
                }else if (cust_balance < total_price){
                    System.out.println("You do not have enough to pay for these items." +
                            "\nPlease remove a few items or item to have enough for payment.");
                    RemoveFromBasket.removeItems();
                }
            }
        }catch (Exception e1){
            System.out.println(e1.getMessage());
        }
    }
    public static void readInForPayment(Scanner inputD) {
        System.out.print("Enter Customer ID: ");
        cust_id = inputD.nextInt();
    }
}
