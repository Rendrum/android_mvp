package com.kata.antonio.dictionary.data;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class DictionaryDataRepositoryTest {

    @Test
    public void testHelloWorld(){
        DictionaryDataRepository repository = new DictionaryDataRepository();
        assertEquals("Values are equals", "hello!", repository.hello());
    }

}
