import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private final String difficulty;
    private final ArrayList<String> words;
    public Game (String difficulty) throws IOException {
        this.difficulty = difficulty;
        this.words = new ArrayList<>();
        this.generateWordsArray();
    }
    private void generateWordsArray() throws IOException {
        File wordDatabase = null;
        switch (this.difficulty) {
            case "easy" -> wordDatabase = new File("utils/words/4-letters-words.txt");
            case "medium" -> wordDatabase = new File("utils/words/5-letters-words.txt");
            case "hard" -> wordDatabase = new File("utils/words/6-letters-words.txt");
        }

        assert wordDatabase != null;
        BufferedReader wordReader = new BufferedReader(new FileReader(wordDatabase));
        String line;
        while((line = wordReader.readLine()) != null) {
            this.words.add(line);
        }
        wordReader.close();
    }
    public String chooseReferenceWord() {
        int randomNumber = new Random().nextInt(this.words.size());
        return this.words.get(randomNumber);
    }
}
