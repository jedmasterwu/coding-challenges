package com.wx.coding.tests;

import com.wx.courses.algs4.graphs.WordNet;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

public class WordNetTest {

    @Test
    public void testWordNet() {
        WordNet wordNet = new WordNet("src/test/resources/wordnet/synsets.txt", "src/test/resources/wordnet/hypernyms300K.txt");
        AssertJUnit.assertTrue(wordNet.isNoun("Aberdeen"));
    }
}
