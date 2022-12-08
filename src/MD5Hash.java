import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/**
 * MD5 hashing class inheriting off Hash class
 */
public class MD5Hash extends Hash {

    /**
     * Constructor for the MD5Hash Class.
     * @param hash MD5 Hash to Crack
     * @param threads Number of Threads
     * @param path Path to Wordlist
     * @param dictionary True - use dictionary / False - no dictionary
     * @param brute True - use bruteforce / False - do not use bruteforce mode
     * @param numberBrute Number of bruteforce entries to generate
     */
    public MD5Hash(String hash, int threads, String path, boolean dictionary, boolean brute, int numberBrute) {
        //call parent class constructor
        super(hash, threads, path, dictionary, brute, numberBrute);
        //lowercase hash
        hash = hash.toLowerCase();
    }

    /**
     * Method to calculate MD5 Hash with concurrency.
     * @param min Min Index Present in Sub-Array Section
     * @param max Max Index Present in Sub-Array Section
     */
    protected void hashAlgorithm(int min, int max) {
        System.out.println(Thread.currentThread().getName() + " Started Hashing");
        boolean twentyfive = false;
        boolean fifty = false;
        boolean seventyfive = false;
        //loop through sub-array
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
            MessageDigest MD5;
            try {
                MD5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            byte[] MD5Bytes = MD5.digest(possible[x].getBytes(StandardCharsets.UTF_8));
            HexFormat MD5Hex = HexFormat.of();
            String MD5Hash = MD5Hex.formatHex(MD5Bytes);
            if (MD5Hash.equals(hash)) {
                //password found
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
     * Static method to validate the hash style for MD5.
     * @param hash MD5 Hash to check.
     * @return Validity of the MD5 Hash.
     */
    public static boolean checkHash(String hash) {
        //verify MD5 hash length and format
        hash = hash.toUpperCase();
        if (hash.length() != 32) {
            return false;
        } else {
            for (int x = 0; x < hash.length(); x++) {
                if (!(hash.charAt(x) == 'A' || hash.charAt(x) == 'B' || hash.charAt(x) == 'C' || hash.charAt(x) == 'D' || hash.charAt(x) == 'E' || hash.charAt(x) == 'F' || hash.charAt(x) == '0' || hash.charAt(x) == '1' || hash.charAt(x) == '2' || hash.charAt(x) == '3' || hash.charAt(x) == '4' || hash.charAt(x) == '5' || hash.charAt(x) == '6' || hash.charAt(x) == '7' || hash.charAt(x) == '8' || hash.charAt(x) == '9')) {
                    return false;
                }
            }
            return true;
        }
    }

}
