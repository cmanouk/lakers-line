package com.chasemanoukian.yourfavoritelakersbackend;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface PlayerRepository extends MongoRepository<Player, String> {
    @Query("{ '_id' : ?0 }")
    Optional<Player> getPlayerById(String id);
}
