package com.chasemanoukian.yourfavoritelakersbackend.models;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document
public class Player {
    private String _id;

    private String firstName;
    private String lastName;
    private String position;
    private String jerseyNumber;
    private String age;
    private String height;
    private String weight;
    private String college;

    private Map<String, Map<String, String>> prevTen = new HashMap<>();
    private Map<String, String> seasonStats = new HashMap<>();
    private Map<String, Map<String, String>> prevSeasons = new HashMap<>();

    private List<String> urls = new ArrayList<>();

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(String jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public Map<String, String> getSeasonStats() {
        return seasonStats;
    }

    public void setSeasonStats(Map<String, String> seasonStats) {
        this.seasonStats = seasonStats;
    }

    public Map<String, Map<String, String>> getPrevTen() {
        return prevTen;
    }

    public void setPrevTen(Map<String, Map<String, String>> prevTen) {
        this.prevTen = prevTen;
    }

    public Map<String, Map<String, String>> getPrevSeasons() {
        return prevSeasons;
    }

    public void setPrevSeasons(Map<String, Map<String, String>> prevSeasons) {
        this.prevSeasons = prevSeasons;
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public void updatePrevTen(String gameStatsKey, Map<String, String> gameStats) {
        Map<String, Map<String, String>> prevTen = this.getPrevTen();
        if (!prevTen.containsKey(gameStatsKey)) {
            prevTen.put(gameStatsKey, gameStats);
            if (prevTen.size() > 10) {
                prevTen.remove(0);
            }
            this.setSeasonStats(seasonStats);
        }
    }

    public void setAllProperties(String id, List<String> playerName, List<String> jerseyAndPosition,
                                 List<String> playerBio, Map<String, String> seasonStats) {
        this.set_id(id);
        this.setFirstName(playerName.get(0));
        this.setLastName(playerName.get(1));

        if (jerseyAndPosition.size() == 2) {
            this.setPosition(jerseyAndPosition.get(1));
            this.setJerseyNumber(jerseyAndPosition.get(0));
        } else {
            this.setPosition(jerseyAndPosition.get(0));
        }

        this.setHeight(playerBio.get(0));
        this.setWeight(playerBio.get(1));
        this.setAge(playerBio.get(2));
        if (playerBio.size() == 4) {
            this.setCollege(playerBio.get(3));
        }

        this.setSeasonStats(seasonStats);

        List<String> urls = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            urls.add("/" + playerName.get(0) + playerName.get(1) + i + ".jpg");
        }
        this.setUrls(urls);
    }

    public void setInitialPrevTenStats(String link) {

    }

    public void setInitialPrevSeasonStats() {

    }

    @Override
    public String toString() {
        return String.format(
                this.getFirstName() + " "  + this.getLastName() + ": " + this.get_id() + "."
        );
    }
}
