package com.worldofwordcraft;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.worldofwordcraft.common.responses.ResponseListJson;
import com.worldofwordcraft.domain.WordPair;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
@SpringBootApplication
public class WorldOfWordcraftApplication implements CommandLineRunner {

    public static ResponseListJson dutchAndEnglishList;
    public static ResponseListJson dutchAndGermanList;

    @Autowired
    private Gson gson;

    public static void main(String[] args) {
        SpringApplication.run(WorldOfWordcraftApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }


    public void loadData (){
        try {
            log.debug("try to load data from json files nl-en");
            String jsonString = getStringFromResource("word-lists/nl-en.json");
            dutchAndEnglishList = new ResponseListJson(gson.fromJson(jsonString, new TypeToken<List<WordPair>>() {
            }.getType())) ;
            log.debug("try to load data from json files nl-de");
            jsonString = getStringFromResource("word-lists/nl-de.json");
            dutchAndGermanList = new ResponseListJson(gson.fromJson(jsonString, new TypeToken<List<WordPair>>() {
            }.getType())) ;
        } catch (JsonSyntaxException | IOException e) {
            e.printStackTrace();
        }
    }

    public File getFileFromResource (String file){
        return new File(getClass().getClassLoader().getResource(file).getFile());
    }

    public String getStringFromResource (String resource) throws IOException {
        File file = getFileFromResource(resource);
        return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
    }
}
