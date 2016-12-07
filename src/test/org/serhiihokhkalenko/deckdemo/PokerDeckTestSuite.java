package org.serhiihokhkalenko.deckdemo;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.serhiihokhkalenko.deckdemo.tests.PokerDeckDealAndSkipTest;
import org.serhiihokhkalenko.deckdemo.tests.PokerDeckShuffleTest;
import org.serhiihokhkalenko.deckdemo.tests.ShuffleQualityAnalyzeUtilTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PokerDeckDealAndSkipTest.class,
        ShuffleQualityAnalyzeUtilTest.class,
        PokerDeckShuffleTest.class
})
public class PokerDeckTestSuite {
}


