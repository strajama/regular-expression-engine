package app;

/**
 * Main for running application
 *
 * @author strajama
 */
public class Main {

    /**
     * Runs application
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        App application = new App();
        application.init(new FileIO("(ab)+|c+", "testi1.txt"));
        application.run();
    }

}
