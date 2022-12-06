import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BcryptHash extends Hash {

    /**
     * Constructor for the BcryptHash Class.
     * @param hash Bcrypt Hash to Crack
     * @param threads Number of Threads
     * @param path Path to Wordlist
     * @param dictionary True - use dictionary / False - no dictionary
     * @param brute True - use bruteforce / False - do not use bruteforce mode
     * @param numberBrute Number of bruteforce entries to generate
     */
    public BcryptHash(String hash, int threads, String path, boolean dictionary, boolean brute, int numberBrute) {
        //call parent class constructor
        super(hash, threads, path, dictionary, brute, numberBrute);
    }

    /**
     * Method to calculate Bcrypt Hash with concurrency.
     * @param min Min Index Present in Sub-Array Section
     * @param max Max Index Present in Sub-Array Section
     */
    protected void hashAlgorithm(int min, int max) {
        System.out.println(Thread.currentThread().getName() + " Started Hashing");
        boolean twentyfive = false;
        boolean fifty = false;
        boolean seventyfive = false;
        //loop through Sub-Array
        for (int x = min; x <= max && !found; x++) {
            //save current percent
            int percent = (int) (((x - (min * 1.0)) / (max - min)) * 100);
            //print progress
            if(percent == 25 && !twentyfive){
                twentyfive = true;
                System.out.println(Thread.currentThread().getName() + " 25% Done Hashing");
            }
            if(percent == 50 && !fifty){
                fifty = true;
                System.out.println(Thread.currentThread().getName() + " 50% Done Hashing");
            }
            if(percent == 75 && !seventyfive){
                seventyfive = true;
                System.out.println(Thread.currentThread().getName() + " 75% Done Hashing");
            }
            //hash and compare
            if (BCrypt.checkpw(possible[x],hash)) {
                System.out.println("Found Password: " + possible[x]);
                found = true;
                stop();
            }
        }
        //countdown once finished
        finished.countDown();
        if(!found){
            System.out.println(Thread.currentThread().getName() + " Finished Hashing");
        }
    }

    /**
     * Static method to validate the hash style for Bcrypt.
     * @param hash Bcrypt Hash to check.
     * @return Validity of the Bcrypt Hash.
     */
    public static boolean checkHash(String hash) {
        //verify Bcrypt hash length and format
        return Pattern.matches("^\\$2[ayb]\\$.{56}$", hash);
    }
}
