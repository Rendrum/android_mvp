package com.kata.antonio.dictionary.data;

/**
 * Created by Antonio on 24/09/2014.
 */
public class WordEntity {

    private String header;
    private String description;

    public WordEntity(){ }

    public WordEntity(String word, String description){
        this.header = word;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getHeader() {
        return header;
    }
}
