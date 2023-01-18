package com.java.lib.dictionary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Dictionary implements IDictionary {
    @Override
    public Map<String, Integer> loadDictionary(String path, IDictionaryCallback callback) throws IOException {
        Map<String,Integer> dict = new HashMap<>();
        FileReader fr = new FileReader(path);
        BufferedReader br = new BufferedReader(fr);
        String line = null;
        while((line = br.readLine()) != null) {
            String word = line.toLowerCase();
            if(!line.contains(" ")) { // check if word is not a phrase
                dict.put(word, dict.getOrDefault(word, 0) + 1); // add word to dictionary
                callback.add(word); // add word to tree
            }else {
                String[] words = line.split("\\s");
                for(String w : words) {
                    w = w.toLowerCase(); // convert word to lower case for search
                    dict.put(w, dict.getOrDefault(w, 0) + 1);
                    callback.add(w);  // add word to tree
                }
            }
        }
        fr.close();
        br.close();
        return dict;
    }
}
