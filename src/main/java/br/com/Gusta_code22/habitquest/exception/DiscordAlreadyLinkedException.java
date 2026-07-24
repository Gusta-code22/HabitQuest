package br.com.Gusta_code22.habitquest.exception;

public class DiscordAlreadyLinkedException extends RuntimeException {
    public DiscordAlreadyLinkedException(String message) {
        super(message);
    }
}
