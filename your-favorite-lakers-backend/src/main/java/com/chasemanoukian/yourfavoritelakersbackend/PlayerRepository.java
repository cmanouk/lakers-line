package com.chasemanoukian.yourfavoritelakersbackend;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends MongoRepository<Player, String> {
    @Query("{ '_id' : ?0 }")
    Player getPlayerById(String id);
}
