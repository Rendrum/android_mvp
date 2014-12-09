package com.kata.antonio.dictionary.data;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


/**
 * Created by Antonio on 13/11/2014.
 */

@RunWith(RobolectricTestRunner.class)
public class WordEntityJSONMapperTest {

    private WordJSONMapper wordJSONMapper;

    @Before
    public void setUp(){
        this.wordJSONMapper = new WordJSONMapper();
    }

    @Test
    public void testMapWordEntityOk() throws JSONException {
        String jsonResponse =
                "  {\n" +
                "    \"textProns\": [],\n" +
                "    \"sourceDictionary\": \"ahd-legacy\",\n" +
                "    \"exampleUses\": [],\n" +
                "    \"relatedWords\": [],\n" +
                "    \"labels\": [],\n" +
                "    \"citations\": [],\n" +
                "    \"word\": \"test\",\n" +
                "    \"text\": \"A procedure for critical evaluation.\",\n" +
                "    \"sequence\": \"0\",\n" +
                "    \"score\": 0,\n" +
                "    \"partOfSpeech\": \"noun\",\n" +
                "    \"attributionText\": \"from The American Heritage® Dictionary of the English Language, 4th Edition\"\n" +
                "  }";
        JSONObject jsonObject = new JSONObject(jsonResponse);
        WordEntity wordEntity = this.wordJSONMapper.mapIntoWord(jsonObject);

        assertThat(wordEntity.getDescription(), is(equalTo("A procedure for critical evaluation.")));
        assertThat(wordEntity.getHeader(), is(equalTo("test")));
    }

    @Test(expected=JSONException.class)
    public void testMapWordEntityFails() throws JSONException {
        String jsonResponse =
                "  {\n" +
                "    \"textProns\": [],\n" +
                "    \"sourceDictionary\": \"ahd-legacy\",\n" +
                "    \"exampleUses\": [],\n" +
                "    \"relatedWords\": [],\n" +
                "    \"labels\": [],\n" +
                "    \"citations\": [],\n" +
                "    \"sequence\": \"0\",\n" +
                "    \"score\": 0,\n" +
                "    \"partOfSpeech\": \"noun\",\n" +
                "    \"attributionText\": \"from The American Heritage® Dictionary of the English Language, 4th Edition\"\n" +
                "  }";
        JSONObject jsonObject = new JSONObject(jsonResponse);
        this.wordJSONMapper.mapIntoWord(jsonObject);
    }
}
