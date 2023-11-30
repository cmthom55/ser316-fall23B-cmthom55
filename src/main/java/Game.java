import java.util.ArrayList;
import java.util.Arrays;

public class Game {

    /** Holds the score for the game. */
    public int score;

    String name;

    /** Holds the answer for the game. */
    String answer;

    /** Holds the current progress towards the answer. */
    char[] progress;

    //SER316 TASK 2 SPOTBUGS FIX: Got rid of leaderboardFn and encodedImage

    /** The status of the game. {0 - In progress, 1 - Game won, 2 - game lost}*/
    protected int gameStatus = 0;

    // all letter guesses, needs to be cleared for each game
    ArrayList<String> guesses = new ArrayList<String>();

    /**
     * Gets the name for the game.
     * @return String The name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets the answer for the game.
     * @return String The Answer.
     */
    public String getAnswer() {
        return this.answer.toLowerCase();
    }

    /**
     * Gets the current progress towards the answer.
     * @return char[] Character Array of the progress.
     */
    public char[] getProgress() {
        //SER316 TASK 2 SPOTBUGS FIX
        return Arrays.copyOf(this.progress, this.progress.length);
    }

    /**
     * Gets the current status of the game.
     */
    public int getGameStatus() {
        return this.gameStatus;
    }

    public void setGameStatus(int statusSet) {
        this.gameStatus = statusSet;
    }

    /**
     * Sets the score for the game.
     */
    public void setScore(int score) {
        this.score = score;
    }


    public int getScore() {
        return this.score;
    }

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

    private void initializeGame(String name, int score, int imageType, String answer) {
        this.name = name.isEmpty() ? "Elsa" : name;
        if (imageType == 0) {
            getRandomWord("city");
        } else if(imageType == 1) {
            getRandomWord("country");
        } else {
            this.answer = answer;
        }
        this.guesses.clear();
        setScore(score);
        this.progress = new char[this.answer.length()];
        char f = '_';
        Arrays.fill(this.progress, f);
    }

    /**
     * Constructs a new hangmanGame.
     */
    public Game(String name, int imageType) {
        initializeGame(name, 14, imageType, "");
    }

    /**
     * Constructs a new hangmanGame with a fixed name.
     */
    public Game(String fixedWord, String name) {
        initializeGame(name, 10, -1, fixedWord);
    }

    /**
     * Constructs a new hangmanGame with no arguments.
     */
    public Game() {
        initializeGame("", 10, -1, "");
    }

    public void init_Game (String answer, String name) {
        initializeGame(name, 10, -1, answer);
    }

    /**
     * Constructs a new hangmanGame with Anonymous as the name of the player.
     * @param imageType 0=city, 1=country
     */
    public Game(int imageType) {
        initializeGame("Anna", 12, 0, "");
    }

    public boolean makeGuess(String guess) {
        int correctCount = 0;

        guess = guess.toLowerCase();

        if (!guess.matches("^[a-zA-Z]+$")) {
            return false;
        }

        if (guesses.contains(guess)) {
            updateScoreAndGameStatus(-2);
            return false;
        }

        guesses.add(guess);

        if (guess.length() == 1) {
            correctCount = updateProgress(guess);

            if (correctCount > 0) {
                updateScoreAndGameStatus(correctCount);
            } else {
                updateScoreAndGameStatus(-1);
            }
        } else if (guess.length() == getAnswer().length()) {
            correctCount = updateProgress(guess);

            if (Arrays.equals(getAnswer().toCharArray(), getProgress())) {
                updateScoreAndGameStatus(correctCount*2);
            } else {
                updateScoreAndGameStatus(-5);
            }

        } else {
            correctCount = updateProgress(guess);

            updateScoreAndGameStatus(correctCount * 2 - 5);
        }
        return correctCount > 0;
    }

    public void updateScoreAndGameStatus(int scoreAdjustment) {
        setScore(getScore() + scoreAdjustment);

        if (Arrays.equals(getAnswer().toCharArray(), getProgress())) {
            System.out.println("You win! :)");
            setGameStatus(1);
        } 

        if (getScore() <= 0) {
            System.out.println("You lose! :(");
            setGameStatus(2);
        }      
    }

    public int updateProgress(String guess) {
        int amountCorrect = 0;
        for (int i = 0; i < getAnswer().length(); i++) {
            char answerLetter = getAnswer().charAt(i);

            for (int j = 0; j < guess.length(); j++) {
                char guessedLetter = guess.charAt(j);
                if (Character.toLowerCase(answerLetter) == guessedLetter) {
                    if (getProgress()[i] == '_') {
                        //SER316 TASK 2 SPOTBUGS FIX
                        this.progress[i] = Character.toLowerCase(answerLetter);
                        amountCorrect++;
                    }
                }
            }
        }  
        return amountCorrect;
    }


    /**
     * Pulls out a random image and encodes it to be communicated to the client.
     * @param dir directory to the relevant image folder.
     */
    public void getRandomWord(String choice) {

        String[] cities = {
                "Aachen", "Berlin", "Phoenix", "Washington", "Munich", "Hamburg"
        };
        String[] countries = {
                "USA", "Germany", "Ireland", "Switzerland", "Austria"
        };

        int randomNum = 0;

        if (choice.equals("city")) {
            randomNum = (int)(Math.floor(Math.random() * (100 - 2 + 1) + 2) % cities.length);
            this.answer = cities[randomNum];
        } else {
            randomNum = (int) (Math.floor(Math.random() * (100 - 2 + 1) + 2) % countries.length);
            this.answer = countries[randomNum];
        }

    }
}