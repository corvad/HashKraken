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
     * @param lengthBrute Max length of bruteforce entries to generate
     */
    public MD5Hash(String hash, int threads, String path, boolean dictionary, boolean brute, int lengthBrute) {
        //call parent class constructor
        super(hash, threads, path, dictionary, brute, lengthBrute);
        //lowercase hash
        hash = hash.toLowerCase();
    }

    /**
     * Method to check MD5 Hash against plaintext hash.
     * @param plaintext Password to Bcrypt Hash
     * @return True if Plaintext Password Matches Existing Hash - False if Plaintext Password Does Not Match Existing Hash
     */
    protected boolean checkHash(String plaintext){
        //hash and compare
        MessageDigest MD5;
        try {
            MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] MD5Bytes = MD5.digest(plaintext.getBytes(StandardCharsets.UTF_8));
        HexFormat MD5Hex = HexFormat.of();
        String MD5Hash = MD5Hex.formatHex(MD5Bytes);
        return MD5Hash.equals(hash);
    }

    /**
     * Static method to validate the hash style for MD5.
     * @param hash MD5 Hash to check.
     * @return Validity of the MD5 Hash.
     */
    public static boolean verifyHash(String hash) {
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
