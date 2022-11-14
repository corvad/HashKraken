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
        SHA256Hash a = new SHA256Hash("b9c950640e1b3740e98acb93e669c65766f6670dd1609ba91ff41052ba48c6f3", 8, "C:\\Users\\100031399\\Downloads\\HashKraken\\src\\Top-10-Million.txt");
        a.start();
    }
}
