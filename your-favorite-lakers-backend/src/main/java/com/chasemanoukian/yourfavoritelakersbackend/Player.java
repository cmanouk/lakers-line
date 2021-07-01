package com.chasemanoukian.yourfavoritelakersbackend;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Document
public class Player {
    @Id
    private String id;

    private String firstName;
    private String lastName;

    private Map<String, String> seasonStats = new HashMap<>();
    private Map<String, Map<String, String>> prevTen = new HashMap<>();
    private Map<String, Map<String, String>> prevSeasons = new HashMap<>();

    private List<String> urls = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
