package com.kata.antonio.dictionary.data;

import java.util.Hashtable;

/**
 * Created by Antonio on 22/09/2014.
 */
public class MemoryDictionary implements DictionaryDataSource{

    private Hashtable<String, WordEntity> dictionary;

    public MemoryDictionary(){
        this.dictionary = new Hashtable<String, WordEntity>();
    }

    public void addWordWithDefinitionToRAEDictionary(WordEntity wordEntity){
        this.dictionary.put(wordEntity.getWord(), wordEntity);
    }

    public WordEntity searchDefinitionInRAEOfWord(String word){
        return this.dictionary.get(word);
    }
}
