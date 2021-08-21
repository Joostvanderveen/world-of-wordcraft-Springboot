package com.worldofwordcraft.service;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.worldofwordcraft.WorldOfWordcraftApplication;
import com.worldofwordcraft.common.constants.Language;
import com.worldofwordcraft.common.exceptions.LanguageNotFoundException;
import com.worldofwordcraft.domain.WordPair;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class DataServiceImpl implements DataService {

    public final Map<Language, List<WordPair>> wordListMap = new ConcurrentHashMap<>();

    private final Gson gson;

    @Inject
    public DataServiceImpl(Gson gson) {
        this.gson = gson;
    }

    @PostConstruct
    public void loadData() {
        try {
            Type wordListType = new TypeToken<List<WordPair>>() {
            }.getType();

            log.info("loading data......");
            log.debug("try to load data from json files nl-en");
            String jsonString = getStringFromResource("word-lists/nl-en.json");
            List<WordPair> dutchAndEnglishWordList = gson.fromJson(jsonString, wordListType);
            wordListMap.put(Language.EN, dutchAndEnglishWordList);

            log.debug("try to load data from json files nl-de");
            jsonString = getStringFromResource("word-lists/nl-de.json");
            List<WordPair> germanAndEnglishWordList = gson.fromJson(jsonString, wordListType);
            wordListMap.put(Language.DE, germanAndEnglishWordList);
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<WordPair> getWordPairList(Language language) throws LanguageNotFoundException {
        if (wordListMap.containsKey(language)) {
            return wordListMap.get(language);
        }

        throw new LanguageNotFoundException("Language not found:" + language.name());
    }

    @Override
    public List<WordPair> getTest (Language language) throws IOException {
        Type wordListType = new TypeToken<List<WordPair>>() {
        }.getType();
        log.info("try to load FR wordPair list from json file...");
        String jsonString = getStringFromResource("word-lists/nl-fr.json");
        List<WordPair> dutchAndFrenchWordList = gson.fromJson(jsonString, wordListType);
        wordListMap.put(Language.FR, dutchAndFrenchWordList);
        return wordListMap.get(language);
    }

    private static File getFileFromResource(String file) {
        return new File(WorldOfWordcraftApplication.class.getClassLoader().getResource(file).getFile());
    }

    private static String getStringFromResource(String resource) throws IOException {
        File file = getFileFromResource(resource);
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}
