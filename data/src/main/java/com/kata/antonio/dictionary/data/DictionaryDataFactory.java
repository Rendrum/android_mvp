package com.kata.antonio.dictionary.data;

/**
 * Created by Antonio on 22/09/2014.
 */
public class DictionaryDataFactory {

    public CloudDictionaryDataSource createCloudDataProvider(){
        return new CloudDictionaryDataSource();
    }

    public MemoryDictionaryDataSource createMemoryDataProvider(){
        return new MemoryDictionaryDataSource();
    }
}
