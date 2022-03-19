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
     * @param language the requested language
     * @return Long number
     */
    Integer getCount(Language language) throws LanguageNotFoundException;

    /**
     * Get Random WordPair for specified language
     *
     * @param language the requested language
     * @return random WordPair
     */
    WordPair getRandomWordPair(Language language) throws LanguageNotFoundException;


    /**
     * Get the whole wordPair list for specified language
     *
     * @param language the requested language
     * @return random Wordpair
     */
    List<WordPair> getWordPairList(Language language) throws LanguageNotFoundException;

    /**
     * Save a wordPairList
     *
     * @param language     the requested language
     * @param wordPairList the list that needs to be saved
     * @return updated list
     * @throws LanguageNotFoundException when the language is not found
     */
    List<WordPair> saveWordPairList(Language language, List<WordPair> wordPairList) throws LanguageNotFoundException;

    /**
     * Returns a String with a hint for the answer from WordPair
     *
     * @param language the requested language
     * @param wordPair the wordPair for which hint is needed
     * @param amount   the number of characters as hint
     * @return Hint for the answer
     */
    String getHint(Language language, WordPair wordPair, int amount) throws HintException;

}
