package com.worldofwordcraft.controller;

import com.worldofwordcraft.common.constants.GameType;
import com.worldofwordcraft.common.responses.ResponseJson;
import com.worldofwordcraft.common.responses.ResponseListJson;
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
    public ResponseJson getRandomWordPair(@PathVariable("gameType") GameType gameType) {
        return new ResponseJson(wordPairService.getRandomWordPair(gameType));
    }

    @GetMapping("{gameType}/list")
    public ResponseListJson getWordPairList(@PathVariable("gameType") GameType gameType) {
        return new ResponseListJson(wordPairService.getWordPairList(gameType));
    }
}
