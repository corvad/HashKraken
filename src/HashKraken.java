public class HashKraken {

    // Program Starts Here
    public static void main(String[] args) throws InterruptedException {
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
        MD5Hash a = new MD5Hash("aa84c94a7cf9b26038adcccd8b0fd0a1",8,"C:\\Users\\100031399\\Downloads\\HashKraken\\src\\Top-10-Million.txt",true);
        a.start();
    }
}
