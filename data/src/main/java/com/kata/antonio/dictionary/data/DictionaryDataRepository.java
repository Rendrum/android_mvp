package com.kata.antonio.dictionary.data;

/**
 * Created by Antonio on 20/09/2014.
 */
public class DictionaryDataRepository {

    private CloudDictionary cloudDataSource;
    private MemoryDictionary memoryDataSource;

    public DictionaryDataRepository(DictionaryDataFactory dataFactory){
        this.cloudDataSource = dataFactory.createCloudDataProvider();
        this.memoryDataSource = dataFactory.createMemoryDataProvider();
    }

    public WordEntity searchDefinitionForWord(String word){
        WordEntity wordEntity = cloudDataSource.searchDefinitionInRAEOfWord(word);
        if (wordEntity.isEmpty()){
            wordEntity = memoryDataSource.searchDefinitionInRAEOfWord(word);
        }
        return wordEntity;
    }

    public void addNewWordWithDefinition(WordEntity wordEntity){
        this.memoryDataSource.addWordWithDefinitionToRAEDictionary(wordEntity);
    }
}
