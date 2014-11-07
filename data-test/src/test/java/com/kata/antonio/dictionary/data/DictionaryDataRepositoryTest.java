package com.kata.antonio.dictionary.data;

import com.kata.antonio.dictionary.exception.NetworkConnectionException;

import org.junit.Test;
import org.junit.Before;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

public class DictionaryDataRepositoryTest {

    @Mock
    private DictionaryDataSource.DictionaryCallback mockDictionaryCallback;

    @Mock
    private ArgumentCaptor<DictionaryDataSource.DictionaryCallback> dictionaryCallbackArgumentCaptor;

    @Mock
    private CloudDictionary mockCloudDictionary;

    private WordEntity wordEntity;

    private NetworkConnectionException networkConnectionException;

    private DictionaryDataRepository dictionaryDataRepository;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.dictionaryDataRepository = new DictionaryDataRepository(this.mockCloudDictionary);
        this.networkConnectionException = new NetworkConnectionException("testing");
        this.wordEntity = new WordEntity();
    }

    @Test
    public void testFindWordOk(){
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((DictionaryDataSource.DictionaryCallback)invocation.getArguments()[1]).
                        onWordLoaded(wordEntity);
                return null;
            }
        }).when(this.mockCloudDictionary).
                searchDefinitionOfWord(anyString(), any(DictionaryDataSource.DictionaryCallback.class));

        this.dictionaryDataRepository.searchWord("test", this.mockDictionaryCallback);

        verify(this.mockDictionaryCallback).onWordLoaded(wordEntity);
    }

    @Test
    public void testFindWordFails(){
        doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                ((DictionaryDataSource.DictionaryCallback)invocation.getArguments()[1]).
                        onError(networkConnectionException);
                return null;
            }
        }).when(this.mockCloudDictionary).
                searchDefinitionOfWord(anyString(), any(DictionaryDataSource.DictionaryCallback.class));

        this.dictionaryDataRepository.searchWord("test", this.mockDictionaryCallback);

        verify(this.mockDictionaryCallback).onError(networkConnectionException);
    }

}
