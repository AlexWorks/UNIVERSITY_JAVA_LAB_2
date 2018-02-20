package ua.kpi.tef.Controller;

public class InvalidBoundsException extends RuntimeException {
    public InvalidBoundsException() {
        super("Invalid Bounds");
    }
}
