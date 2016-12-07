package org.serhiihokhkalenko.deckdemo.tests;

import org.junit.BeforeClass;
import org.junit.Test;
import org.serhiihokhkalenko.deckdemo.model.DeckOfCards;
import org.serhiihokhkalenko.deckdemo.model.PokerDeckOfCards;
import org.serhiihokhkalenko.deckdemo.model.card.Rang;
import org.serhiihokhkalenko.deckdemo.model.card.Suit;
import org.serhiihokhkalenko.deckdemo.util.ShuffleQualityAnalyzeUtil;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


public class ShuffleQualityAnalyzeUtilTest {
    private static final int COUNT_OF_SUITS = Suit.values().length;
    private static final int COUNT_OF_RANGS = Rang.values().length;
    private static final int COUNT_OF_SIMILAR_CARDS_BY_SUIT_FOR_OF_ORDERED_DECK = 1;
    private static final int COUNT_OF_SIMILAR_CARDS_BY_RANG_FOR_OF_ORDERED_DECK = 4;

    private static DeckOfCards orderedDeckOfCards;

    private static List<Integer> similarCardsBySuitCountsList;
    private static List<Integer> similarCardsByRangCountsList;
    private static Map<Integer, Integer> similarCardsBySuitCountsMap;
    private static Map<Integer, Integer> similarCardsByRangCountsMap;

    @BeforeClass
    public static void runBeforeTest() {
        orderedDeckOfCards = PokerDeckOfCards.getOrderedDeckOfCards();

        similarCardsBySuitCountsList = ShuffleQualityAnalyzeUtil.buildSimilarCardsBySuitCountsList(orderedDeckOfCards);
        similarCardsByRangCountsList = ShuffleQualityAnalyzeUtil.buildSimilarCardsByRangCountsList(orderedDeckOfCards);
        similarCardsBySuitCountsMap = ShuffleQualityAnalyzeUtil.buildSimilarCardsBySuitCountsMap(orderedDeckOfCards);
        similarCardsByRangCountsMap = ShuffleQualityAnalyzeUtil.buildSimilarCardsByRangCountsMap(orderedDeckOfCards);
    }


    @Test
    public void similarCardsBySuitCountsListSize() {
        assertFalse(similarCardsBySuitCountsList.isEmpty());
        assertEquals(PokerDeckOfCards.SIZE_OF_DECK, similarCardsBySuitCountsList.size());
    }

    @Test
    public void similarCardsByRangCountsListSize() {
        assertFalse(similarCardsByRangCountsList.isEmpty());
        assertEquals(PokerDeckOfCards.SIZE_OF_DECK / COUNT_OF_SUITS, similarCardsByRangCountsList.size());
    }

    @Test
    public void similarCardsBySuitCountsListContainsFoursOnly() {
        assertEquals(0, similarCardsBySuitCountsList.stream()
                .filter(countInARow -> countInARow != COUNT_OF_SIMILAR_CARDS_BY_SUIT_FOR_OF_ORDERED_DECK).count());
    }

    @Test
    public void similarCardsByRangCountsListContainsOnesOnly() {
        assertEquals(0, similarCardsByRangCountsList.stream()
                .filter(countInARow -> countInARow != COUNT_OF_SIMILAR_CARDS_BY_RANG_FOR_OF_ORDERED_DECK).count());
    }

    //todo @serhii: add tests for map

}


