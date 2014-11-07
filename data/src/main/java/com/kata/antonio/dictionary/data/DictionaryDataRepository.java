package com.kata.antonio.dictionary.data;

/**
 * Created by Antonio on 07/11/2014.
 */
public class DictionaryDataRepository {

    private DictionaryDataSource dictionaryDataSource;

    public DictionaryDataRepository(DictionaryDataSource dataSource){
        this.dictionaryDataSource = dataSource;
    }

    public void searchWord(String word, final DictionaryDataSource.DictionaryCallback dictionaryCallback) {

        this.dictionaryDataSource.searchDefinitionOfWord(word, new DictionaryDataSource.DictionaryCallback() {
            @Override
            public void onWordLoaded(WordEntity wordEntity) {
                dictionaryCallback.onWordLoaded(wordEntity);
            }

            @Override
            public void onError(Exception exception) {
                dictionaryCallback.onError(exception);
            }
        });
    }
}
