package org.serhiihokhkalenko.deckdemo.model;


import org.serhiihokhkalenko.deckdemo.model.card.Card;

/**
 * The interface Deck of cards.
 */
public interface DeckOfCards extends Iterable<Card> {

    /**
     * Shuffle.
     */
    void shuffle();

    /**
     * Deal card card.
     *
     * @return the card
     */
    Card dealCard();

    /**
     * Deal cards card [ ].
     *
     * @param count the count
     * @return the card [ ]
     */
    Card[] dealCards(int count);

    /**
     * Skip card.
     */
    void skipCard();

    /**
     * Skip cards.
     *
     * @param count the count
     */
    void skipCards(int count);

    /**
     * Count of remaining cards .
     *
     * @return the count of remaining cards
     */
    int countOfRemainingCards();
}
