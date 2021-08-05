package com.worldofwordcraft.controller;

import com.worldofwordcraft.domain.WordPair;
import com.worldofwordcraft.service.WordPairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WordPairController {

    @Autowired
    private WordPairService wordPairService;

    @GetMapping("/random")
    public WordPair getRandomWordPair (){
        return wordPairService.getRandomWordPair();
    }
}
