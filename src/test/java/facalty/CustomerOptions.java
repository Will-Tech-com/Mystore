package facalty;

import java.sql.*;
import java.util.Scanner;

public class CustomerOptions {
    static Connection con = null;

    public static void runCustomer(){
        try {
            String url = "jdbc:mysql://localhost/shop";
            String username = "root";
            String password =  "will12boskowski1999";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            customerInfo();
            con.close();


        }catch (Exception er) {
            System.out.println(er.getMessage());
        }
    }

    static void customerInfo(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter 'display' to show Customer Information: " +
                "\nEnter 'input' to create new Customer Profile: " +
                "\nEnter 'quit' to exit Customer Options: ");
        String action = "";
        while (!action.equals("quit")){
            action = input.next();
            switch (action){
                case "display":
                    displayInfo();
                    break;
                case "input":
                    readInCustomerDetails(input);
                    break;
                case "quit":
                    System.out.println("Exiting Customer options");
                    System.out.println("Enter 'cart' to access cart information: "
                            +"\nEnter 'customer' Customer Options: "
                            +"\nEnter 'product' Product Options: "
                            +"\nEnter 'quit' to leave store(COMMENT BOX OPTIONAL): ");
                    break;
                default:
                    System.out.println("Action does not Exist... " +
                            "\nPlease try again...");
                    break;
            }
        }
    }

    public static void displayInfo() {
        String sql = "select * from customer";
        System.out.println("\nCustomer Information:\n");
        try {
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()) {
                int cust_id = rs.getInt("Customer_ID");
                String cust_name = rs.getString("Customer_Name");
                String cust_surname = rs.getString("Customer_Surname");
                String cust_area = rs.getString("Customer_Area");
                int cust_cell_num = rs.getInt("Customer_Cell_No");
                int cust_balance = rs.getInt("Customer_Balance");

                System.out.println(cust_id + " " + cust_name + " " + cust_surname + " " + cust_area + " " + cust_cell_num + " " + cust_balance);
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void readInCustomerDetails(Scanner input) {
        //to insert Customer Info
        Customer customer = new Customer();

        System.out.print("Customer Name: ");
        customer.setCust_name(input.next());
        System.out.print("Customer Surname: ");
        customer.setCust_surname(input.next());
        System.out.print("Customer Area: ");
        customer.setCust_area(input.next());
        System.out.print("Customer Cell Number: ");
        customer.setCust_cell_no(input.next());
        System.out.print("Enter Customer Balance: ");
        customer.setCust_balance(input.nextDouble());
        customerUpdate(customer);
    }

    public static void customerUpdate(Customer customer) {
        String sql = "select * from customer";
            try {
                Statement stat = con.createStatement();
                ResultSet rs = stat.executeQuery(sql);

                int cust_id = 0;
                while (rs.next()) {
                    cust_id = rs.getInt("Customer_ID");
                }
            String sqls = "INSERT INTO customer"
                    + "(Customer_ID, Customer_Name, Customer_Surname, Customer_Area, Customer_Cell_No, Customer_Balance)"
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement state = con.prepareStatement(sqls);

            state.setInt(1, cust_id + 1);
            state.setString(2, customer.getCust_name());
            state.setString(3, customer.getCust_surname());
            state.setString(4, customer.getCust_area());
            state.setString(5, customer.getCust_cell_no());
            state.setDouble(6,customer.getCust_balance());

            int ci = state.executeUpdate();
            if (ci > 0) {
                System.out.println("Customer Information Updated" +
                        "\nEnter 'quit' to exit: ");
            }

        } catch (SQLException e2) {
            System.err.println(e2.getMessage());
        }

    }

    private static class Customer {
        private String cust_name;
        private String cust_surname;
        private String cust_area;
        private String cust_cell_no;
        private double cust_balance;


        public String getCust_name() {
            return cust_name;
        }
        public void setCust_name(String cust_name) {
            this.cust_name = cust_name;
        }

        public String getCust_surname() {
            return cust_surname;
        }
        public void setCust_surname(String cust_surname) {
            this.cust_surname = cust_surname;
        }

        public String getCust_area() {
            return cust_area;
        }
        public void setCust_area(String cust_area) {
            this.cust_area = cust_area;
        }

        public String getCust_cell_no() {
            return cust_cell_no;
        }
        public void setCust_cell_no(String cust_cell_no) {
            this.cust_cell_no = cust_cell_no;
        }

        public double getCust_balance(){
            return cust_balance;
        }
        public void setCust_balance(double cust_balance){
            this.cust_balance = cust_balance;
        }
    }
}