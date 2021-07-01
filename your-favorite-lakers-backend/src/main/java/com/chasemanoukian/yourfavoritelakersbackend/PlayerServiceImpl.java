package com.chasemanoukian.yourfavoritelakersbackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public Player create(Player player) {
        return playerRepository.insert(player);
    }

    @Override
    public List<Player> read() {
        return playerRepository.findAll();
    }

//    @Override
//    public Player findOne(String id) {
//        List<Player> players = playerRepository.findAll();
//        for (Player p:players) {
//            String pId = (String) p.getId();
//            if (pId == id) return p;
//        }
//        return null;
//
//        Player player = new MongoOperations().findById(new ObjectId(id));
//    }

    @Override
    public Player update(Player player) {
        return playerRepository.save(player);
    }

    @Override
    public Map<String, String> delete(String id) {
        playerRepository.deleteById(id);
        return null;
    }
}