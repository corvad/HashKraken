/**
 * Runner Class for HashKraken
 */
public class HashKraken {

    /**
     * Parse CMD arguments and start hashing.
     */
    //program starts here
    public static void main(String[] args) {

        //help message
        String helpMessage = "HashKraken - David Corvaglia 2022\n" +
                "  Documentation: github.com/corvad/HashKraken\n" +
                "  Required Arguments: [Hashing Algorithm] [Hash] [Threads]\n" +
                "  Default Mode Uses Top 10 Million Wordlist Bundled\n" +
                "  Additional Optional Arguments:\n" +
                "    -p/--path [Wordlist Path]\n" +
                "    OR\n" +
                "    -b/--bruteforce [Length of Max Combinations (Cannot be grater than 5)]";

        //algorithm to use
        String algorithm = null;

        //hash
        String hash = null;

        //number of threads
        int threads = 0;

        //mode to use
        String mode = "builtin";

        //path to file
        String path = "";

        //bruteforce length to gen
        int length = 0;

        //check CMD arguments
        //if empty display help message
        if(args.length==0) {
            //display help message
            System.out.println(helpMessage);
        }
        else if(args.length==1 && (args[0].toLowerCase().equals("--help") || args[0].toLowerCase().equals("-h"))){
            //display help message
            System.out.println(helpMessage);
        }
        //look at other arguments
        else {
            //look for algorithm argument
            if(args[0].toLowerCase().equals("md5") || args[0].toLowerCase().equals("sha256") || args[0].toLowerCase().equals("bcrypt")) {
                //set algorithm
                algorithm = args[0].toLowerCase();
            }else{
                //invalid algorithm message and stop program execution
                System.out.println("Invalid or missing algorithm.");
                System.exit(1);
            }
            //look for hash and check hash
            if(args.length >= 2){
                if((MD5Hash.checkHash(args[1]) || SHA256Hash.checkHash(args[1]) || BcryptHash.checkHash(args[1]))) {
                    //set algorithm
                    if(MD5Hash.checkHash(args[1]) && algorithm.equals("md5")) {
                        //set hash
                        hash = args[1];
                    }
                    else if(SHA256Hash.checkHash(args[1]) && algorithm.equals("sha256")){
                        //set hash
                        hash = args[1];
                    }
                    else if(BcryptHash.checkHash(args[1]) && algorithm.equals("bcrypt")){
                        //set hash
                        hash = args[1];
                    }
                    else{
                        //invalid hash message for algorithm and stop program execution
                        System.out.println("Hash does not match algorithm.");
                        System.exit(1);
                    }
                    hash = args[1].toLowerCase();
                }else{
                    //invalid hash message and stop program execution
                    System.out.println("Invalid hash.");
                    System.exit(1);
                }
            }else{
                //missing hash message and stop program execution
                System.out.println("Missing hash.");
                System.exit(1);
            }
            //look for number of threads
            if(args.length >= 3){
                //check if input is integer
                try{
                    threads = Integer.parseInt(args[2]);
                }catch (NumberFormatException e) {
                    //invalid number of threads argument message and stop program execution
                    System.out.println("Invalid number of threads.");
                    System.exit(1);
                }
            }else{
                //no thread argument message and stop program execution
                System.out.println("Missing number of threads.");
                System.exit(1);
            }
            //look for optional arguments
            if(args.length == 5){
                //look for path to wordlist
                if(args[3].toLowerCase().equals("-p") || args[3].toLowerCase().equals("--path")) {
                    mode = "wordlist";
                    path = args[4];
                }
                else if(args[3].toLowerCase().equals("-b") || args[3].toLowerCase().equals("--bruteforce")) {
                    mode = "bruteforce";
                    //read integer for max length of bruteforce
                    try{
                        length = Integer.parseInt(args[4]);
                    }catch (NumberFormatException e) {
                        //invalid length message and stop program execution
                        System.out.println("Invalid max length of bruteforce combinations.");
                        System.exit(1);
                    }
                }
                else{
                    //invalid optional argument message and stop program execution
                    System.out.println("Invalid optional argument(s).");
                    System.exit(1);
                }
            }
        }
        //initialize respective hashing objects and start them
        if(algorithm != null){
            if(algorithm.equals("md5")){
                (new MD5Hash(hash,threads,path,mode.equals("wordlist"),mode.equals("bruteforce"),length)).start();
            }
            if(algorithm.equals("sha256")){
                (new SHA256Hash(hash,threads,path,mode.equals("wordlist"),mode.equals("bruteforce"),length)).start();
            }
            if(algorithm.equals("bcrypt")){
                (new BcryptHash(hash,threads,path,mode.equals("wordlist"),mode.equals("bruteforce"),length)).start();
            }
        }
    }
}
