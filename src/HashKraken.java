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
        MD5Hash a = new MD5Hash("3a170d0d80ff7927834d61a4a38b870b", 8, "C:\\Users\\100031399\\Downloads\\HashKraken\\src\\Top-10-Million.txt");
        a.start();
    }
}
