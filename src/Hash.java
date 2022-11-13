import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;

public class Hash {
    private String path;
    protected String hash;
    private int threads;
    protected String[] possible;
    protected CountDownLatch finished;

    public Hash(String hash, int threads, String path) {
        this.path = path;
        this.threads = threads;
        this.hash = hash;
        finished = new CountDownLatch(threads);
        fileRead();
    }

    public String getPath() {
        return path;
    }

    private void fileRead() {
        // Read Dictionary into Memory.
        try {
            System.out.println("Reading dictionary into memory; This may take a few moments.");
            possible = Files.readAllLines(Paths.get(path), StandardCharsets.ISO_8859_1).toArray(new String[0]);
        } catch (IOException e) {
            System.out.println("Error encountered while reading wordlist.");
            error();
        }
    }

    public void start() {
        // Split Array into Multiple Parts and Start Threads.
        int max = possible.length - 1;
        int lengths = max / threads;
        int indexMax = lengths;
        int indexMin = 0;
        for (int x = 0; x < threads; x++) {
            if (x != 0) {
                indexMax += lengths;
                indexMin += lengths + 1;
            }
            int finalIndexMin = indexMin;
            int finalIndexMax = indexMax;
            new Thread(() -> {
                hashAlgorithm(finalIndexMin, finalIndexMax);
            }).start();
        }
        try {
            finished.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("No Password Found!");

    }

    public void stop() {
        // Stops Program Execution with Normal Code
        System.exit(0);
    }

    public void error() {
        // Stops Program Execution with Error Code
        System.exit(1);
    }

    protected void hashAlgorithm(int finalIndexMin, int finalIndexMax) {
        hashAlgorithm(finalIndexMin, finalIndexMax);
    }

}