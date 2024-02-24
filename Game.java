import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    /*
     * This class is responsible for generating the reference word for the game.
     * It also contains the difficulty level of the game and the words array.
     * The words array is generated from the word database file based on the difficulty level.
     * The reference word is chosen randomly from the words array.
     */
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
        Random random = new Random();
        int randomNumber = random.nextInt(this.words.size());
        return this.words.get(randomNumber).toUpperCase();
    }
    public int getWordLength() {
        return this.difficulty.equals("easy") ? 4 : this.difficulty.equals("medium") ? 5 : 6;
    }
}
