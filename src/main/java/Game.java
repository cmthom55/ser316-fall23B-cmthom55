import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class for handling some game logic for hangman game.
 * Every game starts with a score of 10 and the points are reduced based on the description of "makeGuess". Score holds the currect score for one game.
 * Game is lost when score is 0 or less.
 * Answer holds the current answer, progres hold the currect word with "_" for letters that were not guessed yet.
 *
 */
public class Game {

    /** Holds the score for the game. */
    int score;

    private String name;

    /** Holds the answer for the game. */
    private String answer;

    /** Holds the current progress towards the answer*/
    private char[] progress;

    /** The encoded String for sending to the client*/
    private String encodedImage;

    /** The path to the file holding the leaderboard.*/
    private String leaderboardFName = "leaderboard.txt";

    /** The status of the game. {0 - In progress, 1 - Game won, 2 - game lost}*/
    private int gameStatus = 0;

    // all letter guesses, needs to be cleared for each game
    private ArrayList<String> guesses = new ArrayList<String>();


    /**
     * Gets the name for the game.
     * @return String The name.
     */
    public String getName() {return this.name;}

    /**
     * Gets the answer for the game.
     * @return String The Answer.
     */
    public String getAnswer() {return this.answer.toLowerCase();}

    /**
     * Gets the current progress towards the answer.
     * @return char[] Character Array of the progress.
     */
    public char[] getProgress() {return this.progress;}

    /**
     * Gets the encoded image for for the answer.
     * @return String the image encoded in text
     */
    public String getEncodedImage() {return this.encodedImage;}

    /**
     * Gets the current status of the game.
     * @return
     */
    public int getGameStatus() {return this.gameStatus;}

    /**
     * Sets the score for the game.
     * @param score
     */
    public void setScore(int score) {this.score = score;}
 
    public int getScore() {return this.score;}

    /**
     * Checks what positions a letter should be entered into and sets that value into the progress array.
     * @param letter
     */
    protected int setProgress(char letter) {
        int count = 0;
        int i = 0;
        while(this.getAnswer().indexOf(letter, i) >= 0){
            i = this.getAnswer().indexOf(letter, i) + 1;
            this.progress[i - 1] = letter;
            count++;
        }
        return count;
    }

    /**
     * Completely fills the progress with the answer. Returns the number of letters that were still unturned
     */
    public int fillProgress() {
        int hit = 0;
        for(int i = 0; i < this.getProgress().length; i++){
            char f = '_';
            if (this.progress[i] == (f)) {
                this.progress[i] = this.getAnswer().charAt(i);
                hit++;
            }
        }

        return hit;
    }

    /**
     * Constructs a new hangmanGame.
     * @param name
     * @param imageType 0=city, 1=country
     */
    public Game(String name, int imageType){
        this.name = "Elsa";
        if(imageType == 0){
            getRandomWord("city");
        }else if(imageType == 1){
            getRandomWord("country");
        }
        setScore(14);
        this.progress = new char[answer.length()];
        char f = '_';
        Arrays.fill(this.progress, f);
    }

    /**
     * Constructs a new hangmanGame with a fixed name
     * @param name
     * @param imageType 0=city, 1=country
     */
    public Game(String fixedWord, String name){
        this.name = name;
        this.answer = fixedWord;
        setScore(10);
        this.progress = new char[answer.length()];
        char f = '_';
        Arrays.fill(this.progress, f);
    }

    /**
     * Constructs a new hangmanGame with no arguments
     */
    public Game(){
        this.name = "";
        this.answer = "";
        setScore(10);
        this.progress = new char[answer.length()];
        char f = '_';
        Arrays.fill(this.progress, f);
    }

    public Game(String answer, String name, boolean initGameReplacement) {
        this.name = name;
        this.answer = answer;
        this.guesses.clear();
        setScore(10);
        this.progress = new char[answer.length()];
        char f = '_';
        Arrays.fill(this.progress, f);
    }

    public void init_Game (String answer, String name) {
        this.name = name;
        this.answer = answer;
        this.guesses.clear();
        setScore(10);
        this.progress = new char[answer.length()];
        char f = '_';
        Arrays.fill(this.progress, f);
    }

    /**
     * Constructs a new hangmanGame with Anonymous as the name of the player.
     * @param imageType 0=city, 1=country
     */
    public Game(int imageType){
        this.name = "Anna";
        if(imageType == 1){
            getRandomWord("city");
        }else if(imageType == 2)
            getRandomWord("city");
        setScore(12);
        this.progress = new char[answer.length()];
    }


    /**
     * The method accepts a single parameter, "guess," which can be a letter or a word.
     * It should be case-insensitive, treating uppercase and lowercase letters as equivalent.
     * Method checks if the guess is correct (letter in "answer" or complete word correct), should ignore upper/lowe case.
     * Should set score based on if it was a letter or word guess and based on if it was correct or not.
     *
     * If letter:
     *  Check that the letter is in the answer, if so turn that letter in the process variable
     *  If the letter is in the word more than once then turn all of them. For each correct letter give +1 point, for an incorrect letter -1
     *  If that guess was already made (either correct or incorrect) take -2 points
     * If word:
     *  Check that word is correct
     *  If correct give +2 points for each letter that was still not turned in progress variable
     *  If incorrect guess -5 points overall
     *  If that guess was already made then take 6 points from the score
     *
     *
     * If the word is complete after the guess then set status of game to 1;
     * If the score is 0 or less the player lost and the status should be set to 2.
     *
     * @param guess
     * @return boolean. If the guess was correct.
     */
    public boolean makeGuess(String guess) {
        System.out.println("Not yet implemented here, you will need to implement it in assignment 3!");
        return true;
    }

    /**
     * Pulls out a random image and encodes it to be communicated to the client
     * @param dir directory to the relevant image folder.
     */
    public void getRandomWord(String choice) {

        String[] cities = {"Aachen", "Berlin", "Phoenix", "Washington", "Munich", "Hamburg"};
        String[] countries = {"USA", "Germany", "Ireland", "Switzerland", "Austria"};

        int randomNum = 0;

        if (choice.equals("city")) {
            randomNum = (int)(Math.floor(Math.random()*(100-2+1)+2) % cities.length);
            this.answer = cities[randomNum];
        } else {
            randomNum = (int) (Math.floor(Math.random() * (100 - 2 + 1) + 2) % countries.length);
            this.answer = countries[randomNum];
        }

    }



}