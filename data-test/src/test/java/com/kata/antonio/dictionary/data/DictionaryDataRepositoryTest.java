package com.kata.antonio.dictionary.data;

import org.junit.Test;
import org.junit.Before;

import static org.mockito.Mockito.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.BDDMockito.given;

public class DictionaryDataRepositoryTest {

    @Mock
    private DictionaryDataFactory mockDictionaryFactory;

    @Mock
    private CloudDictionaryDataSource mockCloudDictionaryDataSource;

    @Mock
    private MemoryDictionaryDataSource mockMemoryDictionaryDataSource;

    private DictionaryDataRepository repository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        given(mockDictionaryFactory.createCloudDataProvider()).willReturn(mockCloudDictionaryDataSource);
        given(mockDictionaryFactory.createMemoryDataProvider()).willReturn(mockMemoryDictionaryDataSource);
        repository = new DictionaryDataRepository(mockDictionaryFactory);
    }

    @Test
    public void testFindWord(){
        repository.searchDefinitionForWord(anyString());
        verify(mockCloudDictionaryDataSource).searchDefinitionInRAEOfWord(anyString());
    }

    @Test
    public void testAddNewWord(){
        repository.addNewWordWithDefinition(anyString(), anyString());
        verify(mockMemoryDictionaryDataSource).addWordWithDefinitionToRAEDictionary(anyString(), anyString());
    }

}
