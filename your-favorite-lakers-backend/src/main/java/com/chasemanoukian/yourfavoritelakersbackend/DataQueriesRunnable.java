package com.chasemanoukian.yourfavoritelakersbackend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

public class DataQueriesRunnable implements Runnable {
    @Autowired
    private PlayerRepository playerRepository;

    private static int count = 0;
    private String _id;
    private String link;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect(link).get();
            DataQueries dq = new DataQueries();

            Map<String, String> seasonStats = dq.getStats(doc, "PlayerStats", 1, 5);
            Map<String, String> gameStats = dq.getStats(doc, "gamelogWidget--basketball", 0, 7);

            // ---------- QUERY? NEED ACCESS TO DB...!!! ---------- //
            System.out.println("-----------------------------");
            System.out.println(_id);
            Optional<Player> player = playerRepository.getPlayerById(_id);
            System.out.println("Player: " + player.toString());
            System.out.println("-----------------------------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
