package facalty;

import java.sql.*;
import java.util.Scanner;

public class PosCart {
    static Connection con = null;

    public static void main(String [] args){


    }

    public static void cart(){
        try {
            String url = "jdbc:mysql://localhost/shop";
            String userName = "root";
            String password = "will12boskowski1999";

            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, userName, password);
            cartOptions();
            con.close();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

     public static void cartOptions(){
        Scanner input = new Scanner(System.in);
        String action = "";
        System.out.println("Enter 'add' to add item to Cart: " +
                "\nEnter 'remove' to remove item from Cart: " +
                "\nEnter 'view' to display Cart items: ");

        while(!action.equals("quit")){
            action = input.next();
            switch(action){
                case "add":
                   addCart(input);
                    break;
                case "remove":

                    break;
                case "view":

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
    public static void addCart(Scanner input) {
        //to insert Product information
        System.out.println("\nMenu\n");
        menu();

        PosCart.ProductOp product = new PosCart.ProductOp();

        System.out.print("Product Id: ");
        product.setProd_id(input.nextInt()); // TODO: VALIDATE THESE FIELDS TO MAKE SURE A INTEGER IS ADDED AND NOT A STRING
        System.out.println("Product Name: ");
        product.setProd_name(input.next());

        productUpdate(product);
    }
    public static void productUpdate(ProductOp product) {
        try {
            Statement state = con.createStatement();
            String sql = "SELECT Product_Id, Product_Name, Product_Price, Quantity_of_Stock FROM product" +
                    " WHERE Product_Id = '?','?'";
            PreparedStatement stat = con.prepareStatement(sql);

            stat.setInt(1, product.getProd_id());
            stat.setString(2,product.getProd_name());
            stat.executeQuery(sql);

            ResultSet rs = state.executeQuery(sql);

            while (rs.next()) {
                //Retrieve by column name
                int prod_id = rs.getInt("Product_Id");
                String prod_name = rs.getString("Product_Name");
                int prod_price = rs.getInt("Product_Price");
                int num_stock = rs.getInt("Quantity_of_Stock");

                //Display values
                System.out.println("ID: " + prod_id);
                System.out.println("Name: " + prod_name);
                System.out.println("Price: " + prod_price);
                System.out.println( "Number of Stock: " + num_stock);
            }

        } catch (SQLException e3) {
            System.err.println(e3.getMessage());
        }
    }

    public static void menu() {
        try {
            String sql2 = "select * from product";
            Statement state = con.createStatement();
            ResultSet p2 = state.executeQuery(sql2);
            
            while (p2.next()) {
                int prod_id = p2.getInt("Product_Id");
                String prod_name = p2.getString("Product_Name");
                double prod_price = p2.getDouble("Product_Price");
                int prod_stock = p2.getInt("Quantity_of_Stock");
                String category = p2.getString("Category");


                System.out.println(prod_id + " " + prod_name + " R" + prod_price + " " + prod_stock + " " + category);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private static class ProductOp{
        private int prod_id;
        private String prod_name;

        public int getProd_id() {
            return prod_id;
        }
        public void setProd_id(int prod_id){
            this.prod_id = prod_id;
        }
        public String getProd_name(){
            return prod_name;
        }
        public void setProd_name(String prod_name){
            this.prod_name = prod_name;
        }
    }
}
