package com.worldofwordcraft.service;

import com.worldofwordcraft.domain.WordPair;

public interface WordPairService {

    /**
     * Get a random WordPair object from the DB
     *
     * @return WordPair object
     */
    WordPair getRandomWordPair();

}
