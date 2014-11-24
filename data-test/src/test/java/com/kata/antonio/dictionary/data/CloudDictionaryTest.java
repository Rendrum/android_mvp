package com.kata.antonio.dictionary.data;

import com.kata.antonio.dictionary.exception.NetworkConnectionException;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.json.JSONException;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;
import java.net.URL;

/**
 * Created by Antonio on 16/10/2014.
 */

@RunWith(RobolectricTestRunner.class)
public class CloudDictionaryTest {

    private CloudDictionary cloudDictionary;

    @Mock
    private DictionaryDataSource.DictionaryCallback mockDictionaryCallback;

    @Captor
    private ArgumentCaptor<WordEntity> wordEntityArgumentCaptor;

    private MockWebServer server;


    @Before
    public void setUp(){
        this.server = new MockWebServer();
        this.cloudDictionary = new CloudDictionary();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchWordOk() throws IOException {
        server.enqueue(new MockResponse().setBody("{ \"header\": \"test\",\"description\": \"happy testing!\"}"));
        server.play();

        URL baseUrl = server.getUrl("/dictionary");
        this.cloudDictionary.setURL(baseUrl.toString());
        this.cloudDictionary.searchDefinitionOfWord("test", this.mockDictionaryCallback);

        verify(this.mockDictionaryCallback).onWordLoaded(this.wordEntityArgumentCaptor.capture());

        assertEquals("happy testing!", this.wordEntityArgumentCaptor.getValue().getDescription());

        server.shutdown();
    }

    @Test
    public void testSearchWordFailsBecauseMalformedJSON() throws IOException{
        server.enqueue(new MockResponse().setBody(""));
        server.play();

        URL baseUrl = server.getUrl("/dictionary");
        this.cloudDictionary.setURL(baseUrl.toString());
        this.cloudDictionary.searchDefinitionOfWord("test", this.mockDictionaryCallback);

        verify(this.mockDictionaryCallback).onError(any(JSONException.class));

        server.shutdown();
    }

    @Test
    public void testSearchWordFailsBecauseConnection() throws IOException{
        server.enqueue(new MockResponse().setResponseCode(400).setBody("error"));
        server.play();

        URL baseUrl = server.getUrl("/dictionary");
        this.cloudDictionary.setURL(baseUrl.toString());
        this.cloudDictionary.searchDefinitionOfWord("test", this.mockDictionaryCallback);

        verify(this.mockDictionaryCallback).onError(any(NetworkConnectionException.class));

        server.shutdown();
    }

    // TODO the request url to get the json is: http://api.wordnik.com:80/v4/word.json/test/definitions?limit=1&includeRelated=true&useCanonical=false&includeTags=false&api_key=44b1d84173183206647ba25de7d0d8e9dacec7c04bc9f7660
    //    [
    //    {
    //        "textProns": [],
    //        "sourceDictionary": "ahd-legacy",
    //            "exampleUses": [],
    //        "relatedWords": [],
    //        "labels": [],
    //        "citations": [],
    //        "word": "test",
    //            "text": "A procedure for critical evaluation; a means of determining the presence, quality, or truth of something; a trial:  a test of one's eyesight; subjecting a hypothesis to a test; a test of an athlete's endurance. ",
    //            "sequence": "0",
    //            "score": 0,
    //            "partOfSpeech": "noun",
    //            "attributionText": "from The American Heritage® Dictionary of the English Language, 4th Edition"
    //    }
    //    ]

}
