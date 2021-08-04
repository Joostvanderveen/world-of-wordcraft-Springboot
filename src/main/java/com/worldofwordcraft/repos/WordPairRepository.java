package com.worldofwordcraft.repos;

import com.worldofwordcraft.domain.WordPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordPairRepository extends JpaRepository <WordPair, Long> {
}
