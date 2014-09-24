package com.kata.antonio.dictionary.data;

/**
 * Created by Antonio on 22/09/2014.
 */
public class CloudDictionary implements DictionaryDataSource{

    @Override
    public void addWordWithDefinitionToRAEDictionary(WordEntity wordEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public WordEntity searchDefinitionInRAEOfWord(String word){
        throw new UnsupportedOperationException();
    }
}
