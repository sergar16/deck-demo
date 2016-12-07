package org.serhiihokhkalenko.deckdemo.tests;

import org.serhiihokhkalenko.deckdemo.exceptions.NotEnoughCardsInDeckException;
import org.serhiihokhkalenko.deckdemo.model.*;
import org.serhiihokhkalenko.deckdemo.model.card.*;
import org.junit.*;
import static org.junit.Assert.assertEquals;


public class PokerDeckDealAndSkipTest {

    private static DeckOfCards shuffledDeckOfCards;

    @BeforeClass
    public static void runBefore() {
        shuffledDeckOfCards = PokerDeckOfCards.getShuffledDeckOfCards();
    }


    @Test
    public void dealOneCard() {
        final int countOfCardsBeforeDeal = shuffledDeckOfCards.countOfRemainingCards();
        shuffledDeckOfCards.dealCard();
        assertEquals(countOfCardsBeforeDeal - 1, shuffledDeckOfCards.countOfRemainingCards());
    }


    @Test
    public void dealFewCards() {
        final int countOfCardsBeforeDeal = shuffledDeckOfCards.countOfRemainingCards();
        final int countOfDealCards = 5;
        Card[] dealedCards = shuffledDeckOfCards.dealCards(countOfDealCards);
        assertEquals(countOfDealCards, dealedCards.length);
        assertEquals(countOfCardsBeforeDeal - countOfDealCards, shuffledDeckOfCards.countOfRemainingCards());
    }


    @Test
    public void skipOneCard() {
        final int countOfCardsBeforeDeal = shuffledDeckOfCards.countOfRemainingCards();
        shuffledDeckOfCards.skipCard();
        assertEquals(countOfCardsBeforeDeal - 1, shuffledDeckOfCards.countOfRemainingCards());
    }

    @Test
    public void skipFewCards() {
        final int countOfCardsBeforeDeal = shuffledDeckOfCards.countOfRemainingCards();
        final int countOfSkippedCards = 5;
        shuffledDeckOfCards.skipCards(countOfSkippedCards);
        assertEquals(countOfCardsBeforeDeal - countOfSkippedCards, shuffledDeckOfCards.countOfRemainingCards());
    }

    @Test(expected = NotEnoughCardsInDeckException.class)
    public void notEnoughCardsToDeal(){
        final int countOfCardsBeforeDeal = shuffledDeckOfCards.countOfRemainingCards();
        final int countOfSkippedCards = 50;
        shuffledDeckOfCards.dealCards(countOfSkippedCards);
        assertEquals(countOfCardsBeforeDeal - countOfSkippedCards, shuffledDeckOfCards.countOfRemainingCards());
    }


}


