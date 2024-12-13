package service;

import model.GameState;
import model.Hangman;

import java.util.*;

public final class Game {
    private StringBuilder MASK_WORD = new StringBuilder();
    private final String WORD;
    private GameState gameState;
    private final Set<Character> wrongLetters = new TreeSet<>();
    private final Set<Character> correctLetters = new TreeSet<>();
    private final Hangman HANGMAN;
    private final Scanner SCANNER;

    // Constants
    private static final String WIN_MESSAGE = "Поздравляем! Вы выиграли!";
    private static final String LOSE_MESSAGE = "Игра окончена. Вы проиграли!";
    private static final String ALREADY_ENTERED_MESSAGE = "Эта буква уже была введена.";
    private static final String ENTER_ONE_RUSSIAN_LETTER = "Введите одну русскую букву:";
    private static final String MASK_CHAR = "*";
    private static final String MASK_REGEX = "[^.]";
    private static final int SINGLE_CHAR_LENGTH = 1;
    private static final char CYRILLIC_START = 'А'; // Start Russian alphabet
    private static final char CYRILLIC_END = 'Я';   // End of Russian alphabet
    private static final char CYRILLIC_SPECIAL = 'Ё'; // Letter 'ё' not in alphabet
    private static final int FIRST_CHARACTER_INDEX = 0;

    public Game(Hangman hangman, String word, Scanner scanner){
        SCANNER = scanner;
        this.HANGMAN = hangman;
        WORD = word.toUpperCase();
        initWordMask();
    }

    public void start(){
        gameState = GameState.PLAYING;
        while (gameState == GameState.PLAYING) {
            printScoreboard();
            Character letter = inputLetter();
            if(correctLetters.contains(letter) || wrongLetters.contains(letter)){
                System.out.println(ALREADY_ENTERED_MESSAGE);
                continue;
            }
            else if (WORD.contains(letter.toString())) {
                correctLetters.add(letter);
                updateMaskWord(letter);
            } else {
                if (!wrongLetters.contains(letter)) {
                    wrongLetters.add(letter);
                    HANGMAN.changeState();
                }
            }
            checkGameState();
        }
        printEndMessage();

    }

    private void printScoreboard(){
        List<String> hangmanState = HANGMAN.getHangmanState();
        //Counter for print left sight menu
        int currentLine = 0;
        System.out.println();
        System.out.println(hangmanState.get(currentLine++));
        System.out.println(hangmanState.get(currentLine++) + " " + (gameState == GameState.PLAYING ? MASK_WORD : WORD));
        System.out.println(hangmanState.get(currentLine++) + " Кол-во ошибок: " + HANGMAN.getCurrentState());
        System.out.println(hangmanState.get(currentLine++) + " Правильные буквы: " + correctLetters);
        System.out.println(hangmanState.get(currentLine) + " Неправильные буквы: " + wrongLetters);
        if(gameState == GameState.PLAYING)System.out.println("Введите букву:");
    }

    private Character inputLetter(){
        while (true) {
            try {
                String line = SCANNER.nextLine().trim().toUpperCase();
                if (line.length() != SINGLE_CHAR_LENGTH || !isRussianLetter(line.charAt(FIRST_CHARACTER_INDEX))) {
                    throw new IllegalArgumentException();
                }
                return line.charAt(FIRST_CHARACTER_INDEX);
            } catch (IllegalArgumentException e) {
                System.out.println(ENTER_ONE_RUSSIAN_LETTER);
            }
        }
    }

    private boolean isRussianLetter(char c) {
        return (c >= CYRILLIC_START && c <= CYRILLIC_END) || c == CYRILLIC_SPECIAL;
    }

    private void updateMaskWord(Character letter) {
        for (int i = 0; i < WORD.length(); i++) {
            if (WORD.charAt(i) == letter) {
                MASK_WORD.setCharAt(i, letter);
            }
        }
    }

    private void printEndMessage() {
        if (gameState == GameState.WIN) {
            printScoreboard();
            System.out.println(WIN_MESSAGE);
        } else if (gameState == GameState.LOSE) {
            printScoreboard();
            System.out.println(LOSE_MESSAGE);
        }
    }

    private void checkGameState() {
        if (MASK_WORD.toString().equals(WORD)) {
            gameState = GameState.WIN;
        } else if (HANGMAN.isGameOver()) {
            gameState = GameState.LOSE;
        }
    }

    private void initWordMask(){
        MASK_WORD = new StringBuilder(WORD.replaceAll(MASK_REGEX, MASK_CHAR));
    }
}
