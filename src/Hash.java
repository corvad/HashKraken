import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.concurrent.CountDownLatch;

public class Hash {
    private String path;
    protected String hash;
    protected boolean dictionary;
    protected boolean brute;
    protected int numberBrute;
    private int threads;
    protected String[] possible;
    protected CountDownLatch finished;
    protected boolean found;

    /**
     * Constructor for the Hash Class.
     * @param hash Hash to Crack
     * @param threads Number of Threads
     * @param path Path to Wordlist
     * @param dictionary True - use dictionary / False - no dictionary
     * @param brute True - use bruteforce / False - do not use bruteforce mode
     * @param numberBrute Number of bruteforce entries to generate
     */
    public Hash(String hash, int threads, String path, boolean dictionary, boolean brute, int numberBrute) {
        this.path = path;
        this.threads = threads;
        this.hash = hash;
        this.dictionary = dictionary;
        this.brute = brute;
        this.numberBrute = numberBrute;
        finished = new CountDownLatch(threads);
        found = false;
        if(brute){
            genBruteList();
        }
        else if(dictionary){
            fileRead();
        }else{
            builtinRead();
        }
    }

    /**
     * Generate bruteforce words into an array
     */
    private void genBruteList() {
        //generate bruteforce words into an array
        System.out.println("Generating bruteforce word list; This may take a few moments.");
    }

    /**
     * Read in the built-in wordlist into an array.
     */
    private void builtinRead() {
        //read dictionary from jar into memory
        try {
            System.out.println("Reading dictionary into memory; This may take a few moments.");
            InputStream stream = getClass().getResourceAsStream("Top-10-Million.txt");
            assert stream != null;
            BufferedReader file = new BufferedReader(new InputStreamReader(stream, StandardCharsets.ISO_8859_1));
            ArrayList<String> temp = new ArrayList<>();
            String templine;
            while ((templine = file.readLine()) != null) {
                temp.add(templine);
            }
            possible = temp.toArray(new String[0]);
        }
        catch(IOException e){
            System.out.println("Error Reading File from Jar.");
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