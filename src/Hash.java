import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.concurrent.CountDownLatch;

public class Hash {
    private String path;
    protected String hash;
    private int threads;
    protected String[] possible;
    protected CountDownLatch finished;
    protected boolean found;

    /**
     * Constructor for the Hash Class.
     * @param hash Hash to Crack
     * @param threads Number of Threads
     * @param path Path to Wordlist
     */
    public Hash(String hash, int threads, String path) {
        this.path = path;
        this.threads = threads;
        this.hash = hash;
        finished = new CountDownLatch(threads);
        found = false;
        fileRead();
    }

    /**
     * Read in the dictionary file into an array.
     */
    private void fileRead() {
        //read dictionary into memory
        try {
            System.out.println("Reading dictionary into memory; This may take a few moments.");
            possible = Files.readAllLines(Paths.get(path), StandardCharsets.ISO_8859_1).toArray(new String[0]);
        } catch (IOException e) {
            System.out.println("Error encountered while reading wordlist.");
            error();
        }
    }

    /**
     * Splits array and starts the hashing process.
     */
    public void start() {
        //split array into multiple parts and start threads to hash
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

    /**
     * Method to stop execution of the program with normal code.
     */
    public void stop() {
        //stops program execution with normal code
        System.exit(0);
    }

    /**
     * Method to stop execution of the program with an error code.
     */
    public void error() {
        //stops program execution with error code
        System.exit(1);
    }

    /**
     * Method to call hashAlgorithm() in child class.
     * @param finalIndexMin Min Index Present in Sub-Array Section
     * @param finalIndexMax Max Index Present in Sub-Array Section
     */
    protected void hashAlgorithm(int finalIndexMin, int finalIndexMax) {
        hashAlgorithm(finalIndexMin, finalIndexMax);
    }

}