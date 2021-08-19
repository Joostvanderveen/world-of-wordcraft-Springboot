package com.worldofwordcraft.service;

import com.worldofwordcraft.common.constants.Language;
import com.worldofwordcraft.common.exceptions.HintException;
import com.worldofwordcraft.common.exceptions.LanguageNotFoundException;
import com.worldofwordcraft.domain.WordPair;

import java.util.List;

/**
 * Interface for the WordPair Service
 */
public interface WordPairService {

    /**
     * Get the count of rows for specified language
     *
     * @param language the requested language EN or DE
     * @return Long number
     */
    Integer getCount(Language language) throws LanguageNotFoundException;

    /**
     * Get Random WordPair for specified language
     *
     * @param language the requested language EN or DE
     * @return random Wordpair
     */
    WordPair getRandomWordPair(Language language) throws LanguageNotFoundException;

    /**
     * Add a new wordpair in wordlist based on language
     *
     * @param language the requested language
     * @param wordPair the word-pair to add to the list
     */
    void addNewWordPair(Language language, WordPair wordPair) throws LanguageNotFoundException;

    /**
     * Get the whole wordPair list for specified language
     *
     * @param language the requested language EN or DE
     * @return random Wordpair
     */
    List<WordPair> getWordPairList(Language language) throws LanguageNotFoundException;


    /**
     * Add a List of WordPair objects to existing WordPair List based on language
     *
     * @param language     the requested language EN or DE
     * @param wordPairList the list that needs to be added
     */
    void addNewWordPairList(Language language, List<WordPair> wordPairList) throws LanguageNotFoundException;


    /**
     * Returns a String with a hint for the answer from WordPair
     *
     * @param language the requested language EN or DE
     * @param wordPair the wordPair for which hint is needed
     * @param amount   the number of characters as hint
     * @return
     */
    String getHint(Language language, WordPair wordPair, int amount) throws HintException;
}
