import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Hangman {
    private ArrayList<String> words;
    private String word; //the answer
    private char[] display; //word to be displayed
    private int incorrectGuesses = 0;
    // private boolean exit = false;
    private LinkedList<String> correctList;
    private LinkedList<String> wrongList;
    private char[][] hangman; //picture of the hangman

    /**
     * Initialises the words list with the list passed in.
     * @param words a list of words used to initialize the class
     */
    public Hangman(ArrayList<String> words) {
        this.words = words;
        chooseWord();
        display = new char[word.length()];
        for (int i = 0; i < display.length; i++) {
            display[i] = '-';
        }
        correctList = new LinkedList<>();
        wrongList = new LinkedList<>();
        hangman = new char[9][18];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 18; j++) {
                hangman[i][j] = ' ';
            }
        }
        for (int i = 1; i < 12; i++) {
            hangman[0][i] = '_';
        }
        for (int i = 1; i < 9; i++) {
            hangman[i][0] = '|';
        }
        for (int i = 1; i < 18; i++) {
            hangman[8][i] = '_';
        }
        hangman[1][11] = '|';
    }

    /**
     * Randomly chooses a word from the list.
     */
    public void chooseWord() {
        int length = words.size();
        int index = (int) ((length - 1) * Math.random());
        word = words.get(index);
    }

    /**
     * Handle the guess and add the letter to correctList or WrongList.
     */
    public void handleGuess(String s) {

        if (!word.contains(s)) {
            incorrectGuesses++;
            wrongList.add(s);
            if (incorrectGuesses == 8) {
                gameOver();
            }
        } else {
            correctList.add(s);
            updateWord(s.charAt(0));
        }
    }

    /**
     * @return true if user wins the game.
     */
    public boolean gameWon() {
        for (char c : display) {
            if (c == '-')
                return false;
        }
        return true;
    }

    /**
     * exit the program after the game is over.
     */
    public void gameOver() {
        cls();
        displayWord();
        displayGuessedAndRemain();
        printHangman();
        System.out.println("Game over!");
        System.exit(0);
    }

    /**
     * print hangman after every guess.
     */
    public void printHangman() {
        if (incorrectGuesses == 1) {
            hangman[2][11] = '0';
        }
        if (incorrectGuesses == 2) {
            hangman[3][11] = '|';
        }
        if (incorrectGuesses == 3) {
            for (int i = 8; i < 11; i++) {
                hangman[4][i] = '-';
            }
        }
        if (incorrectGuesses == 4) {
            for (int i = 12; i < 15; i++) {
                hangman[4][i] = '-';
            }
        }
        if (incorrectGuesses == 5) {
            hangman[5][10] = '/';
            hangman[6][9] = '/';
        }
        if (incorrectGuesses == 6) {
            hangman[5][12] = '\\';
            hangman[6][13] = '\\';
        }
        if (incorrectGuesses == 7) {
            hangman[7][7] = '-';
            hangman[7][8] = '-';
        }
        if (incorrectGuesses == 8) {
            hangman[7][14] = '-';
            hangman[7][15] = '-';
        }
        for (char[] line : hangman) {
            for (char c : line) {
                System.out.print(c);
            }
            System.out.println("");
        }
    }

    /**
     * Starts the game.
     */
    public void playGame() {
        System.out.println("Welcome to the hangman game!");
        System.out.println("");
        displayWord();
        displayGuessedAndRemain();
        printHangman();
        guessSentence();
    }

    /**
     * Ask the player to guess.
     */
    public void guessSentence() {
        System.out.println("Please guess a letter in the secret word.");
    }

    /**
     * Update the word to be displayed after correct guess.
     * @param c the letter guessed
     */
    public void updateWord(char c) {
        int index = word.indexOf(c);
        while (index != -1) {
            display[index] = c;
            if (index == (word.length() - 1))
                return;
            index = word.indexOf(c, index + 1);
        }
    }

    /**
     * Display the correctly guessed letters and hide the remaining with dashes.
     */
    public void displayWord() {
        System.out.println(String.valueOf(display));
    }

    /**
     * Display guessed letters.
     */
    public void displayGuessedAndRemain() {
        System.out.println("Correct letters guessed: " + correctList);
        System.out.println("Incorrect letters guessed: " + wrongList);
        System.out.println("Remaining Guesses: " + (8 - incorrectGuesses));
    }

    /**
     * Clear console.
     */
    public static void cls() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> words = new ArrayList<>();
        String s = "software stack overflow occur the call stack pointer exceed stack bound may consist  limited amount  address space often determin start  program  size  stack depend many factor including  programming language machine architecture  thread  and amount  available memory when  program attempts  utility more space than  available that when attempt access  beyond";
        String[] ss = s.split("\\s+");
        for (String word : ss) {
            words.add(word);
        }
        Hangman game = new Hangman(words);
        cls();
        game.playGame();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String letter = "";
        while ((letter = reader.readLine().trim()) != null) {
            if (!letter.isEmpty()) {
                if (letter.length() != 1) {
                    // Maybe you should add another method including line 217 to 220, thoses are also called in line 225-228
                    cls();
                    game.displayWord();
                    game.displayGuessedAndRemain();
                    game.printHangman();
                    System.out.println("Please give a single letter.");
                    continue;
                }
                game.handleGuess(letter);
                cls();
                game.displayWord();
                game.displayGuessedAndRemain();
                game.printHangman();
                if (game.gameWon()) {
                    System.out.println("You win!");
                    return;
                }
                game.guessSentence();
            }
        }
    }
}
