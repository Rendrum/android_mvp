package com.kata.antonio.dictionary.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Antonio on 13/11/2014.
 */
public class WordJSONMapper {

    public WordEntity mapIntoWord(String jsonData) throws JSONException {
        WordEntity wordEntity;
        try {
            JSONArray wordJSONArray = new JSONArray(jsonData);
            JSONObject wordJSON = wordJSONArray.getJSONObject(0);
            wordEntity = new WordEntity(wordJSON.getString("word"), wordJSON.getString("text"));
        } catch (JSONException e) {
            throw new JSONException("Malformed JSON. Please review the format.");
        }
        return wordEntity;
    }
}
