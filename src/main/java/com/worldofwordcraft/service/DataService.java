package com.worldofwordcraft.service;

import com.worldofwordcraft.common.constants.Language;
import com.worldofwordcraft.common.exceptions.LanguageNotFoundException;
import com.worldofwordcraft.domain.WordPair;

import java.util.List;

/**
 * Interface for calls to the data in json format
 */
public interface DataService {

    /**
     * This loads the data from the json files, if there is new data it will be lost
     */
    void loadData();

    /**
     * This method will retrieve the whole wordPair list based on the language
     *
     * @param language the request language
     * @return The list of WordPairs for the language
     * @throws LanguageNotFoundException exception when language not found
     */
    List<WordPair> getWordPairList(Language language) throws LanguageNotFoundException;

}
