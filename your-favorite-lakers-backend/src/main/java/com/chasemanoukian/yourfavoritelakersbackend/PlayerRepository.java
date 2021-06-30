package com.chasemanoukian.yourfavoritelakersbackend;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {
}
