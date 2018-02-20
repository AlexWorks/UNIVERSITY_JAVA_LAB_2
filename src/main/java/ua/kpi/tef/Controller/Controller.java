package ua.kpi.tef.Controller;

import ua.kpi.tef.Model;
import ua.kpi.tef.View;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Controller {

    public static final int LOWER_RANDOM_BOUND = 0;
    public static final int UPPER_RANDOM_BOUND = 100;
    public static final int RANGE_OFFSET = 1;

    public Model model;
    public View view;

    public Controller (Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void startGame() {
        Scanner sc = new Scanner(System.in);

        try {
            initGame();
        } catch (InvalidBoundsException err) {
            view.printMessage(view.BOUNDS_ERROR);
            return;
        }

        view.printMessage(view.GREETINGS);
        int guess;
        do {
            view.printRangeBounds(model.getLowerBound(), model.getUpperBound());
            guess = getNewGuess(sc);
            if (model.isGuessCorrect(guess)) {
               this.processRightGuess(guess);
            } else {
                this.processWrongGuess(guess);
            }
        } while (!model.isGuessCorrect(guess));


        this.showStats();
    }

    private void initGame() throws InvalidBoundsException  {

        if (LOWER_RANDOM_BOUND > UPPER_RANDOM_BOUND) {
            throw new InvalidBoundsException();
        }
        model.setAnswerNumber(getNewRandomNumberInBounds());
        model.setLowerBound(LOWER_RANDOM_BOUND);
        model.setUpperBound(UPPER_RANDOM_BOUND);
    }

    private int getNewRandomNumberInBounds() {
        Random random = new Random();
        return LOWER_RANDOM_BOUND + random.nextInt(UPPER_RANDOM_BOUND);
    }

    private void processWrongGuess(int guess) {
        if ( guess < model.getAnswerNumber()) {
            processTooLowGuess(guess);
        } else {
            processTooHighGuess(guess);
        }
        model.updatePreviousAttempts(guess);
    }

    private void processTooLowGuess(int guess) {
        model.setLowerBound(guess + RANGE_OFFSET);
        view.printMessage(View.TOO_LOW);
    }

    private void processTooHighGuess(int guess) {
        model.setUpperBound(guess - RANGE_OFFSET);
        view.printMessage(View.TOO_HIGH);
    }

    private void processRightGuess(int guess) {
        model.updatePreviousAttempts(guess);
        view.printMessageAndInt(view.CONGRATULATIONS, model.getAnswerNumber());
    }

    private void showStats() {
        ArrayList<Integer> stats = model.getPreviousAttempts();
        view.printMessage(view.STATS_HEAD);
        for (int i = 0; i < stats.size(); i++) {
            view.printInteger(stats.get(i));
        }
    }

    private int getNewGuess (Scanner sc) {
        int guess;
        do {
            guess = this.inputIntValueWithScanner(sc);
            if (!isGuessInRange(guess)) {
                view.printMessage(View.OUT_OF_RANGE);
            }
        } while (!isGuessInRange(guess));

        return guess;
    }

    private int inputIntValueWithScanner(Scanner sc) {
        view.printMessage(View.INPUT_INT_DATA);
        while( ! sc.hasNextInt()) {
            view.printMessage(view.WRONG_INPUT_INT_DATA + view.INPUT_INT_DATA);
            sc.next();
        }
        return sc.nextInt();
    }

    private boolean isGuessInRange(int guess) {
        return guess >= model.getLowerBound() && guess <= model.getUpperBound();
    }
}