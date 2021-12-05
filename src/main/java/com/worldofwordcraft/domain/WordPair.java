package com.worldofwordcraft.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * The WordPair class used to serialize/deserialize
 */
@Getter
@Setter
public class WordPair {

    @JsonProperty("question")
    private String question;

    @JsonProperty("answer")
    private String answer;

    /**
     * Public constructor, should be private but used in testcases so for now it's public
     *
     * @param question the question
     * @param answer the answer
     */
    public WordPair(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

}
