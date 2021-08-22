package com.worldofwordcraft.controller;

import com.worldofwordcraft.common.constants.Language;
import com.worldofwordcraft.common.exceptions.HintException;
import com.worldofwordcraft.common.exceptions.LanguageNotFoundException;
import com.worldofwordcraft.common.responses.HintResponse;
import com.worldofwordcraft.common.responses.WordPairListResponse;
import com.worldofwordcraft.common.responses.WordPairResponse;
import com.worldofwordcraft.domain.WordPair;
import com.worldofwordcraft.service.DataService;
import com.worldofwordcraft.service.WordPairService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class WordPairController {

    @Autowired
    private WordPairService wordPairService;

    @Autowired
    private DataService dataService;

    //    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("{language}/count")
    public ResponseEntity<Object> getCount(@PathVariable("language") Language language) throws LanguageNotFoundException {
        return new ResponseEntity<>(wordPairService.getCount(language), HttpStatus.OK);
    }

    //    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("{language}/random")
    public WordPairResponse getRandomWordPair(@PathVariable("language") Language language) throws LanguageNotFoundException {
        return new WordPairResponse(wordPairService.getRandomWordPair(language));
    }

    //    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("{language}/word")
    public ResponseEntity<Object> addNewWordPair(@PathVariable("language") Language language,
                                                 @RequestBody WordPair wordPair) throws LanguageNotFoundException {
        wordPairService.addNewWordPair(language, wordPair);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    //    @CrossOrigin(origins = "http://127.0.0.1:5500")
//    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "{language}/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public WordPairListResponse getWordPairList(@PathVariable("language") Language language) throws LanguageNotFoundException {
        dataService.loadData();
        return new WordPairListResponse(wordPairService.getWordPairList(language));
    }

    //    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("{language}/list")
    public ResponseEntity<Object> addNewWordPairList(@PathVariable("language") Language language,
                                                     @RequestBody List<WordPair> wordPairList) throws LanguageNotFoundException {
        wordPairService.addNewWordPairList(language, wordPairList);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    //    @CrossOrigin(origins = "http://localhost:3000")
    @PutMapping("{language}/hint/{amount}")
    public HintResponse getHint(@PathVariable("language") Language language,
                                @PathVariable("amount") int amount,
                                @RequestBody WordPair wordPair) throws HintException {

        return new HintResponse(wordPairService.getHint(language, wordPair, amount));

    }

    @GetMapping("{language}/test")
    public WordPairListResponse testEndpoint (@PathVariable("language") Language language) throws IOException {
        //comment
        return new WordPairListResponse(wordPairService.getTest(language));
    }
}
