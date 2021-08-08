package com.worldofwordcraft.service;

import com.worldofwordcraft.common.constants.Language;
import com.worldofwordcraft.domain.WordPair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import static com.worldofwordcraft.WorldOfWordcraftApplication.dutchAndEnglishList;
import static com.worldofwordcraft.WorldOfWordcraftApplication.dutchAndGermanList;

@Slf4j
@Service
public class WordPairServiceImpl implements WordPairService {

    private List<WordPair> wordPairList;

    @Override
    public Integer getCount(Language language) {
        loadWordPairList(language);
        log.debug("Getting size of WordPair list");
        return wordPairList.size();
    }

    @Override
    public WordPair getRandomWordPair(Language language) {
        loadWordPairList(language);
        log.debug("Getting a random WordPair");
        return wordPairList.get(new Random().nextInt(wordPairList.size()));
    }

    @Override
    public void addNewWordPair(Language language, WordPair wordPair) {
        loadWordPairList(language);
        log.debug("Setting new wordpair into {q} wordlist", language);
        wordPairList.add(wordPair);
    }

    @Override
    public List<WordPair> getWordPairList(Language language) {
        loadWordPairList(language);
        log.debug("Getting a wordPairList");
        return wordPairList;
    }

    @Override
    public void addNewWordPairList(Language language, List<WordPair> wordPairList) {
        loadWordPairList(language);
        log.debug("adding list to existing wordpair list");
        this.wordPairList.addAll(wordPairList);
    }

    private void loadWordPairList(Language language){
        switch (language) {
            case EN:
                wordPairList = dutchAndEnglishList.getWordPairList();
                break;
            case DE:
                this.wordPairList = dutchAndGermanList.getWordPairList();
                break;
            default:
                log.debug("default");
                break;
        }
    }
}
