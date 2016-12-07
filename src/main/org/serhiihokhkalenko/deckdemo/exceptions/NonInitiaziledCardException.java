package org.serhiihokhkalenko.deckdemo.exceptions;


public class NonInitiaziledCardException extends RuntimeException {

    public NonInitiaziledCardException() {
        super();
    }

    public NonInitiaziledCardException(String message) {
        super(message);
    }
}
