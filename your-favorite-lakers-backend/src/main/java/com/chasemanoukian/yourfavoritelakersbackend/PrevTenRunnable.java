package com.chasemanoukian.yourfavoritelakersbackend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class PrevTenRunnable implements Runnable {
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

            Elements els = doc.getElementsByClass("Table__TBODY");
            List<Element> filteredEls = new ArrayList<>();
            for (Element el : els) {
                for (int i = 0; i < el.childrenSize(); i++) {
                    if (filteredEls.size() >= 10) break;
                    if (el.child(i).text().contains("vs") || el.child(i).text().contains("@")) {
                        filteredEls.add(el.child(i));
                    }
                }
            }

            Map<String, Map<String, String>> stats = new LinkedHashMap<>();
            List<String> statsKeys = new ArrayList<>();
            for (Element el : filteredEls) {
                String key = el.child(0).text() + " " + el.child(1).text();
                Map<String, String> gameStats = new HashMap<>();

                gameStats.put("REB", el.child(10).text());
                gameStats.put("AST", el.child(11).text());
                gameStats.put("BLK", el.child(12).text());
                gameStats.put("STL", el.child(13).text());
                gameStats.put("PTS", el.child(16).text());

                stats.put(key, gameStats);
                statsKeys.add(key);
            }

            Map<String, Map<String, String>> finalStats = new LinkedHashMap<>();
            for (int i = statsKeys.size() - 1; i >= 0; i--) {
                String key = statsKeys.get(i);
                Map<String, String> gameStats = stats.get(key);
                finalStats.put(key, gameStats);
            }

            Player player = playerService.findById(_id);
            player.setPrevTen(finalStats);
            playerService.update(player);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
