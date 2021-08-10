package com.chasemanoukian.yourfavoritelakersbackend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataQueriesRunnable implements Runnable {
    private String _id;
    private String link;
    private PlayerService playerService;

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

    public PlayerService getPlayerService() {
        return playerService;
    }

    public void setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Override
    public void run() {
        try {
            Document doc = Jsoup.connect(link).get();
            DataQueries dq = new DataQueries();

            List<String> playerName = dq.getPlayerName(doc);
            List<String> jerseyAndPosition = dq.getJerseyAndPosition(doc);
            List<String> playerBio = dq.getPlayerBio(doc);
            Map<String, String> seasonStats = dq.getStats(doc, "PlayerStats", 1, 5);
            Map<String, String> gameStats = dq.getStats(doc, "gamelogWidget--basketball", 0, 7);

            String gameStatsKey = gameStats.get("prev");
            gameStats.remove("prev");

            Player player = playerService.findById(_id);
            if (player != null) {
                Map<String, Map<String, String>> prevTen = player.getPrevTen();
                if (!prevTen.containsKey(gameStatsKey)) {
                    prevTen.put(gameStatsKey, gameStats);
                    if (prevTen.size() > 10) {
                        prevTen.remove(0);
                    }
                    player.setSeasonStats(seasonStats);
                    playerService.update(player);
                }
            } else {
                Player newPlayer = new Player();
                newPlayer.set_id(_id);
                newPlayer.setFirstName(playerName.get(0));
                newPlayer.setLastName(playerName.get(1));
                // logic to account for no jersey num: sound?
                if (jerseyAndPosition.size() == 2) {
                    newPlayer.setPosition(jerseyAndPosition.get(1));
                    newPlayer.setJerseyNumber(jerseyAndPosition.get(0));
                } else {
                    newPlayer.setPosition(jerseyAndPosition.get(0));
                }
                newPlayer.setHeight(playerBio.get(0));
                newPlayer.setWeight(playerBio.get(1));
                newPlayer.setAge(playerBio.get(2));
                if (playerBio.size() == 4) {
                    newPlayer.setCollege(playerBio.get(3));
                }
                newPlayer.setSeasonStats(seasonStats);
                newPlayer.getPrevTen().put(gameStatsKey, gameStats);

                List<String> urls = new ArrayList<>();
                urls.add("/" + playerName.get(0) + playerName.get(1) + "1.jpg");
                urls.add("/" + playerName.get(0) + playerName.get(1) + "2.jpg");
                urls.add("/" + playerName.get(0) + playerName.get(1) + "3.jpg");
                urls.add("/" + playerName.get(0) + playerName.get(1) + "4.jpg");
                newPlayer.setUrls(urls);

                Player createdPlayer = playerService.create(newPlayer);
                if (createdPlayer.get_id() != null) {
                    new QueueRunnable(createdPlayer.get_id(), playerService).executePrevSeasonsRunnable();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
