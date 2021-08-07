package com.worldofwordcraft.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordPair {

    private String question;
    private String answer;

    public WordPair(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
