import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class SHA256Hash extends Hash {

    /**
     * Constructor for the SHA256Hash Class.
     * @param hash SHA-256 Hash to Crack
     * @param threads Number of Threads
     * @param path Path to Wordlist
     */
    public SHA256Hash(String hash, int threads, String path) {
        //call parent class constructor
        super(hash, threads, path);
    }

    /**
     * Method to calculate SHA-256 Hash with concurrency.
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
            MessageDigest SHA256 = null;
            try {
                SHA256 = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            byte[] SHA256Bytes = SHA256.digest(possible[x].getBytes(StandardCharsets.UTF_8));
            HexFormat SHA256Hex = HexFormat.of();
            String SHA256Hash = SHA256Hex.formatHex(SHA256Bytes);
            if (SHA256Hash.equals(hash)) {
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
     * Static method to validate the hash style for SHA-256.
     * @param hash SHA-256 Hash to check.
     * @return Validity of the SHA-256 Hash.
     */
    public static boolean checkHash(String hash) {
        //verify SHA-256 Hash length and format
        hash = hash.toUpperCase();
        if (hash.length() != 64) {
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
