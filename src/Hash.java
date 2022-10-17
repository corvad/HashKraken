public class Hash {
    private String path;
    private String hash;
    private int threads;
    private String[] possible;

    public Hash(String hash, int threads, String path){
        this.path = path;
        this.threads = threads;
        this.hash = hash;
        fileRead();
    }

    public String getPath() {
        return path;
    }

    priavte String fileRead(){

    }
}
