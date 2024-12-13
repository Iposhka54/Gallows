package service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class WordGenerator {
    private static final Path WORDS_FILE = Path.of("src", "main", "resources", "words.txt");
    private static final Random RANDOM = new Random();
    private List<String> words;

    public WordGenerator() {
        try{
            words = Files.readAllLines(WORDS_FILE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getRandomWord(){
        return words.get(RANDOM.nextInt(words.size()));
    }
}
