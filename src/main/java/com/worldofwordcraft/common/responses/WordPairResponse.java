package com.worldofwordcraft.common.responses;

import com.worldofwordcraft.domain.WordPair;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordPairResponse {

    private WordPair wordPair;

    public WordPairResponse(WordPair wordPair) {
        this.wordPair = wordPair;
    }
}
