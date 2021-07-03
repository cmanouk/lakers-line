package com.chasemanoukian.yourfavoritelakersbackend;

public class DataQueriesRunnable implements Runnable {
    private String _id;
    private String link;

    public DataQueriesRunnable(String _id, String link) {
        this._id = _id;
        this.link = link.concat(_id);
    }

    @Override
    public void run() {
        System.out.println(_id + ": " + link);
    }
}
