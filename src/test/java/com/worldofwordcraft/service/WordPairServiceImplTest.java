package com.worldofwordcraft.service;

import com.worldofwordcraft.common.constants.Language;
import com.worldofwordcraft.common.exceptions.LanguageNotFoundException;
import com.worldofwordcraft.domain.WordPair;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
class WordPairServiceImplTest {

    @MockBean
    DataServiceImpl dataService;

    @Autowired
    WordPairServiceImpl wordPairService;

    @Test
    void testGetCount_OK() throws IOException, LanguageNotFoundException {
        when(dataService.getWordPairList(any(Language.class)))
                .thenReturn(mockWordPairList());

        Integer size = wordPairService.getCount(Language.EN);

        assertEquals(4, size);
    }

    @Test
    void testGetCount_language_unknown() throws IOException, LanguageNotFoundException {
        when(dataService.getWordPairList(any(Language.class)))
                .thenThrow(new LanguageNotFoundException("Language not found: "));

        Exception exception = assertThrows(
                LanguageNotFoundException.class, () -> wordPairService.getCount(Language.EN));
        assertEquals("Language not found: ", exception.getMessage());
    }

    @Test
    void testGetRandomWordPair_OK() throws LanguageNotFoundException {
        when(dataService.getWordPairList(any(Language.class)))
                .thenReturn(mockWordPairList());

        WordPair result = wordPairService.getRandomWordPair(Language.EN);

        assertNotNull(result);
        assertNotNull(result.getAnswer());
        assertNotNull(result.getAnswer());
    }

    @Test
    void testGetWordPairList_OK() throws LanguageNotFoundException {
        when(dataService.getWordPairList(any(Language.class)))
                .thenReturn(mockWordPairList());

        List<WordPair> result = wordPairService.getWordPairList(Language.EN);

        assertEquals(4, result.size());
    }

    @Test
    void testGetWordPairList_language_unknown() throws LanguageNotFoundException {
        when(dataService.getWordPairList(any(Language.class)))
                .thenThrow(new LanguageNotFoundException("Language not found: "));

        Exception exception = assertThrows(
                LanguageNotFoundException.class, () -> wordPairService.getWordPairList(Language.EN));
        assertEquals("Language not found: ", exception.getMessage());
    }

    @Test
    void addNewWordPairList() {
    }

    @Test
    void getHint() {
    }


    private List<WordPair> mockWordPairList() {
        List<WordPair> mockList = new ArrayList<>();
        WordPair wp = new WordPair("a", "a");
        WordPair wp1 = new WordPair("b", "a");
        WordPair wp2 = new WordPair("c", "a");
        WordPair wp3 = new WordPair("d", "a");

        mockList.add(wp);
        mockList.add(wp1);
        mockList.add(wp2);
        mockList.add(wp3);
        return mockList;
    }
}