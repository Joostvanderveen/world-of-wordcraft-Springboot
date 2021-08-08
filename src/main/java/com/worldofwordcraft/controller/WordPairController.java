package com.worldofwordcraft.controller;

import com.worldofwordcraft.common.constants.Language;
import com.worldofwordcraft.common.exceptions.NotFoundException;
import com.worldofwordcraft.common.responses.WordPairResponse;
import com.worldofwordcraft.common.responses.WordPairListResponse;
import com.worldofwordcraft.domain.WordPair;
import com.worldofwordcraft.service.WordPairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/api")
public class WordPairController {

    @Autowired
    private WordPairService wordPairService;

    @GetMapping("{language}/count")
    public ResponseEntity <Object> getCount(@PathVariable("language")
                                                        @NotBlank @Size(min=2, max=2,message="too long") Language language) {
        return new ResponseEntity<>(wordPairService.getCount(language), HttpStatus.OK);
    }

    @GetMapping("{language}/random")
    public WordPairResponse getRandomWordPair(@PathVariable("language") Language language) {
        return new WordPairResponse(wordPairService.getRandomWordPair(language));
    }

    @PutMapping("{language}/word")
    public ResponseEntity<Object> addNewWordPair(@PathVariable("language") Language language,
                                                 @RequestBody WordPair wordPair) {
        wordPairService.addNewWordPair(language, wordPair);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("{language}/list")
    public WordPairListResponse getWordPairList(@PathVariable("language") Language language) {
        return new WordPairListResponse(wordPairService.getWordPairList(language));
    }

    @PutMapping("{language}/list")
    public ResponseEntity<Object> addNewWordPairList (@PathVariable("language") Language language,
                                                      @RequestBody List<WordPair> wordPairList){
        wordPairService.addNewWordPairList(language, wordPairList);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
