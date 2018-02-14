package ua.kpi.tef;
import java.util.ArrayList;
import java.util.Random;

public class Model {

    private ArrayList<Integer> previousAttempts = new ArrayList<Integer>();
    private int lowerBound;
    private int UpperBound;
    private int answerNumber;

    public ArrayList<Integer> getPreviousAttempts() {
        return previousAttempts;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public int getLowerBound() {
        return lowerBound;
    }

    public int getUpperBound() {
        return UpperBound;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public void setLowerBound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public void setUpperBound(int upperBound) {
        UpperBound = upperBound;
    }

    public void updatePreviousAttempts(int attemptedValue) {
        this.previousAttempts.add(attemptedValue);
    }

    public boolean isGuessCorrect( int guess) {
        return guess == this.answerNumber;
    }
}