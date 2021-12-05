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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


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

    @CrossOrigin(origins = "*")
    @GetMapping("/count")
    public ResponseEntity<Object> getCount(
            @RequestParam(name = "language") Language language)
    throws LanguageNotFoundException {
        return new ResponseEntity<>(wordPairService.getCount(language), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/random")
    public WordPairResponse getRandomWordPair(
            @RequestParam(name = "language") Language language)
            throws LanguageNotFoundException {
        return new WordPairResponse(wordPairService.getRandomWordPair(language));
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/word")
    public ResponseEntity<Object> addNewWordPair(
            @RequestParam(name = "language") Language language,
            @RequestBody WordPair wordPair
            ) throws LanguageNotFoundException {
        wordPairService.addNewWordPair(language, wordPair);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/word")
    public ResponseEntity<Object> removeWordPair(
            @RequestParam(name = "language") Language language,
            @RequestBody WordPair wordPair
    ) throws LanguageNotFoundException {
        wordPairService.removeWordPair(language, wordPair);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }


    @CrossOrigin(origins = "*")
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public WordPairListResponse getWordPairList(
            @RequestParam(name = "language") Language language)
            throws LanguageNotFoundException {
        return new WordPairListResponse(wordPairService.getWordPairList(language));
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/list")
    public ResponseEntity<Object> addNewWordPairList(
            @RequestParam(name = "language") Language language,
            @RequestBody List<WordPair> wordPairList) throws LanguageNotFoundException {
        wordPairService.addNewWordPairList(language, wordPairList);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/hint/{amount}")
    public HintResponse getHint(
            @RequestParam(name = "language") Language language,
            @PathVariable("amount") int amount,
            @RequestBody WordPair wordPair) throws HintException {

        return new HintResponse(wordPairService.getHint(language, wordPair, amount));
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/reload", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> reloadWordPairList(){
        wordPairService.reloadData();
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

}
