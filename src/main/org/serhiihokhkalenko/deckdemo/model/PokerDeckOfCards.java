package org.serhiihokhkalenko.deckdemo.model;

import org.serhiihokhkalenko.deckdemo.exceptions.NotEnoughCardsInDeckException;
import org.serhiihokhkalenko.deckdemo.model.card.Card;
import org.serhiihokhkalenko.deckdemo.model.card.Rang;
import org.serhiihokhkalenko.deckdemo.model.card.Suit;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The type Standard Poker deck of cards.
 */
public class PokerDeckOfCards implements DeckOfCards {

    public static final int SIZE_OF_DECK = 52;
    private static final Card[] ORDERED_DECK = initializeOrderedDeck();

    private final Card[] deck = ORDERED_DECK.clone();
    private int currentIndexOfCard = 0;


    private PokerDeckOfCards() {
    }

    /**
     * Gets ordered deck of cards.
     *
     * @return the ordered deck of cards
     */
    public static PokerDeckOfCards getOrderedDeckOfCards() {
        return new PokerDeckOfCards();
    }

    /**
     * Gets shuffled deck of cards.
     *
     * @return the shuffled deck of cards
     */
    public static PokerDeckOfCards getShuffledDeckOfCards() {
        PokerDeckOfCards pokerDeckOfCards = new PokerDeckOfCards();
        pokerDeckOfCards.shuffle();
        return pokerDeckOfCards;
    }

    private static Card[] initializeOrderedDeck() {
        final Card[] orderedDeck = new Card[SIZE_OF_DECK];
        int i = 0;
        for (Rang rang : Rang.values()) {
            for (Suit suit : Suit.values()) {
                orderedDeck[i++] = Card.createCard(rang, suit);
            }
        }
        return orderedDeck;

    }

    
    @Override
    public Card dealCard() {
        if (countOfRemainingCards() > 1)
            return deck[currentIndexOfCard++];
        else throw new NotEnoughCardsInDeckException();
    }

    @Override
    public Card[] dealCards(int count) {
        if (countOfRemainingCards() >= count) {
            Card[] cardsToDeal = Arrays.copyOfRange(deck, currentIndexOfCard, currentIndexOfCard + count);
            currentIndexOfCard += count;
            return cardsToDeal;
        } else throw new NotEnoughCardsInDeckException();
    }

    @Override
    public void skipCard() {
        if (countOfRemainingCards() > 1)
            currentIndexOfCard++;
        else throw new NotEnoughCardsInDeckException();
    }

    @Override
    public void skipCards(int count) {
        if (countOfRemainingCards() >= count)
            currentIndexOfCard += count;
        else throw new NotEnoughCardsInDeckException();
    }


    /**
     * Fisher–Yates shuffle
     */
    @Override
    public void shuffle() {
        Random generator = ThreadLocalRandom.current();

        for (int i = currentIndexOfCard; i < deck.length; i++) {
            int positionToSwap = currentIndexOfCard + generator.nextInt(i - currentIndexOfCard + 1);
            Card currentCard = deck[i];
            deck[i] = deck[positionToSwap];
            deck[positionToSwap] = currentCard;
        }
    }

    @Override
    public int countOfRemainingCards() {
        return deck.length - currentIndexOfCard;
    }

    private final class DeckOfCardsIterator implements Iterator<Card> {

        int cursor = currentIndexOfCard;

        @Override
        public boolean hasNext() {
            return deck.length > cursor;
        }

        @Override
        public Card next() {
            if(hasNext())
            return deck[cursor++];
            else throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("Cannot remove an element from Deck of Cards.");
        }
    }

    @Override
    public Iterator<Card> iterator() {
        return new DeckOfCardsIterator();
    }


}
