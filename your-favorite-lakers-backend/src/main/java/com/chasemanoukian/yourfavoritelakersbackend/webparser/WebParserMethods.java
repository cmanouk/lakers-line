package com.chasemanoukian.yourfavoritelakersbackend.webparser;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.*;


public class WebParserMethods {
    private static final String playerLink = "https://www.espn.com/nba/player/_/id/";
    private static final String previousTenLink = "https://www.espn.com/nba/player/gamelog/_/id/";
    private static final String previousSeasonsLink = "";

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

    public Map<String, Map<String, String>> getInitialPrevTen(Document doc) {
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

        return finalStats;
    }

    public Map<String, Map<String, String>> getInitialPreviousSeasonStats(Document doc) {
        Elements els = doc
                .getElementsByClass("Table__TBODY");
        List<Element> filteredEls = new ArrayList<>();

        for (int i = 0; i < 2; i++) {
            Element el = els.get(i);
            filteredEls.add(el);
        }

        Map<String, Map<String, String>> prevSeasonStats = new LinkedHashMap<>();
        for (int i = 0; i < filteredEls.get(0).childrenSize() - 1; i++) {
            String label = filteredEls.get(0).child(i).text();
            Element el = filteredEls.get(1).child(i);

            Map<String, String> stats = new HashMap<>();
            stats.put("GP", el.child(0).text());
            for (int t = 0; t < 11; t++) {
                el.child(0).remove();
            }

            stats.put("PTS", el.child(6).text());
            stats.put("REB", el.child(0).text());
            stats.put("AST", el.child(1).text());
            stats.put("STL", el.child(3).text());
            stats.put("BLK", el.child(2).text());

            prevSeasonStats.put(label, stats);
        }

        return prevSeasonStats;
    }
}
