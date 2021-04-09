package de.mnbn.mytools.bilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author schrader
 */
public class DuplicateFinder {

    public void run(Options options) {
        try (Stream<Path> pathes = Files.walk(Path.of(options.getDirectory()))) {
            Map<String, List<Path>> hashes = pathes.collect(Collectors.groupingBy(this::hash));

            for (Map.Entry<String, List<Path>> hash : hashes.entrySet()) {
                if (hash.getValue().size() > 1) {
                    String value = hash.getValue().stream().map(p -> p.toAbsolutePath().toString()).collect(Collectors.joining("\n\t"));
                    System.out.printf("%s :\n\t%s\n", hash.getKey(), value);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private String hash(Path path) {
        if (!Files.isRegularFile(path)) {
            return path.toAbsolutePath().toString();
        }

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(Files.readAllBytes(path));
            return Base64.getEncoder().encodeToString(digest);
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println("Failed: " + path.toAbsolutePath().toString());
            return path.toAbsolutePath().toString();
        }
    }

}
