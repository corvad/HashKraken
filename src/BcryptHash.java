import org.springframework.security.crypto.bcrypt.BCrypt;
import java.util.regex.Pattern;

/**
 * Bcrypt hashing class inheriting off Hash class
 */
public class BcryptHash extends Hash {

    /**
     * Constructor for the BcryptHash Class.
     * @param hash Bcrypt Hash to Crack
     * @param threads Number of Threads
     * @param path Path to Wordlist
     * @param dictionary True - use dictionary / False - no dictionary
     * @param brute True - use bruteforce / False - do not use bruteforce mode
     * @param lengthBrute Max length of bruteforce entries to generate
     */
    public BcryptHash(String hash, int threads, String path, boolean dictionary, boolean brute, int lengthBrute) {
        //call parent class constructor
        super(hash, threads, path, dictionary, brute, lengthBrute);
    }

    /**
     * Method to check Bcrypt Hash against plaintext hash.
     * @param plaintext Password to Bcrypt Hash
     * @return True if Plaintext Password Matches Existing Hash - False if Plaintext Password Does Not Match Existing Hash
     */
    protected boolean checkHash(String plaintext){
        //hash and compare
        return BCrypt.checkpw(plaintext,hash);
    }

    /**
     * Static method to validate the hash style for Bcrypt.
     * @param hash Bcrypt Hash to check.
     * @return Validity of the Bcrypt Hash.
     */
    public static boolean verifyHash(String hash) {
        //verify Bcrypt hash length and format
        return Pattern.matches("^\\$2[ayb]\\$.{56}$", hash);
    }
}