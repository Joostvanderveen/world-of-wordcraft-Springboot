package com.worldofwordcraft.service;

import com.worldofwordcraft.common.constants.Language;
import com.worldofwordcraft.common.exceptions.HintException;
import com.worldofwordcraft.common.exceptions.LanguageNotFoundException;
import com.worldofwordcraft.domain.WordPair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.IOException;
import java.util.List;
import java.util.Random;


@Slf4j
@Service
public class WordPairServiceImpl implements WordPairService {

    private DataService dataService;

    @Inject
    public WordPairServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    @Override
    public Integer getCount(Language language) throws LanguageNotFoundException {
        log.debug("Getting size of WordPair list");
        return dataService.getWordPairList(language).size();
    }

    @Override
    public WordPair getRandomWordPair(Language language) throws LanguageNotFoundException {
        log.debug("Getting a random WordPair");
        return dataService.getWordPairList(language).get(new Random().nextInt(getCount(language)));

    }

    @Override
    public void addNewWordPair(Language language, WordPair wordPair) throws LanguageNotFoundException {
        log.debug("Setting new wordpair into wordlist");
        dataService.getWordPairList(language).add(wordPair);
    }

    @Override
    public List<WordPair> getWordPairList(Language language) throws LanguageNotFoundException {
        log.debug("Getting a wordPairList");
        return dataService.getWordPairList(language);
    }

    @Override
    public void addNewWordPairList(Language language, List<WordPair> wordPairList) throws LanguageNotFoundException {
        log.debug("adding list to existing wordpair list");
        dataService.getWordPairList(language).addAll(wordPairList);
    }

    @Override
    public List<WordPair> getTest (Language language) throws IOException {
        log.debug("test service called");
        dataService.getTest(language);
        return dataService.getTest(language);
    }

    @Override
    public String getHint(Language language, WordPair wordPair, int amount) throws HintException {

        int size = wordPair.getAnswer().length();
        if (amount > size || amount < 1) {
            throw new HintException("out of bounds");
        }
        StringBuilder hint = new StringBuilder();
        String[] strings = wordPair.getAnswer().split("");
        for (int i = 0; i < size; i++) {
            if (i < amount) {
                hint.append(strings[i]);
            } else {
                hint.append(".");
            }
        }

        return hint.toString();
    }


}
