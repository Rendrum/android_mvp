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

    private String url;

    @Override
    public void searchDefinitionOfWord(String word, DictionaryCallback dictionaryCallback){

        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(this.url);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String stringResponse = getStringFromInputStream(in);
            JSONObject jObject = new JSONObject(stringResponse);

            WordEntity wordEntity = new WordEntity(
                    jObject.getString("header"),
                    jObject.getString("description"));
            dictionaryCallback.onWordLoaded(wordEntity);

        } catch (IOException e){
            dictionaryCallback.onError(new NetworkConnectionException(e.getMessage()));
        } catch (JSONException e){
            dictionaryCallback.onError(e);
        } finally {
            if (urlConnection != null){
                urlConnection.disconnect();
            }
        }
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

    public void setURL(String url) {
        this.url = url;
    }
}
