package com.java.lib.dictionary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DictionaryTest {

    @Test
    void loadDictionary() {
        Dictionary dictionary = new Dictionary();
        try {
            dictionary.loadDictionary("dictionary.txt", new IDictionaryCallback() {
                @Override
                public void add(String word) {
                    System.out.println(word);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}