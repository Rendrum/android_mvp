package com.kata.antonio.dictionary.data;

import com.kata.antonio.dictionary.exception.NetworkConnectionException;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.robolectric.RobolectricTestRunner;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.stub;

/**
 * Created by Antonio on 24/11/2014.
 */

@RunWith(RobolectricTestRunner.class)
public class WordNickAPITest {

    private MockWebServer mockWebServer;

    @Spy
    private WordNickAPI spyWordNickAPI;

    @Before
    public void setUp(){
        this.mockWebServer = new MockWebServer();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetWordOk() throws Exception {
        String jsonResponse = "[{" +
                                "'word': 'test'," +
                                "'text': 'test definition'," +
                                "}]";
        mockWebServer.enqueue(new MockResponse().setBody(jsonResponse));
        mockWebServer.play();
        URL baseUrl = mockWebServer.getUrl("/dictionary");
        stub(this.spyWordNickAPI.buildURLForWord(anyString())).toReturn(baseUrl.toString());

        JSONObject jsonWord = this.spyWordNickAPI.getWord("test");

        assertEquals("test", jsonWord.getString("word"));
        assertEquals("test definition", jsonWord.getString("text"));
        mockWebServer.shutdown();
    }

    @Test(expected = JSONException.class)
    public void testGetWordFailsBecauseMalformedJSON() throws Exception {
        mockWebServer.enqueue(new MockResponse().setBody(""));
        mockWebServer.play();
        URL baseUrl = mockWebServer.getUrl("/dictionary");
        stub(this.spyWordNickAPI.buildURLForWord(anyString())).toReturn(baseUrl.toString());

        this.spyWordNickAPI.getWord("test");

        mockWebServer.shutdown();
    }

    @Test(expected = NetworkConnectionException.class)
    public void testGetWordFailsBecauseConnection() throws Exception {
        mockWebServer.enqueue(new MockResponse().setResponseCode(400).setBody("error"));
        mockWebServer.play();
        URL baseUrl = mockWebServer.getUrl("/dictionary");
        stub(this.spyWordNickAPI.buildURLForWord(anyString())).toReturn(baseUrl.toString());

        this.spyWordNickAPI.getWord("test");

        mockWebServer.shutdown();
    }

    public void testBuildURLOk(){
        String url = this.spyWordNickAPI.buildURLForWord("test");
        assertEquals("http://api.wordnik.com:80/v4/word.json/test/definitions?limit=1&includeRelated=true&useCanonical=false&includeTags=false&api_key=44b1d84173183206647ba25de7d0d8e9dacec7c04bc9f7660",
                    url);
    }

}

