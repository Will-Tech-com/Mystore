package facalty;

import java.sql.DriverManager;
import java.sql.Connection;
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
}
