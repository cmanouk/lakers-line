package com.chasemanoukian.yourfavoritelakersbackend;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WebParserMethods {
    private static final String playerLink = "https://www.espn.com/nba/player/_/id/";

    public List<String> getIds(Elements els) {
        List<String> ids = new ArrayList<>();
        for (Element el:els) {
            String playerUrl = el.select("a").first().attr("href");
            String id = playerUrl.replace(playerLink, "");
            id = id.substring(0, id.indexOf("/"));
            ids.add(id);
        }

        return ids;
    }

    public Map<String, String> getStats(Document doc, String className, int getIdx, int byIdx) {
        Map<String, String> stats = new HashMap<>();

        Elements els = doc
                .getElementsByClass(className)
                .select("[data-idx=0]")
                .get(getIdx)
                .children();

        if (className != "PlayerStats") stats.put("prev", els.get(0).text() + " " + els.get(1).text());
        for (int i = 0; i < byIdx; i++) {
            els.remove(0);
        }
        els.remove(4);
        els.remove(4);

        stats.put("PTS", els.get(4).text());
        stats.put("REB", els.get(0).text());
        stats.put("AST", els.get(1).text());
        stats.put("STL", els.get(3).text());
        stats.put("BLK", els.get(2).text());

        return stats;
    }

    public List<String> getJerseyAndPosition(Document doc) {
        List<String> data = new ArrayList<>();

        Element el = doc.getElementsByClass("PlayerHeader__Team_Info").first();
        data.add(el.child(1).text());
        if (el.childrenSize() == 3) {
            data.add(el.child(2).text());
        }

        return data;
    }

    public List<String> getPlayerBio(Document doc) {
        List<String> data = new ArrayList<>();

        Element el = doc.getElementsByClass("PlayerHeader__Bio_List").first();
        for (int i = 0; i < 3; i++) {
            if (i == 0) {
                String[] heightWeight = el.child(i).child(1).text().split(",");
                data.add(heightWeight[0].trim());
                data.add(heightWeight[1].trim());
            } else if (el.child(i).child(0).text() == "College" || i == 1) {
                data.add(el.child(i).child(1).text());
            }
        }

        return data;
    }

    public List<String> getPlayerName(Document doc) {
        List<String> data = new ArrayList<>();

        Elements els = doc.getElementsByClass("PlayerHeader__Name")
                .first()
                .children();

        for (Element el: els) {
            data.add(el.text());
        }

        return data;
    }


}
