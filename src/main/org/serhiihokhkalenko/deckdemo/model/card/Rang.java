package org.serhiihokhkalenko.deckdemo.model.card;

public enum Rang {
    ACE("A", 14),
    KING("K", 13),
    QUEEN("Q", 12),
    JACK("J", 11),
    TEN("10", 10),
    NINE("9", 9),
    EIGHT("8", 8),
    SEVEN("7", 7),
    SIX("6", 6),
    FIVE("5", 5),
    FOUR("4", 4),
    THREE("3", 3),
    TWO("2", 2);

    private final String text;
    private final Integer value;

    Rang(final String text, final Integer val) {
        this.text = text;
        this.value = val;
    }

    @Override
    public String toString() {
        return text;
    }
}
