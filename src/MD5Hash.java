public class MD5Hash extends Hash{

    public MD5Hash(String hash, int threads, String path, boolean createHashMap) {
        super(hash, threads, path, createHashMap);
    }

    public void startHash(){
        super.start();
    }

    public void hashAlgorithm(int min, int max){
        // Algorithm of MD5 Goes Here
        System.out.println("Min: " + min + "Max:" + max);
    }

}
