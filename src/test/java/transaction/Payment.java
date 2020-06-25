package transaction;

import java.sql.*;
import java.util.Scanner;

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
            String sql2 = "select * from shopping_basket";
            Statement state2 = con.createStatement();
            ResultSet p12 = state2.executeQuery(sql2);

            while (p12.next()) {
                int prod_price1 = p12.getInt("Product_Price");


                String sqls = "INSERT INTO payment"
                        + "(Customer_ID, Total_Price, Customer_Balance)"
                        + "VALUES (?, ?, ?)";

                PreparedStatement state = con.prepareStatement(sqls);

                state.setInt(1, cust_id);
                state.setInt(2,
                        prod_price1 + prod_price1 +prod_price1);
                state.setInt(3, cust_balance - prod_price1);

                int ci = state.executeUpdate();
                if (ci > 0) {
                    System.out.println("Payment Update Successful");
                }
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
