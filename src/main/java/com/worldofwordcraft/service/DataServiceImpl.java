package com.worldofwordcraft.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.worldofwordcraft.WorldOfWordcraftApplication;
import com.worldofwordcraft.common.constants.Language;
import com.worldofwordcraft.common.exceptions.LanguageNotFoundException;
import com.worldofwordcraft.domain.WordPair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * {@inheritDoc}
 */
@Slf4j
@Service
public class DataServiceImpl implements DataService {

    public final Map<Language, List<WordPair>> wordListMap = new ConcurrentHashMap<>();

    private final Gson gson;

    /**
     * public constructor with injected Gson object
     *
     * @param gson Gson object for deserialization
     */
    @Inject
    public DataServiceImpl(Gson gson) {
        this.gson = gson;
    }

    /**
     * {@inheritDoc}
     */
    @PostConstruct
    public void loadData() {
        try {
            Type wordListType = new TypeToken<List<WordPair>>() {
            }.getType();

            log.info("loading data......");
            log.debug("try to load data from json files nl-en");
            String jsonString = getFileFromResource("nl-en.json");
            List<WordPair> dutchAndEnglishWordList = gson.fromJson(jsonString, wordListType);
            wordListMap.put(Language.EN, dutchAndEnglishWordList);

            log.debug("try to load data from json files nl-de");
            jsonString = getFileFromResource("word-lists/nl-de.json");
            List<WordPair> dutchAndGermanWordList = gson.fromJson(jsonString, wordListType);
            wordListMap.put(Language.DE, dutchAndGermanWordList);

            log.debug("try to load data from json files nl-fr");
            jsonString = getFileFromResource("word-lists/nl-fr.json");
            List<WordPair> dutchAndFrenchWordList = gson.fromJson(jsonString, wordListType);
            wordListMap.put(Language.FR, dutchAndFrenchWordList);
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WordPair> getWordPairList(Language language) throws LanguageNotFoundException {
        if (wordListMap.containsKey(language)) {
            return wordListMap.get(language);
        }

        throw new LanguageNotFoundException("Language not found: " + language.name());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WordPair> setWordPairList(Language language, List<WordPair> wordPairList) throws LanguageNotFoundException {
        if (wordListMap.containsKey(language)) {
            wordListMap.put(language, wordPairList);
            return wordListMap.get(language);
        }
        throw new LanguageNotFoundException("Language not found: " + language.name());
    }


    private static String getFileFromResource(String file) throws IOException {
        try (InputStream inputStream = WorldOfWordcraftApplication.class.getClassLoader().getResourceAsStream(file);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
    }
}
