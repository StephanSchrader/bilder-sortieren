package de.mnbn.mytools.bilder;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        Options options = new Options();
        options.setCheckOnly(true);
        options.setDirectory("/Users/schrader/Pictures/Privat/Backup");

        new DuplicateFinder().run(options);

    }

}
