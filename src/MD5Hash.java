import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class MD5Hash extends Hash {

    public MD5Hash(String hash, int threads, String path) {
        super(hash, threads, path);
    }

    protected void hashAlgorithm(int min, int max, int lengths) {
        // Algorithm of MD5 Goes Here
        System.out.println(Thread.currentThread().getName() + " Started Hashing");
        for (int x = min; x <= max; x++) {
            if(x==lengths*.25){
                System.out.println(Thread.currentThread().getName() + " 25% Done Hashing");
            }
            if(x==lengths*.5){
                System.out.println(Thread.currentThread().getName() + " 50% Done Hashing");
            }
            if(x==lengths*.75){
                System.out.println(Thread.currentThread().getName() + " 75% Done Hashing");
            }
            MessageDigest MD5 = null;
            try {
                MD5 = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            byte[] MD5Bytes = MD5.digest(possible[x].getBytes(StandardCharsets.UTF_8));
            HexFormat MD5Hex = HexFormat.of();
            String MD5Hash = MD5Hex.formatHex(MD5Bytes);
            if (MD5Hash.equals(hash)) {
                System.out.println("Found Password: " + possible[x]);
            }
        }
        finished.countDown();
        System.out.println(Thread.currentThread().getName() + " Finished Hashing");
    }

    public static boolean checkHash(String hash) {
        hash = hash.toUpperCase();
        if (hash.length() != 32) {
            return false;
        } else {
            for (int x = 0; x < hash.length(); x++) {
                if (!(hash.charAt(x) == 'A' || hash.charAt(x) == 'B' || hash.charAt(x) == 'C' || hash.charAt(x) == 'D' || hash.charAt(x) == 'E' || hash.charAt(x) == 'F' || hash.charAt(x) == '0' || hash.charAt(x) == '1' || hash.charAt(x) == '2' || hash.charAt(x) == '3' || hash.charAt(x) == '4' || hash.charAt(x) == '5' || hash.charAt(x) == '6' || hash.charAt(x) == '7' || hash.charAt(x) == '8' || hash.charAt(x) == '9')) {
                    // Do nothing.
                } else {
                    return false;
                }
            }
            return true;
        }
    }

}
