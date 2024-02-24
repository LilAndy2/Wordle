import java.util.ArrayList;

public class ReferenceWord {
    /*
     * This class is used to store the reference word and its letters in an array.
     * The reference word is the word that the user is trying to guess.
     * The letters array is used to store the letters of the reference word.
     */
    private final String word;
    private final ArrayList<String> letters;
    public ReferenceWord(String referenceWord) {
        this.word = referenceWord;
        this.letters = new ArrayList<>();
        this.generateLettersArray();
    }
    private void generateLettersArray() {
        for (int i = 0; i < this.word.length(); i++) {
            this.letters.add(String.valueOf(this.word.charAt(i)));
        }
    }
    public ArrayList<String> getLetters() {
        return this.letters;
    }
}
