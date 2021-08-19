package com.worldofwordcraft.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WordPair {

    @JsonProperty("question")
    private String question;

    @JsonProperty("answer")
    private String answer;


    public WordPair(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }
}
