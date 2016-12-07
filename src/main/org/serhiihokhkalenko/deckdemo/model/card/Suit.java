package org.serhiihokhkalenko.deckdemo.model.card;


public enum Suit {
    HEARTS("♥"),
    DIAMONDS("♦"),
    CLUBS("♣"),
    SPADES("♠");

    private final String sign;

    Suit(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return sign;
    }
}
