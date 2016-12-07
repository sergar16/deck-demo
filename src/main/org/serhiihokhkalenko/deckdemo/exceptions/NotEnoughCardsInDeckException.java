package org.serhiihokhkalenko.deckdemo.exceptions;


public class NotEnoughCardsInDeckException extends RuntimeException {

    public NotEnoughCardsInDeckException() {
        super();
    }

    public NotEnoughCardsInDeckException(String message) {
        super(message);
    }
}
