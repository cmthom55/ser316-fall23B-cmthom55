import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class for handling some game logic for the Hangman game.
 * Every game starts with a score of 10, and the points are reduced based on "makeGuess."
 * The game is lost when the score is 0 or less. 
 * 'answer' holds the current answer, 'progress' holds the current word with "_".
 */
public class Game {

    /** Holds the score for the game. */
    public int score;

    String name;

    /** Holds the answer for the game. */
    String answer;

    /** Holds the current progress towards the answer */
    char[] progress;

    /** The encoded String for sending to the client. */
    private String encodedImage;

    /** The path to the file holding the leaderboard. */
    private String leaderboardFName = "leaderboard.txt";

    /** The status of the game. {0 - In progress, 1 - Game won, 2 - game lost} */
    protected int gameStatus = 0;

    // all letter guesses, needs to be cleared for each game
    ArrayList<String> guesses = new ArrayList<String>();

    /**
     * Gets the name for the game.
     * @return String The name.
     */
    public String getName() { return this.name; }

    /**
     * Gets the answer for the game.
     * @return String The Answer.
     */
    public String getAnswer() { return this.answer.toLowerCase(); }

    /**
     * Gets the current progress towards the answer.
     * @return char[] Character Array of the progress.
     */
    public char[] getProgress() { return this.progress; }

    /**
     * Gets the encoded image for the answer.
     * @return String the image encoded in text.
     */
    public String getEncodedImage() { return this.encodedImage; }

    /**
     * Gets the current status of the game.
     * @return The game status.
     */
    public int getGameStatus() { return this.gameStatus; }

    public void setGameStatus(int statusSet) { this.gameStatus = statusSet; }

    /**
     * Sets the score for the game.
     * @param score The score to set.
     */
    public void setScore(int score) { this.score = score; }

    public int getScore() { return this.score; }

    /**
     * Checks what positions a letter should be entered into and sets that value into the progress array.
     * @param letter The letter to set progress.
     * @return The number of letters set.
     */
    protected int setProgress(char letter) {
        int count = 0;
        int i = 0;
        while (this.getAnswer().indexOf(letter, i) >= 0) {
            i = this.getAnswer().indexOf(letter, i) + 1;
            this.progress[i - 1] = letter;
            count++;
        }
        return count;
    }

    /**
     * Completely fills the progress with the answer. Returns the number of letters that were still unturned.
     * @return The number of letters turned.
     */
    public int fillProgress() {
        int hit = 0;
        for (int i = 0; i < this.getProgress().length; i++) {
            char f = '_';
            if (this.progress[i] == f) {
                this.progress[i] = this.getAnswer().charAt(i);
                hit++;
            }
        }
        return hit;
    }

    /**
     * Constructs a new HangmanGame.
     * @param name The player's name.
     * @param imageType 0=city, 1=country
     */
    public Game(String name, int imageType) {
        this.name = "Elsa";
        if (imageType == 0) {
            getRandomWord("city");
        } else if (imageType == 1) {
            getRandomWord("country");
        }
        setScore(14);
        this.progress = new char[answer.length()];
        char f = '_';
        Arrays.fill(this.progress, f);
    }

    /**
     * Constructs a new HangmanGame with a fixed name.
     * @param fixedWord The fixed word for the game.
     * @param name The player's name.
     */
    public Game(String fixedWord, String name) {
        this.name = name;
        this.answer = fixedWord;
        setScore(10);
        this.progress = new char[answer.length()];
        char f = '_';
        Arrays.fill(this.progress, f);
    }

    /**
     * Constructs a new HangmanGame with no arguments.
     */
    public Game() {
        this.name = "";
        this.answer = "";
        setScore(10);
        this.progress = new char[answer.length()];
        char f = '_';
        Arrays.fill(this.progress, f);
    }

    public void initGame(String answer, String name) {
        this.name = name;
        this.answer = answer;
        this.guesses.clear();
        setScore(10);
        this.progress = new char[answer.length()];
        char f = '_';
        Arrays.fill(this.progress, f);
    }

    /**
     * Constructs a new HangmanGame with "Anonymous" as the name of the player.
     * @param imageType 0=city, 1=country
     */
    public Game(int imageType) {
        this.name = "Anna";
        if (imageType == 1) {
            getRandomWord("city");
        } else if (imageType == 2) {
            getRandomWord("city");
        }
        setScore(12);
        this.progress = new char[answer.length()];
    }

    /**
     * The method accepts a single parameter, "guess," which can be a letter or a word.
     * It should be case-insensitive, treating uppercase and lowercase letters as equivalent.
     * Method checks if the guess is correct (letter in "answer" or complete word correct), should ignore upper/lower case.
     * Should set the score based on if it was a letter or word guess and based on if it was correct or not.
     *
     * If letter:
     * Check that the letter is in the answer, if so, turn that letter in the progress variable.
     * If the letter is in the word more than once, then turn all of them. For each correct letter, give +1 point; for an incorrect letter, -1.
     * If that guess was already made (either correct or incorrect), take -2 points.
     * If word:
     * Check that the word is correct.
     * If correct, give +2 points for each letter that was still not turned in the progress variable.
     * If an incorrect guess, -5 points overall.
     * If that guess was already made, then take 6 points from the score.
     * If the word is complete after the guess, then set the status of the game to 1;
     * If the score is 0 or less, the player lost, and the status should be set to 2.
     *
     * @param guess The guess to make.
     * @return boolean If the guess was correct.
     */
    public boolean makeGuess(String guess) {
        // Convert the guess to lowercase to make it case-insensitive
        guess = guess.toLowerCase();

        // Check if the guess contains only alphabetic characters
        if (!guess.matches("^[a-zA-Z]+$")) {
            return false;
        }

        // Check if the guess has already been made
        if (guesses.contains(guess)) {
            // Guess was already made, deduct 2 points
            setScore(getScore() - 2);

            // Check if the game is lost
            if (getScore() <= 0) {
                setGameStatus(2); // Game lost
            }

            return false; // Incorrect guess
        }

        guesses.add(guess);

        if (guess.length() == 1) {
            char letter = guess.charAt(0);

            // Initialize a variable to keep track of the correct letter count
            int correctCount = 0;

            // Check if the letter is in the answer and update progress directly
            for (int i = 0; i < getAnswer().length(); i++) {
                char answerLetter = getAnswer().charAt(i);
                if (Character.toLowerCase(answerLetter) == letter) {
                    if (getProgress()[i] == '_') {
                        getProgress()[i] = Character.toLowerCase(answerLetter);
                        correctCount++;
                    }
                }
            }

            if (correctCount > 0) {
                setScore(getScore() + correctCount);
            }

            if (Arrays.equals(getAnswer().toCharArray(), getProgress())) {
                // The word is complete after the guess
                setGameStatus(1); // Game won
            }

            if (correctCount == 0) {
                setScore(getScore() - 1);
            }

            // Check if the game is lost
            if (getScore() <= 0) {
                setGameStatus(2); // Game lost
            }

            return correctCount > 0; // Correct guess if at least one letter is in the correct position
        } else if (guess.length() == getAnswer().length()) {
            int unguessedLetters = 0;

            // Initialize a variable to keep track of the correct letter count
            int correctCount = 0;

            // Check if the word guess is correct and update progress directly
            for (int i = 0; i < getAnswer().length(); i++) {
                char answerLetter = getAnswer().charAt(i);
                char guessedLetter = guess.charAt(i);

                if (Character.toLowerCase(answerLetter) == guessedLetter) {
                    if (getProgress()[i] == '_') {
                        // Turn the letter in the progress
                        getProgress()[i] = Character.toLowerCase(answerLetter);
                        correctCount++;
                    }
                }
            }

            // Check if all letters are now in the correct position
            if (Arrays.equals(getAnswer().toCharArray(), getProgress())) {
                setScore(getScore() + correctCount * 2);
                setGameStatus(1); // Game won
            } else {
                setScore(getScore() - 5);
            }

            // Check if the game is lost
            if (getScore() <= 0) {
                setGameStatus(2); // Game lost
            }

            return correctCount > 0; // Correct guess if at least one letter is in the correct position
        } else {
            // Invalid guess (neither a letter nor a word of the correct length)

            // Initialize a variable to keep track of the correct letter count
            int correctCount = 0;

            // Check if there are correct letters in the incorrect-length guess and update progress directly
            for (int i = 0; i < getAnswer().length(); i++) {
                char answerLetter = getAnswer().charAt(i);
                for (int j = 0; j < guess.length(); j++) {
                    char guessedLetter = guess.charAt(j);
                    if (Character.toLowerCase(answerLetter) == guessedLetter) {
                        if (getProgress()[i] == '_') {
                            // Turn the letter in the progress
                            getProgress()[i] = Character.toLowerCase(answerLetter);
                            correctCount++;
                        }
                    }
                }
            }

            // Award points for correct letters
            setScore(getScore() + correctCount * 2);

            setScore(getScore() - 5);

            if (Arrays.equals(getAnswer().toCharArray(), getProgress())) {
                // The word is complete after the guess
                setGameStatus(1); // Game won
            }

            // Check if the game is lost
            if (getScore() <= 0) {
                setGameStatus(2); // Game lost
            }

            return correctCount > 0; // Correct guess if at least one letter is in the correct position
        }
    }

    /**
     * Pulls out a random image and encodes it to be communicated to the client.
     * @param choice The choice of image (city or country).
     */
    public void getRandomWord(String choice) {
        String[] cities = {"Aachen", "Berlin", "Phoenix", "Washington", "Munich", "Hamburg"};
        String[] countries = {"USA", "Germany", "Ireland", "Switzerland", "Austria"};

        int randomNum = 0;

        if (choice.equals("city")) {
            randomNum = (int) (Math.floor(Math.random() * (100 - 2 + 1) + 2) % cities.length);
            this.answer = cities[randomNum];
        } else {
            randomNum = (int) (Math.floor(Math.random() * (100 - 2 + 1) + 2) % countries.length);
            this.answer = countries[randomNum];
        }
    }
}
