package org.serhiihokhkalenko.deckdemo.runner;

import org.serhiihokhkalenko.deckdemo.model.PokerDeckOfCards;
import org.serhiihokhkalenko.deckdemo.util.ShuffleQualityAnalyzeUtil;

import java.util.Arrays;


public class Main {

    public static void main(String[] args) {
        System.out.println("Generated ordered deck");
        PokerDeckOfCards pokerDeckOfCards = PokerDeckOfCards.getOrderedDeckOfCards();
        pokerDeckOfCards.forEach(System.out::print);

        System.out.println("\nshuffled deck");
        pokerDeckOfCards.shuffle();
        pokerDeckOfCards.forEach(System.out::print);

        System.out.println("\nsecond time shuffled deck");
        pokerDeckOfCards.shuffle();
        pokerDeckOfCards.forEach(System.out::print);

        System.out.println("\nanalyze list (count of cards with same suit )  ");
        ShuffleQualityAnalyzeUtil.buildSimilarCardsBySuitCountsList(pokerDeckOfCards)
                .forEach( System.out::print);

        System.out.println("\nanalyze list (count of cards with same rang )  ");
        ShuffleQualityAnalyzeUtil.buildSimilarCardsByRangCountsList(pokerDeckOfCards)
                .forEach( System.out::print);

        System.out.println("\nanalyze map (count of cards with same suit -> count )  ");
        ShuffleQualityAnalyzeUtil.buildSimilarCardsBySuitCountsMap(pokerDeckOfCards)
                .forEach((k, v) -> System.out.println("\t" + k + "->" + v));

        System.out.println("\nanalyze map (count of cards with same rang -> count )  ");
        ShuffleQualityAnalyzeUtil.buildSimilarCardsByRangCountsMap(pokerDeckOfCards)
                .forEach((k, v) -> System.out.println("\t" + k + "->" + v));

        System.out.println("\ndeal 20 cands from deck");
        Arrays.stream(pokerDeckOfCards.dealCards(20)).forEach(System.out::print);

        System.out.println("\ndeck without 20 cards");
        pokerDeckOfCards.forEach(System.out::print);

        System.out.println("\ndeck after skipping 25 cards");
        pokerDeckOfCards.skipCards(25);
        pokerDeckOfCards.forEach(System.out::print);

        System.out.println("\nshuffling remaining cards ");
        pokerDeckOfCards.shuffle();
        pokerDeckOfCards.forEach(System.out::print);

    }


}
