package com.kata.antonio.dictionary.data;

/**
 * Created by Antonio on 24/09/2014.
 */
public interface DictionaryDataSource {

    void addWordWithDefinitionToRAEDictionary(WordEntity wordEntity);

    WordEntity searchDefinitionInRAEOfWord(String word);
}
