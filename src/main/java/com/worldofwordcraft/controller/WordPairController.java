package com.worldofwordcraft.controller;

import com.worldofwordcraft.common.constants.GameType;
import com.worldofwordcraft.common.responses.WordPairResponse;
import com.worldofwordcraft.common.responses.WordPairListResponse;
import com.worldofwordcraft.service.WordPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WordPairController {

    @Autowired
    private WordPairService wordPairService;

    @GetMapping("{gameType}/count")
    public Integer getCount(@PathVariable("gameType") GameType gameType) {
        return wordPairService.getCount(gameType);
    }

    @GetMapping("{gameType}/random")
    public WordPairResponse getRandomWordPair(@PathVariable("gameType") GameType gameType) {
        return new WordPairResponse(wordPairService.getRandomWordPair(gameType));
    }

    @GetMapping("{gameType}/list")
    public WordPairListResponse getWordPairList(@PathVariable("gameType") GameType gameType) {
        return new WordPairListResponse(wordPairService.getWordPairList(gameType));
    }
}
