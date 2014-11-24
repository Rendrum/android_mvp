package com.kata.antonio.dictionary.data;

import com.kata.antonio.dictionary.exception.NetworkConnectionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Antonio on 24/11/2014.
 */
public class WordNickAPI implements WorkAPI {


    @Override
    public JSONObject getWord(String word) throws JSONException, NetworkConnectionException {

        JSONObject jObject = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(this.buildURLForWord(word));
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String stringResponse = getStringFromInputStream(in);
            jObject = new JSONArray(stringResponse).getJSONObject(0);
        } catch (MalformedURLException e) {
            throw new NetworkConnectionException(e.getMessage());
        } catch (IOException e) {
            throw new NetworkConnectionException(e.getMessage());
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
        }
        return jObject;
    }

    @Override
    public String buildURLForWord(String word) {
        return String.format("http://api.wordnik.com:80/v4/word.json/%s/definitions?limit=1&includeRelated=true&useCanonical=false&includeTags=false&api_key=44b1d84173183206647ba25de7d0d8e9dacec7c04bc9f7660", word);
    }

    private String getStringFromInputStream(InputStream inputStream) {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilderResult = new StringBuilder();

        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilderResult.append(line);
            }
            return stringBuilderResult.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

}
