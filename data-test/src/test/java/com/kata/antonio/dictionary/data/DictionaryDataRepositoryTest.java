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
    private CloudDictionary mockCloudDictionary;

    @Mock
    private MemoryDictionary mockMemoryDictionary;

    private DictionaryDataRepository repository;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        given(mockDictionaryFactory.createCloudDataProvider()).willReturn(mockCloudDictionary);
        given(mockDictionaryFactory.createMemoryDataProvider()).willReturn(mockMemoryDictionary);
        repository = new DictionaryDataRepository(mockDictionaryFactory);
    }

    @Test
    public void testFindWord(){
        when(mockCloudDictionary.searchDefinitionInRAEOfWord(anyString())).
                thenReturn(WordEntityFactory.createDefaultNotEmptyWord());
        repository.searchDefinitionForWord(anyString());
        verify(mockCloudDictionary).searchDefinitionInRAEOfWord(anyString());
    }

    @Test
    public void testAddNewWord(){
        repository.addNewWordWithDefinition(any(WordEntity.class));
        verify(mockMemoryDictionary).addWordWithDefinitionToRAEDictionary(any(WordEntity.class));
    }

    @Test
    public void testFindWordWhenInternetIsOfflineOrNotFoundInCloud(){
        when(mockCloudDictionary.searchDefinitionInRAEOfWord(anyString())).
                thenReturn(WordEntityFactory.createEmptyWord());
        repository.searchDefinitionForWord(anyString());
        verify(mockMemoryDictionary).searchDefinitionInRAEOfWord(anyString());
    }

}
