package com.chasemanoukian.yourfavoritelakersbackend;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

            Elements els = doc.getElementsByClass("Table__TBODY")
                    .first()
                    .children();

            List<Element> statRows = new ArrayList<>();

            for (int i = 0; i < els.size(); i++) {
                statRows.add(els.get(i));
                System.out.println(els.get(i));
            }

//            Map<String, Map<String, String>> finalStats = new HashMap<>();

//            for (Element el: statRows) {
//
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
