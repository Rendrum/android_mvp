package com.kata.antonio.dictionary.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Antonio on 13/11/2014.
 */
public class WordJSONMapper {

    public WordEntity mapIntoWord(JSONObject jsonObject) throws JSONException {
        WordEntity wordEntity;
        try {
            wordEntity = new WordEntity(jsonObject.getString("word"), jsonObject.getString("text"));
        } catch (JSONException e) {
            throw new JSONException("Malformed JSON. Please review the format.");
        }
        return wordEntity;
    }
}
