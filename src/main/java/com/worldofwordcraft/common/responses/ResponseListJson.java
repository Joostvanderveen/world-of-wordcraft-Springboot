package com.worldofwordcraft.common.responses;

import com.worldofwordcraft.domain.WordPair;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ResponseListJson {

    private List<WordPair> wordPairList;

    public ResponseListJson(List<WordPair> wordPairList) {
        this.wordPairList = wordPairList;
    }
}
