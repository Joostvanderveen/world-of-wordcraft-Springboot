package com.worldofwordcraft.common.responses;

import com.worldofwordcraft.domain.WordPair;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseJson {

    private WordPair wordPair;

    public ResponseJson(WordPair wordPair) {
        this.wordPair = wordPair;
    }
}
