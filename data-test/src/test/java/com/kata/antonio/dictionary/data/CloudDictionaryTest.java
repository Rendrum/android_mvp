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

}
