import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * Hash object for managing hashing threads and file io operations.
 */
public class Hash {
    protected final String path;
    protected final String hash;
    protected final boolean dictionary;
    protected final boolean brute;
    protected final int lengthBrute;
    private final int threads;
    protected String[] possible;
    protected CountDownLatch finished;
    protected boolean found;

    /**
     * Constructor for the Hash Class.
     *
     * @param hash        Hash to Crack
     * @param threads     Number of Threads
     * @param path        Path to Wordlist
     * @param dictionary  True - use dictionary / False - no dictionary
     * @param brute       True - use bruteforce / False - do not use bruteforce mode
     * @param lengthBrute Max length of bruteforce entries to generate
     */
    public Hash(String hash, int threads, String path, boolean dictionary, boolean brute, int lengthBrute) {
        this.path = path;
        this.threads = threads;
        this.hash = hash;
        this.dictionary = dictionary;
        this.brute = brute;
        this.lengthBrute = lengthBrute;
        finished = new CountDownLatch(threads);
        found = false;
        //do respective operation for respective mode
        if (brute && lengthBrute <= 5) {
            genBruteList();
        } else if (dictionary) {
            fileRead();
        } else {
            builtinRead();
        }
        //check that length is not greater than 5
        if (lengthBrute > 5) {
            //return warning
            System.out.println("Warning: Program will run on single thread for bruteforce grater than 5 characters, ignoring provided threads argument.");
        }
        //check threads and array bounds
        if (threads > possible.length) {
            //return error and exit program because of too many threads
            System.out.println("Too many threads in comparison for the size of the possible passwords.");
            error();
        }
    }

    /**
     * Generate bruteforce words into an array.
     */
    private void genBruteList() {
        //generate bruteforce words and load into array in memory
        System.out.println("Generating bruteforce wordlist; This may take a few moments.");
        char[] alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUYWXYZ1234567890".toCharArray();

    }

    /**
     * Read in the built-in wordlist into an array.
     */
    private void builtinRead() {
        //read dictionary from jar into memory
        try {
            System.out.println("Reading built-in dictionary; This may take a few moments.");
            InputStream stream = getClass().getResourceAsStream("Top-10-Million.txt");
            assert stream != null;
            BufferedReader file = new BufferedReader(new InputStreamReader(stream, StandardCharsets.ISO_8859_1));
            ArrayList<String> temp = new ArrayList<>();
            String templine;
            while ((templine = file.readLine()) != null) {
                temp.add(templine);
            }
            possible = temp.toArray(new String[0]);
        } catch (IOException e) {
            //return error and exit program because of error reading from Jar
            System.out.println("Error Reading File from Jar.");
            error();
        }
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
        //split array into multiple parts (# of threads) and start threads to hash passwords
        int max = possible.length;
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
                System.out.println(finalIndexMax);
                hashThread(finalIndexMin, finalIndexMax);
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
     * Method to call checkHash() in child class.
     * @param plaintext Password to Hash in Child Class
     * @return True if Plaintext Password Matches Existing Hash - False if Plaintext Password Does Not Match Existing Hash
     */
    protected boolean checkHash(String plaintext){
        return checkHash(plaintext);
    }

    /**
     * Method to calculate Hashes with concurrency.
     * @param min Min Index Present in Sub-Array Section
     * @param max Max Index Present in Sub-Array Section
     */
    protected void hashThread(int min, int max) {
        System.out.println(Thread.currentThread().getName() + " Started Hashing");
        boolean twentyfive = false;
        boolean fifty = false;
        boolean seventyfive = false;
        //loop through sub-array
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
            if (checkHash(possible[x])) {
                //password found
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
}