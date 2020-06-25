package facalty;

import java.sql.*;
import java.util.Scanner;


public class ProductsOptions {

    static Connection con;

    public static void runProduct() {
        try {
            String url = "jdbc:mysql://localhost/shop";
            String username = "root";
            String password = "";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, username, password);
            productOptions();
            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
public static void productOptions(){
    Scanner input = new Scanner(System.in);
    System.out.print("Enter 'display' to show Product Information: " +
            "\nEnter 'input' to input new Product: " +
            "\nEnter 'quit' to exit Products Options: ");
    String action = "";
    while (!action.equals("quit")){
        action = input.next();
        switch (action){
            case "display":
                displayProducts();
                break;
            case "input":
                readInProductDetails(input);
                break;
            case "quit":
                System.out.println("Exiting Product options");
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
    public static void displayProducts() {
        try {
            String sql2 = "select * from product";
            Statement state = con.createStatement();
            ResultSet p2 = state.executeQuery(sql2);

            System.out.println("\nProduct Information Display\n");
            while (p2.next()) {
                int prod_id = p2.getInt("Product_Id");
                String prod_name = p2.getString("Product_Name");
                double prod_price = p2.getDouble("Product_Price");
                int prod_stock = p2.getInt("Quantity_of_Stock");
                String category = p2.getString("Category");


                System.out.println(prod_id + " " + prod_name + " R" + prod_price + " " + prod_stock + " " + category);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void readInProductDetails(Scanner input) {
        //to insert Product information
        Product product = new Product();
        System.out.print("Product Id: ");
        product.setProd_id(input.nextInt()); // TODO: VALIDATE THESE FIELDS TO MAKE SURE A INTEGER IS ADDED AND NOT A STRING
        System.out.print("Product Name: ");
        product.setProd_name(input.next());
        System.out.print("Product Price: ");
        product.setProd_price(input.nextDouble());
        System.out.print("Number of Product Stock: ");
        product.setNum_stock(input.nextInt());
        System.out.print("Category: ");
        product.setCategory(input.next());

        productUpdate(product);
    }

    public static void productUpdate(Product product) {
        try {
            String sql = "INSERT INTO product"
                    + "(Product_Id, Product_Name, Product_Price, Quantity_of_Stock, Category)"
                    + "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement stat = con.prepareStatement(sql);

            stat.setInt(1, product.getProd_id());
            stat.setString(2, product.getProd_name());
            stat.setDouble(3, product.getProd_price());
            stat.setInt(4, product.getNum_stock());
            stat.setString(5, product.getCategory());

            int cc = stat.executeUpdate();
            if (cc > 0) {
                System.out.println("Sucessfully Updated Product Information" +
                        "\nEnter 'quit' to exit: ");
            }
        } catch (SQLException e3) {
            System.err.println(e3.getMessage());
        }


    }

    private static class Product {

        private int prod_id;
        private String prod_name;
        private double prod_price;
        private int num_stock;
        private String category;

        public int getProd_id() {
            return prod_id;
        }
        public void setProd_id(int prod_id) {
            this.prod_id = prod_id;
        }

        public String getProd_name() {
            return prod_name;
        }

        public void setProd_name(String prod_name) {
            this.prod_name = prod_name;
        }

        public double getProd_price() {
            return prod_price;
        }

        public void setProd_price(double prod_price) {
            this.prod_price = prod_price;
        }

        public int getNum_stock() {
            return num_stock;
        }

        public void setNum_stock(int num_stock) {
            this.num_stock = num_stock;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }
    }
}
