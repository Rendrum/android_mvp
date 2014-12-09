package com.kata.antonio.dictionary.data;

import com.kata.antonio.dictionary.exception.NetworkConnectionException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Antonio on 22/09/2014.
 */
public class CloudDictionary implements DictionaryDataSource{

    private WordJSONMapper wordJSONMapper;
    private WordNickAPI wordNickAPI;

    public CloudDictionary(WordJSONMapper wordJSONMapper, WordNickAPI wordNickAPI) {
        this.wordJSONMapper = wordJSONMapper;
        this.wordNickAPI = wordNickAPI;
    }

    @Override
    public void searchDefinitionOfWord(String word, DictionaryCallback dictionaryCallback){

        try {
            JSONObject jsonObject = this.wordNickAPI.getWord(word);
            WordEntity wordEntity = this.wordJSONMapper.mapIntoWord(jsonObject);
            dictionaryCallback.onWordLoaded(wordEntity);
        } catch (JSONException e) {
            dictionaryCallback.onError(e);
        } catch (NetworkConnectionException e) {
            dictionaryCallback.onError(e);
        }
    }
}
