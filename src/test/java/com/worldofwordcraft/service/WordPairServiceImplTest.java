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

        Integer size = wordPairService.getCount(Language.EN);

        assertEquals(4, size);
    }

    @Test
    void getRandomWordPair() {
    }

    @Test
    void addNewWordPair() {
    }

    @Test
    void getWordPairList() {
    }

    @Test
    void addNewWordPairList() {
    }

    @Test
    void getHint() {
    }


    private List<WordPair> mockWordPairList() throws IOException {
        List<WordPair> mockList = new ArrayList<>();
        WordPair wp = new WordPair("a","a");
        WordPair wp1 = new WordPair("b","a");
        WordPair wp2 = new WordPair("c","a");
        WordPair wp3 = new WordPair("d","a");

        mockList.add(wp);
        mockList.add(wp1);
        mockList.add(wp2);
        mockList.add(wp3);
        return mockList;
    }
}