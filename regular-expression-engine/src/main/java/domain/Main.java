package domain;

/**
 * Main is used to try different things, because there are no user interface at
 * the moment.
 *
 * @author strajama
 */
public class Main {

    public static void main(String[] args) {
        String language = "(ab)*|a*";
        Postfix p = new Postfix(language);
        System.out.println("printtaa " + language);
        System.out.println(p.toString());

    }
}
