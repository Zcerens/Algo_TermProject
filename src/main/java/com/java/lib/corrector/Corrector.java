package com.java.lib.corrector;

import com.java.lib.tree.element.ITree;
import com.java.lib.tree.element.Tree;
import com.java.lib.tree.node.ITreeNode;

import java.util.*;

public class Corrector implements ICorrector {
    private ITree tree;
    private Map<String, Integer> dictionary;
    private List<String> invalidWords;

    public Corrector() {
        this.tree = new Tree();
        this.invalidWords = Arrays.asList("lol", "abcdefghijklmnopqrstuvwxyz"); // add invalid words
    }

    @Override
    public ITree getTree() {
        return this.tree;
    }

    @Override
    public ICorrector loadDictionary(Map<String, Integer> dictionary) {
        this.dictionary = dictionary;
        return this;
    }

    @Override
    public String correctText(String text) {
        String[] words = text.split(" ");
        for (String word : words) {
            // check if word is valid
            if(!this.correctWord(word)) {
                String similarWord = this.suggestSimilarWord(word); // suggest similar word
                if (similarWord != null) {
                    text = text.replace(word, similarWord); // replace word with similar word
                    if(word.substring(0, 1).equals(word.substring(0, 1).toUpperCase())) {
                        // if word is capitalized, capitalize similar word
                        text = text.replace(similarWord, similarWord.substring(0, 1).toUpperCase() + similarWord.substring(1));
                    }
                }
            }
        }
        return text;
    }

    private boolean correctWord(String word) {
        return this.dictionary.containsKey(word.toLowerCase()); // check if word is in dictionary
    }

    public String suggestSimilarWord(String word) {
        if (word == null || word.length() == 0 || this.invalidWords.contains(word.toLowerCase())) return null;
        String wordLower = word.toLowerCase(); // convert word to lower case for search
        String res = null;
        TreeMap<Integer, TreeMap<Integer, TreeSet<String>>> map = new TreeMap<>();
        ITreeNode node = this.tree.find(wordLower);
        if (node == null) {
            // word not found in dictionary, find similar words
            for (String w : this.dictionary.keySet()) {
                int dist = this.editDistance(w, wordLower);
                TreeMap<Integer, TreeSet<String>> similarWords = map.getOrDefault(dist, new TreeMap<>());
                int freq = this.dictionary.get(w);
                TreeSet<String> set = similarWords.getOrDefault(freq, new TreeSet<>());
                set.add(w);

                similarWords.put(freq, set); // add word to similar words
                map.put(dist, similarWords); // add similar words to map
            }
            if (map.size() > 0) {
                // get first element from map
                res = map.firstEntry().getValue().lastEntry().getValue().first();
                if (word.charAt(word.length() - 1) == '.' || word.charAt(word.length() - 1) == ',') {
                    // add punctuation to the end of the word
                    res += word.charAt(word.length() - 1);
                }
                if (Character.isUpperCase(word.charAt(0))) {
                    // capitalize first letter for the first word
                    res = res.substring(0, 1).toUpperCase() + res.substring(1);
                }
            }
        }else {
            // if word is in the dictionary
            res = wordLower;
        }
        return res;
    }

    private int editDistance(String firstWord, String secondWord) {
        int n = firstWord.length();
        int m = secondWord.length();
        // create matrix with n+1 rows and m+1 columns
        int dp[][] = new int[n+1][m+1];
        // fill first row
        for (int i = 0; i <= n; i++) {
            // each cell in the first row is equal to the number of the row
            for (int j = 0; j <= m; j++) {
                // each cell in the first column is equal to the number of the column
                if (i == 0)
                    dp[i][j] = j; // Min. operations = j
                else if (j == 0)
                    dp[i][j] = i; // Min. operations = i
                else if (firstWord.charAt(i-1) == secondWord.charAt(j-1))
                    dp[i][j] = dp[i-1][j-1]; // if the last characters are equal, ignore them and find the min. operations for the rest of the string
                else if (i>1 && j>1  && firstWord.charAt(i-1) == secondWord.charAt(j-2)
                        && firstWord.charAt(i-2) == secondWord.charAt(j-1))
                    dp[i][j] = 1+Math.min(Math.min(dp[i-2][j-2], dp[i-1][j]), Math.min(dp[i][j-1], dp[i-1][j-1])); // if the last characters are not equal, check if the last two characters can be swapped
                else
                    dp[i][j] = 1 + Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1])); // if the last characters are not equal, find the min. operations for the rest of the string
            }
        }
        return dp[n][m];
    }
}
