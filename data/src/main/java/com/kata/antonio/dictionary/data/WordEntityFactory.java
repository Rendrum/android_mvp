package com.kata.antonio.dictionary.data;

/**
 * Created by Antonio on 24/09/2014.
 */
public class WordEntityFactory {

    public static WordEntity createEmptyWord(){
        return new WordEntity("", "");
    }

    public static WordEntity createDefaultNotEmptyWord(){
        return new WordEntity("wordValue", "definitionValue");
    }
}
