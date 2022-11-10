import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Hash{
    private String path;
    private String hash;
    private int threads;
    private String[] possible;
    private boolean createHashMap;
    private HashMap<String,String> rainbow = new HashMap<>();

    public Hash(String hash, int threads, String path, boolean createHashMap){
        this.path = path;
        this.threads = threads;
        this.hash = hash;
        this.createHashMap = createHashMap;
        fileRead();
    }

    public String getPath() {
        return path;
    }

    private void fileRead(){
        // Read Dictionary into Memory.
        try {
            possible = Files.readAllLines(Paths.get(path)).toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start(){
        // Check If Hashmap Dictionary For Hash Algorithm Exists

        // Start Threads to Hash Passwords and Split Array Indexes Into Equal Parts
        int max = possible.length-1;
        int lengths = max/threads;
        int indexMax = lengths;
        int indexMin = 0;
        for(int x=0; x<threads;x++){
            indexMax+=lengths;
            indexMin+=lengths+1;
            int finalIndexMin = indexMin;
            int finalIndexMax = indexMax;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    hashAlgorithm(finalIndexMin, finalIndexMax);
                }
            });
        }
    }

    public void stop(){
        // Stops Program Execution
        System.exit(0);
    }


}