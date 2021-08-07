package com.worldofwordcraft.service;

import com.worldofwordcraft.common.constants.GameType;
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
    public Integer getCount(GameType gameType) {
        loadWordPairList(gameType);
        log.debug("Getting size of WordPair list");
        return wordPairList.size();
    }

    @Override
    public WordPair getRandomWordPair(GameType gameType) {
        loadWordPairList(gameType);
        log.debug("Getting a random WordPair");
        return wordPairList.get(new Random().nextInt(wordPairList.size()));
    }

    @Override
    public List<WordPair> getWordPairList(GameType gameType) {
        loadWordPairList(gameType);
        log.debug("Getting a wordPairList");
        return wordPairList;
    }

    private void loadWordPairList(GameType gameType){
        switch (gameType) {
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
