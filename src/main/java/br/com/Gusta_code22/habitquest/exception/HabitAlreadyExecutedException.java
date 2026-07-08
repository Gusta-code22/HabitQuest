package br.com.Gusta_code22.habitquest.exception;

public class HabitAlreadyExecutedException extends RuntimeException {
    public HabitAlreadyExecutedException(String message) {
        super(message);
    }
}
