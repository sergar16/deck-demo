package org.serhiihokhkalenko.deckdemo.util;


import org.serhiihokhkalenko.deckdemo.model.DeckOfCards;
import org.serhiihokhkalenko.deckdemo.model.PokerDeckOfCards;
import org.serhiihokhkalenko.deckdemo.model.card.Card;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

/**
 * Analyze util for PokerDeck
 */
public class ShuffleQualityAnalyzeUtil {
    /**
     * return map (count of cards with the same Rank in a row -> how many time this count repeated in the deck)
     * example:
     * 1->48     [♣4] [♦Q] [♠4]
     * 2->2      [♦5] [♠A] [♣A] [♦5] - two cards with same rang
     *
     * @param deckOfCards - deck of cards
     * @return map of counts
     */
    public static Map<Integer, Integer> buildSimilarCardsByRangCountsMap(DeckOfCards deckOfCards) {
        return buildSimilarCardsCountsMap(buildSimilarCardsByRangCountsList(deckOfCards));
    }

    /**
     * return map (count of cards with the same Suit in a row -> how many time this count repeated in the deck)
     * <p>
     * example:
     * 1->28    (28 cards with not same suit as previous and next like ♣8 - [♠4] [♣8] [♦4])
     * 2->9     [♦7] [♥5] [♥8] [♦5]  - two in a row with the same suit
     * 3->2     [♦5] [♠2] [♠3] [♠8] [♦2] - three in a row with the same suit
     *
     * @param deckOfCards - deck of cards
     * @return map of counts
     */
    public static Map<Integer, Integer> buildSimilarCardsBySuitCountsMap(DeckOfCards deckOfCards) {
        return buildSimilarCardsCountsMap(buildSimilarCardsBySuitCountsList(deckOfCards));
    }

    /**
     * return map (count of  the similar cards by criteria in a row -> how many time this count repeated in the deck)
     * based on similaritiesList list
     *
     * @param similaritiesList - list with the counts of similar cards in row by some criteria
     * @return map of counts
     */
    private static Map<Integer, Integer> buildSimilarCardsCountsMap(List<Integer> similaritiesList) {
        return similaritiesList
                .stream()
                .collect(groupingBy(Function.identity(), summingInt(e -> 1)));
    }

    /**
     * Functional interface for criteria
     */
    @FunctionalInterface
    private interface TwoCardsCriteria {
        boolean isSimilar(Card firstCard, Card secondCard);
    }

    /**
     * Returns the List consist of count of Cards that have similar Rang in a row
     * <p>
     * example : 11111111111111111111112111111111111111111111111211
     *
     * @param deckOfCards - deck of cards
     * @return - list of counts of cards that have same Rang
     */
    public static List<Integer> buildSimilarCardsByRangCountsList(DeckOfCards deckOfCards) {
        TwoCardsCriteria suitCriteria = (firstCard, secondCard) -> firstCard.getRang().equals(secondCard.getRang());
        return buildSimilarCardsCountsListByCriteria(deckOfCards, suitCriteria);
    }

    /**
     * Returns the List consist of count of Cards that have similar Suit in a row
     * <p>
     * example : 1313111111121111111121222211132112121
     *
     * @param deckOfCards - deck of cards
     * @return - list of counts of cards that have same Suit
     */
    public static List<Integer> buildSimilarCardsBySuitCountsList(DeckOfCards deckOfCards) {
        TwoCardsCriteria suitCriteria = (firstCard, secondCard) -> firstCard.getSuit().equals(secondCard.getSuit());
        return buildSimilarCardsCountsListByCriteria(deckOfCards, suitCriteria);
    }

    /**
     * Returns the List consist of count of cards that are similar by twoCardsCriteria in a row
     *
     * @param deckOfCards      - deck of cards
     * @param twoCardsCriteria - criteria for comparing two cards
     * @return - list consist of count of cards that are similar by criteria
     */
    private static List<Integer> buildSimilarCardsCountsListByCriteria(DeckOfCards deckOfCards, TwoCardsCriteria twoCardsCriteria) {
        final Iterator<Card> deckOfCardsIterator = deckOfCards.iterator();
        Card cardBefore = deckOfCardsIterator.next();
        int countOfSuitInTheRow = 1;
        final List<Integer> similarCardsCountsList = new ArrayList<>(PokerDeckOfCards.SIZE_OF_DECK);

        while (deckOfCardsIterator.hasNext()) {
            Card currentCard = deckOfCardsIterator.next();

            if (twoCardsCriteria.isSimilar(cardBefore, currentCard)) {
                countOfSuitInTheRow++;
            } else {
                similarCardsCountsList.add(countOfSuitInTheRow);
                countOfSuitInTheRow = 1;
            }
            cardBefore = currentCard;
        }
        //add the latest one
        similarCardsCountsList.add(countOfSuitInTheRow);

        return similarCardsCountsList;
    }
}
