package transaction;

import java.sql.*;
import java.util.Scanner;

public class CreateTransaction {

    static Connection con;
    static String url = "jdbc:mysql://localhost/shop";
    static String username = "root";
    static String password = "will12boskowski1999";
    static Scanner inputD = new Scanner(System.in);
    static int cust_id;
    static int cust_price;
    static int total_balance;

    public static void runCustomer() {
        try {
            String url = "jdbc:mysql://localhost/shop";
            String username = "root";
            String password = "will12boskowski1999";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            readInCartDetails(inputD);
            con.close();

        } catch (Exception er) {
            System.out.println(er.getMessage());
        }
    }
    public static void readInCartDetails(Scanner input) {
        //to insert Customer Info
       ToCart into_cart = new ToCart();

        into_cart.setCuste_id(cust_id); // TODO: VALIDATE THESE FIELDS TO MAKE SURE A INTEGER IS ADDED AND NOT A STRING
        into_cart.setCuste_balance(total_balance - cust_price);
        into_cart.setTotal_price(cust_price);

        itemCost(into_cart);
    }

    public static void itemCost(ToCart customer) {
        PreparedStatement ps;
        ResultSet rs;

        try {
            con = DriverManager.getConnection(url, username, password);
            readInTransact(inputD);

            ps = con.prepareStatement("select * from customer where Customer_Id=?");
            ps.setInt(1, cust_id);

            rs = ps.executeQuery();
            while (rs.next()) {
                int cust_id = rs.getInt("Customer_Id");
                String cust_name = rs.getString("Customer_Name");
                String cust_surname = rs.getString("Customer_Surname");
                int cust_balance = rs.getInt("Customer_Balance");

                System.out.println("Customer ID: " + cust_id);
                System.out.println("Customer Name: " + cust_name);
                System.out.println("Customer Surname: " + cust_surname);
                System.out.println("Customer Balance: " + cust_balance);
            }
            try {
                String sqls = "INSERT INTO cart"
                        + "(Customer_ID, Total_Price, Customer_Balance)"
                        + "VALUES (?, ?, ?)";

                PreparedStatement state = con.prepareStatement(sqls);

                state.setInt(1, customer.getCuste_id());
                state.setInt(2,customer.getCuste_balance());
                state.setInt(2, customer.getTotal_price());

                int ci = state.executeUpdate();
                if (ci > 0) {
                    System.out.println("Customer Information Updated" +
                            "\nEnter 'quit' to exit: ");
                }

            } catch (SQLException e2) {
                System.err.println(e2.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readInTransact(Scanner inputD){
        System.out.print("Enter Customer ID: ");
        cust_id = inputD.nextInt();

        }
    private static class ToCart {
        private int cust_id;
        private int cust_balance;
        private int total_price;

        public int getCuste_id() {
            return cust_id;
        }
        public void setCuste_id(int cust_id) {
            this.cust_id = cust_id;
        }
        public int getCuste_balance(){
            return cust_balance;
        }
        public void setCuste_balance(int cust_balance){
            this.cust_balance = cust_balance;
        }
        public int getTotal_price() {
            return total_price;
        }
        public void setTotal_price(int total_price){
            this.total_price = total_price;
        }
    }
}
