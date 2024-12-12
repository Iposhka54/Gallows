import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private final static Scanner SCANNER = new Scanner(System.in);
    private static Difficulty currentDifficulty = Difficulty.EASY;

    public static void main(String[] args) {
        startGame();
    }
    public static void startGame(){
        Hangman hangman = new Hangman();
        gameLoop(hangman);

    }
    private static void gameLoop(Hangman hangman){
        boolean isPlaying = true;

        while(isPlaying){
            printGameMenu();
            int choice = enterChoice();
            isPlaying = executeMenuItem(choice);
        }
    }

    private static void printGameMenu(){
        List<String> strings = Hangman.hangmanState.get(Hangman.COUNT_STAGES - 1);
        int currentLine = 0;
        System.out.println(strings.get(currentLine++));
        System.out.println(strings.get(currentLine++) + " 1. Начать игру");
        System.out.println(strings.get(currentLine++) + " 2. Сложность: " + currentDifficulty.difficulty);
        System.out.println(strings.get(currentLine) + " 3. Выйти из игры");
        System.out.println(strings.get(currentLine++));
    }

    private static int enterChoice(){
        int choice;
        while(true){
            try{
                choice = SCANNER.nextInt();
                if(choice < 1 || choice > 3){
                    throw new IndexOutOfBoundsException();
                }
                break;
            }catch (InputMismatchException | IndexOutOfBoundsException e){
                System.out.println("Введите цифру(1-3):");
                SCANNER.nextLine();
            }
        }
        return choice;
    }

    private static boolean executeMenuItem(int choice){
        boolean CONTINUE_GAME = true;
        boolean EXIT_GAME = false;
        switch (choice){
            case 1:
                Scoreboard scoreboard = new Scoreboard();
                return CONTINUE_GAME;
            case 2:
                if(currentDifficulty == Difficulty.EASY)currentDifficulty = Difficulty.NORMAL;
                else if(currentDifficulty == Difficulty.NORMAL)currentDifficulty = Difficulty.HARD;
                else currentDifficulty = Difficulty.EASY;
                return CONTINUE_GAME;
            case 3:
                return EXIT_GAME;
        }
        return true;
    }
}