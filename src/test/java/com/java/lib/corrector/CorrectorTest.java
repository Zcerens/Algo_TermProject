package com.java.lib.corrector;

import com.java.lib.dictionary.Dictionary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CorrectorTest {

    @Test
    void getTree() {
        Corrector corrector = new Corrector();
        assertNotNull(corrector.getTree());
    }

    @Test
    void loadDictionary() {
        Corrector corrector = new Corrector();
        assertNotNull(corrector.loadDictionary(null));
        Dictionary dictionary = new Dictionary();
        try {
            assertNotNull(corrector.loadDictionary(dictionary.loadDictionary("dictionary.txt", corrector.getTree())));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void correctText() {
        Corrector corrector = new Corrector();
        Dictionary dictionary = new Dictionary();
        try {
            corrector.loadDictionary(dictionary.loadDictionary("dictionary.txt", corrector.getTree()));
            String wrongText = "Hellı, mu name is John";
            String correctText = corrector.correctText(wrongText);
            // check corrector is works
            assertEquals("Hello, my name is John", correctText);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    void suggestSimilarWord() {
        Corrector corrector = new Corrector();
        Dictionary dictionary = new Dictionary();
        try {
            corrector.loadDictionary(dictionary.loadDictionary("dictionary.txt", corrector.getTree()));
            String wrongText = "Hellı, mu name is John";
            String correctText = corrector.correctText(wrongText);
            assertEquals("Hello, my name is John", correctText);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}