package com.worldofwordcraft.common.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.worldofwordcraft.domain.WordPair;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordPairResponse {

    @JsonProperty("wordPair")
    private WordPair wordPair;

    public WordPairResponse(WordPair wordPair) {
        this.wordPair = wordPair;
    }
}
