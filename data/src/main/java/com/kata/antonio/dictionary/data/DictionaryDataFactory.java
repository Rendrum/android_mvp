package com.kata.antonio.dictionary.data;

/**
 * Created by Antonio on 22/09/2014.
 */
public class DictionaryDataFactory {

    public CloudDictionary createCloudDataProvider(){
        return new CloudDictionary();
    }

    public MemoryDictionary createMemoryDataProvider(){
        return new MemoryDictionary();
    }
}
