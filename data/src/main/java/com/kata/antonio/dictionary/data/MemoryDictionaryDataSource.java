package com.kata.antonio.dictionary.data;

import java.util.Hashtable;

/**
 * Created by Antonio on 22/09/2014.
 */
public class MemoryDictionaryDataSource {

    private Hashtable<String, String> dictionary;

    public MemoryDictionaryDataSource(){
        this.dictionary = new Hashtable<String, String>();
    }

    public void addWordWithDefinitionToRAEDictionary(String word, String definition){
        this.dictionary.put(word, definition);
    }
}
