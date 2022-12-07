import java.io.IOException;

public class HashKraken {

    // Program Starts Here
    public static void main(String[] args) {
        /*
        String helpMessage = "This will be a help message";

        // Check CMD Arguments
        if(args.length==0){
            // Display Help Message
            System.out.println(helpMessage);
        }
        else{
            if(args[0].toLowerCase().equals("--help") || args[0].toLowerCase().equals("-h")){
                // Display Help Message
                System.out.println(helpMessage);
            }
            else if(args[0].equals("")){

            }
            else{
                // Cloud Not Identify
                System.out.println("Could Not Identify Command(s)");
                // Display Help Message
                System.out.println(helpMessage);
                // Stop Execution of Program
                System.exit(0);
            }
        }
        */
        MD5Hash a = new MD5Hash("482c811da5d5b4bc6d497ffa98491e38", 8, "",false,false,0);
        a.start();
    }
}
