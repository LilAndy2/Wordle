import java.util.ArrayList;

public class ReferenceWord {
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
