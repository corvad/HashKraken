import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Hash<priavte> {
    private String path;
    private String hash;
    private int threads;
    private String[] possible;
    private rainbow
    private HashMap<String,String> rainbow = new HashMap<>();

    public Hash(String hash, int threads, String path){
        this.path = path;
        this.threads = threads;
        this.hash = hash;
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

    public String start(){
        // Create New Hashing Threads.

    }

    public void stop(){
        // Stop all threads.

    }


}