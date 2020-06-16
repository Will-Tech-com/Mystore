package facalty;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class NoInterest {

    public static void main(String[] args) {

    }

    public static void runComments() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter 'comment' to give feedback on shop experience: " +
                "\nEnter 'quit' to leave immediately");
        String action = "";

        while (!action.equals("quit")) {
            action = input.next();
            switch (action) {
                case "comment":
                    comments();
                    break;
                case "quit":
                    System.out.println("Thank you for coming to our store...");

            }
        }
    }
        public static void comments(){
            Scanner insert = new Scanner(System.in);
            System.out.print("Enter Comment: ");
            String action;
            action = insert.next();
        try {
            FileWriter writer = new FileWriter("C:\\Users\\Will\\IdeaProjects\\Pos1\\Comments.txt", true);
            BufferedWriter cust_comment = new BufferedWriter(writer);

            cust_comment.write(action);
            cust_comment.newLine();
            cust_comment.write("Thank you for your help...");


            cust_comment.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

