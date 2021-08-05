package com.worldofwordcraft.repos;

import com.worldofwordcraft.domain.WordPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WordPairRepository extends JpaRepository<WordPair, Long> {


//    @Query(value="SELECT wp "
//            + "FROM WordPair wp "
//            + "WHERE wp.id = 1", nativeQuery = true)
//    WordPair getRandomWordPair();


//    Integer getWordPairsSize();
}
