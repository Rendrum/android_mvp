package com.kata.antonio.dictionary.data;

/**
 * Created by Antonio on 24/09/2014.
 */
public interface DictionaryDataSource {

    interface DictionaryCallback {

        void onWordLoaded(WordEntity wordEntity);

        void onError(Exception exception);
    }

    void searchDefinitionOfWord(String word, DictionaryCallback dictionaryCallback);
}
