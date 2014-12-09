package com.kata.antonio.dictionary.data;

import com.kata.antonio.dictionary.exception.NetworkConnectionException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

import java.io.IOException;

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

    @Mock
    private WordNickAPI mockWordNickAPI;

    @Mock
    private WordJSONMapper mockWordJSONMapper;

    private WordNickAPI wordNickAPI;


    @Before
    public void setUp() throws NetworkConnectionException, JSONException {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchWordOk() throws IOException, NetworkConnectionException, JSONException {
        this.cloudDictionary = new CloudDictionary(this.mockWordJSONMapper, this.mockWordNickAPI);
        this.cloudDictionary.searchDefinitionOfWord("test", this.mockDictionaryCallback);

        verify(this.mockWordNickAPI).getWord("test");
        verify(this.mockWordJSONMapper).mapIntoWord(any(JSONObject.class));
        verify(this.mockDictionaryCallback).onWordLoaded(this.wordEntityArgumentCaptor.capture());
    }

    @Test
    public void testSearchWordFailsBecauseMalformedJSON() throws IOException, JSONException, NetworkConnectionException {
        this.wordNickAPI = new WordNickAPI();
        this.cloudDictionary = new CloudDictionary(this.mockWordJSONMapper, this.wordNickAPI);
        doReturn(new JSONObject()).when(this.mockWordNickAPI).getWord(anyString());

        this.cloudDictionary.searchDefinitionOfWord("this will fail", this.mockDictionaryCallback);

        verify(this.mockDictionaryCallback).onError(any(JSONException.class));
    }

    @Test
    public void testSearchWordFailsBecauseConnection() throws IOException, NetworkConnectionException, JSONException {
        this.cloudDictionary = new CloudDictionary(this.mockWordJSONMapper, this.mockWordNickAPI);
        doThrow(new NetworkConnectionException("message")).when(this.mockWordNickAPI).getWord(anyString());

        this.cloudDictionary.searchDefinitionOfWord("this will fail", this.mockDictionaryCallback);

        verify(this.mockDictionaryCallback).onError(any(NetworkConnectionException.class));
    }
}
