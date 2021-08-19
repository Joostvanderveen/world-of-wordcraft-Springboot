package com.worldofwordcraft.service;

import com.worldofwordcraft.common.constants.Language;
import com.worldofwordcraft.common.exceptions.LanguageNotFoundException;
import com.worldofwordcraft.domain.WordPair;

import java.util.List;

public interface DataService {

    void loadData();

    List<WordPair> getWordPairList(Language language) throws LanguageNotFoundException;
}
