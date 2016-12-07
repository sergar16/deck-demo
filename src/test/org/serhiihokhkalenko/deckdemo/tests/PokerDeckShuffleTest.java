package org.serhiihokhkalenko.deckdemo.tests;

import org.serhiihokhkalenko.deckdemo.model.*;
import org.serhiihokhkalenko.deckdemo.model.card.*;
import org.serhiihokhkalenko.deckdemo.util.ShuffleQualityAnalyzeUtil;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class PokerDeckShuffleTest {
    private static final int COUNT_OF_SUITS = Suit.values().length;
    private static final int COUNT_OF_RANGS = Rang.values().length;
    //todo @serhii: add dependency on count of suits and rangs to coefficients
    private static final float CARDS_IN_DECK_CHANGED_POSITION_COEFFICIENT = 0.90F;
    private static final float CARD_WITH_NOT_SAME_SUIT_AS_NEXT_CARD_COEFFICIENT = 0.40F;
    private static final float CARD_WITH_NOT_SAME_RANG_AS_NEXT_CARD_COEFFICIENT = 0.80F;
    private static final int COUNT_OF_CHANGED_POSITION_CARD = Math.round(PokerDeckOfCards.SIZE_OF_DECK * CARDS_IN_DECK_CHANGED_POSITION_COEFFICIENT);
    private static final int COUNT_OF_CARD_WITH_NOT_SAME_SUIT_AS_NEXT_CARD = Math.round(PokerDeckOfCards.SIZE_OF_DECK * CARD_WITH_NOT_SAME_SUIT_AS_NEXT_CARD_COEFFICIENT);
    private static final int COUNT_OF_CARD_WITH_NOT_SAME_RANG_AS_NEXT_CARD = Math.round(PokerDeckOfCards.SIZE_OF_DECK * CARD_WITH_NOT_SAME_RANG_AS_NEXT_CARD_COEFFICIENT);


    private static DeckOfCards orderedDeckOfCards;
    private static DeckOfCards shuffledDeckOfCards;

    private static List<Integer> similarCardsBySuitCountsList;
    private static List<Integer> similarCardsByRangCountsList;
    private static Map<Integer, Integer> similarCardsBySuitCountsMap;
    private static Map<Integer, Integer> similarCardsByRangCountsMap;

    @BeforeClass
    public static void runBeforeTest() {
        orderedDeckOfCards = PokerDeckOfCards.getOrderedDeckOfCards();
        shuffledDeckOfCards = PokerDeckOfCards.getShuffledDeckOfCards();

        similarCardsBySuitCountsList = ShuffleQualityAnalyzeUtil.buildSimilarCardsBySuitCountsList(shuffledDeckOfCards);
        similarCardsByRangCountsList = ShuffleQualityAnalyzeUtil.buildSimilarCardsByRangCountsList(shuffledDeckOfCards);
        similarCardsBySuitCountsMap = ShuffleQualityAnalyzeUtil.buildSimilarCardsBySuitCountsMap(shuffledDeckOfCards);
        similarCardsByRangCountsMap = ShuffleQualityAnalyzeUtil.buildSimilarCardsByRangCountsMap(shuffledDeckOfCards);
    }


    @Test
    public void countOfChangedPositionCardsTest() {
        int countOfNotChangedPositionCards = 0;

        Iterator<Card> orderedDeckIterator = orderedDeckOfCards.iterator();
        Iterator<Card> shuffledDeckIterator = shuffledDeckOfCards.iterator();

        while (orderedDeckIterator.hasNext() && shuffledDeckIterator.hasNext()) {
            Card cardFromOrderedDeck = orderedDeckIterator.next();
            Card cardFromShuffledDeck = shuffledDeckIterator.next();

            if (cardFromOrderedDeck.equals(cardFromShuffledDeck)) {
                countOfNotChangedPositionCards++;
            }
        }

        assertTrue(PokerDeckOfCards.SIZE_OF_DECK - COUNT_OF_CHANGED_POSITION_CARD > countOfNotChangedPositionCards);
    }

    @Test
    public void countOfCardsWithNotSameSuitAsNextCard() {
        assertTrue(similarCardsBySuitCountsMap.get(1) > COUNT_OF_CARD_WITH_NOT_SAME_SUIT_AS_NEXT_CARD);
    }

    @Test
    public void countOfCardsWithNotSameRangAsNextCard() {
        assertTrue(similarCardsByRangCountsMap.get(1) > COUNT_OF_CARD_WITH_NOT_SAME_RANG_AS_NEXT_CARD);
    }
}


