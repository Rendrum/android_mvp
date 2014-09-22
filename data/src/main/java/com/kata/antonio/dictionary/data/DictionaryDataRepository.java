package com.kata.antonio.dictionary.data;

/**
 * Created by Antonio on 20/09/2014.
 */
public class DictionaryDataRepository {

    private CloudDictionaryDataSource cloudDataSource;
    private MemoryDictionaryDataSource memoryDataSource;

    public DictionaryDataRepository(DictionaryDataFactory dataFactory){
        this.cloudDataSource = dataFactory.createCloudDataProvider();
        this.memoryDataSource = dataFactory.createMemoryDataProvider();
    }

    public String searchDefinitionForWord(String word){
        return cloudDataSource.searchDefinitionInRAEOfWord(word);
    }

    public void addNewWordWithDefinition(String word, String definition){
        this.memoryDataSource.addWordWithDefinitionToRAEDictionary(word, definition);
    }
}
