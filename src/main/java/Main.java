import model.Hangman;
import service.Game;
import service.WordGenerator;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final int LAST_HANGMAN_STAGE = Hangman.COUNT_STAGES - 1;

    public static void main(String[] args) {
        startGame();
    }
    public static void startGame() {
        Hangman hangman = new Hangman();
        WordGenerator wordGenerator = new WordGenerator();
        gameLoop(hangman, wordGenerator);
    }

    private static void gameLoop(Hangman hangman, WordGenerator wordGenerator){
        boolean isPlaying = true;

        while(isPlaying){
            printGameMenu();
            int choice = enterChoice();
            isPlaying = executeMenuItem(choice);
            if(isPlaying){
                String word = wordGenerator.getRandomWord();
                Game game = new Game(hangman, word,SCANNER);
                game.start();
            }
        }
    }

    private static void printGameMenu(){
        List<String> lastHangmanState = Hangman.hangmanState.get(LAST_HANGMAN_STAGE);
        //Counter for print left sight menu
        int currentLine = 0;
        System.out.println(lastHangmanState.get(currentLine++));
        System.out.println(lastHangmanState.get(currentLine++));
        System.out.println(lastHangmanState.get(currentLine++) + " 1. Начать игру");
        System.out.println(lastHangmanState.get(currentLine++) + " 2. Выйти из игры");
        System.out.println(lastHangmanState.get(currentLine));
    }

    private static int enterChoice(){
        int choice;
        while(true){
            try{
                choice = SCANNER.nextInt();
                if(choice < 1 || choice > 2){
                    throw new IndexOutOfBoundsException();
                }
                break;
            }catch (InputMismatchException | IndexOutOfBoundsException e){
                System.out.println("Введите цифру(1-2):");
                SCANNER.nextLine();
            }
        }
        return choice;
    }

    private static boolean executeMenuItem(int choice){
        boolean CONTINUE_GAME = true;
        boolean EXIT_GAME = false;
        return switch (choice) {
            case 1 -> CONTINUE_GAME;
            case 2 -> EXIT_GAME;
            default -> CONTINUE_GAME;
        };
    }
}