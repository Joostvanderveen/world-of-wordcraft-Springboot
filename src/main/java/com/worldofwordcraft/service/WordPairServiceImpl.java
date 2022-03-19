package com.worldofwordcraft.service;

import com.worldofwordcraft.common.constants.Language;
import com.worldofwordcraft.common.exceptions.HintException;
import com.worldofwordcraft.common.exceptions.LanguageNotFoundException;
import com.worldofwordcraft.domain.WordPair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Random;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
public class WordPairServiceImpl implements WordPairService {

    private final DataService dataService;

    /**
     * public constructor with injected dataService
     *
     * @param dataService dataService
     */
    @Inject
    public WordPairServiceImpl(DataService dataService) {
        this.dataService = dataService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getCount(Language language) throws LanguageNotFoundException {
        log.info("Getting size of WordPair list {}", language);
        return dataService.getWordPairList(language).size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WordPair getRandomWordPair(Language language) throws LanguageNotFoundException {
        log.info("Getting a random WordPair from WordPairList {}", language);
        return dataService.getWordPairList(language).get(new Random().nextInt(getCount(language)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WordPair> getWordPairList(Language language) throws LanguageNotFoundException {
        log.info("Getting wordPairList {}", language);
        return dataService.getWordPairList(language);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WordPair> saveWordPairList(Language language, List<WordPair> wordPairList) throws LanguageNotFoundException {
        log.info("saving list: {}", language);
        return dataService.setWordPairList(language, wordPairList);
    }

    /**
     * {@inheritDoc}
     */
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
