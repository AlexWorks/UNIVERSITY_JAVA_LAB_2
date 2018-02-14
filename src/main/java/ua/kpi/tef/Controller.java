package ua.kpi.tef;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Controller {

    public static final int LOWER_RANDOM_BOUND = 0;
    public static final int UPPER_RANDOM_BOUND = 100;

    public Model model;
    public View view;

    public Controller (Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void play() {
        Scanner sc = new Scanner(System.in);

        view.printMessage(view.GREETINGS);

        this.initGame();
        int guess;
        do {
            view.printRangeBounds(model.getLowerBound(), model.getUpperBound());
            guess = getNewGuess(sc);
            if (!model.isGuessCorrect(guess)) {
               this.processWrongGuess(guess);
            }
        } while (!model.isGuessCorrect(guess));

        model.updatePreviousAttempts(guess);

        view.printMessageAndInt(view.CONGRATULATIONS, model.getAnswerNumber());
        this.showStats();
    }

    public void initGame() {
        model.setAnswerNumber(getNewRandomNumberInBounds());
        model.setLowerBound(LOWER_RANDOM_BOUND);
        model.setUpperBound(UPPER_RANDOM_BOUND);
    }

    public int getNewRandomNumberInBounds() {
        Random random = new Random();
        return LOWER_RANDOM_BOUND + random.nextInt(UPPER_RANDOM_BOUND);
    }

    public void processWrongGuess(int guess) {
        if ( guess < model.getAnswerNumber()) {
            model.setLowerBound(guess);
            view.printMessage(View.TOO_LOW);
        } else {
            model.setUpperBound(guess);
            view.printMessage(View.TOO_HIGH);
        }

        model.updatePreviousAttempts(guess);
    }

    public void showStats() {
        ArrayList<Integer> stats = model.getPreviousAttempts();
        view.printMessage(view.STATS_HEAD);
        for (int i = 0; i < stats.size(); i++) {
            view.printInteger(stats.get(i));
        }
    }

    public int getNewGuess (Scanner sc) {
        int guess;
        do {
            guess = this.inputIntValueWithScanner(sc);
            if (!isGuessInRange(guess)) {
                view.printMessage(View.OUT_OF_RANGE);
            }
        } while (!isGuessInRange(guess));

        return guess;
    }

    public int inputIntValueWithScanner(Scanner sc) {
        view.printMessage(View.INPUT_INT_DATA);
        while( ! sc.hasNextInt()) {
            view.printMessage(view.WRONG_INPUT_INT_DATA + view.INPUT_INT_DATA);
            sc.next();
        }
        return sc.nextInt();
    }

    public boolean isGuessInRange(int guess) {
        return guess >= model.getLowerBound() && guess <= model.getUpperBound();
    }

}