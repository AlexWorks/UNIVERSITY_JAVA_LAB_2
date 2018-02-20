package ua.kpi.tef;

import java.util.ArrayList;

public class View {

    public static final String GREETINGS = "Guess a number in the following range";
    public static final String INPUT_INT_DATA = "Input INT value = ";
    public static final String WRONG_INPUT_INT_DATA = "Wrong input! Repeat please! ";
    public static final String TOO_HIGH = "Too high!";
    public static final String TOO_LOW = "Too low!";
    public static final String CONGRATULATIONS = "Congratulations! You won! Guessed number is: ";
    public static final String STATS_HEAD = "Guess stats: ";
    public static final String OUT_OF_RANGE = "This value is out of RANGE. Repeat operation!";
    public static final String SHOW_RANGE = "RANGE is: ";
    public static final String BOUNDS_ERROR = "Internal bounds error has occurred. Game session will be stopped immediately.";

    public void printMessage(String message){
        System.out.println(message);
    }

    public void printMessageAndInt(String message, int value){
        System.out.println(message + value);
    }

    public void printRangeBounds(int lowerBound, int upperBound) {
        System.out.println(this.SHOW_RANGE + "[" + lowerBound + ":" + upperBound + "]");
    }

    public void printInteger( int value) {
        System.out.println(value);
    }
}
