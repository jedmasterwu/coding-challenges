package com.wx.coding.tests;

import com.wx.coding.problems.WordSearch;
import com.wx.utils.Trie;
import edu.princeton.cs.algs4.Stopwatch;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

public class TrieTest {

    @Test
    public void testTrieAddSearch() {
        String[] words = new String[] {
                "bad", "0",
                "bed", "0",
                "bet", "1",
                "better",  "0",
                "bladder", "0",
                "bat", "1",
                "bats", "0",
                "batter", "1",
                "battery", "0",
                "batteries", "0",
                "battering", "0",
                "a", "1",
                "an", "1",
                "and", "0",
                "ant", "0",
                "ban", "1",
                "bang", "1",
                "banged", "0",
                "banner", "0",
                "hat", "1",
                "hatch", "1",
                "hatchery", "0",
        };

        Trie trie = new Trie();
        for (int i = 0; i < words.length; i+= 2) {
            String word = words[i];
            trie.add(word);
        }
        for (int i = 0; i < words.length; i+= 2) {
            String word = words[i];
            trie.add(word);
            short result = trie.contains(word);
            if (words[i + 1].equals("1")) {
                AssertJUnit.assertTrue(word + " is a prefix too " + result, result == (Trie.IS_PREFIX | Trie.IS_WORD));
            } else {
                AssertJUnit.assertTrue(word + " is in the trie " + result, result == Trie.IS_WORD);
            }
        }

        AssertJUnit.assertEquals(trie.contains("ba"), Trie.IS_PREFIX);
        AssertJUnit.assertEquals(trie.contains("zander"), Trie.NOT_FOUND);
    }

    @Test
    public void testWordSearchII() {
        char[][] board = {
                "doaf".toCharArray(),
                "agai".toCharArray(),
                "dcan".toCharArray()
        };

        ArrayList<String> dictionary = new ArrayList<>();
        Stopwatch stopwatch = new Stopwatch();
        Collections.addAll(dictionary, "dog", "dad", "dgdg", "can", "again");

        WordSearch ws = new WordSearch();
        Set<String> result = ws.wordSearchII(board, dictionary);
        System.out.println(stopwatch.elapsedTime());
        result.forEach(System.out::println);
    }
}
