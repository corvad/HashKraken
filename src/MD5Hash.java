import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class MD5Hash extends Hash{

    public MD5Hash(String hash, int threads, String path, boolean createHashMap) {
        super(hash, threads, path, createHashMap);
    }

    private void hashAlgorithm(int min, int max) throws NoSuchAlgorithmException {
        // Algorithm of MD5 Goes Here
        System.out.println("Right One");
        for(int x=min;x<=max;x++){
            MessageDigest MD5 = MessageDigest.getInstance("MD5");
            byte[] MD5Bytes = MD5.digest(possible[x].getBytes(StandardCharsets.UTF_8));
            HexFormat MD5Hex = HexFormat.of();
            String MD5Hash = MD5Hex.formatHex(MD5Bytes);
            if(MD5Hash.equals(hash)){
                System.out.println("Found Password: " + possible[x]);
                stop();
            }
        }

    }

    private void h(){
        System.out.println("Inheritance is good.");
    }

}
