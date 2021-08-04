package com.worldofwordcraft;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.worldofwordcraft.domain.WordPair;
import com.worldofwordcraft.repos.WordPairRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories
public class WorldOfWordcraftApplication implements CommandLineRunner {

    @Autowired
    private Gson gson;

    @Autowired
    private WordPairRepository wordPairRepository;

    public static void main(String[] args) {
        SpringApplication.run(WorldOfWordcraftApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            String jsonString = getStringFromResource("word-lists/nl-en.json");
            List<WordPair> wordPairList = gson.fromJson(jsonString, new TypeToken<List<WordPair>>() {
            }.getType());
            wordPairRepository.saveAll(wordPairList);
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
