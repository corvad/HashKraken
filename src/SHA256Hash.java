import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

/**
 * SHA256 hashing class inheriting off Hash class
 */
public class SHA256Hash extends Hash {

    /**
     * Constructor for the SHA256Hash Class.
     * @param hash SHA256 Hash to Crack
     * @param threads Number of Threads
     * @param path Path to Wordlist
     * @param dictionary True - use dictionary / False - no dictionary
     * @param brute True - use bruteforce / False - do not use bruteforce mode
     * @param lengthBrute Max length of bruteforce entries to generate
     */
    public SHA256Hash(String hash, int threads, String path, boolean dictionary, boolean brute, int lengthBrute) {
        //call parent class constructor
        super(hash, threads, path, dictionary, brute, lengthBrute);
        //lowercase hash
        hash = hash.toLowerCase();
    }

    /**
     * Method to check SHA256 Hash against plaintext hash.
     * @param plaintext Password to Bcrypt Hash
     * @return True if Plaintext Password Matches Existing Hash - False if Plaintext Password Does Not Match Existing Hash
     */
    protected boolean checkHash(String plaintext){
        //hash and compare
        MessageDigest SHA256;
        try {
            SHA256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] SHA256Bytes = SHA256.digest(plaintext.getBytes(StandardCharsets.UTF_8));
        HexFormat SHA256Hex = HexFormat.of();
        String SHA256Hash = SHA256Hex.formatHex(SHA256Bytes);
        return SHA256Hash.equals(hash);
    }

    /**
     * Static method to validate the hash style for SHA256.
     * @param hash SHA256 Hash to check.
     * @return Validity of the SHA256 Hash.
     */
    public static boolean verifyHash(String hash) {
        //verify SHA256 hash length and format
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
