package com.kata.antonio.dictionary.data;

/**
 * Created by Antonio on 24/09/2014.
 */
public class WordEntity {

    private String word;
    private String description;

    public WordEntity(String word, String description){
        this.word = word;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public boolean isEmpty(){
        return word == "" && description == "";
    }
}
