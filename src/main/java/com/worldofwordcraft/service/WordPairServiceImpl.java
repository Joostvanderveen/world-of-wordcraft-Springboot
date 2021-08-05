package com.worldofwordcraft.service;

import com.worldofwordcraft.domain.WordPair;
import com.worldofwordcraft.repos.WordPairRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WordPairServiceImpl implements WordPairService{

    private WordPairRepository wordPairRepository;

    public WordPairServiceImpl(WordPairRepository wordPairRepository) {
        this.wordPairRepository = wordPairRepository;
    }

    @Override
    public WordPair getRandomWordPair() {
        log.debug("Getting a random WordPair");
        return null;
//        return wordPairRepository.getRandomWordPair();
    }
}
