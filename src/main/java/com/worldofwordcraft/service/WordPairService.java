package com.worldofwordcraft.service;

import com.worldofwordcraft.common.constants.GameType;
import com.worldofwordcraft.domain.WordPair;

import java.util.List;

public interface WordPairService {

    /**
     * Get the count of rows.
     *
     * @param gameType the requested gametype EN or DE
     * @return Long number
     */
    Integer getCount(GameType gameType);

    /**
     * Get Random WordPair
     *
     * @param gameType the requested gametype EN or DE
     * @return random Wordpair
     */
    WordPair getRandomWordPair(GameType gameType);

    /**
     * Get Random WordPair
     *
     * @param gameType the requested gametype EN or DE
     * @return random Wordpair
     */
    List<WordPair> getWordPairList(GameType gameType);
}
