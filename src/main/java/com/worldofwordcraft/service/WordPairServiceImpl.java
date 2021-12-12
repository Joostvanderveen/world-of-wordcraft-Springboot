package com.worldofwordcraft.service;

import com.worldofwordcraft.common.constants.Language;
import com.worldofwordcraft.common.exceptions.HintException;
import com.worldofwordcraft.common.exceptions.LanguageNotFoundException;
import com.worldofwordcraft.domain.WordPair;
import lombok.extern.slf4j.Slf4j;
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
    public void addNewWordPair(Language language, WordPair wordPair) throws LanguageNotFoundException {
        log.info("Setting new wordPair {} into wordlist {}", wordPair.getQuestion(), language);
        dataService.getWordPairList(language).add(wordPair);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeWordPair(Language language, WordPair wordPair) throws LanguageNotFoundException {
        log.info("Trying to remove wordPair {} from list {}", wordPair.getQuestion(), language);
        dataService.getWordPairList(language).removeIf(wp -> wordPair.getQuestion().equalsIgnoreCase(wp.getQuestion()));
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
    public void addNewWordPairList(Language language, List<WordPair> wordPairList) throws LanguageNotFoundException {
        log.info("adding list to existing wordPair list {}", language);
        dataService.getWordPairList(language).addAll(wordPairList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reloadData() {
        log.info("reload data");
        dataService.loadData();
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
