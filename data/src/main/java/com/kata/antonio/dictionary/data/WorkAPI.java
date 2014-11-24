package com.kata.antonio.dictionary.data;

import com.kata.antonio.dictionary.exception.NetworkConnectionException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Antonio on 24/11/2014.
 */
public interface WorkAPI {

    JSONObject getWord(String word) throws JSONException, IOException, NetworkConnectionException;

    String buildURLForWord(String word);
}
