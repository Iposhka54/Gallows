import java.util.ArrayList;
import java.util.List;

public class Hangman {
    private int currentState;
    public static final Integer COUNT_STAGES = 8;
    public static final List<List<String>> hangmanState = List.of(
            List.of(
                    "==================",
                    "|                |",
                    "|                |",
                    "|                |",
                    "|                |"
            ),
            List.of(
                    "==================",
                    "|       |        |",
                    "|                |",
                    "|                |",
                    "|                |"
            ),
            List.of(
                    "==================",
                    "|       |        |",
                    "|       0        |",
                    "|                |",
                    "|                |"
            ),
            List.of(
                    "==================",
                    "|       |        |",
                    "|       0        |",
                    "|       |        |",
                    "|                |"
            ),
            List.of(
                    "==================",
                    "|       |        |",
                    "|       0        |",
                    "|       |\\       |",
                    "|                |"
            ),
            List.of(
                    "==================",
                    "|       |        |",
                    "|       0        |",
                    "|      /|\\       |",
                    "|                |"
            ),
            List.of(
                    "==================",
                    "|       |        |",
                    "|       0        |",
                    "|      /|\\       |",
                    "|      /         |"
            ),
            List.of(
            "==================",
            "|       |        |",
            "|       0        |",
            "|      /|\\       |",
            "|      / \\       |"
    ));

    public void changeState() {
        currentState++;
    }

    public List<String> getHangmanState() {
        return hangmanState.get(currentState);
    }

    public boolean isGameOver(){
        return currentState == COUNT_STAGES - 1;
    }

    public int getCurrentState(){
        return currentState;
    }
}
