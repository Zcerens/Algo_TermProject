package com.java.lib.dictionary;

import java.io.IOException;
import java.util.Map;

public interface IDictionary {
    Map<String, Integer> loadDictionary(String path, IDictionaryCallback callback) throws IOException;
}
