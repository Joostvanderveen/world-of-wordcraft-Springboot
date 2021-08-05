package com.worldofwordcraft.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="word_pairs")
public class WordPair {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="question")
    private String question;

    @Column(name="answer")
    private String answer;

    public WordPair() {
        //Empty Constructor for Hibernate
    }
}
