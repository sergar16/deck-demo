package org.serhiihokhkalenko.deckdemo.model.card;


import org.serhiihokhkalenko.deckdemo.exceptions.NonInitiaziledCardException;

/**
 * The type Card.
 */
public class Card {

    private final Rang rang;
    private final Suit suit;

    private Card(Rang rang, Suit suit) {
        this.rang = rang;
        this.suit = suit;
    }

    /**
     * Create  card.
     *
     * @param rang the rang
     * @param suit the suit
     * @return the card
     * @throws NonInitiaziledCardException
     */
    public static Card createCard(Rang rang, Suit suit) {
        if (rang != null && suit != null) {
            return new Card(rang, suit);
        } else throw new NonInitiaziledCardException();
    }

    /**
     * Gets rang.
     *
     * @return the rang
     */
    public Rang getRang() {
        return rang;
    }

    /**
     * Gets suit.
     *
     * @return the suit
     */
    public Suit getSuit() {
        return suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Card card = (Card) o;
        if (rang != card.rang)
            return false;
        return suit == card.suit;
    }

    @Override
    public int hashCode() {
        int result = rang.hashCode();
        result = 31 * result + suit.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "[" + suit + rang + "] ";
    }
}
