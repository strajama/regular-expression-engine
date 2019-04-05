package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * FileIO implements IO-interface and is given to App when application reads
 * words from the file.
 *
 * @author strajama
 */
public class FileIO implements IO {

    private ArrayList<String> words;

    /**
     * Creates new FileIO. It creates new list that has command "matcher" in the
     * beginning, then the language and then all the words in the given file
     *
     * @param language - language that is recognised
     * @param file - file that has list of words
     * @throws FileNotFoundException
     */
    public FileIO(String language, String file) throws FileNotFoundException {
        this.words = new ArrayList<>();
        words.add("matcher");
        words.add(language);
        try (Scanner scanner = new Scanner(new File(file))) {
            while (scanner.hasNext()) {
                words.add(scanner.next());
            }
        } catch (Exception e) {
            System.out.println("Error " + e.getMessage());
        }
        words.add("no");
    }

    /**
     * Returns and removes next word from the list. If list is empty returns
     * "quit"
     *
     * @param line - String that is given by the App and which is not used
     * @return next word from the list or "quit"
     */
    @Override
    public String readLine(String line) {
        if (!words.isEmpty()) {
            String word = this.words.get(0);
            words.remove(0);
            return word;
        }
        return "quit";
    }

}
